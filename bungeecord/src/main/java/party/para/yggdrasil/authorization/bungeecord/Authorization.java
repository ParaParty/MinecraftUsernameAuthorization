package party.para.yggdrasil.authorization.bungeecord;

import net.md_5.bungee.api.plugin.Plugin;

public final class Authorization extends Plugin {

    @Override
    public void onEnable() {
        Logging.log = getLogger();
        getProxy().getPluginManager().registerListener(this, new EventsHandler());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
