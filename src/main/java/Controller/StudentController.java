package Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softsmith.eduManage.Service.StudentService;

import Entity.StudentEntity;

@RestController
@RequestMapping("/students")
public class StudentController {
	private StudentService studentService;
	
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@PostMapping
	public ResponseEntity<StudentEntity> createStudent(@RequestBody StudentEntity studentEntity) {
		StudentEntity savedResponse = (StudentEntity) studentService.createStudent(studentEntity);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(savedResponse);
	}
}
