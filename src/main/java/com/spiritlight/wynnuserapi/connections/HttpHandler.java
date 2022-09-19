package com.spiritlight.wynnuserapi.connections;

import com.google.gson.JsonObject;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;

public class HttpHandler {
    private static final OkHttpClient client = new OkHttpClient();

    /**
     * Utility method to create an HTTP GET request to {@code url}.<br>
     * <b>NOTE:</b> This method will block the thread.
     * @param url The url to send the request to
     * @return A {@link HttpResponse} with the result, or an {@link HttpResponses#emptyResponse()} if request failed.
     */
    public static HttpResponse get(final String url) {
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            return new HttpResponse(response.headers(), response.body().string(), response.code());
        } catch (IOException e) {
            e.printStackTrace();
            return HttpResponses.emptyResponse();
        } catch (NullPointerException e) {
            return HttpResponses.emptyResponse();
        }
    }

    /**
     * Utility method to create an HTTP POST request to {@code url}.<br>
     * Supplied {@code contents} should be a Map of simple key:value to be parsed into a {@link JsonObject}.<br>
     * <b>NOTE:</b> This method will block the thread.
     * @param url The url to send the request to
     * @param contents The Map&lt;Key, Value> to send to the server.
     * @return A {@link HttpResponse} with the result, or an {@link HttpResponses#emptyResponse()} if request failed.
     */
    public static HttpResponse post(String url, Map<String, String> contents) {
        JsonObject jsonObject = new JsonObject();
        for(Map.Entry<String, String> contentMap : contents.entrySet()) {
            jsonObject.addProperty(contentMap.getKey(), contentMap.getValue());
        }
        RequestBody body = RequestBody.create(jsonObject.toString(), MediaType.parse("application/json"));
        Request request = new Request.Builder().url(url).post(body).build();
        try (Response response = client.newCall(request).execute()) {
            return new HttpResponse(response.headers(), response.body().string(), response.code());
        } catch (IOException e) {
            e.printStackTrace();
            return HttpResponses.emptyResponse();
        } catch (NullPointerException e) {
            return HttpResponses.emptyResponse();
        }
    }
}
