package com.example.springmvcthymeleafhibernatevalidation2bangqlhs.service.classroom;

import com.example.springmvcthymeleafhibernatevalidation2bangqlhs.model.Classroom;
import com.example.springmvcthymeleafhibernatevalidation2bangqlhs.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
@Service
@Transactional
public class ClassroomService implements IClassroomService {
    @Autowired
    ClassroomRepository classroomRepository;
    @Override
    public Iterable<Classroom> findAll() {
        return classroomRepository.findAll();
    }

    @Override
    public void save(Classroom classroom) {
        classroomRepository.save(classroom);
    }

    @Override
    public Optional<Classroom> findById(Long id) {
        return classroomRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        classroomRepository.deleteById(id);
    }
}
