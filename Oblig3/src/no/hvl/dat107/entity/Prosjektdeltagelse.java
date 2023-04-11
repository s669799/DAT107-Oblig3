package no.hvl.dat107.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "prosjektdeltagelse", schema = "Oblig3")
public class Prosjektdeltagelse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int deltagelse_id;

	private String rolle;
	private int timer;

	@ManyToOne
	@JoinColumn(name = "ansatt_id")
	private Ansatt ansatt;

	@ManyToOne
	@JoinColumn(name = "prosjekt_id")
	private Prosjekt prosjekt;

	public Prosjektdeltagelse() {
	}
	
	public Prosjektdeltagelse(Ansatt ansatt, Prosjekt prosjekt, int timer) {
        this.ansatt = ansatt;
        this.prosjekt = prosjekt;
        this.timer = timer;
	}

}
