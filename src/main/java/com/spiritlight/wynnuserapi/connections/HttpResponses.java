package com.spiritlight.wynnuserapi.connections;

public class HttpResponses {
    public static HttpResponse emptyResponse() {
        return new HttpResponse(null, "", 204);
    }
}
