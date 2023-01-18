package service;

import repository.UserRepository;

//! service sẽ xử lý logic code
public class LoginService {
    //! tên hàm sẽ là tên chức năng
    public boolean checkLogin(String username, String password) {
        UserRepository userRepository = new UserRepository();

        int count = userRepository.countUsersByEmailAndPassword(username, password);
        return count > 0;
    }
}
