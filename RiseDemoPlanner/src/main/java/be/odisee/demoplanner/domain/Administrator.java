package be.odisee.demoplanner.domain;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("Administrator")
public class Administrator extends Rol {

 	private static final long serialVersionUID = 1L;

	public Administrator(){}

    public Administrator(String status, String usernaam, Sessie sessie, Persoon persoon){
        super(status,usernaam,sessie,persoon);
    }

    public Administrator(int id, String status, String usernaam, Sessie sessie, Persoon persoon){
        super(id,status,usernaam,sessie,persoon);
    }

    // nu nog niet @Override
    public String getType() {
        return "Administrator";
    }

}