package ru.savelevvn.SalaryPeople.service;

import org.springframework.stereotype.Service;
import ru.savelevvn.SalaryPeople.dto.UserDto;
import ru.savelevvn.SalaryPeople.entity.User;


import java.util.List;
@Service
public interface UserService {

    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
