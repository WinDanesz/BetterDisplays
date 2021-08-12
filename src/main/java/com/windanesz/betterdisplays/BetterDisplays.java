package com.windanesz.betterdisplays;

import com.windanesz.betterdisplays.registry.BDBlocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Random;

@Mod(modid = BetterDisplays.MODID, name = BetterDisplays.NAME, version = "@VERSION@", acceptedMinecraftVersions = "[@MCVERSION@]", guiFactory = "com.windanesz.betterdisplays.BetterDisplaysGuiFactory"
)
public class BetterDisplays {

	public static final String MODID = "betterdisplays";
	public static final String NAME = "Better Displays";

	public static final Random rand = new Random();

	public static Logger logger;

	// The instance of wizardry that Forge uses.
	@Mod.Instance(BetterDisplays.MODID)
	public static BetterDisplays instance;

	// Location of the proxy code, used by Forge.
	@SidedProxy(clientSide = "com.windanesz.betterdisplays.client.ClientProxy", serverSide = "com.windanesz.betterdisplays.CommonProxy")
	public static CommonProxy proxy;

	public static final Settings settings = new Settings();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();

		configDirectory = new File(event.getModConfigurationDirectory(), MODID);
		settings.initConfig(event);

		proxy.registerRenderers();

		BDBlocks.registerTileEntities();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

		MinecraftForge.EVENT_BUS.register(instance); // Since there's already an instance we might as well use it
		//		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandlerBD());

		proxy.init();

	}

	public static File configDirectory;

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) { }

	@EventHandler
	public void serverStarting(FMLServerStartingEvent event) { }

	@SubscribeEvent
	public void onConfigChanged(net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equals(MODID)) {
			settings.saveConfigChanges();
		}
	}
}
