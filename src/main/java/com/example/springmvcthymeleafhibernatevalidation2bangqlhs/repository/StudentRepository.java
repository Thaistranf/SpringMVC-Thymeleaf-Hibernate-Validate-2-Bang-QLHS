package com.example.springmvcthymeleafhibernatevalidation2bangqlhs.repository;

import com.example.springmvcthymeleafhibernatevalidation2bangqlhs.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findStudentByNameContaining(String keyword);
}
