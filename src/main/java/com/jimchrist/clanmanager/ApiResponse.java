package com.jimchrist.clanmanager;
/*
* This class represents the response from the API
*/
public class ApiResponse {
    private int statusCode;
    private String body;
    public ApiResponse(int statusCode, String body) {
        this.statusCode=statusCode;
        this.body=body;
    }
    // Getters
    int getStatusCode() {
        return this.statusCode;
    }
    String getBody() {
        return this.body;
    }
}
