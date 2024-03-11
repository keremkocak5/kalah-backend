package com.kocak.kalah.service;

import com.kocak.kalah.model.dto.incoming.MakeMoveRequestDto;
import com.kocak.kalah.model.dto.outgoing.BoardResponseDto;

public interface GamePlayService {

    BoardResponseDto makeMove(MakeMoveRequestDto makeMoveDto);

}
