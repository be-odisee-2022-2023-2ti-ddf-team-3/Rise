package be.odisee.demoplanner.domain;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("Idee")
public class Idee extends Bijdrage implements Serializable {

 	private static final long serialVersionUID = 1L;

	public Idee(){

    }

    public Idee(int id, String status, Deelnemer deelnemer, Bijdrage reactieOp, String tekst, Onderwerp onderwerp) {
        super(id, status, deelnemer, tekst, onderwerp);
        if (reactieOp != null) this.reactieOp = reactieOp;
    }

    public Idee(String status, Deelnemer deelnemer, Bijdrage reactieOp, String tekst, Onderwerp onderwerp) {
        super(status, deelnemer, tekst, onderwerp);
        if (reactieOp != null) this.reactieOp = reactieOp;
    }

    public Bijdrage getReactieOp(){
        return reactieOp;
    }

    public void setReactieOp(Bijdrage newVal){
        reactieOp = newVal;
    }

}