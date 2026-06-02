package noisethreader;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import zone.rong.mixinbooter.IEarlyMixinLoader;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@IFMLLoadingPlugin.MCVersion("1.12.2")
@IFMLLoadingPlugin.SortingIndex(1001) // 确保早期加载
public class NoiseThreaderPlugin implements IFMLLoadingPlugin, IEarlyMixinLoader {

    public NoiseThreaderPlugin() {
        // 初始化 Mixin 系统
        MixinBootstrap.init();
    }

    @Override
    public List<String> getMixinConfigs() {
        List<String> configs = new ArrayList<>();

        // 1. 原版噪声优化（始终启用）
        configs.add("mixins.noisethreader.vanilla.json");

        // 2. 原版缓存线程安全（仅在 BetterCaves 存在时启用）
        if (Loader.isModLoaded("bettercaves")) {
            configs.add("mixins.noisethreader.vanilla.cache.json");
        }

        // 3. OTG 噪声优化（仅在 OTG 存在时启用）
        if (Loader.isModLoaded("openterraingenerator")) {
            configs.add("mixins.noisethreader.otg.json");
            
            // OTG 缓存（需要同时有 BetterCaves）
            if (Loader.isModLoaded("bettercaves")) {
                configs.add("mixins.noisethreader.otg.cache.json");
            }
        }

        // 4. BetterCaves 优化（仅在 BetterCaves 存在时启用）
        if (Loader.isModLoaded("bettercaves")) {
            configs.add("mixins.noisethreader.bettercaves.json");
        }

        return configs;
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    @Nullable
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
        // 不需要额外处理
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
