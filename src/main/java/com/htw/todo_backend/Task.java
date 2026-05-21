package com.htw.todo_backend;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// @Entity: markiert diese Klasse als Datenbank-Tabelle
// JPA/Hibernate erzeugt automatisch eine Tabelle "task" in PostgreSQL
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor  // Leerer Konstruktor wird von Hibernate benoetigt
public class Task {

    // @Id: markiert dieses Feld als Primaerschluessel
    // @GeneratedValue: ID wird automatisch hochgezaehlt (1, 2, 3, ...)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private boolean completed;
}