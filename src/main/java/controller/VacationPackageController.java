package controller;

import model.VacationDestination;
import model.VacationPackage;
import model.enums.Status;
import service.VacationPackageService;

import java.util.Date;
import java.util.List;

public class VacationPackageController {
    private final VacationPackageService vacationPackageService;
    private final VacationDestinationController vacationDestinationController;

    public VacationPackageController() {
        this.vacationPackageService = new VacationPackageService();
        this.vacationDestinationController = new VacationDestinationController();
    }

    public void insertFinalPackage(String name, Double price, Date startDate, Date endDate, String details, Integer nrOfAvailableSears, Integer destinationId){
        VacationDestination vacationDestination = vacationDestinationController.findFinalDestinationById(destinationId);
        VacationPackage vacationPackage = new VacationPackage(name,price,startDate,endDate,details,nrOfAvailableSears,Status.not_booked,vacationDestination);
        vacationPackageService.insertValidPackage(vacationPackage);
    }

    public VacationPackage findFinalPackageById(Integer id){
        return vacationPackageService.findPackageById(id);
    }

    public List<VacationPackage> getAllFinalPackages(){
        return vacationPackageService.getAllValidPackages();
    }

    public void updateFinalPackage(Integer packageId, String name, Double price, Date startDate, Date endDate, String details, Integer nrOfAvailableSeats, VacationDestination destination){
        vacationPackageService.updateValidPackage(packageId,name, price, startDate, endDate, details, nrOfAvailableSeats, destination);
    }

    public void deleteFinalPackage(Integer id){
        vacationPackageService.deleteValidPackage(id);
    }

    public void bookFinal(VacationPackage vacationPackage){
        vacationPackageService.bookValid(vacationPackage);
    }

    public List<VacationPackage> findFinalByKeyword(String name){
        return vacationPackageService.findValidByKeyword(name);
    }

    public List<VacationPackage> findFinalBetweenPrice(Double minPrice, Double maxPrice){
        return vacationPackageService.findValidBetweenPrice(minPrice, maxPrice);
    }

    public List<VacationPackage> findFinalBetweenPeriod(Date startDate, Date endDate){
        return vacationPackageService.findValidBetweenPeriod(startDate, endDate);
    }

    public void closeConnection(){
        vacationPackageService.closeConnection();
    }
}