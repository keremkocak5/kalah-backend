package com.kocak.kalah.model.entity;

import com.kocak.kalah.model.enums.GameStatus;
import com.kocak.kalah.model.enums.PlayerSide;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Map;

import static com.kocak.kalah.util.Util.getRandomPlayer;

@Entity
@Table(name = "GAME")
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    @Setter
    private GameStatus status;

    @Column(name = "TURN", nullable = false)
    @Enumerated(EnumType.STRING)
    private PlayerSide turn;

    @Column(name = "WINNER")
    @Enumerated(EnumType.STRING)
    @Setter
    private PlayerSide winner;

    @Column(name = "PIT_COUNT", nullable = false)
    private int pitCount;

    @Column(name = "AGAINST_COMPUTER", nullable = false)
    private boolean againstComputer;

    @Column(name = "CREATION_DATE", nullable = false)
    @CreationTimestamp
    private LocalDateTime creationDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "game")
    @MapKeyColumn(name = "PIT")
    private Map<Integer, Board> boards;

    public Game(int pitCount, boolean againstComputer) {
        this.againstComputer = againstComputer;
        this.pitCount = pitCount;
        this.status = GameStatus.ACTIVE;
        this.turn = getRandomPlayer();
    }

    public int getEffectivePitCount() {
        return (this.getPitCount() * 2) + 2;
    }

    public void switchSide() {
        this.turn = this.turn.nextSide();
    }

    public boolean isActive() {
        return GameStatus.ACTIVE.equals(this.status);
    }

    public int getOppositePit(int pit) {
        return (pit + this.pitCount)%this.getEffectivePitCount();
    }

}
