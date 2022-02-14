package de.telekom.de.bigBankingBrojekt.Implementation;

import javax.persistence.Entity;
import javax.persistence.Table;

import de.telekom.de.bigBankingBrojekt.Interfaces.IBAN;

@Entity
@ManyToOne
@Table(name="iban")
public class IBANImpl implements IBAN {
	
	private int	id_iban;
	private String empfaengerIBAN;
	
	public int getId_iban() {
		return id_iban;
	}
	public void setId_iban(int id_iban) {
		this.id_iban = id_iban;
	}
	public String getEmpfaengerIBAN() {
		return empfaengerIBAN;
	}
	public void setEmpfaengerIBAN(String empfaengerIBAN) {
		this.empfaengerIBAN = empfaengerIBAN;
	}
	
}
