package com.htw.todo_backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

// Startet den kompletten Server auf einem zufälligen Port zum Testen
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TaskRepository taskRepository;

    // Vor jedem Test die Datenbank leeren
    @BeforeEach
    void setUp() {
        taskRepository.deleteAll();
    }

    // Test: GET /tasks gibt leere Liste zurueck wenn keine Tasks existieren
    @Test
    void getTasksShouldReturnEmptyList() {
        ResponseEntity<Task[]> response = restTemplate.getForEntity("/tasks", Task[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(0, response.getBody().length);
    }

    // Test: POST /tasks erstellt einen neuen Task
    @Test
    void createTaskShouldReturnCreatedTask() {
        Task newTask = new Task();
        newTask.setTitle("Test Aufgabe");
        newTask.setDescription("Test Beschreibung");
        newTask.setCompleted(false);

        ResponseEntity<Task> response = restTemplate.postForEntity("/tasks", newTask, Task.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Test Aufgabe", response.getBody().getTitle());
        assertNotNull(response.getBody().getId());
    }

    // Test: GET /tasks gibt Tasks zurueck nach dem Erstellen
    @Test
    void getTasksShouldReturnTasksAfterCreating() {
        Task task = new Task();
        task.setTitle("Aufgabe 1");
        task.setDescription("Beschreibung 1");
        task.setCompleted(false);
        restTemplate.postForEntity("/tasks", task, Task.class);

        ResponseEntity<Task[]> response = restTemplate.getForEntity("/tasks", Task[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().length);
        assertEquals("Aufgabe 1", response.getBody()[0].getTitle());
    }

    // Test: DELETE /tasks/{id} löscht einen Task
    @Test
    void deleteTaskShouldRemoveTask() {
        Task task = new Task();
        task.setTitle("Zu löschen");
        task.setDescription("Wird gelöscht");
        task.setCompleted(false);
        ResponseEntity<Task> created = restTemplate.postForEntity("/tasks", task, Task.class);
        Long id = created.getBody().getId();

        restTemplate.delete("/tasks/" + id);

        ResponseEntity<Task[]> response = restTemplate.getForEntity("/tasks", Task[].class);
        assertEquals(0, response.getBody().length);
    }

    // Test: PUT /tasks/{id} aktualisiert einen Task
    @Test
    void updateTaskShouldModifyTask() {
        Task task = new Task();
        task.setTitle("Alt");
        task.setDescription("Alte Beschreibung");
        task.setCompleted(false);
        ResponseEntity<Task> created = restTemplate.postForEntity("/tasks", task, Task.class);
        Long id = created.getBody().getId();

        Task updated = new Task();
        updated.setTitle("Neu");
        updated.setDescription("Neue Beschreibung");
        updated.setCompleted(true);
        restTemplate.put("/tasks/" + id, updated);

        ResponseEntity<Task> response = restTemplate.getForEntity("/tasks/" + id, Task.class);
        assertEquals("Neu", response.getBody().getTitle());
        assertTrue(response.getBody().isCompleted());
    }
}