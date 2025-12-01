package com.example.student.Service;


import com.example.student.Repository.UniversityRepository;
import com.example.student.model.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UniversityService {

    @Autowired
    private UniversityRepository universityRepository;



    public University addUniversity(University university) {
        return universityRepository.save(university);
    }

    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }

    public void deleteUniversity(Long id) {
        universityRepository.deleteById(id);
    }

    public University getUniversityById(Long id) {
        return universityRepository.findById(id).orElse(null);
    }
}
