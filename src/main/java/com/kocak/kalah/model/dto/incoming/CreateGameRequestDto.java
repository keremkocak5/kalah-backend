package com.kocak.kalah.model.dto.incoming;

import jakarta.validation.constraints.NotBlank;

public record CreateGameRequestDto(@NotBlank String playerBlueName,
                                   @NotBlank String playerRedName,
                                   @NotBlank int pitCount) {
}
