package com.automation.api;

import com.getbase.Client;
import com.getbase.Configuration;
import com.getbase.services.LeadsService;
import com.jayway.restassured.RestAssured;

import java.util.HashMap;
import java.util.Map;

public class RestApiExecutor {

    private static volatile RestApiExecutor restApiExecutor;
    static Client client = new Client(new Configuration.Builder()
//            .accessToken(System.getenv("BASECRM_ACCESS_TOKEN"))
            .accessToken("324c5c7d2677791132b96e37a11be6cdb47718b8202d5aad65cecb9b64cb3b30")
//            .baseUrl("https://app.futuresimple.com/settings/leads")
            .verbose()
            .build());

    private RestApiExecutor() {

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

    public static void main(String[] args) {
//        client.leads().list(new LeadsService.SearchCriteria()
//                .page(1)
//                .perPage(100))
//                .stream()
//                .forEach(System.out::println);
        Map a = new HashMap<>();
//        a.put("_","1459884121720");
        a.put("includes","custom_fields");
//        a.put("sortable",true);
        System.out.println(client.leads().get(1890381790).getCustomFields());
//        client.getHttpClient().get("",a).getHttpStatus();
//        System.out.println(client.getHttpClient().get("/custom_fields",a));
        System.out.println(client.getHttpClient().get("/v2/leads",null));
//        System.out.println(client.getHttpClient().get("/v2/leads?ids=1890381790",null));
//        RestAssured.get().then().assertThat().statusCode(200);
//        https://app.futuresimple.com/apis/leads/api/v1/custom_fields.json
//        https://api.getbase.com/v2/leads?ids=1890381790
    }
}
