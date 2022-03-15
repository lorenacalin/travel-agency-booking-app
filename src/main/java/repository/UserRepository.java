package repository;

import model.User;
import model.VacationDestination;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

public class UserRepository {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");
    private EntityManager entityManager;

    public UserRepository(){
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void insertUser(User user){
        if(!entityManager.getTransaction().isActive()){
            entityManager.getTransaction().begin();
        }
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public User findUserByUsername(String username){
        if(!entityManager.getTransaction().isActive()){
            entityManager.getTransaction().begin();
        }
        try{
            return entityManager.createQuery("SELECT u from User u WHERE u.username = :username", User.class)
                    .setParameter("username",username)
                    .getSingleResult();
        }catch (NoResultException e){
            System.out.println("No user with this username.");
        }
        entityManager.getTransaction().commit();
        //entityManager.close();
        return null;
    }

    public void closeConnection(){
        entityManager.close();
    }
}
