package de.telekom.de.bigBankingBrojekt.Implementation;

import javax.persistence.Entity;
import javax.persistence.Table;

import de.telekom.de.bigBankingBrojekt.Interfaces.Empfaenger;

@Entity
@ManyToOne
@Table(name="empfaenger")
public class EmpfaengerImpl implements Empfaenger {
	
	private int id_empfaenger;
	private String empfaenger;
	private int iban_id;
	
	
	public int getId_empfaenger() {
		return id_empfaenger;
	}
	public void setId_empfaenger(int id_empfaenger) {
		this.id_empfaenger = id_empfaenger;
	}
	public String getEmpfaenger() {
		return empfaenger;
	}
	public void setEmpfaenger(String empfaenger) {
		this.empfaenger = empfaenger;
	}
	public int getIban_id() {
		return iban_id;
	}
	public void setIban_id(int iban_id) {
		this.iban_id = iban_id;
	}
	
}
