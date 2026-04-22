package com.htw.todo_backend;

// Lombok-Annotationen: generieren automatisch Getter, Setter und Konstruktor
// ohne das diese Methoden manuell geschreiben werden muss

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter //generiert automatisch getId(), getTitle(), etc.
@Setter //generiert automatisch setTitle(), setDescription(), etc.
@AllArgsConstructor //generiert einen Konstruktor mit allen Feldern
public class Task {
    private String title; // Titel aufgabe
    private String description; // Beschreibung Aufgabe
    private boolean completed; // Status Aufgabe
}