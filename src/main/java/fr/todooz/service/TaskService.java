package fr.todooz.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.joda.time.Interval;

import fr.todooz.domain.Task;

public interface TaskService {

	public void save(Task task);

	public void delete(Long id);

	public List<Task> findAll();

	public List<Task> findByQuery(String query);

	public int count();

	public SessionFactory getSessionFactory();

	public void setSessionFactory(SessionFactory sessionFactory);

	public Task findById(Long id);

	public List findByTag(String string);

	List<Task> findByInterval(Interval interval);

}