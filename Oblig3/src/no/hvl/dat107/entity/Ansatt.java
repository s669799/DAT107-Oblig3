package no.hvl.dat107.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ansatt", schema = "Oblig3")
public class Ansatt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ansatt_id;
	private String brukernavn;
	private String fornavn;
	private String etternavn;
	private LocalDate ansattdato;
	private String stilling;
	private int manedslonn;

	@ManyToOne
	@JoinColumn(name = "avdeling", referencedColumnName = "avdeling_id")
	public Avdeling avdeling;

	@OneToMany(mappedBy = "ansatt", cascade = CascadeType.PERSIST)
	private List<Prosjektdeltagelse> deltagelser;

	public Ansatt() {
	}

	public Ansatt(String brukernavn, String fornavn, String etternavn, String stilling, int manedslonn,
			Avdeling avdeling) {
		this.brukernavn = brukernavn;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		ansattdato = LocalDate.now();
		this.stilling = stilling;
		this.manedslonn = manedslonn;
		this.avdeling = avdeling;
		this.deltagelser = new ArrayList<Prosjektdeltagelse>();
	}

	public Ansatt(String brukernavn, String fornavn, String etternavn, String dato, String stilling, int manedslonn,
			Avdeling avdeling) {
		this.brukernavn = brukernavn;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.ansattdato = LocalDate.parse(dato);
		this.stilling = stilling;
		this.manedslonn = manedslonn;
		this.avdeling = avdeling;
		this.deltagelser = new ArrayList<Prosjektdeltagelse>();
	}

	public int getAnsattId() {
		return ansatt_id;
	}

	public String getBrukernavn() {
		return brukernavn;
	}

	public void setBrukernavn(String brukernavn) {
		this.brukernavn = brukernavn;
	}

	public String getFornavn() {
		return fornavn;
	}

	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}

	public String getEtternavn() {
		return etternavn;
	}

	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}

	public LocalDate getAnsattdato() {
		return ansattdato;
	}

	public void setAnsattdato(LocalDate ansattdato) {
		this.ansattdato = ansattdato;
	}

	public String getStilling() {
		return stilling;
	}

	public void setStilling(String stilling) {
		this.stilling = stilling;
	}

	public int getManedslonn() {
		return manedslonn;
	}

	public void setManedslonn(int manedslonn) {
		this.manedslonn = manedslonn;
	}

	public Avdeling getAvdeling() {
		return avdeling;
	}

	public void setAvdeling(Avdeling avdeling) {
		this.avdeling = avdeling;
	}

	public List<Prosjektdeltagelse> getDeltagelser() {
		return deltagelser;
	}

	public void leggTilDeltagelse(Prosjektdeltagelse deltagelse) {
		deltagelser.add(deltagelse);
	}

	public void setDeltagelser(List<Prosjektdeltagelse> deltagelser) {
		this.deltagelser = deltagelser;
	}

	public boolean erSjef() {

		if (this.avdeling.getSjef().equals(this)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Ansatt [Id: " + ansatt_id + ", " + brukernavn + ", navn=" + fornavn + " " + etternavn
				+ ", " + stilling + ", lønn: " + manedslonn + ", " + avdeling + "]";
	}

}
