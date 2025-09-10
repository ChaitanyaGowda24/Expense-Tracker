package com.example.UserService.dto;

public record JwtResponse(String token, String tokenType, String username, String role) {}

