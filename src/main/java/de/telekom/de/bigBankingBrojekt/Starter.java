package de.telekom.de.bigBankingBrojekt;

import java.io.IOException;

import de.telekom.de.bigBankingBrojekt.Implementation.ApplicationImpl;
// import de.telekom.de.bigBankingBrojekt.Implementation.IBAN;
import de.telekom.de.bigBankingBrojekt.Implementation.IBANImpl;
// import de.telekom.de.bigBankingBrojekt.Implementation.IbanImpl;
// import de.telekom.de.bigBankingBrojekt.Implementation.Impl;
import de.telekom.de.bigBankingBrojekt.Interfaces.Application;

/** Klasse ist vergleich mit einer .exe, sie startet das Programm
*   Zwecck: Direkter Einstieg in die Objektorientierung
*/
public class Starter {

	
	public static void main(String[] args) throws Exception, IOException {
		
		
		
		Application application = new ApplicationImpl();
		/** nach Ausführung der Methode run, befinden wir uns in der Objektorientierung */
		application.run(args);
	}

}