package com.kocak.kalah.service.impl;

import com.kocak.kalah.converter.DomainToViewConvertable;
import com.kocak.kalah.exception.KalahRuntimeException;
import com.kocak.kalah.model.dto.outgoing.BoardHeaderResponseDto;
import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.model.enums.KalahError;
import com.kocak.kalah.model.enums.RuleType;
import com.kocak.kalah.repository.GameRepository;
import com.kocak.kalah.rule.Rulable;
import com.kocak.kalah.service.PlayService;
import com.kocak.kalah.service.RuleFactoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlayServiceImpl implements PlayService {

    private final DomainToViewConvertable<Game, BoardHeaderResponseDto> domainToViewConvertable;
    private final GameRepository gameRepository;
    private final RuleFactoryService ruleFactoryService;

    @Override
    @Transactional
    public BoardHeaderResponseDto makeMove(long gameId, int pit) {
        try {
            Game game = findGameOrThrowException(gameId);
            runGameRuleChain(game, pit);
            return domainToViewConvertable.convertToView(game);
        } catch (KalahRuntimeException e) {
            log.info("An exception during makeMove. gameId {} and pit {} and errorCode {}.", gameId, pit, e.getKalahError().getErrorId());
            throw e;
        } catch (Exception e) {
            log.error("An unexpected exception during makeMove. GameId {} and pit {}. Error: {}", gameId, pit, e);
            throw new KalahRuntimeException(KalahError.UNKNOWN_ERROR);
        }
    }

    private void runGameRuleChain(Game game, int pit) {
        Optional<Rulable> rule = ruleFactoryService.getFirstRule();
        while (rule.isPresent()) {
            RuleType ruleType = rule.get().applyRule(game, pit);
            rule = ruleFactoryService.getNextRule(rule.get(), ruleType);
        }
    }

    private Game findGameOrThrowException(long gameId) {
        return gameRepository.findById(gameId).orElseThrow(() -> new KalahRuntimeException(KalahError.NO_SUCH_GAME_FOUND));
    }

}
