package controller;

import model.User;
import service.UserService;

public class UserController {
    private final UserService personService;

    public UserController() {
        personService = new UserService();
    }

    public void insertFinalUser(String username, String password, String type){
        User user = new User(username, password, type);
        personService.insertValidUser(user);
    }

    public User findUserByUsername(String username){
        return personService.findUserByUsername(username);
    }



    public void closeConnection(){
        personService.closeConnection();
    }
}
