package com.kocak.kalah.model.dto.outgoing;

import com.kocak.kalah.model.enums.PlayerSide;
import lombok.NonNull;

public record BoardResponseDto(@NonNull long id,
                               @NonNull int pit,
                               @NonNull int tokenCount,
                               @NonNull PlayerSide playerSide,
                               boolean kalah) {
}
