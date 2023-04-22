package com.Hileb.ofstream_fastirr.mixin;

import com.Hileb.ofstream.ofstream.lang.LangHelper;
import itemrender.client.export.ItemData;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemData.class)
public abstract class MixinItemData {

    @Shadow
    private String name;

    @Shadow
    private String englishName;

    @Shadow
    private String CreativeTabName;


    @Shadow
    public abstract ItemStack getItemStack();


    public void setEnglishName(String englishNameIn) {
        englishName=LangHelper.getI18n(LangHelper.EN_US).format(getItemStack().getUnlocalizedName()+".name");
    }


    public void setCreativeName(String nameIn) {
        if (getItemStack().getItem().getCreativeTab()!=null){
            String key=getItemStack().getItem().getCreativeTab().getTranslatedTabLabel();
            if (LangHelper.getI18n(LangHelper.ZH_CN).hasKey(key)){
                CreativeTabName=LangHelper.getI18n(LangHelper.ZH_CN).format(key);
            }
            else CreativeTabName=LangHelper.getI18n(LangHelper.EN_US).format(key);
        }
        else CreativeTabName="";
    }

    public void setName(String nameIn) {
        name=LangHelper.getI18n(LangHelper.ZH_CN).format(getItemStack().getUnlocalizedName()+".name");
    }
}
