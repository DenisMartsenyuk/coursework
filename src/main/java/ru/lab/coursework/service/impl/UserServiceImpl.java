package ru.lab.coursework.service.impl;

//бизнеслогика аккаунта

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lab.coursework.model.User;
import ru.lab.coursework.repository.UserRepository;
import ru.lab.coursework.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addNewAccount() {
        User user = new User();
//        user.setId(10L);
        user.setLogin("keksde");
        user.setPassword("khgf");
        user.setName("df");
        user.setSurname("sf");
        user.setEmail("dfghsdsjlol@bk.ru");
//        System.out.println(userRepository.save(user).getId());
        userRepository.saveAndFlush(user);
//        System.out.println(userRepository.findById(5L).get().getLogin());
    }

    @Override
    public User getAccount(String login, String password) {
        return new User();
    }

    @Override
    public void setDisableAccount(String login) {

    }
}
