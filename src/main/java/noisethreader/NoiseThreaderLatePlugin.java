package noisethreader;

import net.minecraftforge.fml.common.Loader;
import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.ArrayList;
import java.util.List;

public class NoiseThreaderLatePlugin implements ILateMixinLoader {

    @Override
    public List<String> getLateMixinConfigs() {
        List<String> configs = new ArrayList<>();

        if (Loader.isModLoaded("bettercaves")) {
            configs.add("mixins.noisethreader.vanilla.cache.json");
            configs.add("mixins.noisethreader.bettercaves.json");
        }

        if (Loader.isModLoaded("openterraingenerator")) {
            configs.add("mixins.noisethreader.otg.json");
            if (Loader.isModLoaded("bettercaves")) {
                configs.add("mixins.noisethreader.otg.cache.json");
            }
        }

        return configs;
    }
}
