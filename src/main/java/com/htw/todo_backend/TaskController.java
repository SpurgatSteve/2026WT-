package com.htw.todo_backend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// @RestController: alle Methoden geben automatisch JSON zurueck
@RestController
public class TaskController {

    private final TaskService taskService;

    // Dependency Injection: Spring injected den TaskService automatisch
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // GET /tasks - Alle Tasks aus der Datenbank holen
    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getTasks() {
        List<Task> tasks = taskService.getAll();
        return ResponseEntity.ok(tasks);
    }

    // GET /tasks/{id} - Einen Task anhand der ID holen
    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        Task task = taskService.getById(id);
        if (task != null) {
            return ResponseEntity.ok(task);
        }
        return ResponseEntity.notFound().build();
    }

    // POST /tasks - Neuen Task erstellen
    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task saved = taskService.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // PUT /tasks/{id} - Task aktualisieren
    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        Task updated = taskService.update(id, task);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE /tasks/{id} - Task loeschen
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}