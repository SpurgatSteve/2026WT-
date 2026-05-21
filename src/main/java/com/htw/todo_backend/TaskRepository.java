package com.htw.todo_backend;

import org.springframework.data.repository.CrudRepository;

// CrudRepository stellt Standard-Methoden bereit:
// findAll(), findById(), save(), deleteById()
// <Task, Long> = Entity-Typ und Typ der ID
public interface TaskRepository extends CrudRepository<Task, Long> {
}