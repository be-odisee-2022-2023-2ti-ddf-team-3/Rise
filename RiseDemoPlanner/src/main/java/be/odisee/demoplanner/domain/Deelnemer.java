package be.odisee.demoplanner.domain;

import java.util.List;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("Deelnemer")
public class Deelnemer extends Rol {

 	private static final long serialVersionUID = 1L;
	@OneToMany(fetch=FetchType.LAZY,mappedBy="m_Deelnemer")
    private List<Bijdrage> m_Bijdragen;

    public Deelnemer(){}

    public Deelnemer(String status, String usernaam, Sessie sessie, Persoon persoon){
        super(status,usernaam,sessie,persoon);
    }

    public Deelnemer(int id, String status, String usernaam, Sessie sessie, Persoon persoon){
        super(id,status,usernaam,sessie,persoon);
    }

    public List<Bijdrage> getBijdragen(){
            return m_Bijdragen;
    }

    public void voegBijdrageToe(Bijdrage newVal){
            m_Bijdragen.add(newVal);
    }

}