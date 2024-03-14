package com.kocak.kalah.controller;

import com.kocak.kalah.model.dto.outgoing.BoardHeaderResponseDto;
import com.kocak.kalah.model.dto.outgoing.BoardResponseDto;
import com.kocak.kalah.service.GamePlayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gameplay")
@RequiredArgsConstructor
public class GameplayController {

    private final GamePlayService gamePlayService;

    @Operation(summary = "Make a movement.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movement complete.")
    })
    @PostMapping(path = "/gameId/{gameId}/pit/{pit}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BoardHeaderResponseDto> postMove(@NonNull @PathVariable(value = "gameId") long gameId,
                                              @NonNull @PathVariable(value = "pit") short pit) {
        return new ResponseEntity<>(gamePlayService.makeMove(gameId, pit), HttpStatus.OK);
    }

    @Operation(summary = "Make a movement.") // kerem
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movement complete.")
    })
    @GetMapping(path = "/gameId/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BoardHeaderResponseDto> getBoard(@NonNull @PathVariable(value = "gameId") long gameId) {
        return new ResponseEntity<>(gamePlayService.getBoard(gameId), HttpStatus.OK);
    }



}
