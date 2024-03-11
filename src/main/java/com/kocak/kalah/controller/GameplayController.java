package com.kocak.kalah.controller;

import com.kocak.kalah.model.dto.incoming.MakeMoveRequestDto;
import com.kocak.kalah.model.dto.outgoing.BoardResponseDto;
import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.service.GamePlayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gameplay")
@RequiredArgsConstructor public class GameplayController {

    private final GamePlayService gamePlayService;

    @Operation(summary = "Makes a movement.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movement complete.")
    })
    @PostMapping()
    ResponseEntity<BoardResponseDto> makeMove(@NonNull @RequestBody MakeMoveRequestDto makeMoveDto) {
        return new ResponseEntity<>(gamePlayService.makeMove(makeMoveDto), HttpStatus.OK);
    }

}
