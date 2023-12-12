package com.Hileb.ofstream_fastirr.mixin;

import com.Hileb.ofstream_fastirr.NameSwitcher;
import itemrender.client.export.MobData;
import net.minecraftforge.fml.common.registry.EntityEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MobData.class)
public abstract class MixinMobData {


    private String name;

    private String Englishname;


    public abstract EntityEntry getMob();


    public void setName(String nameIn) {
        name= NameSwitcher.getMainName(NameSwitcher.getEntityTranslateKey(getMob()));
    }


    public void setEnglishname(String nameIn) {
        Englishname=NameSwitcher.getOffName(NameSwitcher.getEntityTranslateKey(getMob()));
    }
}
