package com.kocak.kalah.model.dto.incoming;

import lombok.NonNull;

public record MakeMoveRequestDto(@NonNull long gameId,
                                 @NonNull short pit) {
}
