package ru.lab.coursework.service.impl;

//бизнеслогика аккаунта

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lab.coursework.dto.SignInRequestDTO;
import ru.lab.coursework.dto.SignInResponseDTO;
import ru.lab.coursework.dto.SignUpRequestDTO;
import ru.lab.coursework.dto.SignUpResponseDTO;
import ru.lab.coursework.model.Role;
import ru.lab.coursework.model.User;
import ru.lab.coursework.model.UserRole;
import ru.lab.coursework.repository.RoleRepository;
import ru.lab.coursework.repository.UserRepository;
import ru.lab.coursework.repository.UserRoleRepository;
import ru.lab.coursework.service.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    @Override
    public SignUpResponseDTO addNewAccount(SignUpRequestDTO signUpRequestDTO) {

        User user = new User();
        user.setLogin(signUpRequestDTO.getLogin());
        user.setPassword(signUpRequestDTO.getPassword());
        user.setName(signUpRequestDTO.getName());
        user.setSurname(signUpRequestDTO.getSurname());
        user.setMiddleName(signUpRequestDTO.getMiddleName());
        user.setEmail(signUpRequestDTO.getEmail());
        user = userRepository.save(user);

        Role role = roleRepository.findByName(signUpRequestDTO.getRoleName());

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        userRoleRepository.save(userRole);

        SignUpResponseDTO signUpResponseDTO = new SignUpResponseDTO();
        signUpResponseDTO.setId(user.getId());
        signUpResponseDTO.setName(user.getName());
        signUpResponseDTO.setSurname(user.getSurname());
        signUpResponseDTO.setMiddleName(user.getMiddleName());
        signUpResponseDTO.setEmail(user.getEmail());
        signUpResponseDTO.setRole(role.getName());
        return signUpResponseDTO;
    }

    @Override
    public SignInResponseDTO getAccount(SignInRequestDTO signInRequestDTO) {
        User user = userRepository.findUserByLogin(signInRequestDTO.getLogin());

        SignInResponseDTO signInResponseDTO = new SignInResponseDTO();
        signInResponseDTO.setId(user.getId());
        signInResponseDTO.setName(user.getName());
        signInResponseDTO.setSurname(user.getSurname());
        signInResponseDTO.setMiddleName(user.getMiddleName());
        signInResponseDTO.setEmail(user.getEmail());

        UserRole userRole = userRoleRepository.findUserRoleByUser(user);
        Role role = userRole.getRole();

        signInResponseDTO.setRole(role.getName());

        return signInResponseDTO;
    }


    @Override
    public void setDisableAccount(String login) {

    }
}
