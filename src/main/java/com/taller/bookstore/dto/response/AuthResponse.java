package com.taller.bookstore.dto.response;

public class AuthResponse {

    private String token;
    private Long expiresIn;
    private String role;

    public AuthResponse() {}

    public AuthResponse(String token, Long expiresIn, String role) {
        this.token = token;
        this.expiresIn = expiresIn;
        this.role = role;
    }

    // 👇 getters
    public String getToken() { return token; }
    public Long getExpiresIn() { return expiresIn; }
    public String getRole() { return role; }
}