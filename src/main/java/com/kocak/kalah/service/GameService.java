package com.kocak.kalah.service;

import com.kocak.kalah.model.dto.incoming.CreateGameRequestDto;
import com.kocak.kalah.model.entity.Game;

public interface GameService {

    Game createGame(CreateGameRequestDto createGameDto);

}
