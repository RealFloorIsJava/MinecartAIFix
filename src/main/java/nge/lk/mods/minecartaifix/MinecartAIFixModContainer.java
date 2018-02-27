package nge.lk.mods.minecartaifix;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.network.NetworkCheckHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;

import java.util.Collections;
import java.util.Map;

/**
 * Container for the core mod.
 */
public class MinecartAIFixModContainer extends DummyModContainer {

    /**
     * Constructor.
     */
    public MinecartAIFixModContainer() {
        super(new ModMetadata());
        getMetadata().authorList = Collections.singletonList("LordKorea");
        getMetadata().modId = "minecartaifix";
        getMetadata().name = "Minecart AI Fix";
        getMetadata().version = "0.1.1";
        getMetadata().description = "Mod fixing MC-64836";
    }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller) {
        bus.register(this);
        return true;
    }

    @Subscribe
    public void modConstruction(FMLConstructionEvent event) {
        NetworkRegistry.INSTANCE.register(this, getClass(), null,
                event.getASMHarvestedData());
    }

    @NetworkCheckHandler
    public boolean check(Map<String, String> remoteVersions, Side side) {
        // Remote version is not required.
        return true;
    }

    @Override
    public Object getMod() {
        return this;
    }
}
