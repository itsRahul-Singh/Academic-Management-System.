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
	
public StudentEntity createStudent(StudentEntity studentRequest) {
	studentRequest.setDeleted(false);
	StudentEntity studentResponse = studentRepository.save(studentRequest);
	return studentResponse; 
			}

public StudentEntity retriveStudent(Long id) {
	 Optional<StudentEntity> studentResponse =studentRepository.findByIdAndDeletedIsFalse(id);
	 if(studentResponse.isPresent()) {
		 return studentResponse.get();
	 }
	return null;
}

public List<StudentEntity> retriveAllStudent() {
  List<StudentEntity> studentList= studentRepository.findByDeletedIsFalse();
	return studentList;
}

public StudentEntity updateStudent(Long id, StudentEntity studentReq) {
	Optional<StudentEntity> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id);
	
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

public Boolean deleteStudentSoftly(Long id) {
	Optional<StudentEntity> existingStudent =studentRepository.findByIdAndDeletedIsFalse(id);
	
	if(existingStudent.isEmpty()) {
		return false;
	}
	
	StudentEntity studentToSet = existingStudent.get();
	studentToSet.setDeleted(true);
	studentRepository.save(studentToSet);
	
	return true;
	
}

}
