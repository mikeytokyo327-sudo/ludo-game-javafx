package com.ludogame.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Role Entity
 * Demonstrates Encapsulation principle
 */
@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    public Role(String name) {
        this.name = name;
    }
}