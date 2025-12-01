package com.example.student.controller;

import com.example.student.Service.UniversityService;
import com.example.student.model.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/university")
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @PostMapping("/add")
    public University addUniversity(@RequestBody University university) {
        return universityService.addUniversity(university);
    }

    @GetMapping("/all")
    public List<University> getAllUniversities() {
        return universityService.getAllUniversities();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUniversity(@PathVariable Long id) {
        universityService.deleteUniversity(id);
        return "University deleted successfully";
    }

    @GetMapping("/{id}/students")
    public List<?> getStudentsByUniversity(@PathVariable Long id) {
        University u = universityService.getUniversityById(id);
        return u != null ? u.getStudents() : List.of();
    }
}
