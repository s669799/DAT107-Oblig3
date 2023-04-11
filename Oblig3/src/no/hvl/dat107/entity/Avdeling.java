package no.hvl.dat107.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "avdeling", schema = "Oblig3")
public class Avdeling {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int avdeling_id;
	private String avdelingsnavn;
	
	@OneToOne(mappedBy = "avdeling")
	private Ansatt sjef;
	
	public Avdeling() {
		
	}
	
	public Avdeling(String avdeling) {
		this.avdelingsnavn = avdeling;
		this.sjef = null;
	}
	
	public Avdeling(String avdeling, Ansatt sjef) {
		this.avdelingsnavn = avdeling; 
		this.sjef = sjef;
	}
	

	public int getAvdeling_id() {
		return avdeling_id;
	}


	public String getAvdelingNavn() {
		return avdelingsnavn;
	}

	public void setAvdelingNavn(String avdeling) {
		this.avdelingsnavn = avdeling;
	}

	public Ansatt getSjef() {
		return sjef;
	}

	public void setSjef(Ansatt sjef) {
		this.sjef = sjef;
	}

	@Override
	public String toString() {
		return "Avdeling [avdeling_id=" + avdeling_id + ", avdelingsnavn=" + avdelingsnavn + ", sjef=" + sjef.getAnsattId() + ", " + sjef.getBrukernavn() + "]";
	}
	
	

}
