package com.kocak.kalah.model.entity;

import com.kocak.kalah.enums.PlayerSide;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "BOARD")
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Board /*implements Comparable<Board>*/ {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @JoinColumn(name = "GAME_ID", referencedColumnName = "ID", nullable = false, unique = true)
    @ManyToOne()
    private Game game;

    @Column(name = "PIT", nullable = false)
    private int pit;

    @Column(name = "PLAYER_SIDE", nullable = false)
    @Enumerated(EnumType.STRING )
    private PlayerSide playerSide;

    @Column(name = "TOKEN_COUNT", nullable = false)
    private int tokenCount;

    @Column(name = "KALAH", nullable = false)
    private boolean kalah;

    @Column(name = "CREATION_DATE", nullable = false)
    @CreationTimestamp
    private LocalDateTime creationDate;

    @Column(name = "UPDATE_DATE", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateDate;

    public Board(Game game, short pit, PlayerSide playerSide, int tokenCount, boolean kalah) {
        this.game = game;
        this.pit = pit;
        this.playerSide = playerSide;
        this.tokenCount = tokenCount;
        this.kalah = kalah;
    }

    public void incrementTokenCount() {
        this.tokenCount++;
    }

    public void resetTokenCount() {
        this.tokenCount = 0;
    }
/*
    @Override
    public int compareTo(Board o) {
        return o.pit > this.pit ? 1 : -1;
    }*/
}
