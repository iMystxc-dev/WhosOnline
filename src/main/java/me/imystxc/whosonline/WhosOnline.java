package me.imystxc.whosonline;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = WhosOnline.MOD_ID,
        name = WhosOnline.MOD_NAME,
        version = WhosOnline.VERSION,
        acceptableRemoteVersions = "*",
        acceptedMinecraftVersions = "[1.12.2]"
)
public class WhosOnline {

    public static final String MOD_ID = "whosonline";
    public static final String MOD_NAME = "WhosOnline";
    public static final String VERSION = "2.0.0";
    private static final Logger logger = LogManager.getLogger();

    @Mod.Instance(MOD_ID)
    public static WhosOnline INSTANCE;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
    }


    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {

    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        event.registerServerCommand(new Command());
        event.registerServerCommand(new StaffCommand());
        logger.info("WhosOnline Has Successfully Loaded");
    }
}
