package com.softsmith.eduManage.Service;

import java.util.Optional;

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

public StudentEntity retriveStudent(Long id) {
	 Optional<StudentEntity> studentResponse =studentRepository.findById(id);
	 if(studentResponse.isPresent()) {
		 return studentResponse.get();
	 }
	return null;
}
}
