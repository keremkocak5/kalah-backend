package com.kocak.kalah.model.entity;

import com.kocak.kalah.model.enums.PlayerSide;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "PLAYER")
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @Column(name = "GAME_ID")
    private Long gameId;

    @Column(name = "PLAYER_SIDE", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private PlayerSide playerSide;

    @Column(name = "PLAYER_NAME", nullable = false, updatable = false)
    private String playerName;

    @Column(name = "CREATION_DATE", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime creationDate;

    public Player(Long gameId, PlayerSide playerSide, String playerName) {
        this.playerName = playerName;
        this.playerSide = playerSide;
        this.gameId = gameId;
    }

    public boolean isPlayerRed() {
        return PlayerSide.RED.equals(this.playerSide);
    }

    public boolean isPlayerBlue() {
        return PlayerSide.BLUE.equals(this.playerSide);
    }
}
