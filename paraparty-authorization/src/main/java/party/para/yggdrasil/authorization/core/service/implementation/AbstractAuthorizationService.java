package party.para.yggdrasil.authorization.core.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import party.para.yggdrasil.authorization.core.model.CheckResult;
import party.para.yggdrasil.authorization.core.service.AuthorizationService;

import java.io.IOException;

public abstract class AbstractAuthorizationService implements AuthorizationService {

    abstract public void onFail();

    abstract public void onError(Exception ex);

    abstract public void onSuccess(CheckResult ret);

    @Override
    @Nullable
    public CheckResult checkStatus(@NotNull String name, @NotNull String locale) {
        try {
            OkHttpClient client = new OkHttpClient();
            HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("yggdrasil.paraparty.forestsay.cc")
                .addPathSegment("api/extra/check_need_kick")
                .addQueryParameter("username", name)
                .build();
            Request request = new Request.Builder()
                .url(url)
                .header("Accept-Language", locale)
                .get()
                .build();
            Response response = client.newCall(request).execute();

            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                String body = responseBody.string();

                ObjectMapper mapper = new ObjectMapper();
                CheckResult ret = mapper.readValue(body, CheckResult.class);

                onSuccess(ret);
                return ret;
            }
        } catch (IOException ex) {
            onError(ex);
        }

        onFail();
        return null;
    }
}
