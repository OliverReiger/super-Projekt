package de.telekom.de.bigBankingBrojekt.Implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.persistence.Entity;
import javax.persistence.Table;

import de.telekom.de.bigBankingBrojekt.Interfaces.Waehrung;

@Entity
@ManyToOne
@Table(name="waehrung")
public class WaehrungImpl implements Waehrung {
	
	private int id_waehrung;
	private String waehrung;
			
	public int getId_waehrung() {
		return id_waehrung;
	}
	public void setId_waehrung(int id_waehrung) {
		this.id_waehrung = id_waehrung;
	}
	public String getWaehrung() {
		return waehrung;
	}
	public void setWaehrung(String waehrung) {
		this.waehrung = waehrung;
	}
	
	

}
