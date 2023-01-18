package repository;

import config.MysqlConfig;
import model.RoleColumn;
import model.RoleModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository {
    public List<RoleModel> getAllRoles() {
        List<RoleModel> listRoles = new ArrayList<RoleModel>();
        //! start connect to database server
        Connection connection = MysqlConfig.getConnection();
        String query = "SELECT * FROM roles";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                RoleModel roleModel = new RoleModel();
                roleModel.setId(resultSet.getInt(RoleColumn.ID.getValue()));
                roleModel.setRoleName(resultSet.getString(RoleColumn.NAME.getValue()));
                roleModel.setDescription(resultSet.getString(RoleColumn.DESCRIPTION.getValue()));
                listRoles.add(roleModel);
            }
        } catch (Exception e) {
            System.out.println("Lỗi thực thi câu truy vấn tìm all roles" + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return listRoles;
    }

    public int deleteRoleById(int id) {
        int isDeleteSuccess = -1;
        //! start connect to database server
        Connection connection = MysqlConfig.getConnection();
        String query = "DELETE FROM roles r WHERE r.id = ? ";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            isDeleteSuccess = statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("Lỗi thực thi câu truy vấn delete " + e.getMessage());
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

    public int addNewRole(String name, String description) {
        int result = -1;
        //! start connect to database server
        Connection connection = MysqlConfig.getConnection();
        String query = "INSERT INTO roles (name, description) VALUES (?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, description);
            result = statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Lỗi thực thi câu truy vấn delete " + e.getMessage());
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
}

