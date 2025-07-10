package com.testapi.utils;

import org.json.JSONObject;

public class JsonPayload {
    public static String user(String name, String email) {
        return new JSONObject()
                .put("name", name)
                .put("email", email)
                .toString();
    }
}
