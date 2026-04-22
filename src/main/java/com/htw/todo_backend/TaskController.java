package com.htw.todo_backend;

import org.springframework.http.ResponseEntity; //repräsentiert eine HTTP-Antwort (Status + Daten)
import org.springframework.web.bind.annotation.GetMapping;  //definiert eine HTTP GET-Route
import org.springframework.web.bind.annotation.RestController; //markiert diese Klasse als REST-Controller (Rückgabewert = JSON)

import java.util.List;

@RestController
public class TaskController {
    @GetMapping("/tasks")  //definiert die Route: GET http.. aufruft wird diese Methode ausgeführt
    public ResponseEntity<List<Task>> getTasks() {
        List<Task> tasks = List.of(
                new Task("Aufgabe 1", "Erste Aufgabe", false),
                new Task("Aufgabe 2", "Zweite Aufgabe", true)
        );
        return ResponseEntity.ok(tasks);
    }
}
