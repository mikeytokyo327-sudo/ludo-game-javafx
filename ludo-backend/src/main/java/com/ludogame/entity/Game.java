package com.ludogame.entity;

import com.ludogame.entity.enums.GameStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

/**
 * Game Entity
 * Demonstrates Inheritance from BaseEntity
 * Demonstrates Encapsulation and relationships
 */
@Entity
@Table(name = "games")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Game extends BaseEntity {

    @Column(nullable = false)
    private String gameName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GameStatus status;

    @Min(value = 2, message = "Game must have at least 2 players")
    @Column(nullable = false)
    private Integer totalPlayers;

    @Column(nullable = false)
    private Integer currentPlayers = 0;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "game_players",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> players = new HashSet<>();

    public void addPlayer(User player) {
        if (this.players.size() < this.totalPlayers) {
            this.players.add(player);
            this.currentPlayers = this.players.size();
        }
    }

    public void removePlayer(User player) {
        this.players.remove(player);
        this.currentPlayers = this.players.size();
    }

    public boolean isFull() {
        return this.currentPlayers >= this.totalPlayers;
    }
}