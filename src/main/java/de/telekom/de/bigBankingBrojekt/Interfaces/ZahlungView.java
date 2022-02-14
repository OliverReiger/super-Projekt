package de.telekom.de.bigBankingBrojekt.Interfaces;

public interface ZahlungView {
	/**
	 * Methode zum generieren der View (was für die Einzelausgabe notwendig ist)
	 */
	String detailOutput(Zahlungen zahlungen, int position) throws Exception;
	
	
	// Zahlung testZahlung();

}
