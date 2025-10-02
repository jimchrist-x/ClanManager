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
    public int getStatusCode() {
        return this.statusCode;
    }
    public String getBody() {
        return this.body;
    }
}
