package com.softsmith.eduManage.Service;

import java.util.Optional;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.softsmith.eduManage.DTO.StudentRequestDto;
import com.softsmith.eduManage.DTO.StudentResponseDto;
import com.softsmith.eduManage.DTO.UpdateStudentRequestDto;
import com.softsmith.eduManage.DTO.UpdateStudentResponseDto;

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



public StudentResponseDto retriveStudent(Long id) {
	 Optional<StudentEntity> studentResponse =studentRepository.findByIdAndDeletedIsFalse(id);
//	 if(studentResponse.isPresent()) {
//		 return mapToDto(studentResponse.get());
//	 }
//	return null;
	 return mapToDto(studentResponse.get());
}



public List<StudentEntity> retriveAllStudent() {
  List<StudentEntity> studentList= studentRepository.findByDeletedIsFalse();
	return studentList;
}



public UpdateStudentResponseDto updateStudent(Long id, UpdateStudentRequestDto studentReq) {
	Optional<StudentEntity> existingStudent = 
			studentRepository.findByIdAndDeletedIsFalse(id);
	
	if(existingStudent.isEmpty()) {
		return null;
	}
	
	StudentEntity studentToUpdate = existingStudent.get();
	studentToUpdate.setName(studentReq.getName());
	studentToUpdate.setRollNo(studentReq.getRollNo());
	studentToUpdate.setAge(studentReq.getAge());
	studentToUpdate.setUpdatedAt(LocalDateTime.now());
	studentToUpdate.setCourse(studentReq.getCourse());
	
	StudentEntity savedStudent = studentRepository.save(studentToUpdate);
	
	return mapToUpdateDto(savedStudent);
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


private UpdateStudentResponseDto mapToUpdateDto(StudentEntity savedStudent) {
	UpdateStudentResponseDto studentResponseDto = new UpdateStudentResponseDto();
	studentResponseDto.setName(savedStudent.getName());
	studentResponseDto.setAge(savedStudent.getAge());
	studentResponseDto.setEmail(savedStudent.getEmail());
	studentResponseDto.setRollNo(savedStudent.getRollNo());
	studentResponseDto.setCourse(savedStudent.getCourse());
	studentResponseDto.setUpdatedAt(savedStudent.getUpdatedAt());
	
	return studentResponseDto;
}


}
