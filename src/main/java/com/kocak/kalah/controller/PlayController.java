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

@RestController
@RequestMapping("/play")
@RequiredArgsConstructor
public class PlayController {

    private final PlayService playService;

    @Operation(summary = "Make a movement.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movement complete.")
    })
    @PostMapping(path = "/gameId/{gameId}/pit/{pit}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BoardHeaderResponseDto> makeMove(@NonNull @PathVariable(value = "gameId") long gameId,
                                                    @NonNull @PathVariable(value = "pit") short pit) {
        return new ResponseEntity<>(playService.makeMove(gameId, pit), HttpStatus.OK);
    }

}
