package com.myproj.springtest.model.dto;

import java.time.LocalDateTime;

public record PostDto(Long id, String title, String content, LocalDateTime creationDate) {
}
