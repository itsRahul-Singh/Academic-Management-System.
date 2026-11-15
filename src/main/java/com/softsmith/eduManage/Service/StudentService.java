package com.softsmith.eduManage.Service;

import java.util.Optional;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.softsmith.eduManage.DTO.StudentRequestDto;
import com.softsmith.eduManage.DTO.StudentResponseDto;

import Entity.StudentEntity;
import Repository.StudentRepository;

@Service
public class StudentService {
	
	private StudentRepository studentRepository;
	
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
public StudentResponseDto createStudent(StudentRequestDto studentRequestDto) {
	StudentEntity studentEntity = mapToEntity(studentRequestDto);
	
	studentEntity.setCreatedAt(LocalDateTime.now());
	studentEntity.setUpdatedAt(LocalDateTime.now());
	
	StudentEntity studentResponse = studentRepository.save(studentEntity);
	
	return mapToDto(studentResponse);
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

private StudentEntity mapToEntity(StudentRequestDto studentRequestDto) {
	StudentEntity studentEntity = new StudentEntity();
	studentEntity.setName(studentRequestDto.getName());
	studentEntity.setAge(studentRequestDto.getAge());
	studentEntity.setEmail(studentRequestDto.getEmail());
	studentEntity.setRollNo(studentRequestDto.getRollNo());
	studentEntity.setCourse(studentRequestDto.getCourse());
	
	studentEntity.setDeleted(false);
	return studentEntity;
}

private StudentResponseDto mapToDto(StudentEntity studentEntity) {
	StudentResponseDto studentResponseDto = new StudentResponseDto();
	studentResponseDto.setName(studentEntity.getName());
	studentResponseDto.setAge(studentEntity.getAge());
	studentResponseDto.setEmail(studentEntity.getEmail());
	studentResponseDto.setRollNo(studentEntity.getRollNo());
	studentResponseDto.setCourse(studentEntity.getCourse());
	studentResponseDto.setId(studentEntity.getId());
	studentResponseDto.setMessage("Student Created Successfully");
	studentResponseDto.setCreatedAt(studentEntity.getCreatedAt());
	studentResponseDto.setUpdatedAt(studentEntity.getUpdatedAt());
	
	return studentResponseDto;
}


}
