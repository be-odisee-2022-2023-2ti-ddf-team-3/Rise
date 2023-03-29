package be.odisee.demoplanner.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import org.hibernate.annotations.IndexColumn;

@Entity
@Table(name="bijdragen")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type",discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("Bijdrage")
public abstract class Bijdrage implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    protected int id;

    @Column
    protected String status;

    @Column
    protected String tekst;

    @ManyToOne
    @JoinColumn(name="deelnemer_id")
    protected Deelnemer m_Deelnemer;

    @ManyToOne
    @JoinColumn(name="onderwerp_id",nullable=false)
    private Onderwerp onderwerp;

    // hebben we nodig om IndexColumn te kunnen gebruien (bag vermijden)
    @Column
    private int rangId;

    @ManyToOne
    @JoinColumn(name="reactie_op_id")
    protected Bijdrage reactieOp;

    @OneToMany(fetch=FetchType.EAGER,mappedBy="reactieOp")
    @IndexColumn(name="id")
    private List<Bijdrage> m_Reacties = new ArrayList<Bijdrage>();


    public Bijdrage(){}

    public Bijdrage(int id, String status, Deelnemer m_Deelnemer, String tekst, Onderwerp onderwerp) {
        this.id = id;
        this.status = status;
        this.m_Deelnemer = m_Deelnemer;
        this.tekst = tekst;
        this.onderwerp = onderwerp;
        this.rangId = onderwerp.getBijdragen().size()+1;
    }

    public Bijdrage(String status, Deelnemer m_Deelnemer, String tekst, Onderwerp onderwerp) {
        this.status = status;
        this.m_Deelnemer = m_Deelnemer;
        this.tekst = tekst;
        this.onderwerp = onderwerp;
        this.rangId = onderwerp.getBijdragen().size()+1;
    }

    public int getId() {
        return id;
    }

    public String getTekst() {
        return tekst;
    }

    public String getUserid(){
        return m_Deelnemer.getUsernaam();
    }

    public int getReactieOpBijdrageId() {
        if (reactieOp != null) return reactieOp.getId();
        else return 0;
    }

    public String getType(){
        String fullyQualifiedClassname = this.getClass().toString();
        if (fullyQualifiedClassname.contains("Idee")) return("Idee");
        else if(fullyQualifiedClassname.contains("Reactie")) return ("Reactie");
        else throw new RuntimeException();
    }

    public Reactie voegReactieToe(int id, Deelnemer deelnemer, String tekst, Onderwerp onderwerp) throws Exception{
        Reactie newReactie=null;
        newReactie= new Reactie(id, "actief", deelnemer, this, tekst, onderwerp);
        m_Reacties.add(newReactie);
        return newReactie;
    }

    public Reactie voegReactieToe(Deelnemer deelnemer, String tekst, Onderwerp onderwerp) throws Exception{
        Reactie newReactie=null;
        newReactie= new Reactie("actief", deelnemer, this, tekst, onderwerp);
        m_Reacties.add(newReactie);
        return newReactie;
    }

    public List<Bijdrage> getReacties() {
        return m_Reacties;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setM_Deelnemer(Deelnemer m_Deelnemer) {
        this.m_Deelnemer = m_Deelnemer;
    }

    public void setM_Reacties(List<Bijdrage> m_Reacties) {
        this.m_Reacties = m_Reacties;
    }

    public void setOnderwerp(Onderwerp onderwerp) {
        this.onderwerp = onderwerp;
    }

    public void setRangId(int rangId) {
        this.rangId = rangId;
    }

    public void setReactieOp(Bijdrage reactieOp) {
        this.reactieOp = reactieOp;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }


    public boolean verwijderBijdrage(){
        /* moet nog uitgewerkt worden */
        return false;
    }

    public boolean verwijderReactie(Reactie deReactie){
        /* moet nog uitgewerkt worden */
        return false;
    }

}