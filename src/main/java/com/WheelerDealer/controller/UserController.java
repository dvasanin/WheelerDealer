package com.WheelerDealer.controller;

import com.WheelerDealer.dao.UserDao;
import com.WheelerDealer.dao.UserDaoSQL;
import com.WheelerDealer.spring.misc.RegisterUserModel;
import com.WheelerDealer.spring.misc.UpdateUserModel;
import com.WheelerDealer.spring.request.user.ChangeUserInfoRequestModel;
import com.WheelerDealer.spring.request.user.LoginUserRequestModel;
import com.WheelerDealer.spring.request.user.RegisterUserRequestModel;
import com.WheelerDealer.spring.response.user.ChangeUserInfoResponseModel;
import com.WheelerDealer.spring.response.user.GetUserResponseModel;
import com.WheelerDealer.spring.response.user.LoginUserResponseModel;
import com.WheelerDealer.spring.response.user.RegisterUserResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController

public class UserController {
    private static final UserDao userDao = new UserDaoSQL();

    //Validity check - Email
    private boolean isEmailValid(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    //Validity check - Password
    private boolean isPasswordValid(String password) {
        if (password.length() < 8) {
            return false;
        }
        char[] array = password.toCharArray();
        int letterCount = 0, digitCount = 0;
        for (char c : array) {
            if (Character.isLetter(c)) {
                letterCount++;
            }
            if (Character.isDigit(c)) {
                digitCount++;
            }
            if (letterCount >= 1 && digitCount >= 1) {
                return true;
            }
        }
        return false;
    }

    //Turning a password into it's Sha256 format
    private String getSHA256(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes());
            byte[] digestedBytes = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digestedBytes) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        throw new ResponseStatusException(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
    }

    //users/register - POST
    @PostMapping("/users/register")
    public RegisterUserResponseModel register(@RequestBody RegisterUserRequestModel user) {
        String username = user.getUsername();
        String email = user.getEmail();
        String password = user.getPassword();
        if (username.length() < 3) {
            return new RegisterUserResponseModel(false,
                    "Invalid username, must contain over 3 characters!");
        }
        if (userDao.userNameExists(username)) {
            return new RegisterUserResponseModel(false,
                    "Existing username. Please try again!");
        }
        if (!isEmailValid(email)) {
            return new RegisterUserResponseModel(false,
                    "Invalid email address, please try again!");
        }
        if (userDao.emailExists(email)) {
            return new RegisterUserResponseModel(false,
                    "Existing email address. Please try again!");
        }
        if (!isPasswordValid(password)) {
            return new RegisterUserResponseModel(false,
                    "Invalid password. Please try again!");
        } else {
            password = getSHA256(password);
        }

        userDao.registerUser(new RegisterUserModel(username, email, password));

        return new RegisterUserResponseModel(true, "Welcome " + username + "!");
    }

    //users/login - POST
    @PostMapping("/users/login")
    public LoginUserResponseModel login(@RequestBody LoginUserRequestModel user) {
        String identification = user.getIdentification();
        String password = getSHA256(user.getPassword());
        if (userDao.userNameExists(identification) || userDao.emailExists(identification)) {
            if (password.equals(userDao.getPasswordWithIdentification(identification)))
                return new LoginUserResponseModel(true, userDao.getIdWithIdentification(identification));
        }
        return new LoginUserResponseModel(false,
                "Invalid credentials. Please try again!");
    }

    //users/{id} - PATCH
    @PatchMapping("/users/{id}")
    public ChangeUserInfoResponseModel changeUserInfo(@PathVariable("id") String id,
                                                      @RequestBody ChangeUserInfoRequestModel user) {
        String username = user.getUsername();
        String password = getSHA256(user.getPassword());
        String newPassword = user.getNewPassword();
        if (!userDao.getPasswordWithUUID(id).equals(password)) {
            return new ChangeUserInfoResponseModel(false, "Invalid password. Please try again!");
        }
        if (!newPassword.isEmpty()) {
            if (!isPasswordValid(newPassword)) {
                return new ChangeUserInfoResponseModel(false, "New password is invalid. Please try again!");
            }
            newPassword = getSHA256(newPassword);
        }
        if (userDao.userNameExists(username)) {
            username = "";
        }
        UpdateUserModel updateUser = new UpdateUserModel(username, newPassword, user.getFirstName(),
                user.getLastName(), user.getPhoneNumber(), user.getImage());
        userDao.updateUser(updateUser, id);
        return new ChangeUserInfoResponseModel(true, "Your account was updated!");
    }

    //users/{id} - GET
    @GetMapping("/users/{id}")
    public GetUserResponseModel getUser(@PathVariable(value = "id") String id) {
        return userDao.getUser(id);
    }

    //users - GET, @RequestHeader("authorization")
    @GetMapping("/users")
    public List<GetUserResponseModel> getAllUsers(@RequestHeader("authorization") String id) {
        if (userDao.isAdmin(id)) {
            return userDao.getAllUsers();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}