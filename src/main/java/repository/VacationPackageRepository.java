package repository;

import controller.VacationDestinationController;
import model.VacationDestination;
import model.VacationPackage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public class VacationPackageRepository {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");
    private EntityManager entityManager;
    private final VacationDestinationController vacationDestinationController = new VacationDestinationController();

    public VacationPackageRepository() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void insertVacationPackage(VacationPackage vacationPackage) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        entityManager.persist(vacationPackage);
        entityManager.getTransaction().commit();
        //entityManager.close();
    }

    public List<VacationPackage> getAllPackages() {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        List<VacationPackage> allPackages = entityManager.createQuery("SELECT a FROM VacationPackage a", VacationPackage.class)
                .getResultList();
        entityManager.getTransaction().commit();
        //entityManager.close();
        return allPackages;
    }

    public VacationPackage findById(Integer packageId) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        try {
            return entityManager.createQuery("SELECT a from VacationPackage a WHERE a.packageId = :packageId", VacationPackage.class)
                    .setParameter("packageId", packageId)
                    .getSingleResult();
        } catch (NoResultException e) {
            System.out.println("No package with this id");
        }
        entityManager.getTransaction().commit();
        //entityManager.close();
        return null;
    }

    public void updatePackage(Integer packageId, String name, Double price, Date startDate, Date endDate, String details, Integer nrOfAvailableSeats, VacationDestination destination) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        VacationPackage vacationPackage = findById(packageId);
        if (vacationPackage != null) {
            entityManager.createQuery("UPDATE VacationPackage v SET v.name = :name, v.price = :price, " +
                            "v.startDate = :startDate, v.endDate = :endDate, v.details = :details, v.nrOfAvailableSeats = :nrOfAvailableSeats," +
                            " v.destination = :destination WHERE v.packageId = :packageId")
                    .setParameter("name", name)
                    .setParameter("packageId", packageId)
                    .setParameter("price", price)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .setParameter("details", details)
                    .setParameter("nrOfAvailableSeats", nrOfAvailableSeats)
                    .setParameter("destination", destination)
                    .executeUpdate();
            entityManager.getTransaction().commit();
        } else {
            System.out.println("No package found with this id. Cannot update package");
        }
    }

    @Transactional
    public void deletePackage(Integer packageId) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        VacationPackage vacationPackage = findById(packageId);
        if (vacationPackage != null) {
            entityManager.createQuery("DELETE FROM VacationPackage v WHERE v.packageId = :packageId")
                    .setParameter("packageId", packageId).executeUpdate();
            entityManager.getTransaction().commit();
        } else {
            System.out.println("No package found with this id.");
        }
    }

    public void book(VacationPackage vacationPackage) {
        vacationPackage.setNrOfAvailableSeats(vacationPackage.getNrOfAvailableSeats() - 1);
    }

    public List<VacationPackage> findByKeyword(String name) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        try {
            return entityManager.createQuery("SELECT a from VacationPackage a WHERE a.name LIKE :name AND (a.status = 'not_booked' OR a.status = 'in_progress')", VacationPackage.class)
                    .setParameter("name", "%" + name + "%")
                    .getResultList();
        } catch (NoResultException e) {
            System.out.println("No results found.");
        }
        entityManager.getTransaction().commit();
        //entityManager.close();
        return null;
    }

    public List<VacationPackage> findBetweenPrice(Double minPrice, Double maxPrice) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        try {
            return entityManager.createQuery("SELECT a from VacationPackage a WHERE a.price > :minPrice AND a.price < :maxPrice", VacationPackage.class)
                    .setParameter("minPrice", minPrice)
                    .setParameter("maxPrice", maxPrice)
                    .getResultList();
        } catch (NoResultException e) {
            System.out.println("No results found.");
        }
        entityManager.getTransaction().commit();
        //entityManager.close();
        return null;
    }

    public List<VacationPackage> findBetweenPeriod(Date startDate, Date endDate){
        if(!entityManager.getTransaction().isActive()){
            entityManager.getTransaction().begin();
        }
        try{
            return entityManager.createQuery("SELECT a from VacationPackage a WHERE a.startDate BETWEEN :startDate AND :endDate", VacationPackage.class)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getResultList();
        }catch (NoResultException e){
            System.out.println("No results found.");
        }
        entityManager.getTransaction().commit();
        //entityManager.close();
        return null;
    }

    public void closeConnection() {
        entityManager.close();
    }
}
