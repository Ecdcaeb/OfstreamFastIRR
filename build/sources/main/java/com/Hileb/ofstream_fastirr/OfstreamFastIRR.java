package com.Hileb.ofstream_fastirr;

import com.Hileb.ofstream_fastirr.proxy.ProxyBase;
import com.Hileb.ofstream_fastirr.proxy.ClientProxy;
import com.Hileb.ofstream_fastirr.proxy.ServerProxy;
import itemrender.client.rendering.FBOHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;


@Mod(modid = OfstreamFastIRR.MODID, name = OfstreamFastIRR.NAME, version = OfstreamFastIRR.VERSION,dependencies = "required-after:ofstream_t@[1.0.0.2,);required-after:itemrender")
public class OfstreamFastIRR {
    public static final String MODID = "ofstream_fastirr";
    public static final String NAME = "Ofstream FastIRR";
    public static final String VERSION = "1.2";

    public static Logger logger;

    public static final boolean SHOW_WARN = true;

    @Mod.Instance
    public static OfstreamFastIRR instance;

    @SidedProxy(clientSide = ClientProxy.PROXY, serverSide = ServerProxy.PROXY)
    public static ProxyBase proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();


        FBOHelper fbo=new FBOHelper(0);
        fbo.begin();

    }



    public static void LogWarning(String str, Object... args) {
        if (SHOW_WARN) {
            logger.warn(String.format(str, args));
        }
    }

    public static void LogWarning(String str) {
        if (SHOW_WARN) {
            logger.warn(str);
        }
    }

    public static void Log(String str) {
        logger.info(str);
    }

    public static void Log(String str, Object... args) {
        logger.info(String.format(str, args));
    }
}