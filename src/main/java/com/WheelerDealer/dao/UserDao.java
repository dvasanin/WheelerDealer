package com.WheelerDealer.dao;

import com.WheelerDealer.db.DatabaseConnection;
import com.WheelerDealer.spring.misc.RegisterUserModel;
import com.WheelerDealer.spring.misc.UpdateUserModel;
import com.WheelerDealer.spring.response.admin.AdminUpdateUserModel;
import com.WheelerDealer.spring.response.user.GetUserResponseModel;

import java.sql.Connection;
import java.util.List;

public interface UserDao {
    Connection conn = DatabaseConnection.getConnection();

    GetUserResponseModel getUser(String id);

    boolean isAdmin(String id);

    List<GetUserResponseModel> getAllUsers();

    void registerUser(RegisterUserModel user);

    void updateUser(UpdateUserModel user, String id);

    void adminUpdateUserInfo(AdminUpdateUserModel user, String id);

    boolean userNameExists(String username);

    boolean emailExists(String email);

    String getPasswordWithIdentification(String identification);

    String getIdWithIdentification(String identification);

    String getPasswordWithUUID(String id);
}
