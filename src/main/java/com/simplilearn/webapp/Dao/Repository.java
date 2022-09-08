package com.simplilearn.webapp.Dao;





import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.simplilearn.webapp.model.Admin;

@SuppressWarnings("deprecation")
public class Repository {

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	
	public boolean forgotPassword(String email,String password) {
		Session session=hibernateTemplate.getSessionFactory().openSession();
		Transaction tx=session.beginTransaction();  
		@SuppressWarnings("unchecked")
		Query q=session.createQuery("update Admin set password=:p where email=:e");  
		q.setParameter("e",email);  
		q.setParameter("p",password);  
		  
		int status=q.executeUpdate();  
		System.out.println(status);  
		tx.commit();  
		return status>0?true:false;
	}
}