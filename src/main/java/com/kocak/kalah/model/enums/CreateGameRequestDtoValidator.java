package com.kocak.kalah.model.enums;

import com.kocak.kalah.model.dto.incoming.CreateGameRequestDto;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.kocak.kalah.util.Util.*;

public enum CreateGameRequestDtoValidator {

    PLAYER_RED_SHOULD_NOT_BE_EMPTY(IS_STRING_EMPTY, createGameRequestDto -> createGameRequestDto.playerRedName(), "Player Red should have a name"),
    PLAYER_RED_SHOULD_HAVE_MAX_50_CHARACTERS(IS_STRING_MAX_50_CHARS, createGameRequestDto -> createGameRequestDto.playerRedName(), "Player Red name should be max 50 chars"),
    PLAYER_RED_SHOULD_HAVE_ONLY_LATIN_CHARACTERS_AND_NUMBERS(IS_STRING_NO_SPECIAL_CHARS, createGameRequestDto -> createGameRequestDto.playerRedName(), "Player Red name should not contain special characters"),
    PLAYER_BLUE_SHOULD_NOT_BE_EMPTY(IS_STRING_EMPTY, createGameRequestDto -> createGameRequestDto.playerBlueName(), "Player Blue should have a name"),
    PLAYER_BLUE_SHOULD_HAVE_MAX_50_CHARACTERS(IS_STRING_MAX_50_CHARS, createGameRequestDto -> createGameRequestDto.playerBlueName(), "Player Blue name should be max 50 chars"),
    PLAYER_BLUE_SHOULD_HAVE_ONLY_LATIN_CHARACTERS_AND_NUMBERS(IS_STRING_NO_SPECIAL_CHARS, createGameRequestDto -> createGameRequestDto.playerBlueName(), "Player Blue name should not contain special characters"),
    PIT_COUNT_SHOULD_BE_BETWEEN_1_AND_10(IS_INT_BETWEEN_1_AND_10, createGameRequestDto -> createGameRequestDto.pitCount(), "Pit count should be between 1 and 10");

    private final Predicate validator;
    private final Function fieldMapper;
    private final String validationMessage;

    CreateGameRequestDtoValidator(Predicate validator, Function<CreateGameRequestDto, Object> fieldMapper, String validationMessage) {
        this.validator = validator;
        this.fieldMapper = fieldMapper;
        this.validationMessage = validationMessage;
    }

    public Optional<String> getInvalidField(CreateGameRequestDto createGameRequestDto) {
        return this.validator.test(fieldMapper.apply(createGameRequestDto)) ? Optional.empty() : Optional.of(this.validationMessage);
    }

}
