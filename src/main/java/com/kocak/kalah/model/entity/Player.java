package com.kocak.kalah.model.entity;

import com.kocak.kalah.enums.PlayerSide;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "PLAYER")
@Getter
@EqualsAndHashCode
@ToString
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    public Long id;

    @Column(name = "GAME_ID", nullable = false)
    public Long gameId;

    @Column(name = "PLAYER_SIDE", nullable = false)
    @Enumerated(EnumType.STRING )
    public PlayerSide playerSide;

    @Column(name = "PLAYER_NAME", nullable = false)
    public String playerName;

    @Column(name = "CREATION_DATE", nullable = false)
    @CreationTimestamp
    public LocalDateTime creationDate;

    public Player(long gameId, PlayerSide playerSide, String playerName) {
        this.playerName = playerName;
        this.playerSide = playerSide;
        this.gameId = gameId;
    }

}
