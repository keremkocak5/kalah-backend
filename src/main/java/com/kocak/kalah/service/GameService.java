package com.kocak.kalah.service;

import com.kocak.kalah.model.dto.incoming.CreateGameRequestDto;
import com.kocak.kalah.model.dto.outgoing.GameResponseDto;

public interface GameService {

    GameResponseDto createGame(CreateGameRequestDto createGameDto);

}
