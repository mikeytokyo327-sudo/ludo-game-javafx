package com.ludogame.entity.enums;

/**
 * Game Status Enum
 * Demonstrates Encapsulation with enumerated types
 */
public enum GameStatus {
    WAITING("Waiting for players"),
    READY("Ready to start"),
    IN_PROGRESS("Game in progress"),
    COMPLETED("Game completed"),
    CANCELLED("Game cancelled");

    private final String description;

    GameStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}