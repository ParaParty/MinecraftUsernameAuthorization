package party.para.yggdrasil.authorization.bungeecord;

import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import org.jetbrains.annotations.NotNull;
import party.para.yggdrasil.authorization.core.model.CheckResult;
import party.para.yggdrasil.authorization.core.service.AuthorizationService;
import party.para.yggdrasil.authorization.core.service.implementation.AbstractAuthorizationService;

import java.util.Locale;
import java.util.logging.Level;

public class EventsHandler implements Listener {

    AuthorizationService authService = new AbstractAuthorizationService() {
        @Override
        public void onFail() {

        }

        @Override
        public void onError(Exception ex) {
            Logging.log.log(Level.FINEST, ex.toString());
        }

        @Override
        public void onSuccess(CheckResult ret) {

        }
    };

    @EventHandler
    public void onPostLogin(@NotNull PostLoginEvent event) {
        ProxiedPlayer player = event.getPlayer();
        Locale locale = player.getLocale();
        String name = player.getName();

        CheckResult result = authService.checkStatus(name, locale == null ? "zh_cn" : locale.toString());
        if (result == null) {
            event.getPlayer().disconnect();
            return;
        }

        if (result.getStatus() != 0) {
            event.getPlayer().disconnect(new TextComponent(result.getMessage()));
            return;
        }
    }
}
