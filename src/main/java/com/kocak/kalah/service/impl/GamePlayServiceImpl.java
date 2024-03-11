package com.kocak.kalah.service.impl;

import com.kocak.kalah.model.dto.incoming.MakeMoveRequestDto;
import com.kocak.kalah.model.dto.outgoing.BoardResponseDto;
import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.repository.GameRepository;
import com.kocak.kalah.rule.impl.Sow;
import com.kocak.kalah.service.GamePlayService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GamePlayServiceImpl implements GamePlayService {

    private final GameRepository gameRepository;
    private final Sow makeMove;

    @Override
    @Transactional
    public BoardResponseDto makeMove(MakeMoveRequestDto makeMoveDto) {
        // gelen talebi valide et: oyun var mi?
        // oyun varsa, talep edilen pit numarasi kisiye ozel mi? kisiye ozelse, dolu mu? oyun statusu nedir
        // oyna.
        // kontrolleri yap, ucluyu calistir
        // oyuncu degismesi gerekiyorsa degistir
        // oyun bitti mi diye kontrol et. kazanma varsa kazanma operasyonu
        // board geri dondur

        Optional<Game> game = gameRepository.findById(makeMoveDto.gameId());
        makeMove.apply(game.get(), makeMoveDto.pit());
        return null;
    }
}
