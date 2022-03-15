package service;

import com.sun.xml.bind.v2.TODO;
import model.VacationDestination;
import model.VacationPackage;
import model.enums.Status;
import repository.VacationPackageRepository;

import javax.swing.*;
import java.util.Date;
import java.util.List;

public class VacationPackageService {
    private final VacationPackageRepository vacationPackageRepository;

    public VacationPackageService() {
        this.vacationPackageRepository = new VacationPackageRepository();
    }

    public void insertValidPackage(VacationPackage vacationPackage){
        if(vacationPackage.getName() == null || vacationPackage.getName().isEmpty()){
            JOptionPane.showMessageDialog(null,"Invalid name for the package.");
        }
        else if(vacationPackage.getPrice() <= 0){
            JOptionPane.showMessageDialog(null,"Invalid price. Please introduce a positive number");
        }else if(vacationPackage.getNrOfAvailableSeats() <= 0){
            JOptionPane.showMessageDialog(null,"Invalid number of available seats.");
        }else if(!vacationPackage.getStartDate().before(vacationPackage.getEndDate())){
            JOptionPane.showMessageDialog(null,"Invalid period. Start date must be before end date.");
        }else{
            vacationPackageRepository.insertVacationPackage(vacationPackage);
            JOptionPane.showMessageDialog(null,"Successful insertion.");
        }
    }

    public VacationPackage findPackageById(Integer id){
        if(id != null){
            return vacationPackageRepository.findById(id);
        }
        else{
            JOptionPane.showMessageDialog(null,"There is no vacation package with this id.");
            System.out.println("Cannot find package with this id.");
            return null;
        }
    }

    public List<VacationPackage> getAllValidPackages(){
        if(!vacationPackageRepository.getAllPackages().isEmpty()){
            return vacationPackageRepository.getAllPackages();
        }
        else{
            JOptionPane.showMessageDialog(null,"No packages available.");
            return null;
        }
    }

    public void updateValidPackage(Integer id, String name, Double price, Date startDate, Date endDate, String details, Integer nrOfAvailableSeats, VacationDestination destination){
        if(name == null || name.isEmpty()){
            JOptionPane.showMessageDialog(null,"Invalid name for the package.");
        }
        else if(price <= 0){
            JOptionPane.showMessageDialog(null,"Invalid price. Please introduce a positive number");
        }else if(nrOfAvailableSeats <= 0){
            JOptionPane.showMessageDialog(null,"Invalid number of available seats.");
        }else if(!startDate.before(endDate)){
            JOptionPane.showMessageDialog(null,"Invalid period. Start date must be before end date.");
        }else{
            vacationPackageRepository.updatePackage(id,name, price, startDate, endDate, details, nrOfAvailableSeats, destination);
            JOptionPane.showMessageDialog(null,"Successful update.");
        }
    }

    public void deleteValidPackage(Integer id){
//        if(id != null){
//            vacationPackageRepository.deletePackage(id);
//        }
//        else{
//            //JOptionPane.showMessageDialog(null,"There is no vacation destination with this id.");
//            System.out.println("Cannot find package with this id.");
//        }
        if(id == null){
            JOptionPane.showMessageDialog(null,"There is no vacation package with this id.");
            System.out.println("Cannot find package with this id.");
        }else{
            JOptionPane.showMessageDialog(null,"You successfully removed a package.");
            vacationPackageRepository.deletePackage(id);
        }
    }

    public void bookValid(VacationPackage vacationPackage){
        if(vacationPackage.getNrOfAvailableSeats() > 1){
            vacationPackageRepository.book(vacationPackage);
            vacationPackage.setStatus(Status.in_progress);
        }else if(vacationPackage.getNrOfAvailableSeats() == 1){
            vacationPackageRepository.book(vacationPackage);
            vacationPackage.setStatus(Status.booked);
        }
        else{
            System.out.println("There are no more available seats for this vacation.");
            JOptionPane.showMessageDialog(null,"Sorry! There are no more available seats for this vacation.");
        }
    }

    public List<VacationPackage> findValidByKeyword(String name){
        if(name == null && name.isEmpty()){
            JOptionPane.showMessageDialog(null,"Invalid keyword.");
            System.out.println("Cannot find package containing this keyword.");
            return null;

        }
        else{
            return vacationPackageRepository.findByKeyword(name);
        }
    }

    public List<VacationPackage> findValidBetweenPrice(Double minPrice, Double maxPrice){
        if(minPrice < 0 || maxPrice < 0 || minPrice > maxPrice){
            JOptionPane.showMessageDialog(null,"Invalid price bounds.");
            return null;
        }else{
            return vacationPackageRepository.findBetweenPrice(minPrice, maxPrice);
        }
    }

    public List<VacationPackage> findValidBetweenPeriod(Date startDate, Date endDate){
        if(startDate.after(endDate)){
            JOptionPane.showMessageDialog(null,"Invalid date bounds.");
            return null;
        }else{
            return vacationPackageRepository.findBetweenPeriod(startDate, endDate);
        }
    }

    public void closeConnection(){
        vacationPackageRepository.closeConnection();
    }
}
