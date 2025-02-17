package com.kocak.kalah.model.entity;

import com.kocak.kalah.model.enums.PlayerSide;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "BOARD")
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @JoinColumn(name = "GAME_ID", referencedColumnName = "ID", nullable = false, updatable = false)
    @ManyToOne()
    private Game game;

    @Column(name = "PIT", nullable = false, updatable = false)
    private int pit;

    @Column(name = "PLAYER_SIDE", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private PlayerSide playerSide;

    @Column(name = "TOKEN_COUNT", nullable = false)
    private int tokenCount;

    @Column(name = "KALAH", nullable = false, updatable = false)
    private boolean kalah;

    @Column(name = "CREATION_DATE", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime creationDate;

    @Column(name = "UPDATE_DATE", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateDate;

    public Board(Game game, int pit, PlayerSide playerSide, int tokenCount, boolean kalah) {
        this.game = game;
        this.pit = pit;
        this.playerSide = playerSide;
        this.tokenCount = tokenCount;
        this.kalah = kalah;
    }

    public void incrementTokenCount() {
        this.tokenCount++;
    }

    public void incrementTokenCount(int tokenCount) {
        this.tokenCount += tokenCount;
    }

    public void resetTokenCount() {
        this.tokenCount = 0;
    }

}
