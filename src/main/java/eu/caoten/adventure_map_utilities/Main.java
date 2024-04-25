package eu.caoten.adventure_map_utilities;

import eu.caoten.adventure_map_utilities.config.Config;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {
	public static final String MOD_ID = "adventure_map_utilities";
	public static final String MOD_VERSION = "1.0.7";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static boolean ENABLED_KEYBINDINGS = true;

	@Override
	public void onInitialize() {
		Config.Read();
	}
}
