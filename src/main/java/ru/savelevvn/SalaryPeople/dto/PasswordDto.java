package ru.savelevvn.SalaryPeople.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.savelevvn.SalaryPeople.validation.ValidPassword;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordDto {

    private String oldPassword;
    private  String token;
    //@ValidPassword
    private String newPassword;

}
