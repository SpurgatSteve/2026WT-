package com.htw.todo_backend;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

// @Service: markiert diese Klasse als Service-Klasse
// Enthaelt die Geschaeftslogik zwischen Controller und Repository
@Service
public class TaskService {

    // Repository wird von Spring automatisch injected (Dependency Injection)
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Alle Tasks aus der Datenbank holen
    public List<Task> getAll() {
        List<Task> tasks = new ArrayList<>();
        taskRepository.findAll().forEach(tasks::add);
        return tasks;
    }

    // Einen Task anhand der ID holen
    public Task getById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    // Neuen Task speichern
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    // Task aktualisieren
    public Task update(Long id, Task updatedTask) {
        Task existing = taskRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setTitle(updatedTask.getTitle());
            existing.setDescription(updatedTask.getDescription());
            existing.setCompleted(updatedTask.isCompleted());
            return taskRepository.save(existing);
        }
        return null;
    }

    // Task loeschen
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}