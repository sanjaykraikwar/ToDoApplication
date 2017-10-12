package com.emirates.todo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.emirates.todo.model.Task;

public interface TaskRepository extends MongoRepository<Task,String> {

}
