package be.odisee.demoplanner.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="sessies")
public class Sessie implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column
    private String status;

    @Column
    private String titel;

    @Column
    @Temporal(jakarta.persistence.TemporalType.TIMESTAMP)
    private Date begin_tijdstip;

    @Column
    @Temporal(jakarta.persistence.TemporalType.TIMESTAMP)
    private Date eind_tijdstip;

    @OneToMany(fetch=FetchType.EAGER,mappedBy="sessie")
    private List<Onderwerp> m_Onderwerpen = new ArrayList<Onderwerp>();

    public Sessie(){}

    public Sessie(int id, String status, String titel) {
        this.id = id;
        this.status = status;
        this.titel = titel;
    }

    public Sessie(String status, String titel) {
        this.status = status;
        this.titel = titel;
    }

    public int getId() {
        return id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public Date getBegin() {
        return begin_tijdstip;
    }

    public void setBegin(Date begin) {
        this.begin_tijdstip = begin;
    }

    public Date getEinde() {
        return eind_tijdstip;
    }

    public void setEinde(Date einde) {
        this.eind_tijdstip = einde;
    }

    public List<Onderwerp> getOnderwerpen(){
        return m_Onderwerpen;
    }

    public Onderwerp voegOnderwerpToe
            (int id, Facilitator facilitator,Onderwerp vorigOnderwerp,String tekstOnderwerp){

        Onderwerp newVal = new Onderwerp(id,"actueel",tekstOnderwerp,facilitator,this);

        roteerOnderwerp(newVal, vorigOnderwerp);

        m_Onderwerpen.add(newVal);
        return newVal;
    }

    public Onderwerp voegOnderwerpToe
            (Facilitator facilitator,Onderwerp vorigOnderwerp,String tekstOnderwerp){

        Onderwerp newVal = new Onderwerp("actueel",tekstOnderwerp,facilitator,this);

        roteerOnderwerp(newVal, vorigOnderwerp);
        
        m_Onderwerpen.add(newVal);
        return newVal;
    }

    // gemeenschappelijk aan die met en zonder id
    private void roteerOnderwerp(Onderwerp nieuwOnderwerp, Onderwerp vorigOnderwerp){

        nieuwOnderwerp.setvorigOnderwerp(vorigOnderwerp);

        // het vorige "Actueel" onderwerp moet "Vorig" onderwerp worden
        if (vorigOnderwerp != null) vorigOnderwerp.veranderOnderwerp();

        // het vorige "Vorig" onderwerp moet "Gesloten" onderwerp worden
        if (vorigOnderwerp != null) {
            Onderwerp voorVorigOnderwerp = vorigOnderwerp.getvorigOnderwerp();
            if (voorVorigOnderwerp != null) voorVorigOnderwerp.veranderOnderwerp();
        }

    }

    public void toonSessieResultaten() {
        // bedoeld voor preliminaire testen
        for (Onderwerp onderwerp : m_Onderwerpen){
            System.out.println("\nResultaten voor onderwerp: '"+onderwerp.getTitel()+"'");
            onderwerp.toonOnderwerpResultaten();
        }
    }
}






























