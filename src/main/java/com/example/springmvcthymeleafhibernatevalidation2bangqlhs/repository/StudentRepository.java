package com.example.springmvcthymeleafhibernatevalidation2bangqlhs.repository;

import com.example.springmvcthymeleafhibernatevalidation2bangqlhs.model.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findStudentByNameContaining(String keyword);

//    @Query(value = "SELECT * \n" +
//            "FROM class c join student s on c.id = s.classroom_id  \n" +
//            " join student_subject ss on s.id = ss.student_id\n" +
//            " join subject sb on ss.subject_id = sb.id", nativeQuery = true)
//    List<Student> findStudents();
}
