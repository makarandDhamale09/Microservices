package com.myauth.security.auth;

import lombok.Builder;

@Builder
public record AuthenticationResponse(String token) {}
