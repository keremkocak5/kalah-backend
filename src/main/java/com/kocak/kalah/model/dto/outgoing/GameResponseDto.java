package com.kocak.kalah.model.dto.outgoing;

import com.kocak.kalah.enums.GameStatus;
import com.kocak.kalah.enums.PlayerSide;
import com.kocak.kalah.repository.BoardRepository;

import java.util.List;

public record GameResponseDto(int pitCount, long id, String playerRedName, String playerBlueName, PlayerSide turn, GameStatus status, List<BoardResponseDto> boardResponseDtos) {
}
