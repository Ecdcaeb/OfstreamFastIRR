package com.Hileb.ofstream_fastirr.mixin;

import com.Hileb.ofstream.ofstream.lang.LangHelper;
import itemrender.client.export.MobData;
import net.minecraftforge.fml.common.registry.EntityEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MobData.class)
public abstract class MixinMobData {

    @Shadow
    private String name;
    @Shadow
    private String Englishname;

    @Shadow
    public abstract EntityEntry getMob();


    public void setName(String nameIn) {
        name=LangHelper.getI18n(LangHelper.ZH_CN).format("entity." + this.getMob().getName() + ".name");
    }


    public void setEnglishname(String nameIn) {
        Englishname=LangHelper.getI18n(LangHelper.EN_US).format("entity." + this.getMob().getName() + ".name");
    }
}
