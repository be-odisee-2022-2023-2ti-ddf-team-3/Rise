package be.odisee.ti2.se4.rise.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import jakarta.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "ENTRIES")
@Data
@RequiredArgsConstructor
@NoArgsConstructor(force=true)
public class Entry {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private final long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Project project;

    @ManyToOne
    private Objective objective;

    private LocalDateTime dateTimeFrom, dateTimeTo;
    private Duration duration;

    private String description;
}
