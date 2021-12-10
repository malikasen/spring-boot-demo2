package com.example.demo;

import com.example.demo.student.Student;
import com.example.demo.student.StudentRepository;
import com.example.demo.student.StudentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.time.Month.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Demo2ApplicationTests {

	@Autowired
	private StudentRepository studentRepository;

	private StudentService underTest;

	@BeforeEach
	void setUp() {
		underTest = new StudentService(studentRepository);
	}

	@AfterEach
	void tearDown() {
		studentRepository.deleteAll();
	}

	@Test
	void getStudents() {
		//given
		Student zemfira = new Student(
				"Zemfira Ramazanova",
				"zemfira123@gmail.com",
				LocalDate.of(1980, JANUARY, 5)
		);
		Student diana = new Student(
				"Diana Arbenina",
				"n_snaipery@gmail.com",
				LocalDate.of(1981, JULY, 27)
		);
		studentRepository.saveAll(Arrays.asList(zemfira, diana));

		//when
		List<Student> students = underTest.getStudents();

		//then
		assertEquals(2, students.size());
	}

	@Test
	void addNewStudent() {
		//when
		Student malika = new Student(
				"Malika Kassen-Lao",
				"malika@gmail.com",
				LocalDate.of(1989, OCTOBER, 19)
		);

		underTest.addNewStudent(malika);
		Optional<Student> actual= studentRepository.findStudentByEmail("malikasenova@gmail.com");
		System.out.println(actual);

		//then
		assertEquals("malikasenova@gmail.com", actual.email);
	}
}
