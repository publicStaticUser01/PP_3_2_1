package com.example.webapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(min = 2, max = 30, message = " Name should be between 2 and 30 characters")
    @NotEmpty(message = "Name should not be empty!")
    @Column(name = "name")
    private String name;

    @Size(min = 2, max = 30, message = " Surname should be between 2 and 30 characters")
    @NotEmpty(message = "Last Name should not be empty!")
    @Column(name = "last_name")
    private String lastName;

    @Email(message = "Email should be valid!")
    @NotEmpty(message = "Email should not be empty!")
    @Column(name = "email")
    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, email);
    }

    public User(long id, String name, String lastName, String email) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

}