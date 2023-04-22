package com.Hileb.ofstream_fastirr;

import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.ArrayList;
import java.util.List;

public class MixinHandler implements ILateMixinLoader {
    @Override
    public List<String> getMixinConfigs() {
        List<String> list=new ArrayList<>();
        list.add("mixin.ofstream.fastirr.mixin.json");
        return list;
    }
}
