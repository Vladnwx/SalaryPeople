package ru.savelevvn.SalaryPeople.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "privileges")
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;


    public Privilege(final String name) {
        super();
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 33;
        int result = 1;
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Privilege other = (Privilege) obj;
        if (getName() == null) {
            return other.getName() == null;
        } else return getName().equals(other.getName());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder
                .append("Privilege [name=")
                .append(name)
                .append("]")
                .append("[id=")
                .append(id)
                .append("]");
        return builder.toString();
    }
}
