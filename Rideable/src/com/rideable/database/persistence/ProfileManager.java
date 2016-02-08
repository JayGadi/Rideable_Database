package com.rideable.database.persistence;



import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.rideable.database.models.UserProfile;

import org.hibernate.SQLQuery;

public class ProfileManager extends HibernateUtil{

	private static String PROFILE_TABLE_NAME = "USER_PROFILE";
	private static String PROFILE_CLASS_NAME = "UserProfile";
	private static final String DROP_TABLE_SQL = "drop table "
			+ PROFILE_TABLE_NAME + ";";
	
	private static final String CREATE_TABLE_SQL = "create table if not exists " + PROFILE_TABLE_NAME + "(id integer primary key,"
			+ "RATING integer, USER integer NOT NULL);";

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
	
	public UserProfile addUserProfile(UserProfile aProfile){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(aProfile);
		session.getTransaction().commit();
		return aProfile;
	}
	
	public UserProfile deleteUserProfile(Integer id){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		UserProfile aProfile = (UserProfile) session.load(UserProfile.class, id);
		if(aProfile != null){
			session.delete(aProfile);
		}
		session.getTransaction().commit();
		return aProfile;
	}
	
	public UserProfile updateRatings(Integer id, int rating){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		UserProfile aProfile = (UserProfile)session.get(UserProfile.class, id);
		aProfile.setRating(rating);
		session.update(aProfile);
		session.getTransaction().commit();
		return aProfile;
	}
	public List<UserProfile> listProfiles(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<UserProfile> profiles = null;
		try{
			profiles = (List<UserProfile>)session.createQuery("From UserProfile").list();
		}catch(HibernateException e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return profiles;
	}
}
