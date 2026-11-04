package Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/get/{id}")
	public ResponseEntity<StudentEntity> getStudent(@PathVariable Long id){
		StudentEntity studentResponse =studentService.retriveStudent(id);
		
		if(studentResponse == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(studentResponse);
	}
}
