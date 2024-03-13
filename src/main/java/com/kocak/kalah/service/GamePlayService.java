package com.kocak.kalah.service;

import com.kocak.kalah.model.dto.outgoing.BoardHeaderResponseDto;
import com.kocak.kalah.model.dto.outgoing.BoardResponseDto;

public interface GamePlayService {

    BoardHeaderResponseDto makeMove(long gameId, short pit);

    BoardHeaderResponseDto getBoard(long gameId);
}
