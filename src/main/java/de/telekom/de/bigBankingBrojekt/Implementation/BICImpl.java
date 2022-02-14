package de.telekom.de.bigBankingBrojekt.Implementation;

import javax.persistence.Entity;
import javax.persistence.Table;

import de.telekom.de.bigBankingBrojekt.Interfaces.BIC;

@Entity
@ManyToOne
@Table(name="bic")
public class BICImpl implements BIC {
	
	private int id_bic;
	private String bic;
	private String bankname;
	
	
	public int getId_bic() {
		return id_bic;
	}
	public void setId_bic(int id_bic) {
		this.id_bic = id_bic;
	}
	public String getBic() {
		return bic;
	}
	public void setBic(String bic) {
		this.bic = bic;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	
	

}
