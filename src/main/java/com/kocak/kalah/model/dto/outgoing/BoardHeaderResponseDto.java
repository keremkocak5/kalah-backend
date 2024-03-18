package com.kocak.kalah.model.dto.outgoing;

import com.kocak.kalah.model.enums.GameStatus;
import com.kocak.kalah.model.enums.PlayerSide;
import lombok.NonNull;

import java.util.List;

public record BoardHeaderResponseDto(@NonNull List<BoardResponseDto> boardResponseDtos,
                                     @NonNull PlayerSide turn,
                                     @NonNull GameStatus gameStatus,
                                     PlayerSide winner) {
}
