package fr.todooz.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import static org.hibernate.criterion.Restrictions.*;

import fr.todooz.Task;

public class TaskService {

	private SessionFactory sessionFactory;
	
	public void save(Task task) {
		Session session = sessionFactory.openSession();

	    session.save(task);

	    session.close();
	   }

	   public void delete(Long id) {
		   Session session = sessionFactory.openSession();
		   Query q = session.createQuery("delete from Task where id = :id ");
	        q.setLong("id", id);
	        q.executeUpdate();
	        session.close();
	   }

	   public List<Task> findAll() {
		   Session session = sessionFactory.openSession();
		   Query query = session.createQuery("from Task");
		   List<Task> tasks = query.list();
		   return tasks;
	   }

	   public List<Task> findByQuery(String query) {
	      // TODO
		   Session session = sessionFactory.openSession();
		   Criteria criteria = session.createCriteria(Task.class);
		   
		   criteria.add(or(ilike("title", query, MatchMode.ANYWHERE), ilike("text", query, MatchMode.ANYWHERE)));
		   List<Task> taskList = criteria.list();
		   
		   session.close();
		   
		   return taskList;
	   }

	   public int count() {
		   return findAll().size();
	   }

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	   
	   
}
