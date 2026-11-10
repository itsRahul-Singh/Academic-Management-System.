package com.softsmith.eduManage.Service;

import java.util.Optional;
import java.util.List;

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

public List<StudentEntity> retriveAllStudent() {
  List<StudentEntity>studentList= studentRepository.findAll();
	return studentList;
}

public StudentEntity updateStudent(Long id, StudentEntity studentReq) {
	Optional<StudentEntity> existingStudent = studentRepository.findById(id);
	
	if(existingStudent.isEmpty()) {
		return null;
	}
	
	StudentEntity studentToUpdate = existingStudent.get();
	studentToUpdate.setName(studentReq.getName());
	studentToUpdate.setRollNo(studentReq.getRollNo());
	studentToUpdate.setAge(studentReq.getAge());
	studentToUpdate.setEmail(studentReq.getEmail());
	studentToUpdate.setCourse(studentReq.getCourse());
	
	return studentRepository.save(studentToUpdate);
}

public Boolean deleteStudent(Long id) {
	Boolean isExists = studentRepository.existsById(id);
	
	if(!isExists) return false;
	
	studentRepository.deleteById(id);
	return true;
}

}
