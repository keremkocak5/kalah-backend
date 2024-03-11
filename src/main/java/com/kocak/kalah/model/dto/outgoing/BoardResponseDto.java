package com.kocak.kalah.model.dto.outgoing;

import lombok.NonNull;

public record BoardResponseDto(@NonNull long gameId,
                               @NonNull short pit) {
}
