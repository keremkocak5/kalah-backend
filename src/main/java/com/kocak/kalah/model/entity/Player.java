package com.kocak.kalah.model.entity;

import com.kocak.kalah.model.enums.PlayerSide;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "PLAYER")
@Getter
@EqualsAndHashCode
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "GAME_ID", nullable = false)
    private Long gameId;

    @Column(name = "PLAYER_SIDE", nullable = false)
    @Enumerated(EnumType.STRING)
    private PlayerSide playerSide;

    @Column(name = "PLAYER_NAME", nullable = false)
    private String playerName;

    @Column(name = "CREATION_DATE", nullable = false)
    @CreationTimestamp
    private LocalDateTime creationDate;

    public Player(long gameId, PlayerSide playerSide, String playerName) {
        this.playerName = playerName;
        this.playerSide = playerSide;
        this.gameId = gameId;
    }

}
