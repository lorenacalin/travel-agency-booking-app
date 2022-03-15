package controller;

import model.VacationDestination;
import service.VacationDestinationService;

import java.util.List;

public class VacationDestinationController {
    private final VacationDestinationService vacationDestinationService;

    public VacationDestinationController() {
        this.vacationDestinationService = new VacationDestinationService();
    }

    public void insertFinalDestination(String name){
        VacationDestination vacationDestination = new VacationDestination(name);
        vacationDestinationService.insertValidDestination(vacationDestination);
    }

    public VacationDestination findFinalDestinationById(Integer id){
        return vacationDestinationService.findDestinationById(id);
    }

    public List<VacationDestination> getAllFinalDestinations(){
        return vacationDestinationService.getAllValidDestinations();
    }

    public void updateFinalDestination(Integer id, String name){
        vacationDestinationService.updateValidDestination(id,name);
    }

    public void deleteFinalDestination(Integer id){
        vacationDestinationService.deleteValidDestination(id);
    }

    public void closeConnection(){
        vacationDestinationService.closeConnection();
    }
}
