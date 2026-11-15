package com.softsmith.eduManage.DTO;

import java.time.LocalDateTime;

public class StudentResponseDto {
	private int id;
	private String name;
	private int age;
	private long rollNo;
	private String email;
	private String course;
	private String message;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createAt) {
		this.createdAt = createAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public long getRollNo() {
		return rollNo;
	}
	public void setRollNo(long rollNo) {
		this.rollNo = rollNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
