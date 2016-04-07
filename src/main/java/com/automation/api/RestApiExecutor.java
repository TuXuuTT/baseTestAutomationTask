package com.automation.api;

import com.automation.environment.EnvironmentConfigurator;
import com.getbase.Client;
import com.getbase.Configuration;
import com.getbase.models.Lead;
import ru.yandex.qatools.allure.annotations.Step;

public class RestApiExecutor {

    private static volatile RestApiExecutor restApiExecutor;
    protected Client client;

    private RestApiExecutor() {
        client = new Client(new Configuration.Builder()
                .accessToken(EnvironmentConfigurator.getInstance().getApiBearerToken())
                .verbose()
                .build());
    }

    public static RestApiExecutor getInstance() {

        RestApiExecutor sysProps = restApiExecutor;
        if (sysProps == null) {
            synchronized (RestApiExecutor.class) {
                sysProps = restApiExecutor;
                if (sysProps == null) {
                    restApiExecutor = sysProps = new RestApiExecutor();
                }
            }
        }
        return sysProps;
    }

    public Client getApiClient() {
        return client;
    }

    @Step
    public void createNewLeadViaApi(Lead lead) {
        getApiClient().leads().create(lead);
    }
}
