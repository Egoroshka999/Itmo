package model;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.criteria.JpaCriteriaQuery;

import jakarta.persistence.criteria.Root;


public class ResultDAOImpl implements ResultDAO {

    @Override
    public void save(Result result) throws SQLException {
       Session session = null;
       try {
            session = Factory.getSessionFactory().openSession();        
            session.beginTransaction();
            session.persist(result);
            session.getTransaction().commit();
       } catch (Exception e) {
            System.err.println("Something went wrong in DAO: " + e);
            throw new SQLException(e);
       } finally {
            if (session != null && session.isOpen()){
                session.close();
            }
       }
    }

    @Override
    public void update(Long point_id, Result result) throws SQLException {
       Session session = null;
       try {
            session = Factory.getSessionFactory().openSession();        
            session.beginTransaction();
            session.merge(result);
            session.getTransaction().commit();
       } catch (Exception e) {
            System.err.println("Something went wrong in DAO: " + e);
            throw new SQLException(e);
       } finally {
            if (session != null && session.isOpen()){
                session.close();
            }
       }
    }

    @Override
    public void delete(Result result) throws SQLException {
        Session session = null;
        try {
            session = Factory.getSessionFactory().openSession();
            session.beginTransaction();
            session.remove(result);
            session.getTransaction().commit();
        } catch (Throwable e) {
            System.err.println("Something went wrong in DAO: " + e);
            throw new SQLException(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public final String TABLE_NAME = "results";
    @Override
    public void clear() throws SQLException {
        Session session = null;
        try {
            session = Factory.getSessionFactory().openSession();
            session.beginTransaction();
            String sql = "delete from " + TABLE_NAME;
            session.createNativeQuery(sql, this.getClass()).executeUpdate();
            session.getTransaction().commit();
        } catch (Throwable e) {
            System.err.println("Something went wrong in DAO: " + e);
            throw new SQLException(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public Result getResultByID(Long result_id) throws SQLException {
        Session session = null;
        Result result;
        try {
            session = Factory.getSessionFactory().openSession();
            result = session.getReference(Result.class, result_id);
        } catch (Throwable e) {
            System.err.println("Something went wrong in DAO: " + e);
            throw new SQLException(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return result;
    }

    @Override
    public Collection<Result> getAllResults() throws SQLException {
        Session session = null;
        List<Result> results;
        try {
            session = Factory.getSessionFactory().openSession();
            JpaCriteriaQuery<Result> criteriaQuery = session.getCriteriaBuilder().createQuery(Result.class);
            Root<Result> root = criteriaQuery.from(Result.class);
            results = session.createQuery(criteriaQuery.select(root)).getResultList();
        } catch (Throwable e) {
            System.err.println("Something went wrong in DAO: " + e);
            throw new SQLException(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return results;
    }
    
}
