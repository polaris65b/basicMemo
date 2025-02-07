package com.example.basicmemo.dto;

import lombok.Getter;

@Getter
public class MemoResponseDto {

    // Response라서 final
    private final Long id;
    private final String content;

    public MemoResponseDto(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}
