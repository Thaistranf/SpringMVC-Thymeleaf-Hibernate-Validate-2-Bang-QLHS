package com.example.springmvcthymeleafhibernatevalidation2bangqlhs.service.student;

import com.example.springmvcthymeleafhibernatevalidation2bangqlhs.model.Student;
import com.example.springmvcthymeleafhibernatevalidation2bangqlhs.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentService implements IStudentService {
    @Autowired
    StudentRepository studentRepository;
    @Override
    public Iterable<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> search(String keyword) {
        return studentRepository.findStudentByNameContaining(keyword);
    }

//    @Override
//    public List<Student> findAllStudent() {
//        return studentRepository.findStudents();
//    }


}
