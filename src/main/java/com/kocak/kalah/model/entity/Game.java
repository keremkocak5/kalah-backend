package com.kocak.kalah.model.entity;

import com.kocak.kalah.enums.GameStatus;
import com.kocak.kalah.enums.PlayerSide;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

import static com.kocak.kalah.util.Util.pickRandomPlayer;

@Entity
@Table(name = "GAME")
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    public Long id;

    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING )
    public GameStatus status;

    @Column(name = "TURN", nullable = false)
    @Enumerated(EnumType.STRING )
    public PlayerSide turn;

    @Column(name = "WINNER")
    @Enumerated(EnumType.STRING )
    public PlayerSide winner;

    @Column(name = "PIT_COUNT", nullable = false)
    public int pitCount;

    @Column(name = "AGAINST_COMPUTER", nullable = false)
    public boolean againstComputer;

    @Column(name = "CREATION_DATE", nullable = false)
    @CreationTimestamp
    public LocalDateTime creationDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "game")
    @Setter
    private List<Board> boards;

    public Game(int pitCount, boolean againstComputer) {
        this.againstComputer = againstComputer;
        this.pitCount = pitCount;
        this.status = GameStatus.INITIALIZED;
        this.turn = pickRandomPlayer();
    }

}
