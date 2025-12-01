    package com.example.student.Repository;

    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;

    import com.example.student.model.Student;
    import java.util.List;



    public interface StudentRepository extends JpaRepository<Student, Long> {
        List<Student> findByFirstNameContainingIgnoreCase(String name);



        @Query("SELECT s FROM Student s WHERE LOWER(s.firstName) = LOWER(:name)")
        List<Student> findByFirstName(@Param("name") String name);

        @Query("SELECT s FROM Student s WHERE s.id = :id")
        Student findByStudentId(@Param("id") Long id);

        @Query("SELECT s FROM Student s WHERE LOWER(s.university.name) = LOWER(:univName)")
        List<Student> findByUniversity(@Param("univName") String univName);
    }