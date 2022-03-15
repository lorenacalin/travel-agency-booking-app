package service;

import model.VacationDestination;
import repository.VacationDestinationRepository;

import javax.swing.*;
import java.util.List;

public class VacationDestinationService {

    private final VacationDestinationRepository vacationDestinationRepository;

    public VacationDestinationService() {
        this.vacationDestinationRepository = new VacationDestinationRepository();
    }

    public void insertValidDestination(VacationDestination vacationDestination){
        if(vacationDestination.getName() == null || vacationDestination.getName().isEmpty()){
            JOptionPane.showMessageDialog(null,"Invalid name for the destination.");
        }
        else{
            vacationDestinationRepository.insertVacationDestination(vacationDestination);
            JOptionPane.showMessageDialog(null,"You successfully inserted a new vacation destination");
        }
    }

    public VacationDestination findDestinationById(Integer id){
        if(id != null){
            return vacationDestinationRepository.findById(id);
        }
        else{
            JOptionPane.showMessageDialog(null,"There is no vacation destination with this id.");
            System.out.println("Cannot find destination with this id.");
            return null;
        }
    }

    public List<VacationDestination> getAllValidDestinations(){
        if(!vacationDestinationRepository.getAllDestinations().isEmpty()){
            return vacationDestinationRepository.getAllDestinations();
        }
        else{
            JOptionPane.showMessageDialog(null,"No destinations available.");
            System.out.println("No destinations available.");
            return null;
        }
    }

    public void updateValidDestination(Integer id, String name){
        if(name == null || name.isEmpty()){
            JOptionPane.showMessageDialog(null,"Invalid name for the destination.");
        }
        else{
            vacationDestinationRepository.updateDestination(id, name);
            JOptionPane.showMessageDialog(null,"You successfully updated the vacation destination");
        }
    }

    public void deleteValidDestination(Integer id){
        if(id == null){
            JOptionPane.showMessageDialog(null,"Invalid id for the destination.");
        }
        else{
            vacationDestinationRepository.deleteDestination(id);
            JOptionPane.showMessageDialog(null,"You successfully deleted the vacation destination.");
        }
    }

    public void closeConnection(){
        vacationDestinationRepository.closeConnection();
    }
}
