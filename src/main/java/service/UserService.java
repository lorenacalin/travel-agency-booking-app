package service;

import model.User;
import repository.UserRepository;

public class UserService {
    private final UserRepository personRepository;

    public UserService() {
        personRepository = new UserRepository();
    }

    public void insertValidUser(User user){
        if(user.getUsername() != null && !user.getUsername().isEmpty()){
            personRepository.insertUser(user);
        }
        else{
            System.out.println("Invalid username for the user. Cannot insert user.");
        }
    }

    public User findUserByUsername(String username){
        if(username != null && !username.isEmpty()){
            return personRepository.findUserByUsername(username);
        }else{
            System.out.println("Cannot find user with this username");
            return null;
        }
    }

    public void closeConnection(){
        personRepository.closeConnection();
    }
}
