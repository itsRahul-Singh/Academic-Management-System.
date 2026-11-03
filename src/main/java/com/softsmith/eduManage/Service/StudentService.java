package com.softsmith.eduManage.Service;

import org.springframework.stereotype.Service;

import Entity.StudentEntity;
import Repository.StudentRepository;

@Service
public class StudentService {
	
	private StudentRepository studentRepository;
	
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
public StudentEntity createStudent(StudentEntity studentEntity) {
	StudentEntity studentResp = studentRepository.save(studentEntity);
	return studentResp; 
			}
}
