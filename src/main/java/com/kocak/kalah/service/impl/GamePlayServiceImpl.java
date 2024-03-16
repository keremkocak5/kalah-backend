package com.kocak.kalah.service.impl;

import com.kocak.kalah.model.dto.outgoing.BoardHeaderResponseDto;
import com.kocak.kalah.model.dto.outgoing.BoardResponseDto;
import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.repository.BoardRepository;
import com.kocak.kalah.repository.GameRepository;
import com.kocak.kalah.rule.Ruleable;
import com.kocak.kalah.rule.impl.Rule1UsersTurn;
import com.kocak.kalah.rule2.impl.Collect;
import com.kocak.kalah.rule2.Ruleable2;
import com.kocak.kalah.rule2.impl.Sow;
import com.kocak.kalah.rule2.impl.SwitchCase;
import com.kocak.kalah.service.GamePlayService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GamePlayServiceImpl implements GamePlayService {

    private final GameRepository gameRepository;
    private final BoardRepository boardRepository;

    private final Rule1UsersTurn AUsersTurn;

    @Override
    @Transactional
    public BoardHeaderResponseDto makeMove(long gameId, short pit) {
        // gelen talebi valide et: oyun var mi?
        // oyun varsa, talep edilen pit numarasi kisiye ozel mi? kisiye ozelse, dolu mu? oyun statusu nedir
        // oyna.
        // kontrolleri yap, ucluyu calistir
        // oyuncu degismesi gerekiyorsa degistir
        // oyun bitti mi diye kontrol et. kazanma varsa kazanma operasyonu
        // board geri dondur
        Optional<Game> game = gameRepository.findById(gameId);
        //makeMove.apply(game.get(), pit);
        //switchSide.apply(game.get(), pit);
        Ruleable ruleable = AUsersTurn;
        while (ruleable != null) {
            ruleable = ruleable.applyRule(game.get(), new Integer(pit));
        }
        /*
        List<Game> rules = List.of(switchCase, collect, sow)
                .stream()
                .sorted(collectors)
                .filter(ruleable -> ruleable.isRuleApplicable(game.get(), new Integer(pit)))
                .sorted(collectors2)
                .map(ruleable -> ruleable.applyRule(game.get(), new Integer(pit)))
                .collect(Collectors.toList());
        System.out.println(game.get().getTurn());*/
        return new BoardHeaderResponseDto(game.get().getBoards().stream().map(board -> new BoardResponseDto( // buraya mapper
                board.getId(),
                board.getPit(),
                board.getTokenCount(),
                board.getPlayerSide(),
                board.isKalah()
        )).collect(Collectors.toUnmodifiableList()), game.get().getTurn());
    }

    Comparator<Ruleable2> collectors = Comparator.comparing(ruleable -> ruleable.getRuleOrder());
    Comparator<Ruleable2> collectors2 = Comparator.comparing(ruleable -> ruleable.getRuleOrder2());

    @Override
    public BoardHeaderResponseDto getBoard(long gameId) {


        try {
            return new BoardHeaderResponseDto(boardRepository.findByGameId(gameId).stream().map(board -> new BoardResponseDto(
                    board.getId(),
                    board.getPit(),
                    board.getTokenCount(),
                    board.getPlayerSide(),
                    board.isKalah()
            )).collect(Collectors.toUnmodifiableList()), boardRepository.findByGameId(gameId).get(0).getGame().getTurn()); // kerem!!!!!!! dikkat
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            // kerem
            return null;
        }
    }
}
