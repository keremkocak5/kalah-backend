package com.kocak.kalah.model.dto.outgoing;

import com.kocak.kalah.enums.PlayerSide;
import lombok.NonNull;

import java.util.List;

public record BoardHeaderResponseDto(@NonNull List<BoardResponseDto> boardResponseDtos, @NonNull PlayerSide turn) {
}
