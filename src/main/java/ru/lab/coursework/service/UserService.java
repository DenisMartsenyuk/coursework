package ru.lab.coursework.service;

import ru.lab.coursework.model.User;

public interface UserService {
    void addNewAccount();
    User getAccount(String login, String password);
    void setDisableAccount(String login);
}
