package com.emirates.todo.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "task")
public class Task {
	
	  private String id;

	  private String taskName;

	  private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	


}
