package com.Hileb.ofstream_fastirr.mixin;

import com.Hileb.ofstream.ofstream.lang.LangHelper;
import com.Hileb.ofstream_fastirr.NameSwitcher;
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
        name= NameSwitcher.getMainName(NameSwitcher.getEntityTranslateKey(getMob()));
    }


    public void setEnglishname(String nameIn) {
        Englishname=NameSwitcher.getOffName(NameSwitcher.getEntityTranslateKey(getMob()));
    }
}
