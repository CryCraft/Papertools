package papertools.main;

import io.netty.channel.*;
import net.minecraft.server.v1_15_R1.PacketPlayInUseEntity;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import papertools.api.npc.NPCPlayer;

import java.lang.reflect.Field;

public class packetReader {

    public static void inject(Player player, Papertools plugin) {
        ChannelDuplexHandler channelDuplexHandler = new ChannelDuplexHandler() {
            @Override
            public void channelRead(ChannelHandlerContext context, Object packet) throws Exception {
                if (packet instanceof PacketPlayInUseEntity) {
                    int id = (int) getValue(packet, "a");
                    String action = getValue(packet, "action").toString();
                    for (NPCPlayer npc: plugin.npcManager.getAll().values()) {
                        if (npc.getEntityId() == id) {
                            if(action.equalsIgnoreCase("INTERACT")) {
                                if (getValue(packet, "d").toString().equalsIgnoreCase("MAIN_HAND")) {
                                    Bukkit.getServer().getScheduler().runTask(plugin, () -> npc.run(action, player, plugin));
                                }
                            } else if (action.equalsIgnoreCase("ATTACK")) {
                                Bukkit.getServer().getScheduler().runTask(plugin, () -> npc.run(action, player, plugin));
                            }
                        }
                    }
                }
                super.channelRead(context, packet);
            }
        };

        ChannelPipeline pipeline = ((CraftPlayer) player).getHandle().playerConnection.networkManager.channel.pipeline();
        pipeline.addBefore("packet_handler", player.getName(), channelDuplexHandler);
    }

    public static void eject(Player player) {
        Channel channel = ((CraftPlayer) player).getHandle().playerConnection.networkManager.channel;
        channel.eventLoop().submit(() -> {
           channel.pipeline().remove(player.getName());
           return null;
        });
    }

    private static Object getValue(Object packet, String name) {
        try {
            Field field = packet.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return field.get(packet);
        } catch(Exception ignored) {}
        return null;
    }
}
