package org.nati.grade.services;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.nati.grade.domain.Subject;
import org.nati.grade.repositories.SubjectRepository;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {

	@Resource
	private SubjectRepository subjectRepository;

	public Subject createSubject(Subject subject) {
		return subjectRepository.save(subject);
	}

	public List<Subject> getSubjects() {
		List<Subject> subjects = (List<Subject>) subjectRepository.findAll();
		return subjects;
	}

	public Subject findById(Integer id) {
		return subjectRepository.findById(id);
	}

	public void delete(Subject subject) {
		subjectRepository.delete(subject);
	}
}
