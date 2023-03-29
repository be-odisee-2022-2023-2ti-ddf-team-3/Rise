package be.odisee.demoplanner.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import org.hibernate.annotations.IndexColumn;

@Entity
@Table(name="onderwerpen")
public class Onderwerp implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    // we willen dit even aan de db overlaten
    // specifieke noden FitLibrary zullen we met een functie oplossen
    // private static int nextid=1;

    @Column
    private String status;

    @Column
    private String titel;

    @ManyToOne
    @JoinColumn(name="sessie_id")
    private Sessie sessie;

    @ManyToOne
    @JoinColumn(name="facilitator_id")
    private Facilitator m_Facilitator;

    @OneToOne
    @JoinColumn(name="vorig_onderwerp_id")
    private Onderwerp vorigOnderwerp;

    @OneToMany(fetch=FetchType.EAGER,mappedBy="onderwerp")
    @IndexColumn(name="rangId",base=1)
    private List<Bijdrage> m_Bijdragen = new ArrayList<Bijdrage>();

    public Onderwerp(){}

    public Onderwerp(String status, String titel, Facilitator facilitator, Sessie sessie) {
        this.status=status;
        this.titel=titel;
        this.m_Facilitator=facilitator;
        this.sessie=sessie;
    }

    public Onderwerp(int id, String status, String titel, Facilitator facilitator, Sessie sessie) {
        this.id =id;
        this.status=status;
        this.titel=titel;
        this.m_Facilitator=facilitator;
        this.sessie=sessie;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getTitel() {
        return titel;
    }

    public Onderwerp getvorigOnderwerp(){
        return vorigOnderwerp;
    }

    public void setvorigOnderwerp(Onderwerp newVal){
        vorigOnderwerp = newVal;
    }

    public List<Bijdrage> getBijdragen(){
        return m_Bijdragen;
    }

    // TODO deze en andere operaties (ook in andere klassen
    // met een versie met id en zonder id te refactoren (dubbele code)

    public Bijdrage voegBijdrageToe(int id, Deelnemer deelnemer, String type, Bijdrage reactieOp, String tekst)
            throws Exception{

        System.out.println("DEBUG onderwerp.voegBijdrageToe: onderwerpId="+this.id+
                                ", statusOnderwerp="+this.status+
                                ", deelnemerUserId="+deelnemer.getUsernaam()+
                                ", type="+type+
                                ", reactieOpBijdrageId="+reactieOp+
                                ", tekst="+tekst);

        if (status.equals("gesloten")) return null;

        Bijdrage newBijdrage=null;
        if (type.equals("Idee")) newBijdrage= new Idee(id, "actief", deelnemer, reactieOp, tekst, this);
        if (type.equals("Reactie")) newBijdrage= reactieOp.voegReactieToe(id,deelnemer, tekst, this);
        m_Bijdragen.add(newBijdrage);
        return newBijdrage;
    }

    public Bijdrage voegBijdrageToe(Deelnemer deelnemer, String type, Bijdrage reactieOp, String tekst)
            throws Exception{

        if (status.equals("gesloten")) return null;

        Bijdrage newBijdrage=null;
        if (type.equals("Idee")) newBijdrage= new Idee("actief", deelnemer, reactieOp, tekst,this);
        if (type.equals("Reactie")) newBijdrage= reactieOp.voegReactieToe(deelnemer, tekst,this);
        m_Bijdragen.add(newBijdrage);
        return newBijdrage;
    }

    public void veranderOnderwerp(){
        if (status.equals ("vorig")) status = "gesloten";
        if (status.equals("actueel")) status = "vorig";
    }

    void toonOnderwerpResultaten() {
        // bedoeld voor preliminaire testen
        System.out.println();
        for (Bijdrage bijdrage : m_Bijdragen){
            System.out.println("Bijdrage: '"+bijdrage.getTekst()+"'");
        }
    }
}