package com.kocak.kalah.model.dto.incoming;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NonNull;

public record CreateGameRequestDto(
        @NonNull @Schema(description = "Name of the Blue Player, only Latin characters, max length 50", example = "Kerem1") String playerBlueName,
        @NonNull @Schema(description = "Name of the Red Player, only Latin characters, max length 50", example = "Jane") String playerRedName,
        @NonNull @Schema(description = "Number of pits, integers only, min 1, max 10", example = "1") int pitCount) {
}
