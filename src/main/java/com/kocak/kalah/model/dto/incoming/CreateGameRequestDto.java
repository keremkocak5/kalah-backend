package com.kocak.kalah.model.dto.incoming;

import lombok.NonNull;

public record CreateGameRequestDto(@NonNull String playerBlueName,
                                   @NonNull String playerRedName,
                                   @NonNull short pitCount,
                                   @NonNull boolean againstComputer) {
}
