package com.example.student.controller;

import com.example.student.Repository.StudentRepository;
import com.example.student.Repository.UniversityRepository;
import com.example.student.Service.StudentService;
import com.example.student.model.Student;
import com.example.student.model.University;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UniversityRepository universityRepository;

    @PostMapping("/add")
    public Student addStudent(@RequestBody Map<String, Object> body) {
        String first_name = (String) body.get("first_name");
        String last_name = (String) body.get("last_name");
        String email = (String) body.get("email");
        Long university_id = ((Number) body.get("university_id")).longValue();

        Student student = new Student();
        student.setFirstName(first_name);
        student.setLastName(last_name);
        student.setEmail(email);

        return studentService.addStudent(student, university_id);
    }
   

    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }
    @PutMapping("/update/{id}")
public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
    Student updated = studentService.updateStudent(id, studentDetails);
    return ResponseEntity.ok(updated);
}


    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "Student deleted successfully";
    }
    @GetMapping("/findStudUniv")
public List<Student> findStudentsByUniversity(@RequestParam String univName) {
return studentService.findStudentsByUniversity(univName);
}
@GetMapping("/findStudId/{id}")
public Student findStudentsById(@PathVariable Long id) {
return studentService.getStudentById(id);
}
@GetMapping("/findStudName/{name}")
public List<Student> findStudentsByName(@PathVariable String name) {
return studentService.findStudentsByName(name);
}
@GetMapping("/filterByCourse/{courseId}")
public ResponseEntity<List<Student>> filterStudentsByCourse(@PathVariable Long courseId) {
    List<Student> students = studentService.findStudentsByCourse(courseId);
    return ResponseEntity.ok(students);
}
}
