package repository;

import model.VacationDestination;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.swing.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class VacationDestinationRepository {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");
    private EntityManager entityManager;

    public VacationDestinationRepository(){
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void insertVacationDestination(VacationDestination vacationDestination){
        if(!entityManager.getTransaction().isActive()){
            entityManager.getTransaction().begin();
        }
        entityManager.persist(vacationDestination);
        entityManager.getTransaction().commit();
        //entityManager.close();
    }

    public List<VacationDestination> getAllDestinations(){
        if(!entityManager.getTransaction().isActive()){
            entityManager.getTransaction().begin();
        }
        List<VacationDestination> allDestinations = entityManager.createQuery("SELECT a FROM VacationDestination a", VacationDestination.class)
                .getResultList();
        entityManager.getTransaction().commit();
        //entityManager.close();
        return allDestinations;
    }

    public VacationDestination findById(Integer destinationId){
        if(!entityManager.getTransaction().isActive()){
            entityManager.getTransaction().begin();
        }
        try{
            return entityManager.createQuery("SELECT a from VacationDestination a WHERE a.destinationId = :destinationId", VacationDestination.class)
                    .setParameter("destinationId",destinationId)
                    .getSingleResult();
        }catch (NoResultException e){
            System.out.println("No destination with this id with this id");
        }
        entityManager.getTransaction().commit();
        //entityManager.close();
        return null;
    }

    public void updateDestination(Integer destinationId, String name){
        if(!entityManager.getTransaction().isActive()){
            entityManager.getTransaction().begin();
        }
        VacationDestination vacationDestination = findById(destinationId);
        if(vacationDestination != null){
            entityManager.createQuery("UPDATE VacationDestination v SET v.name = :name WHERE v.destinationId = :destinationId")
                    .setParameter("name",name)
                    .setParameter("destinationId",destinationId).executeUpdate();
            entityManager.getTransaction().commit();
        }else{
            System.out.println("No destination found with this id. Cannot update destination");
        }
    }

    @Transactional
    public void deleteDestination(Integer destinationId){
        if(!entityManager.getTransaction().isActive()){
            entityManager.getTransaction().begin();
        }
        VacationDestination vacationDestination = findById(destinationId);
        if(vacationDestination != null){
            entityManager.createQuery("DELETE FROM VacationDestination v WHERE v.destinationId = :destinationId")
                    .setParameter("destinationId",destinationId).executeUpdate();
            entityManager.getTransaction().commit();
        }else{
            System.out.println("No destination found with this id.");
        }
    }

    public void closeConnection(){
        entityManager.close();
    }

}
