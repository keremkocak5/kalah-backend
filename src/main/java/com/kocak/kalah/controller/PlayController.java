package com.kocak.kalah.controller;

import com.kocak.kalah.model.dto.outgoing.BoardHeaderResponseDto;
import com.kocak.kalah.service.PlayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.kocak.kalah.Constants.API_VERSION;

@RestController
@RequestMapping(API_VERSION + "/play")
@RequiredArgsConstructor
public class PlayController {

    private final PlayService playService;

    @Operation(summary = "Makes a movement.", description = "Accepts game id, and pit number to be sowed as arguments.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movement complete."),
            @ApiResponse(responseCode = "400", description = "Bad request."),
            @ApiResponse(responseCode = "422", description = "Movement not allowed."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @PostMapping(path = "/game/{game}/pit/{pit}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BoardHeaderResponseDto> makeMove(@NonNull @PathVariable(value = "game") long gameId,
                                                    @NonNull @PathVariable(value = "pit") int pit) {
        return new ResponseEntity<>(playService.makeMove(gameId, pit), HttpStatus.OK);
    }

}
