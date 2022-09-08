package com.simplilearn.webapp.Dao;



import java.util.List;




import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.simplilearn.webapp.model.Admin;



@SuppressWarnings("deprecation")
public class AdminRepository {

	
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	
	public boolean verifyAdmin(Admin a) {
		Session session=hibernateTemplate.getSessionFactory().openSession();
		Transaction transaction=session.beginTransaction();
		
		String hql = "from Admin where email=:email and password=:password";
		Query<Admin> query = session.createQuery(hql,Admin.class);
		query.setParameter("email", a.getEmail());
		query.setParameter("password", a.getPassword());
		List<Admin> results = query.getResultList();
		transaction.commit();
		session.close();
		
		return results.size()>0;	
	}
	
}