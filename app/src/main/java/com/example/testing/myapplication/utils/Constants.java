package com.example.testing.myapplication.utils;

import java.util.HashMap;

public class Constants {


    public static final String API_BASE_URL = "http://test@cottacush.com";
    /**
     * Shared Preference keys
     */

    public static final String PREF_KEY_ACCESS_TOKEN = "pref_key_access_token";
    public static final String PREF_KEY_TOKEN_EXPIRES = "token_expiry";

    /* OAUTH Request body Map
     */
    public static HashMap<String, String> getOauthCredentialsMap() {
        HashMap<String, String> body = new HashMap<>();

        return body;
    }
}
