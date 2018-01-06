package com.example.testing.myapplication.utils;

import com.google.gson.JsonElement;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;

public class JsendResponse {

    public static final String ERROR_MESSAGE = "An unexpected network error occurred.";
    private JsonElement successBody;
    private JSONObject errorBody;

    public JsendResponse(JsonElement successBody, ResponseBody errorBody) {
        this.successBody = successBody;
        if (errorBody != null) {
            try {
                this.errorBody = new JSONObject(errorBody.string());
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isSuccess() {
        return (successBody != null && successBody.getAsJsonObject().has("status") &&
                successBody.getAsJsonObject().get("status").getAsString().equalsIgnoreCase("success"));
    }

    public JsonElement getData() {
        return successBody.getAsJsonObject().get("data");
    }

    public JsonElement getDataAsObject() {
        return successBody.getAsJsonObject().get("data").getAsJsonObject();
    }

    public JsonElement getDataAsArray() {
        return successBody.getAsJsonObject().get("data").getAsJsonArray();
    }

    public String getErrorMessage() {
        if (errorBody == null) {
            return ERROR_MESSAGE;
        }
        try {
            return errorBody.getString("message");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ERROR_MESSAGE;
    }

    public String getCode() {
        if (errorBody == null) {
            return "-1";
        }
        try {
            return errorBody.getString("code");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "-1";
    }

}
