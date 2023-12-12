package com.Hileb.ofstream_fastirr.mixin;

import com.Hileb.ofstream.ofstream.lang.LangHelper;
import com.Hileb.ofstream_fastirr.NameSwitcher;
import itemrender.client.export.ItemData;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemData.class)
public abstract class MixinItemData {


    private String name;


    private String englishName;


    private String CreativeTabName;



    public abstract ItemStack getItemStack();


    public void setEnglishName(String englishNameIn) {
        englishName=NameSwitcher.getOffName(NameSwitcher.getItemTranslateKey(getItemStack()));
    }


    public void setCreativeName(String nameIn) {
        if (getItemStack().getItem().getCreativeTab()!=null){
            String key=getItemStack().getItem().getCreativeTab().getTranslatedTabLabel();
            CreativeTabName=NameSwitcher.getMainName(key);
        }
        else CreativeTabName="";
    }

    public void setName(String nameIn) {
        name=NameSwitcher.getMainName(NameSwitcher.getItemTranslateKey(getItemStack()));
    }
}
