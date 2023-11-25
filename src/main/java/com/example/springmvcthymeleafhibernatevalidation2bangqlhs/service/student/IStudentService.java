package com.example.springmvcthymeleafhibernatevalidation2bangqlhs.service.student;

import com.example.springmvcthymeleafhibernatevalidation2bangqlhs.model.student.Student;
import com.example.springmvcthymeleafhibernatevalidation2bangqlhs.service.IGeneralService;

import java.util.List;

public interface IStudentService extends IGeneralService<Student> {
    List<Student> search(String keyword);

//    @Query(value = "SELECT * \n" +
//            "FROM class c join student s on c.id = s.classroom_id  \n" +
//            " join student_subject ss on s.id = ss.student_id\n" +
//            " join subject sb on ss.subject_id = sb.id", nativeQuery = true)
//    List<Student> findAllStudent();
}
