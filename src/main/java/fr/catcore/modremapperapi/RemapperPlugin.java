package fr.catcore.modremapperapi;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class RemapperPlugin implements IMixinConfigPlugin {

    static {

    }

    @Override
    public void onLoad(String mixinPackage){}

    @Override
    public String getRefMapperConfig(){
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName){
        return false;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets){}

    @Override
    public List<String> getMixins(){
        try {
            ((Runnable)Class.forName("fr.catcore.modremapperapi.ModRemappingApiInit").newInstance()).run();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo){}

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo){}
}
