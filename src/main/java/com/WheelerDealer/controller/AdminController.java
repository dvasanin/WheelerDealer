package com.WheelerDealer.controller;

import com.WheelerDealer.dao.UserDao;
import com.WheelerDealer.dao.UserDaoSQL;
import com.WheelerDealer.db.DatabaseConnection;
import com.WheelerDealer.spring.request.admin.AdminChangeUserInfoRequestModel;
import com.WheelerDealer.spring.response.admin.AdminChangeUserInfoResponseModel;
import com.WheelerDealer.spring.response.admin.AdminUpdateUserModel;
import org.springframework.web.bind.annotation.*;

import java.sql.*;

@RestController
public class AdminController {
    private static PreparedStatement preparedStatement;
    private static Statement statement;
    Connection conn = DatabaseConnection.getConnection();
    UserDao userDao = new UserDaoSQL();

    //Validity check - Email
    private boolean isEmailValid(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    //Verification - Username
    private boolean userNameExists(String username) {
        try {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE " +
                    " username = '" + username + "';");
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //Verification - Email
    private boolean emailExists(String email) {
        try {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE " +
                    " email = '" + email + "';");
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //admin/update/{id} - PATCH
    @PatchMapping("/admin/update/{id}")
    public AdminChangeUserInfoResponseModel changeUserInformation(@PathVariable("id") String userId,
                                                                  @RequestHeader("authorization") String adminId,
                                                                  @RequestBody AdminChangeUserInfoRequestModel info) {
        if (!userDao.isAdmin(adminId)) {
            return new AdminChangeUserInfoResponseModel
                    (false, "Access prohibited. Invalid credentials!");
        }

        String username = info.getUsername();
        String email = info.getEmail();

        if ((!username.isEmpty()) && username.length() < 3) {
            username = "";
        }
        if ((!email.isEmpty()) && !isEmailValid(email)) {
            email = "";
        }

        if (userNameExists(username) || emailExists(email)){
            return new AdminChangeUserInfoResponseModel(false,"Existing username/email. Please try again!");
        }

        if (username.isEmpty() && email.isEmpty() && info.getFirstName().isEmpty()
                && info.getLastName().isEmpty() && info.getPersonalNumber().isEmpty()
                && info.getPhoneNumber().isEmpty() && info.getImage().isEmpty()) {
            return new AdminChangeUserInfoResponseModel(false, "Nothing to update!");
        }

        AdminUpdateUserModel userInfo = new AdminUpdateUserModel(username, email, info.getFirstName(),
                info.getLastName(), info.getPhoneNumber(), info.getPersonalNumber(), info.getImage());

        userDao.adminUpdateUserInfo(userInfo, userId);
        return new AdminChangeUserInfoResponseModel(true, "Successful update of " + info.getUsername() + "'s account!");
    }
}
