package com.kocak.kalah.model.dto.outgoing;

import com.kocak.kalah.model.enums.GameStatus;
import com.kocak.kalah.model.enums.PlayerSide;
import lombok.NonNull;

import java.util.List;

public record GameResponseDto(@NonNull int pitCount,
                              @NonNull long id,
                              @NonNull String playerRedName,
                              @NonNull String playerBlueName,
                              @NonNull PlayerSide turn,
                              @NonNull GameStatus status,
                              @NonNull List<BoardResponseDto> boardResponseDtos) {
}
