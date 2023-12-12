/*
 * Copyright (c) 2015 Jerrell Fang
 *
 * This project is Open Source and distributed under The MIT License (MIT)
 * (http://opensource.org/licenses/MIT)
 *
 * You should have received a copy of the The MIT License along with
 * this project.   If not, see <http://opensource.org/licenses/MIT>.
 */

package com.Hileb.ofstream_fastirr.mixin;

import com.Hileb.ofstream.ofstream.lang.LangHelper;
import com.Hileb.ofstream_fastirr.OfstreamFastIRR;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import itemrender.ItemRenderMod;
import itemrender.client.export.ExportUtils;
import itemrender.client.export.ItemData;
import itemrender.client.export.ItemList;
import itemrender.client.export.MobData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by Meow J on 8/17/2015.
 *
 * @author Meow J
 */
@Mixin(ExportUtils.class)
public abstract class MixinExportUtils{


    private List<ItemData> itemDataList;


    private List<MobData> mobDataList;



    abstract String getItemOwner(ItemStack itemStack) ;



    abstract String getEntityOwner(EntityEntry Entitymob) ;

    public MixinExportUtils() {
    }


    public void exportMods() throws IOException{
        LangHelper.getI18nForcedReload(LangHelper.EN_US);
        LangHelper.getI18nForcedReload(LangHelper.ZH_CN);
        OfstreamFastIRR.log.info("mixin export--");
        try{
            Minecraft minecraft = FMLClientHandler.instance().getClient();
            itemDataList.clear();
            mobDataList.clear();
            List<String> modList = new ArrayList<String>();

            Gson gson = new GsonBuilder().disableHtmlEscaping().create();
            ItemData itemData;
            MobData mobData;
            String identifier;

            Class clazz= ItemList.class;
            Field field_1= clazz.getDeclaredField("items");
            field_1.setAccessible(true);
            LinkedList<ItemStack> stacksItems=(LinkedList<ItemStack>) field_1.get(null);

            for (ItemStack itemStack : stacksItems) {
                if (itemStack == null) continue;
                if (getItemOwner(itemStack).equals("minecraft") && !ItemRenderMod.exportVanillaItems) continue;

                identifier = itemStack.getItem().getUnlocalizedName() + "@" + itemStack.getMetadata();
                if (ItemRenderMod.blacklist.contains(identifier)) continue;

                itemData = new ItemData(itemStack);
                itemDataList.add(itemData);
                if (!modList.contains(getItemOwner(itemStack))) modList.add(getItemOwner(itemStack));
            }
            for (EntityEntry Entity : ForgeRegistries.ENTITIES) {
                if (Entity == null) continue;
//            if (!(Entity.newInstance(minecraft.world) instanceof EntityLivingBase)||!(Entity.newInstance(minecraft.world) instanceof EntityMob)) continue;
                if (getEntityOwner(Entity).equals("minecraft") && !ItemRenderMod.exportVanillaItems) continue;

                mobData = new MobData(Entity);
                mobDataList.add(mobData);
                if (!modList.contains(getEntityOwner(Entity))) modList.add(getEntityOwner(Entity));
            }
            // Since refreshResources takes a long time, only refresh once for all the items
//        minecraft.getLanguageManager().setCurrentLanguage(new Language("zh_CN", "涓浗", "绠�浣撲腑鏂�", false));
//        minecraft.gameSettings.language = "zh_CN";
//        minecraft.refreshResources();
//        minecraft.gameSettings.saveOptions();

            for (ItemData data : itemDataList) {
                if (ItemRenderMod.debugMode)
                    ItemRenderMod.instance.log.info(I18n.format("itemrender.msg.addCN", data.getItemStack().getItem().getUnlocalizedName() + "@" + data.getItemStack().getMetadata()));
                data.setName("");
                data.setCreativeName("");
            }
            for (MobData data : mobDataList) {
                if (ItemRenderMod.debugMode)
                    ItemRenderMod.instance.log.info(I18n.format("itemrender.msg.addCN", data.getMob().getRegistryName()));
                data.setName("");
            }

//        minecraft.getLanguageManager().setCurrentLanguage(new Language("en_US", "US", "English", false));
//        minecraft.gameSettings.language = "en_US";
//        minecraft.refreshResources();
//        minecraft.fontRendererObj.setUnicodeFlag(false);
//        minecraft.gameSettings.saveOptions();

            for (ItemData data : itemDataList) {
                if (ItemRenderMod.debugMode)
                    ItemRenderMod.instance.log.info(I18n.format("itemrender.msg.addEN", data.getItemStack().getItem().getUnlocalizedName() + "@" + data.getItemStack().getMetadata()));
                data.setEnglishName("");
            }

            for (MobData data : mobDataList) {
                if (ItemRenderMod.debugMode)
                    ItemRenderMod.instance.log.info(I18n.format("itemrender.msg.addEN", data.getMob().getRegistryName()));
                data.setEnglishname("");
            }

            File export;
            File export1;
            for (String modid : modList) {
                export = new File(minecraft.mcDataDir, String.format("export"+File.separator+modid+"_item.json", modid.replaceAll("[^A-Za-z0-9()\\[\\]]", "")));
                if (!export.getParentFile().exists()) export.getParentFile().mkdirs();
                if (!export.exists()) export.createNewFile();
                PrintWriter pw = new PrintWriter(export, "UTF-8");

                for (ItemData data : itemDataList) {
                    if (modid.equals(getItemOwner(data.getItemStack())))
                        pw.println(gson.toJson(data));
                }
                pw.close();

            }
            for (String modid : modList) {
                export1 = new File(minecraft.mcDataDir, String.format("export"+File.separator+modid+"_entity.json", modid.replaceAll("[^A-Za-z0-9()\\[\\]]", "")));
                if (!export1.getParentFile().exists()) export1.getParentFile().mkdirs();
                if (!export1.exists()) export1.createNewFile();
                PrintWriter pw1 = new PrintWriter(export1, "UTF-8");

                for (MobData data : mobDataList) {
                    if (modid.equals(getEntityOwner(data.getMob())))
                        pw1.println(gson.toJson(data));
                }
                pw1.close();
            }
        }catch (Exception e){
            OfstreamFastIRR.log.error(e);
        }
    }
}
