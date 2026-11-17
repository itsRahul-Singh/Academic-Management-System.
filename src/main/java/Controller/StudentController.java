package Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softsmith.eduManage.DTO.StudentRequestDto;
import com.softsmith.eduManage.DTO.StudentResponseDto;
import com.softsmith.eduManage.DTO.UpdateStudentRequestDto;
import com.softsmith.eduManage.DTO.UpdateStudentResponseDto;
import com.softsmith.eduManage.Service.StudentService;

import Entity.StudentEntity;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentController {
	private StudentService studentService;
	
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@PostMapping
	public ResponseEntity<StudentResponseDto> createStudent(
			@Valid @RequestBody StudentRequestDto studentRequestDto) {
		StudentResponseDto createdStudent = studentService.createStudent(studentRequestDto);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(createdStudent);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<StudentResponseDto> getStudent(@PathVariable Long id){
		StudentResponseDto studentResponse =studentService.retriveStudent(id);
		
		if(studentResponse == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(studentResponse);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<StudentEntity>> getAllStudent(){
		List<StudentEntity> studentList =studentService.retriveAllStudent();
		
		if(studentList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(studentList);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<UpdateStudentResponseDto> updateStudent(@PathVariable Long id, @RequestBody UpdateStudentRequestDto studentReq){
		UpdateStudentResponseDto updatedStudent = studentService.updateStudent(id,studentReq);
		
		if(updatedStudent == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updatedStudent);		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
		Boolean isDeleted = studentService.deleteStudent(id);
		
		if(!isDeleted) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok("Record Deleted");
	}
	
	@PatchMapping("soft-delete/{id}")
	public ResponseEntity<String> deleteStudentSoftly(@PathVariable Long id){
		Boolean isDeleted = studentService.deleteStudentSoftly(id);
		
		if(!isDeleted) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok("Record Deleted");
				
	}
}
