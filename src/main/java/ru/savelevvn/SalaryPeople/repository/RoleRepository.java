package ru.savelevvn.SalaryPeople.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.savelevvn.SalaryPeople.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
