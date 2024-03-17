package com.kocak.kalah.service;

import com.kocak.kalah.model.dto.outgoing.BoardHeaderResponseDto;

public interface PlayService {

    BoardHeaderResponseDto makeMove(long gameId, int pit);

}
