package pis.skalowalnosc.service;

import pis.skalowalnosc.errors.AppException;
import pis.skalowalnosc.controller.api.requests.LoginRequest;
import pis.skalowalnosc.controller.api.requests.RegisterRequest;

public interface UserService {
    boolean login(LoginRequest login) throws AppException;

    boolean register(RegisterRequest register) throws AppException;

    void validatePassword(String password) throws AppException;

    void validateMail(String mail) throws AppException;

    byte[] hashPassword(String password, byte[] salt) throws AppException;
}
