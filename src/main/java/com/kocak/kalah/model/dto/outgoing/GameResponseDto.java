package com.kocak.kalah.model.dto.outgoing;

import com.kocak.kalah.model.enums.GameStatus;
import com.kocak.kalah.model.enums.PlayerSide;

import java.util.List;

public record GameResponseDto(int pitCount, long id, String playerRedName, String playerBlueName, PlayerSide turn,
                              GameStatus status, List<BoardResponseDto> boardResponseDtos) {
}
