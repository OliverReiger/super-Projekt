package de.telekom.de.bigBankingBrojekt.Implementation;

import de.telekom.de.bigBankingBrojekt.Interfaces.Zahlung;
import de.telekom.de.bigBankingBrojekt.Interfaces.ZahlungView;
import de.telekom.de.bigBankingBrojekt.Interfaces.Zahlungen;

public class ZahlungViewImpl implements ZahlungView {

	/** ToDo: erweitern, das aus allen Überweisungen die Details angzeigt werden*/
	public String detailOutput(Zahlungen zahlungen, int position) throws Exception {
		Zahlung zahlung = zahlungen.get(position);
		return "Empfänger: " + zahlung.getEmpfaenger() + "\nIBAN Empfänger: " + zahlung.getEmpfaengerIBAN()
				+ "\nBIC Empfänger: " + zahlung.getEmpfaengerBIC() + "\nBetrag: " + zahlung.getBetrag() + " "
				+ zahlung.getWaehrung() + "\nVerwendungszweck: " + zahlung.getVerwendungszweck();

	}


	/**
	 * abmanagen und austauschen gegen eine Erfassung einer Einzelüberweisung
	 
	
	public Zahlung testZahlung() {
		// return new ZahlungImpl("Testkunde", "TestIBAN", "TestBIC", 222.22, "Eruo", "Test Verwendung", 0);
		
	}
	**/
}
