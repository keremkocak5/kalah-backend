package com.kocak.kalah.controller;

import com.kocak.kalah.model.dto.incoming.CreateGameRequestDto;
import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.service.GameService;
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
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @Operation(summary = "Creates a new Kalah game.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Game created.")
    })
    @PostMapping()
    ResponseEntity<Game> createGame(@NonNull @RequestBody CreateGameRequestDto createGameDto) {
        return new ResponseEntity<>(gameService.createGame(createGameDto), HttpStatus.OK);
    }




}
