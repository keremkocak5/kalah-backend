package com.kocak.kalah.controller;

import com.kocak.kalah.model.dto.incoming.CreateGameRequestDto;
import com.kocak.kalah.model.dto.outgoing.GameResponseDto;
import com.kocak.kalah.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @Operation(summary = "Creates a new Kalah game.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Game created."),
            @ApiResponse(responseCode = "400", description = "Bad request."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<GameResponseDto> createGame(@NonNull @RequestBody CreateGameRequestDto createGameDto) {
        return new ResponseEntity<>(gameService.createGame(createGameDto), HttpStatus.OK);
    }

    @Operation(summary = "Returns an existing game.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Game created."),
            @ApiResponse(responseCode = "400", description = "Bad request."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @GetMapping(path = "/game/{game}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<GameResponseDto> getGame(@NonNull @PathVariable(value = "game") long gameId) {
        return new ResponseEntity<>(gameService.getGame(gameId), HttpStatus.OK);
    }

}
