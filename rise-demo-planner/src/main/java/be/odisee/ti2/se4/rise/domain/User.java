package be.odisee.ti2.se4.rise.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "USERS")
@Data
@RequiredArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE,force=true)
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private final long id;

    private String userid;
    private String password;
}
