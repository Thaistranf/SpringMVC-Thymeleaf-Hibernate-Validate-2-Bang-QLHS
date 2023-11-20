package com.example.springmvcthymeleafhibernatevalidation2bangqlhs.service.student;

import com.example.springmvcthymeleafhibernatevalidation2bangqlhs.model.Student;
import com.example.springmvcthymeleafhibernatevalidation2bangqlhs.service.IGeneralService;

import java.util.List;

public interface IStudentService extends IGeneralService<Student> {
    List<Student> search(String keyword);
}
