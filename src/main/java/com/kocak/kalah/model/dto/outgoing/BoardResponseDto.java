package com.kocak.kalah.model.dto.outgoing;

import com.kocak.kalah.model.enums.PlayerSide;
import jakarta.validation.constraints.NotBlank;

public record BoardResponseDto(@NotBlank long id,
                               @NotBlank int pit,
                               @NotBlank int tokenCount,
                               @NotBlank PlayerSide playerSide,
                               @NotBlank boolean isKalah) {
}
