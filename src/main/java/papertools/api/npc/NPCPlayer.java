package papertools.api.npc;

import java.util.UUID;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import org.bukkit.craftbukkit.v1_15_R1.CraftServer;
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;

import net.minecraft.server.v1_15_R1.DataWatcher;
import net.minecraft.server.v1_15_R1.DataWatcherObject;
import net.minecraft.server.v1_15_R1.DataWatcherRegistry;
import net.minecraft.server.v1_15_R1.EntityPlayer;
import net.minecraft.server.v1_15_R1.MinecraftServer;
import net.minecraft.server.v1_15_R1.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_15_R1.PacketPlayOutEntityHeadRotation;
import net.minecraft.server.v1_15_R1.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_15_R1.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_15_R1.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_15_R1.PlayerConnection;
import net.minecraft.server.v1_15_R1.PlayerInteractManager;
import net.minecraft.server.v1_15_R1.WorldServer;
import net.minecraft.server.v1_15_R1.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;
import papertools.main.Papertools;

public class NPCPlayer {

	public final Location loc;
	public final String name;
	public final UUID uuid;

	private EntityPlayer entityPlayer;
	public final String texture;
	public final String signature;
	private final NPCBehavior behavior;
	private final byte layers;

	public NPCPlayer(String name, Location loc, UUID uuid, NPCBehavior behavior, String texture, String signature, byte layers) {
		this.name = name;
		this.loc = loc;
		this.uuid = uuid;
		this.behavior = behavior;
		this.texture = texture;
		this.signature = signature;
		this.layers = layers;
	}

	public void create() {
		MinecraftServer nmsServer = ((CraftServer) Bukkit.getServer()).getServer();
		WorldServer nmsWorld = ((CraftWorld) this.loc.getWorld()).getHandle();

		GameProfile gameProfile = new GameProfile(this.uuid, this.name);
		gameProfile.getProperties().put("textures", new Property("textures", this.texture, this.signature));

		this.entityPlayer = new EntityPlayer(nmsServer, nmsWorld, gameProfile, new PlayerInteractManager(nmsWorld));
		this.entityPlayer.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
	}
	
	public void show(Player player) {

		PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;

		connection.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.ADD_PLAYER, this.entityPlayer));
		connection.sendPacket(new PacketPlayOutNamedEntitySpawn(this.entityPlayer));
		connection.sendPacket(new PacketPlayOutEntityHeadRotation(this.entityPlayer, (byte) ((loc.getYaw() * 256.0F) / 360.0F)));

		DataWatcher watcher = this.entityPlayer.getDataWatcher();
		watcher.set(new DataWatcherObject<>(16, DataWatcherRegistry.a), this.layers);
		connection.sendPacket(new PacketPlayOutEntityMetadata(this.entityPlayer.getId(), watcher, true));
	}

	public void remove(Player player) {
		PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
		connection.sendPacket(new PacketPlayOutEntityDestroy(this.entityPlayer.getId()));
	}

	public void run(String label, Player player, Papertools papertools) {
		if (this.behavior.type == NPCBehaviorType.GUI) {
			try {
				player.openInventory(papertools.guiManager.getGui(this.behavior.info).getInventory());
			} catch (Exception ignored) {
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "GUI: '" + this.behavior.info + "' not found!");
			}
		}

//		 if (label.equalsIgnoreCase("INTERACT")) {
//
//		 } else if (label.equalsIgnoreCase("ATTACK")) {
//
//		 }
	}

	public void removeFromTab(Player player) {
		PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
		connection.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.REMOVE_PLAYER, this.entityPlayer));
	}

	public int getEntityId() {
		return this.entityPlayer.getId();
	}
	public String getName() {
		return this.name;
	}
}