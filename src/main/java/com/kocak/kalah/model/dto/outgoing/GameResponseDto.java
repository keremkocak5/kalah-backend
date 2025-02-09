package com.kocak.kalah.model.dto.outgoing;

import com.kocak.kalah.model.enums.GameStatus;
import com.kocak.kalah.model.enums.PlayerSide;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record GameResponseDto(@NotBlank int pitCount,
                              @NotBlank long id,
                              @NotBlank String playerRedName,
                              @NotBlank String playerBlueName,
                              @NotBlank PlayerSide turn,
                              @NotBlank GameStatus status,
                              PlayerSide winner,
                              @NotBlank List<BoardResponseDto> boardResponseDtos) {
}
