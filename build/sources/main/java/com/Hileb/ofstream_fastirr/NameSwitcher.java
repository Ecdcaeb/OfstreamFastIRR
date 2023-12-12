package com.Hileb.ofstream_fastirr;

import com.Hileb.ofstream.ofstream.lang.I18n;
import com.Hileb.ofstream.ofstream.lang.LangHelper;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.EntityEntry;

/**
 * @Project OfstreamFastIRR
 * @Author Hileb
 * @Date 2023/12/12 12:24
 **/
public class NameSwitcher {
    public static String getMainName(String key){
        I18n zh_cn=LangHelper.getI18n(LangHelper.ZH_CN);
        if (zh_cn.hasKey(key)){
            return zh_cn.format(key).trim();
        }else {
            I18n en_us=LangHelper.getI18n(LangHelper.EN_US);
            if (en_us.hasKey(key)){
                return en_us.format(key).trim();
            }else return key;
        }
    }
    public static String getOffName(String key){
        I18n zh_cn=LangHelper.getI18n(LangHelper.ZH_CN);
        I18n en_us=LangHelper.getI18n(LangHelper.EN_US);
        if (zh_cn.hasKey(key)){
            if (en_us.hasKey(key))return en_us.format(key).trim();
            else return "";
        }else {
            return "";
        }
    }
    public static String getItemTranslateKey(ItemStack stack){
        return stack.getItem().getUnlocalizedNameInefficiently(stack) + ".name";
    }
    public static String getEntityTranslateKey(EntityEntry entity){
        String s=entity.getName();
        return "entity." + s + ".name";
    }
}
