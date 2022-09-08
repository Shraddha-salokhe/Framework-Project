package com.simplilearn.webapp.Dao;



import java.util.List;




import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.simplilearn.webapp.model.User;


@SuppressWarnings("deprecation")
public class UserRepository {
	
	
	HibernateTemplate hibernateTemplate;

	
	public UserRepository() {
		super();
	}

	
	public UserRepository(HibernateTemplate hibernateTemplate) {
		super();
		this.hibernateTemplate = hibernateTemplate;
	}


	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public List<User> getAllUsers(){
		
		List<User> userList=hibernateTemplate.loadAll(User.class);
		return userList;
		
	}
	
	
	public List<User> searchUser(String email){
		
		Session session=hibernateTemplate.getSessionFactory().openSession();
		Transaction transaction=session.beginTransaction();
		
		String hql = "from User where userEmail=:email";
		Query<User> query = session.createQuery(hql,User.class);
		query.setParameter("email", email);
		List<User> user = query.getResultList();
		transaction.commit();
		session.close();
		
		return user;
	}
	
}