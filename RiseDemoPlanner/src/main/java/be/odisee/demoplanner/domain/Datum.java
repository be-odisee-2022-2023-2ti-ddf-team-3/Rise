package be.odisee.demoplanner.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="datum")
public class Datum implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) // HV 201804015 tbv Gebruik AutoIncrement
    private int id;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime start;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime einde;

    @Column
    private String status;

    @ManyToOne
    @JoinColumn(name="demo_id",nullable=false)
    private Demo demo;

    public Datum(){

    }
    public Datum( LocalDateTime start, LocalDateTime einde, String status, Demo demo ) {
        this.start = start;
        this.einde = einde;
        this.status = status;
        this.demo = demo;
    }

    public Datum( int id, LocalDateTime start, LocalDateTime einde, String status, Demo demo ) {
        this.id = id;
        this.start = start;
        this.einde = einde;
        this.status = status;
        this.demo = demo;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEinde() {
        return einde;
    }

    public String getStatus() {
        return status;
    }

    public Demo getDemo() {
        return demo;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public void setEinde(LocalDateTime einde) {
        this.einde = einde;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDemo(Demo demo) {
        this.demo = demo;
    }

}
