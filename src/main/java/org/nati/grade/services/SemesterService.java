package org.nati.grade.services;

import java.util.List;

import javax.annotation.Resource;

import org.nati.grade.domain.Course;
import org.nati.grade.domain.Semester;
import org.nati.grade.repositories.SemesterRepository;
import org.springframework.stereotype.Service;

@Service
public class SemesterService {

	@Resource
	private SemesterRepository semesterRepository;

	public Semester createSemester(Semester semester) {
		return semesterRepository.save(semester);
	}

	public List<Semester> getSemesters() {
		List<Semester> semesters = 
			(List<Semester>) semesterRepository.findAll();
		return semesters;
	}

	public List<Semester> findByCourse(Course course) {
		List<Semester> semesters = 
			(List<Semester>) semesterRepository.findByCourse(course);
		return semesters;
	}

	public Semester findById(Integer id) {
		return semesterRepository.findById(id);
	}

	public void delete(Semester semester) {
		semesterRepository.delete(semester);
	}
}
