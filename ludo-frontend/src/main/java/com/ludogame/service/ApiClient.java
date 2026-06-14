package com.ludogame.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * API Client Service
 * Demonstrates Encapsulation of API communication
 */
public class ApiClient {

    private static final String BASE_URL = "http://localhost:8080/api";
    private static final Gson gson = new Gson();
    private static String authToken;

    public static String login(String email, String password) throws Exception {
        JsonObject payload = new JsonObject();
        payload.addProperty("email", email);
        payload.addProperty("password", password);

        String response = sendRequest("/auth/login", "POST", payload.toString(), null);
        JsonObject jsonResponse = gson.fromJson(response, JsonObject.class);
        authToken = jsonResponse.get("accessToken").getAsString();
        return authToken;
    }

    public static String register(String username, String email, String password, String confirmPassword) throws Exception {
        JsonObject payload = new JsonObject();
        payload.addProperty("username", username);
        payload.addProperty("email", email);
        payload.addProperty("password", password);
        payload.addProperty("confirmPassword", confirmPassword);

        String response = sendRequest("/auth/register", "POST", payload.toString(), null);
        JsonObject jsonResponse = gson.fromJson(response, JsonObject.class);
        authToken = jsonResponse.get("accessToken").getAsString();
        return authToken;
    }

    public static String getUser(Long userId) throws Exception {
        return sendRequest("/users/" + userId, "GET", null, authToken);
    }

    public static String updateUser(Long userId, String username, String email) throws Exception {
        JsonObject payload = new JsonObject();
        payload.addProperty("username", username);
        payload.addProperty("email", email);

        return sendRequest("/users/" + userId, "PUT", payload.toString(), authToken);
    }

    private static String sendRequest(String endpoint, String method, String body, String token) throws Exception {
        URL url = new URL(BASE_URL + endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setRequestProperty("Content-Type", "application/json");

        if (token != null) {
            connection.setRequestProperty("Authorization", "Bearer " + token);
        }

        if (body != null) {
            connection.setDoOutput(true);
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = body.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
        }

        int responseCode = connection.getResponseCode();
        StringBuilder response = new StringBuilder();

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
        }

        return response.toString();
    }

    public static void setAuthToken(String token) {
        authToken = token;
    }
}