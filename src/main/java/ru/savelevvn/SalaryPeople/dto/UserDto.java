package ru.savelevvn.SalaryPeople.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.savelevvn.SalaryPeople.entity.Role;
import ru.savelevvn.SalaryPeople.validation.PasswordMatches;
import ru.savelevvn.SalaryPeople.validation.ValidEmail;
import ru.savelevvn.SalaryPeople.validation.ValidPassword;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@PasswordMatches
public class UserDto {
    private Long id;
    @NotNull
    @Size(min = 1, message = "{Size.userDto.firstName}")
    private String firstName;
    @NotNull
    @Size(min = 1, message = "{Size.userDto.lastName}")
    private String lastName;
    @NotEmpty(message = "Email should not be empty")
    @Email
    //@ValidEmail
    @Size(min = 1, message = "{Size.userDto.email}")
    private String email;
    @NotEmpty(message = "Password should not be empty")
    //@ValidPassword
    private String password;
    @NotNull
    @Size(min = 1)
//    private String matchingPassword;
//    private boolean isUsing2FA;
//    private Role role;

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("UserDto [firstName=")
                .append(firstName)
                .append(", lastName=")
                .append(lastName)
                .append(", email=")
                .append(email)
                .append(", isUsing2FA=")
//                .append(isUsing2FA)
                .append(", role=")
//                .append(role)
                .append("]");
        return builder.toString();
    }
}
