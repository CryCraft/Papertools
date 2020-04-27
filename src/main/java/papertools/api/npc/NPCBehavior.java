package papertools.api.npc;

public class NPCBehavior {

	public final NPCBehaviorType type;
	public final String info;

	public NPCBehavior(NPCBehaviorType type, String info) {
		this.type = type;
		this.info = info;
	}
}