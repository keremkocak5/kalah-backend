package com.kocak.kalah.model.dto.incoming;

import lombok.NonNull;

public record CreateGameRequestDto(@NonNull String playerAName,
                                   @NonNull String playerBName,
                                   @NonNull short pitCount,
                                   @NonNull boolean againstComputer) {
}
