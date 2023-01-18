package repository;

import config.MysqlConfig;
import model.RoleColumn;
import model.RoleModel;
import model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserRepository {
    public int countUsersByEmailAndPassword(String email, String password) {
        int count = 0;
        //! start connect to database server
        Connection connection = MysqlConfig.getConnection();
        String query = "SELECT count(*) as count FROM users WHERE email= ? AND password= ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            count = 0;
            while (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (Exception e) {
            System.out.println("Lỗi thực thi câu truy vấn login" + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return count;
    }


    public List<UserModel> getAllUsers() {
        List<UserModel> listUser = new ArrayList<>();
        //! start connect to database server
        Connection connection = MysqlConfig.getConnection();
        String query = "SELECT u.id, u.email, u.firstname as firstname, u.lastname as lastname, r.name as roleName  FROM users as u JOIN roles as r ON u.role_id = r.id";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UserModel user = new UserModel();
                user.setId(resultSet.getString("id"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setLastName(resultSet.getString("lastname"));
                user.setUserName(resultSet.getString("email"));
                user.setRole(resultSet.getString("roleName"));
                listUser.add(user);
                System.out.println(listUser);
            }
        } catch (Exception e) {
            System.out.println("Lỗi thực thi câu truy vấn tìm all users " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return listUser;
    }

    public int createUser(String firstName, String lastName, String email, String password, int phoneNumber, String country) {
        int result = -1;
        int defaultRole = 3;
        //! start connect to database server
        Connection connection = MysqlConfig.getConnection();
        String query = "INSERT INTO users (email, password, firstname, lastname, phone, country, role_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);
            statement.setString(3, firstName);
            statement.setString(4, lastName);
            statement.setInt(5, phoneNumber);
            statement.setString(6, country);
            statement.setInt(7, defaultRole);
            result = statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("Lỗi thực thi câu lệnh add user " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return result;
    }

    public int deleteUserById(int userID) {
        int isDeleteSuccess = -1;
        //! start connect to database server
        Connection connection = MysqlConfig.getConnection();
        String query = "DELETE FROM users WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userID);
            isDeleteSuccess = statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("Lỗi thực thi câu truy vấn delete user " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return isDeleteSuccess;
    }

    public UserModel getUserById(int userID) {
        UserModel userDetail = new UserModel();
        //! start connect to database server
        Connection connection = MysqlConfig.getConnection();
        String query = "SELECT * FROM users WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                userDetail.setId(String.valueOf(resultSet.getInt("id")));
                userDetail.setUserName(resultSet.getString("email"));
                userDetail.setFirstName(resultSet.getString("firstname"));
                userDetail.setLastName(resultSet.getString("lastname"));
                userDetail.setRole(resultSet.getString("role_id"));
                return userDetail;

            }
        } catch (Exception e) {
            System.out.println("Lỗi thực thi câu truy vấn tìm user detail " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return userDetail;
    }
}
