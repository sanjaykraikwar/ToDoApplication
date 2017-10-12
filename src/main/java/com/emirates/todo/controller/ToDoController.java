package com.emirates.todo.controller;

import java.util.List;
import java.util.UUID;

import org.apache.commons.collections.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emirates.todo.model.Task;
import com.emirates.todo.repository.TaskRepository;

@RestController
public class ToDoController {
	
	static Logger logger = LoggerFactory.getLogger(ToDoController.class);

	@Autowired	
	TaskRepository taskRepository;
	
	@CrossOrigin(origins = "*")
	@PostMapping(value = "/tasks", headers = "Accept=application/json", produces = "application/json")
	
		public ResponseEntity<Task> addTask(@RequestBody Task task) {

		logger.info("Inside Add Task Service ");

		task.setId(UUID.randomUUID().toString());

		Task newTask = taskRepository.save(task);

		logger.info("New Task has been Created");

		return new ResponseEntity(newTask, HttpStatus.CREATED);
	}
	@GetMapping(value = "/tasks/all", headers = "Accept=application/json", produces = "application/json")
	@CrossOrigin(origins = "*")
	public ResponseEntity<List<Task>> getAllTasks() {

		logger.info("Inside Get All Task Service ");

		Iterable<Task> tasks = taskRepository.findAll();

		if (tasks == null) {
			logger.info("task not found");
			return new ResponseEntity(HttpStatus.NOT_FOUND);

		} else {
			logger.info("all Tasks retrieved");

			List<Task> listTasks = IteratorUtils.toList(tasks.iterator());

			return new ResponseEntity(listTasks, HttpStatus.OK);
		}

	}

	// ------------------- Update a Task------------------------------
	
	@CrossOrigin(origins = "*")
	@PutMapping(value = "/tasks/{id}", headers = "Accept=application/json", produces = "application/json")
	public ResponseEntity<Task> updateTaskStatus(@PathVariable("id") String id) {

		logger.info("Inside Update Task Status Service ");


		 Task newTask = taskRepository.findOne(id);
			
		 newTask.setStatus("Approved");
		 newTask = taskRepository.save(newTask);
		

		logger.info("Task Status Successfully Updated ");

		return new ResponseEntity(newTask, HttpStatus.OK);
	}

	// ------------------- Delete a Task---------------------------
	
	@CrossOrigin(origins = "*")
	@DeleteMapping("/tasks/{id}")
	public ResponseEntity<Task> deleteTask(@PathVariable("id") String id) {
		logger.info("Inside Delete Task Service ");

		taskRepository.delete(id);
		logger.info("Task Successfully Deleted");
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	

}
