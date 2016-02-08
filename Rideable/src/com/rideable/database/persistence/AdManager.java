package com.rideable.database.persistence;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.rideable.database.models.Ad;
import com.rideable.database.models.User;

public class AdManager extends HibernateUtil{
	
	private static String AD_TABLE_NAME = "AD";
	private static String AD_CLASS_NAME = "Ad";
	private static String AD_PASSENGER_JOIN_TABLE= "AD_PASSENGERS";
	private static final String DROP_TABLE_SQL = "drop table "
			+ AD_TABLE_NAME + ";";
	private static final String CREATE_TABLE_SQL = "create table if not exists " + AD_TABLE_NAME + "(id integer primary key auto_increment,"
			+ "D_LONG double, D_LAT double, A_LONG double, A_LAT double, D_CITY varchar(100), A_CITY varchar(100), PRICE double, PASSENGERS integer, D_Date varchar(100), USER_ID integer default NULL);";
	private static final String CREATE_AD_PASSENGER_JOIN_TABLE = "create table if not exists " + AD_PASSENGER_JOIN_TABLE +
			"(AD_ID integer, PASSENGER_ID integer);";
	
	private static AdManager manager;
	private AdManager() {}
	
	public static AdManager getDefault(){
		if (manager == null){
			manager = new AdManager();
		}
		return manager;
	}
	
	public boolean executeCreateJoinTable(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	session.beginTransaction();
        SQLQuery query = session.createSQLQuery(CREATE_AD_PASSENGER_JOIN_TABLE);
        query.executeUpdate();
        session.getTransaction().commit();
        return true;
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

	public synchronized List<Ad> getAdsWithPassenger(String email){
		Session session = null;
		List<Ad> ads = null;
		try{
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("select ad from Ad as ad join ad.ridePassengers p where p.userEmail = :email");
			query.setParameter("email", email);
			ads = (List<Ad>)query.list();
			session.getTransaction().commit();
		}catch(HibernateException e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return ads;
	}
	public synchronized Ad updateAd(Ad ad){
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.update(ad);
			session.getTransaction().commit();
		}catch(HibernateException e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return ad;
	}
	
	public synchronized List<Ad> findAdsByDCityAndACity(String dCity, String aCity){
		Session session = null;
		List<Ad> ads = null;
		try{
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("From Ad as ad where ad.departureCity = :dCity AND ad.arrivalCity = :aCity");
			query.setParameter("dCity", dCity);
			query.setParameter("aCity", aCity);
			ads = (List<Ad>)query.list();
			session.getTransaction().commit();
		}catch(HibernateException e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return ads;
	}
		
	public synchronized List<Ad> findAdsByDepartureCity(String dCity){
		Session session = null;
		List<Ad> ads = null;
		try{
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("From Ad as ad where ad.departureCity = :dCity");
			query.setParameter("dCity", dCity);
			ads = (List<Ad>)query.list();
			session.getTransaction().commit();
			
		}catch(HibernateException e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return ads;
	}
	
	public synchronized List<Ad> findAdsByArrivalCity(String aCity){
		Session session = null;
		List<Ad> ads = null;
		try{
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("From Ad as ad where ad.A_CITY = ?");
			query.setParameter(0, aCity);
			ads = (List<Ad>)query.list();
			session.getTransaction().commit();
		}catch(HibernateException e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	
		return ads;
	}
	public synchronized Ad addAd(Ad aAd){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(aAd);
		session.getTransaction().commit();
		return aAd;
	}
	
	public synchronized Ad deleteAd(Integer id){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Ad aAd = (Ad) session.load(Ad.class, id);
		if(aAd != null){
			session.delete(aAd);
		}
		session.getTransaction().commit();
		return aAd;
		
	}
	
	public synchronized Ad getAdWithId(Integer id){
		Session session = null;
		Ad aAd = null;
		try{
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			aAd = (Ad) session.load(Ad.class, id);
			session.getTransaction().commit();
		}catch(HibernateException e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return aAd;
	}
	
	public synchronized Ad getAdsWithDepartureLocation(double dLong, double dLat){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Ad ad = null;
		try{
			Query query = session.createQuery("From Ad as ad where ad.D_LONG = ? AND ad.D_LAT = ?");
			query.setParameter(0, dLong);
			query.setParameter(1, dLat);
			ad = (Ad) query.uniqueResult();
			session.getTransaction().commit();
		}catch(HibernateException e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	
		return ad;
	}
	
	public synchronized Ad getAdsWithArrivalLocation(double aLong, double aLat){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Ad ad = null;
		try{
			Query query = session.createQuery("From Ad as ad where ad.D_LONG = ? AND ad.D_LAT = ?");
			query.setParameter(0, aLong);
			query.setParameter(1, aLat);
			ad = (Ad) query.uniqueResult();
			session.getTransaction().commit();
		}catch(HibernateException e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return ad;
	}
	
	
	public synchronized List<Ad> listAds(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Ad> ads = null;
		try{
			ads = (List<Ad>)session.createQuery("From Ad").list();
			session.getTransaction().commit();
		}catch(HibernateException e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return ads;
	}
		

}
