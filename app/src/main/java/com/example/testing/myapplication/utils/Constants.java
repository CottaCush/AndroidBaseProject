package com.example.testing.myapplication.utils;

import java.util.HashMap;

public class Constants {

    /**
     *  Constants for OAUTH and FBNI API Base URL
     */

    public static final String API_BASE_URL = "http://staging.fbn-service.cottacush.com";
    public static final String OAUTH_CLIENT_ID = "fbn-service-staging";
    public static final String OAUTH_CLIENT_SECRET = "HkOG71GywS";
    /**
     * Shared Preference keys
     */

    public static final String PREF_KEY_ACCESS_TOKEN = "pref_key_access_token";
    public static final String PREF_KEY_AGENT_DATA = "pref_key_agent_data";
    public static final String PREF_KEY_CITIES_LOVS = "pref_key_cities_lovs";
    public static final String PREF_KEY_CUSTOMER_DATA = "pref_key_customer_data";
    public static final String PREF_KEY_DATE_OF_BIRTH_FOR_WCBC_SELL_PRODUCTS = "pref_key_date_of_birth_for_wcbc_sell_products";
    public static final String PREF_KEY_DEVICE_PHONE_NUMBER = "device_phone_number";
    public static final String PREF_KEY_FOR_REVIEW = "pref_key_for_review";
    public static final String PREF_KEY_IS_NOTIFICATION_SUBSCRIBED = "is_notification_subscribed";
    public static final String PREF_KEY_NOTIFICATION_LIST = "notification_list";
    public static final String PREF_KEY_NOTIFICATION_TOKEN = "notification_token";
    public static final String PREF_KEY_PREMIUM_PAYABLE = "pref_key_premium_payable";
    public static final String PREF_KEY_PRODUCT_CATEGORIES = "pref_key_product_categories";
    public static final String PREF_KEY_PROPOSALS_LOVS = "pref_key_proposal_lovs";
    public static final String PREF_KEY_PROPOSAL_ID = "pref_key_proposal_id";
    public static final String PREF_KEY_PROPOSAL_SUMMARY = "pref_key_proposal_summary";
    public static final String PREF_KEY_QUOTE_REQUEST_OBJECT = "pref_key_quote_request_object";
    public static final String PREF_KEY_SOLUTIONS_LOVS = "pref_key_solutions_lovs";
    public static final String PREF_KEY_TOKEN_EXPIRES = "token_expiry";
    public static final String PREF_KEY_WCBCS = "pref_key_wcbc";
    public static final String PREF_KEY_WCBC_QUOTE_REQUEST_OBJECT = "pref_key_wcbc_quote_request";
    /*
        String Constants
     */
    public static final String PREMIUM = "Premium";
    public static final String PROPOSALS = "proposals";
    public static final String QUOTE = "quote";
    public static final String RESULTS = "results";
    public static final String CUSTOMERS = "customers";
    public static final String MAIN = "main";
    public static final String ANNUITY = "annuity";
    public static final String PHONE = "phone";
    public static final String NONE = "none";
    public static final String STATES = "states";
    public static final String SUM_ASSURED = "Sum Assured";
    public static final String TYPE = "type";
    public static final String USER_NOT_FOUND = "record_not_found";
    public static final String WCBC = "wcbc";
    public static final String IDENTIFIER = "identifier";

    /* OAUTH Request body Map
     */
    public static HashMap<String, String> getOauthCredentialsMap() {
        HashMap<String, String> body = new HashMap<>();

        body.put("grant_type", "client_credentials");
        body.put("client_id", Constants.OAUTH_CLIENT_ID);
        body.put("client_secret", Constants.OAUTH_CLIENT_SECRET);

        return body;
    }
}
