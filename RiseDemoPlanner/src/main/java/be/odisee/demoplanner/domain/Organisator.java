package be.odisee.demoplanner.domain;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("Organisator")
public class Organisator extends Rol {

    // nu dit nog niet doen @OneToMany(fetch=FetchType.LAZY,mappedBy="organisator")
    // private List<Sessie> sessies;

	private static final long serialVersionUID = 1L;

	public Organisator(){}

    public Organisator(String status, String usernaam, Sessie sessie, Persoon persoon){
        super(status,usernaam,sessie,persoon);
    }

    public Organisator(int id, String status, String usernaam, Sessie sessie, Persoon persoon){
        super(id,status,usernaam,sessie,persoon);
    }

    // nu dit nog niet doen @Override
    public String getType() {
        return "Organisator";
    }

}