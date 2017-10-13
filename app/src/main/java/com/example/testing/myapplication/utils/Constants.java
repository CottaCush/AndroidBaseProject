package com.example.testing.myapplication.utils;

import java.util.HashMap;

/**
 * @author Adegoke Obasa <goke@cottacush.com>
 */

public class Constants {
    public static final String API_BASE_URL = "http://staging.fbn-service.cottacush.com";
    public static final String OAUTH_CLIENT_ID = "fbn-service-staging";
    public static final String OAUTH_CLIENT_SECRET = "HkOG71GywS";
    public static final String AGENT_DATA = "agent";
    public static final String PHONE = "phone";

    public static HashMap<String, String> getOauthCredentialsMap() {
        HashMap<String, String> body = new HashMap<>();

        body.put("grant_type", "client_credentials");
        body.put("client_id", Constants.OAUTH_CLIENT_ID);
        body.put("client_secret", Constants.OAUTH_CLIENT_SECRET);

        return body;
    }
}
