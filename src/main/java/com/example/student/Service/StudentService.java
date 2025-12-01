package com.example.student.Service;


import com.example.student.Repository.StudentRepository;
import com.example.student.Repository.UniversityRepository;
import com.example.student.model.Student;
import com.example.student.model.University;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UniversityRepository universityRepository;

    public Student addStudent(Student student, Long universityId) {
        University university = universityRepository.findById(universityId)
                .orElseThrow(() -> new RuntimeException("University not found"));
        student.setUniversity(university);
        return studentRepository.save(student);
    }
    

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Student updateStudent(Long id, Student studentDetails) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id " + id));

        existingStudent.setFirstName(studentDetails.getFirstName());
        existingStudent.setLastName(studentDetails.getLastName());
        existingStudent.setEmail(studentDetails.getEmail());

        if (studentDetails.getUniversity() != null && studentDetails.getUniversity().getId() != null) {
            University university = universityRepository.findById(studentDetails.getUniversity().getId())
                    .orElseThrow(() -> new RuntimeException("University not found"));
            existingStudent.setUniversity(university);
        }

        return studentRepository.save(existingStudent);
    }
     public List<Student> findStudentsByUniversity(String universityName) {
        return studentRepository.findByUniversity(universityName);
    }

   

     public Student getStudentById(Long id) {
        return studentRepository.findByStudentId(id);
    }

    public List<Student> findStudentsByName(String name) {
        return studentRepository.findByFirstName(name);
    }

 public List<Student> findStudentsByCourse(Long courseId) {
        String url = "http://localhost:8000/api/enrollments/?course_id=" + courseId;
        RestTemplate restTemplate = new RestTemplate();

        try {
            // Appel vers Django
            ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
            List<Map<String, Object>> enrollments = response.getBody();

            if (enrollments == null || enrollments.isEmpty()) {
                return List.of();
            }

            // Extraire les IDs d'étudiants
            List<Long> studentIds = enrollments.stream()
                    .map(e -> ((Number) e.get("student_id")).longValue())
                    .toList();

            // Retourner les étudiants correspondants
            return studentRepository.findAllById(studentIds);

        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la communication avec le service Django : " + e.getMessage());
        }
    }
}




