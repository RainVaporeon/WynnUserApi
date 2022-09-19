package com.spiritlight.wynnuserapi.connections;

import com.google.gson.JsonObject;
import okhttp3.Headers;

import javax.annotation.Nullable;
import java.util.Set;

public class HttpResponse {
    private final String BODY;
    private final Headers HEADER;
    private final int CODE;


    public HttpResponse(Headers header, String body, int code) {
        this.BODY = (body == null ? "null" : body);
        this.HEADER = header;
        this.CODE = code;
    }

    public String getBody() {
        return BODY;
    }

    public @Nullable Headers getHeader() {
        return HEADER;
    }

    public int getCode() {
        return CODE;
    }

    public String serialize() {
        final JsonObject object = new JsonObject();
        if(getHeader() != null) {
            Set<String> headerList = getHeader().names();
            for(String s : headerList) {
                object.addProperty(s, getHeader().get(s));
            }
        }
        return "!!com.spiritlight.wynnuserapi.connection.HttpResponse{HEADER=" + object + ", BODY=" + BODY + ", CODE=" + CODE + "}";
    }
}
