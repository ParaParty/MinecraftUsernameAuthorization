package party.para.yggdrasil.authorization.core.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import party.para.yggdrasil.authorization.core.model.CheckResult;

public interface AuthorizationService {
    /**
     * 检测该用户名是否允许登陆到服务器<br/>
     * 检测失败时返回空
     *
     * @param name 用户名
     * @param locale 语言
     * @return 返回值
     */
    @Nullable CheckResult checkStatus(@NotNull String name, @NotNull String locale);
}
