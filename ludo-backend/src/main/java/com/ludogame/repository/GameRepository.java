package com.ludogame.repository;

import com.ludogame.entity.Game;
import com.ludogame.entity.enums.GameStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Game Repository
 */
@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByStatus(GameStatus status);
    List<Game> findByCreatorId(Long creatorId);
}