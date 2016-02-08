package com.rideable.database.persistence;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.JDBCConnectionException;

import com.rideable.database.models.Ad;
import com.rideable.database.models.User;

import org.hibernate.Query;
import org.hibernate.SQLQuery;



public class UserManager extends HibernateUtil {

	private static String USER_TABLE_NAME = "USER";
	private static String USER_CLASS_NAME = "User";
	private static final String DROP_TABLE_SQL = "drop table "
			+ USER_TABLE_NAME + ";";
	private static final String CREATE_TABLE_SQL = "create table if not exists " + USER_TABLE_NAME + "(id integer primary key auto_increment,"
			+ "FIRST_NAME varchar(100), LAST_NAME varchar(100), EMAIL_ADDRESS varchar(100), PASSWORD varchar(100), REG_ID text);";
	
	private static UserManager manager;
	
	private UserManager(){}
	
	public static UserManager getDefault(){
		if(manager == null){
			manager = new UserManager();
		}
		return manager;
	}
	
	public boolean executeCreateTable() {

	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	session.beginTransaction();
        SQLQuery query = session.createSQLQuery(CREATE_TABLE_SQL);
        query.executeUpdate();
        session.getTransaction().commit();
        return true;
	}
	
	public boolean executeDropTable() {

	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	session.beginTransaction();
        SQLQuery query = session.createSQLQuery(DROP_TABLE_SQL);
        query.executeUpdate();
        session.getTransaction().commit();
        return true;
	}
	
	public synchronized User addUser(User aUser){
		
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.save(aUser);
			session.getTransaction().commit();
		}catch(HibernateException e){
			session.getTransaction().rollback();
		}
		
		return aUser;
	}
	
	public synchronized User getUser(Integer id){
		
		Session session = null;
		User aUser = null;
		try{
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			aUser = (User) session.load(User.class, id);
			session.getTransaction().commit();
		}catch(HibernateException e){
			session.getTransaction().rollback();
		}
		return aUser;
			
	}
	
	public synchronized User getUser(String email){
		Session session = null;
		User aUser = null;
		try{
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = (Query)session.createQuery("From User as user where user.userEmail = :email");
			query.setParameter("email",  email);
			aUser = (User)query.uniqueResult();
			session.getTransaction().commit();
		}catch(HibernateException e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return aUser;
	}
	
	public synchronized User updateUser(User aUser){
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.update(aUser);
			session.getTransaction().commit();
		}catch(HibernateException e){
			session.getTransaction().rollback();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
		}
		
		return aUser;
	}
	
	public synchronized User deleteUser(Integer id){
		
		Session session = null;
		User aUser = null;
		
		try{
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			aUser = (User) session.load(User.class, id);
			if(aUser != null){
				session.delete(aUser);
			}
			session.getTransaction().commit();
		}catch(HibernateException e){
			session.getTransaction().rollback();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
		}
		return aUser;
	}
	
	public synchronized List<User> listUsers(){
		Session session = null;
		List<User> users = null;
		try{
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			users = (List<User>)session.createQuery("From User").list();
			session.getTransaction().commit();
		}catch(HibernateException e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return users;
	}
	
	
	
}
