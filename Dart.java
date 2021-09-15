package dart;

import org.lwjgl.opengl.Display;

import dart.event.EventManager;
import dart.event.EventTarget;
import dart.event.impl.ClientTick;
import dart.hud.HUDConfigScreen;
import dart.hud.mod.HudManager;
import dart.mod.ModManager;
import dart.ui.clickgui.ClickGUI;
import dart.util.SessionChanger;
import net.minecraft.client.Minecraft;

public class Dart {
	
	public String NAME = "Dart Client", VERSION = "1.8.8", AUTHOR = "Dartts", NAMEVER = NAME + " " + VERSION;
	public static Dart INSTANCE = new Dart();
	public Minecraft mc = Minecraft.getMinecraft();
	
	public EventManager eventManager;
	public ModManager modManager;
	public HudManager hudManager;
	
	
	public void startup() {
		eventManager = new EventManager();
		modManager = new ModManager();
		hudManager = new HudManager();
		
		SessionChanger.getInstance().setUserOffline("Dartts");
		
		Display.setTitle(NAMEVER);
		
		System.out.println("Starting " + NAMEVER + " by " + AUTHOR);
		
		eventManager.register(this);	
	}
	
	public void shutdown() {
		System.out.println("Shutting down " + NAMEVER + " by " + AUTHOR);
		
		eventManager.unregister(this);
	}
	
	@EventTarget
	public void onTick(ClientTick event) {
		if(mc.gameSettings.TEST_MOD.isPressed()) {
			modManager.testMod.toggle();
		}
		if(mc.gameSettings.HUD_CONFIG.isPressed()) {
			mc.displayGuiScreen(new HUDConfigScreen());
		}
		if(mc.gameSettings.CLICK_GUI.isPressed()) {
			modManager.toggleSprint.toggle();
			mc.displayGuiScreen(new ClickGUI());
		} 
		
		if(mc.gameSettings.keyBindSprint.isPressed()) {
			modManager.toggleSprint.toggle();
		} 
	}
	
}
