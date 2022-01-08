package party.para.yggdrasil.forge_1_16_5;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import party.para.yggdrasil.authorization.core.model.CheckResult;
import party.para.yggdrasil.authorization.core.service.AuthorizationService;
import party.para.yggdrasil.authorization.core.service.implementation.AbstractAuthorizationService;

import java.util.Locale;

@Mod("paraparty-authorization")
public class ParapartyAuthorizationForge {

    private static final Logger LOGGER = LogManager.getLogger();

    AuthorizationService authService = new AbstractAuthorizationService() {
        @Override
        public void onFail() {

        }

        @Override
        public void onError(Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        }

        @Override
        public void onSuccess(CheckResult ret) {

        }
    };

    public ParapartyAuthorizationForge() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void playerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        PlayerEntity playerObj = event.getPlayer();
        if (!(playerObj instanceof ServerPlayerEntity)) {
            return;
        }

        ServerPlayerEntity player = (ServerPlayerEntity) playerObj;

        Locale locale = Locale.getDefault();
        String name = player.getName().getString();

        CheckResult result = authService.checkStatus(name, locale == null ? "zh_cn" : locale.toString());
        if (result == null) {
            player.connection.disconnect(new StringTextComponent(""));
            return;
        }

        if (result.getStatus() != 0) {
            player.connection.disconnect(new StringTextComponent(result.getMessage()));
            return;
        }
    }
}
