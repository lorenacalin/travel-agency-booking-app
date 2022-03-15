import controller.UserController;
import controller.VacationDestinationController;
import controller.VacationPackageController;
import gui.AgencyGUI;
import gui.ClientGUI;
import gui.LogInGUI;
import gui.RegisterGUI;
import model.User;

public class Main {
    public static void main(String[] args) {
        LogInGUI logInGUI = new LogInGUI(new RegisterGUI(), new AgencyGUI(), new ClientGUI());
        logInGUI.setVisible(true);
    }
}
