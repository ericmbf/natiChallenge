package org.nati.grade.services;

import java.util.List;

import javax.annotation.Resource;

import org.nati.grade.domain.Course;
import org.nati.grade.repositories.CourseRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

	@Resource
	private CourseRepository courseRepository;

	public Course createCourse(Course course) {
		return courseRepository.save(course);
	}

	public List<Course> getCourses() {
		List<Course> courses = (List<Course>) courseRepository.findAll();
		return courses;
	}

	public Course findById(Integer id) {
		return courseRepository.findById(id);
	}

	public void delete(Course course) {
		courseRepository.delete(course);
	}
}
