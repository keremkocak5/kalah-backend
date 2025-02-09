package com.kocak.kalah.model.dto.outgoing;

import com.kocak.kalah.model.enums.GameStatus;
import com.kocak.kalah.model.enums.PlayerSide;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record BoardHeaderResponseDto(@NotBlank List<BoardResponseDto> boardResponseDtos,
                                     @NotBlank PlayerSide turn,
                                     @NotBlank GameStatus gameStatus,
                                     PlayerSide winner) {
}
