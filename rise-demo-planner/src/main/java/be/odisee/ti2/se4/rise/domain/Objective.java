package be.odisee.ti2.se4.rise.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "OBJECTIVES")
@Data
@RequiredArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE,force=true)
public class Objective {

    @Id
    private final long id;

    private final String name;

}
