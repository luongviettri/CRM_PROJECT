package service;

import model.RoleModel;
import model.UserModel;
import repository.RoleRepository;
import repository.UserRepository;

import java.util.List;

public class UserService {
    public List<UserModel> getAllUsers() {
        UserRepository userRepository = new UserRepository();
        return userRepository.getAllUsers();
    }

    public int createUser(String fullName, String email, String password, int phoneNumber, String country) {
        String[] fullNameArray = fullName.split(" ");
        String firstName = fullNameArray[0];
        String lastName = fullNameArray[1];

        UserRepository userRepository = new UserRepository();

        return userRepository.createUser(firstName, lastName, email, password,
                phoneNumber, country);
    }

    public int deleteUserById(int userID) {
        UserRepository userRepository = new UserRepository();
        return userRepository.deleteUserById(userID);
    }

    public UserModel getUserByID(int userID) {
        UserRepository userRepository = new UserRepository();
        return userRepository.getUserById(userID);
    }
}
