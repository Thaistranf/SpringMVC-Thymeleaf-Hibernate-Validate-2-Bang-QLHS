package com.example.springmvcthymeleafhibernatevalidation2bangqlhs.service.subject;

import com.example.springmvcthymeleafhibernatevalidation2bangqlhs.model.Subject;
import com.example.springmvcthymeleafhibernatevalidation2bangqlhs.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.Optional;
@Service
@Transactional
public class SubjectService implements ISubjectService{
    @Autowired
    SubjectRepository subjectRepository;
    @Override
    public Iterable<Subject> findAll() {
        return subjectRepository.findAll();
    }

    @Override
    public void save(Subject subject) {
        subjectRepository.save(subject);
    }

    @Override
    public Optional<Subject> findById(Long id) {
        return subjectRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        subjectRepository.deleteById(id);
    }
}
