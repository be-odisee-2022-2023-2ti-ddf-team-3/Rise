package be.odisee.demoplanner.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

// @Index bij een veld is deprecated, 
// jakarta.persistence.Index is te gebruiken en 
// die moet als deel van @Table worden meegegeven
@Entity
@Table(name="ambassadeur",
		indexes = { @Index(name="IAmbassadeur_naam",columnList="familienaam, voornaam"),
                    @Index(name="IAmbassadeur_email",columnList="emailadres")
                } )
public class Persoon implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) // HV 201804015 tbv Gebruik AutoIncrement
    private int id;


    @Column
    private String voornaam;

    @Column
    private String familienaam;

    @Column
    private String emailadres;

    @Column
    private String paswoord;

    public Persoon(){

    }

    public Persoon( String voornaam, String familienaam, String emailadres, String paswoord) {
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.emailadres = emailadres;
        this.paswoord = paswoord;
    }

    public Persoon(int id, String voornaam, String familienaam, String emailadres, String paswoord) {
        this.id = id;
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.emailadres = emailadres;
        this.paswoord = paswoord;
    }

    public int getId() {
        return id;
    }

    public String getEmailadres() {
        return emailadres;
    }

    public String getPaswoord() {
        return paswoord;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public void setEmailadres(String emailadres) {
        this.emailadres = emailadres;
    }

    public void setFamilienaam(String familienaam) {
        this.familienaam = familienaam;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPaswoord(String paswoord) {
        this.paswoord = paswoord;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

}