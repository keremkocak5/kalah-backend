package com.kocak.kalah.converter.impl;

import com.kocak.kalah.converter.DomainToViewConvertable;
import com.kocak.kalah.model.dto.outgoing.BoardHeaderResponseDto;
import com.kocak.kalah.model.dto.outgoing.BoardResponseDto;
import com.kocak.kalah.model.entity.Game;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class GameToBoardHeaderResponseDto implements DomainToViewConvertable<Game, BoardHeaderResponseDto> {
    @Override
    public BoardHeaderResponseDto convertToView(Game game) {
        return new BoardHeaderResponseDto(game
                .getBoards()
                .entrySet()
                .stream()
                .map(board -> new BoardResponseDto(
                        board.getValue().getId(),
                        board.getValue().getPit(),
                        board.getValue().getTokenCount(),
                        board.getValue().getPlayerSide(),
                        board.getValue().isKalah()
                ))
                .collect(Collectors.toUnmodifiableList()), game.getTurn());
    }
}
