package nge.lk.mods.minecartaifix;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.Name;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.SortingIndex;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * Loading plugin for the mod.
 */
@Name("Minecart AI Fix")
@MCVersion("1.11.2")
@TransformerExclusions("nge.lk.mods.")
@SortingIndex(1001)
public class MinecartAIFixLoadingPlugin implements IFMLLoadingPlugin {

    @Override
    public void injectData(Map<String, Object> data) {
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[]{
                EntityMinecartTransformer.class.getName()
        };
    }

    @Override
    public String getModContainerClass() {
        return "nge.lk.mods.minecartaifix.MinecartAIFixModContainer";
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
