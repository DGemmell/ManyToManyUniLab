package db;

import models.Lesson;
import models.Student;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DBHelper {

    private static Transaction transaction;
    private static Session session;

    public static void save(Object object){
        session = HibernateUtil.getSessionFactory().openSession();
        try{
            transaction = session.beginTransaction();
            session.save(object);
            transaction.commit();
        } catch(HibernateException ex){
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void update(Object object){
        session = HibernateUtil.getSessionFactory().openSession();
        try{
            transaction = session.beginTransaction();
            session.update(object);
            transaction.commit();
        } catch(HibernateException ex){
            transaction.rollback();
            ex.printStackTrace();
        } finally{
            session.close();
        }
    }

    public static void delete(Object object){
        session = HibernateUtil.getSessionFactory().openSession();
        try{
            transaction = session.beginTransaction();
            session.delete(object);
            transaction.commit();
        } catch(HibernateException ex){
            transaction.rollback();
            ex.printStackTrace();
        } finally{
            session.close();
        }
    }

    public static <T> List<T> getAll(Class classType){
        session = HibernateUtil.getSessionFactory().openSession();
        List<T> results = null;
        Criteria cr = session.createCriteria(classType);
        results = getList(cr);
        return results;
    }

    public static void addStudentToLesson(Student student, Lesson lesson){
        student.addLesson(lesson);
        lesson.addStudent(student);
        update(lesson);
        update(student);
    }

    public static <T> List<T> getList(Criteria criteria ){
        List<T> results = null;
        try {
            transaction = session.beginTransaction();
            results = criteria.list();
            transaction.commit();
        }catch(HibernateException ex){
            transaction.rollback();
            ex.printStackTrace();
        }finally {
            session.close();
        }
        return results;
    }

    public static <T> T getUnique(Criteria criteria){
        T result = null;
        try {
            transaction = session.beginTransaction();
            result = (T)criteria.uniqueResult();
            transaction.commit();
        }catch(HibernateException ex){
            transaction.rollback();
            ex.printStackTrace();
        }finally{
            session.close();
        }
        return result;
    }




}
