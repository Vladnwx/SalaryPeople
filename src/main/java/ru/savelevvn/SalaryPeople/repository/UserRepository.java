package ru.savelevvn.SalaryPeople.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.savelevvn.SalaryPeople.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
