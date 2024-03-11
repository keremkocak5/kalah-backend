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
public class Board implements Comparable<Board> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    public Long id;

    @JoinColumn(name = "GAME_ID", referencedColumnName = "ID", nullable = false, unique = true)
    @ManyToOne()
    public Game game;

    @Column(name = "PIT", nullable = false)
    public int pit;

    @Column(name = "PLAYER_SIDE", nullable = false)
    @Enumerated(EnumType.STRING )
    public PlayerSide playerSide;

    @Column(name = "TOKEN_COUNT", nullable = false)
    public int tokenCount;

    @Column(name = "KALAH", nullable = false)
    public boolean kalah;

    @Column(name = "CREATION_DATE", nullable = false)
    @CreationTimestamp
    public LocalDateTime creationDate;

    @Column(name = "UPDATE_DATE", nullable = false)
    @UpdateTimestamp
    public LocalDateTime updateDate;

    public Board(Game game, short pit, PlayerSide playerSide, int tokenCount, boolean kalah) {
        this.game = game;
        this.pit = pit;
        this.playerSide = playerSide;
        this.tokenCount = tokenCount;
        this.kalah = kalah;
    }

    @Override
    public int compareTo(Board o) {
        return o.pit > this.pit ? 1 : -1;
    }
}
