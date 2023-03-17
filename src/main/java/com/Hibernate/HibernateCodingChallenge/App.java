package com.Hibernate.HibernateCodingChallenge;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.Hibernate.HibernateCodingChallenge.config.HibernateConfig;
import com.Hibernate.HibernateCodingChallenge.entity.Teacher;


/**
 * Hello world!
 *
 */
public class App 
{
	private static SessionFactory factory = HibernateConfig.getSessionFactory();
	
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        Teacher t1=new Teacher("Shalini","Garg","shalini@gl.com");
        
        int id=insertTeacher(t1);
//        System.out.println(id);
        
//        t1.setEmail("shalini@greatLearning.com");
//        Teacher t2=updateTeacher(t1);
//        System.out.println(t2);
        
      deleteTeacher(t1);
        
        Teacher t3=getTeacherById(id);
        System.out.println(t3);
        
        List<Teacher> teachers=getAllTeachers();
        for(Teacher t:teachers)
        	System.out.println(t);
        
    }
    
    public static int insertTeacher(Teacher teacher)
	{
		// Create a session
		// DML commit => transaction
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(teacher);
		tx.commit();
		session.close();
		return teacher.getId();
	}
	
	public static Teacher updateTeacher(Teacher teacher)
	{
		// Create a session
		// DML commit => transaction
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Teacher updatedTeacher = (Teacher)session.merge(teacher);
		tx.commit();
		session.close();
		return updatedTeacher;
	}
	
	// remove and delete
	public static void deleteTeacher(Teacher teacher)
	{
		// Create a session
		// DML commit => transaction
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.remove(teacher);
		tx.commit();
		session.close();
	}
	
	public static Teacher getTeacherById(int teacherid)
	{
		Session session = factory.openSession();
		Teacher teacher = session.get(Teacher.class, teacherid);
		session.close();
		return teacher;
	}
	
	public static List<Teacher> getAllTeachers()
	{
		Session session = factory.openSession();
		// from Author [ java class ]
		List<Teacher> teachers = session.createQuery("from Teacher", Teacher.class).getResultList();
		//List<Author> authors = session.createQuery("select a from Author a", Author.class).getResultList();
		session.close();
		return teachers;
	}
      
    
}
