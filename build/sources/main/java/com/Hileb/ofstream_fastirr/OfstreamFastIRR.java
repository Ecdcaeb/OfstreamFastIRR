package com.Hileb.ofstream_fastirr;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(modid = OfstreamFastIRR.MODID, name = OfstreamFastIRR.NAME, version = OfstreamFastIRR.VERSION,dependencies = "required-after:ofstream_t@[1.0.0.2,);required-after:itemrender")
public class OfstreamFastIRR {
    public static final String MODID = "ofstream_fastirr";
    public static final String NAME = "Ofstream FastIRR";
    public static final String VERSION = "1.2";

    @Mod.Instance
    public static OfstreamFastIRR instance;
    public static final Logger log= LogManager.getLogger(NAME);


}