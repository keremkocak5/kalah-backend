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
import java.util.List;
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
    @Column(name = "ID", nullable = false, updatable = false)
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

    @Column(name = "PIT_COUNT", nullable = false, updatable = false)
    private int pitCount;

    @Column(name = "CREATION_DATE", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime creationDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "game")
    @MapKeyColumn(name = "PIT")
    private Map<Integer, Board> boards;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "game")
    private List<Player> players;

    public Game(int pitCount) {
        this.pitCount = pitCount;
        this.status = GameStatus.ACTIVE;
        this.turn = getRandomPlayer();
    }

    public int getEffectivePitCount() {
        return (this.getPitCount() * 2) + 2;
    }

    public void switchSide() {
        this.turn = this.getTurn().nextSide();
    }

    public boolean isActive() {
        return GameStatus.ACTIVE.equals(this.getStatus());
    }

    public int getOppositePit(int pit) {
        return (this.getPitCount() * 2) - pit;
    }

    public boolean isPitKalah(int pit) {
        return pit % (this.getPitCount() + 1) == this.getPitCount();
    }

    public int getKalahIndex(PlayerSide playerSide) {
        return PlayerSide.BLUE.equals(playerSide) ? this.getPitCount() + 1 : (this.getPitCount() * 2) + 1;
    }

    public int getKalahIndexOfCurrentPlayer() {
        return PlayerSide.BLUE.equals(this.getTurn()) ? this.getPitCount() + 1 : (this.getPitCount() * 2) + 1;
    }

    @Transient
    @Setter
    private Integer lastIndex;

}
