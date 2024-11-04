package ru.savelevvn.SalaryPeople.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(nullable = false)
    @Size(min=2, message = "Не меньше 2 знаков")
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    @Size(min=5, message = "Не меньше 5 знаков")
    private String password;
    @Transient
    private String passwordConfirm = "NO";
    private boolean enabled;
    private boolean isUsing2FA;
    private String secret;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = { @JoinColumn(name = "user_id", referencedColumnName =  "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName =  "id")}
    )
    private Collection<Role> roles;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_people",
            joinColumns = { @JoinColumn(name = "user_id", referencedColumnName =  "id")},
            inverseJoinColumns = {@JoinColumn(name = "people_id", referencedColumnName =  "id")}
    )
    private People people;


    public User() {
        super();
        Random random = ThreadLocalRandom.current();
        byte[] r = new byte[256]; //Means 2048 bit
        random.nextBytes(r);
        this.secret = Base64.getEncoder().encodeToString(r);
        this.enabled = false;
    }

    @Override
    public int hashCode() {
        final int prime = 33;
        int result = 1;
        result = (prime * result) + ((getEmail() == null) ? 0 : getEmail().hashCode());
        return result;
    }
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User user = (User) obj;
        if (!getEmail().equals(user.getEmail())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("User [id=")
                .append(id)
                .append(", Name=").append(name)
                .append(", email=").append(email)
                .append(", enabled=").append(enabled)
                .append(", isUsing2FA=").append(isUsing2FA)
                .append(", secret=").append(secret)
                .append(", roles=").append(roles)
                .append("]");
        return builder.toString();
    }

}
