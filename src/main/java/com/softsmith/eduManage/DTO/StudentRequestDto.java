package com.softsmith.eduManage.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class StudentRequestDto {
	
	@NotBlank(message = "name should be valid")
	@Size(min =2, max = 50, message = "student name must be within 2 to 50 character")
	private String name;
	
	@Min(value = 18)
	private Integer  age;
	
	@NotBlank(message ="RollNo is required")
	private Integer rollNo;
	
	@Email(message = "Student email must be valid")
	private String email;
	
	@NotBlank
	private String course;
	
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
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
	public Integer getRollNo() {
		return rollNo;
	}
	public void setRollNo(Integer rollNo) {
		this.rollNo = rollNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

}
