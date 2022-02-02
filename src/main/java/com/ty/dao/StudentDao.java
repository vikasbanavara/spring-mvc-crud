package com.ty.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.dto.Student;

@Repository
public class StudentDao {
	
	@Autowired
	private EntityManager entityManager;
	private EntityTransaction entityTransaction;
	
	public void saveStudent(Student student) {
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(student);
		entityTransaction.commit();
	}
	
	public Student getStudentById(int id) {
		return entityManager.find(Student.class, id);
	}
	
	public void updateStudent(Student student) {
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.merge(student);
		entityTransaction.commit();
	}
	
	public boolean deleteStudent(int id) {
		Student student = entityManager.find(Student.class, id);
		if(student != null) {
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.remove(student);
			entityTransaction.commit();
			return true;
		} else {
			return false;
		}
	}
	
	public List<Student> getAllStudent() {
		Query query = entityManager.createQuery("SELECT s FROM Student s");
		return query.getResultList();
	}
}
