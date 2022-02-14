package de.telekom.de.bigBankingBrojekt.Implementation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import de.telekom.de.bigBankingBrojekt.Interfaces.Zahlung;
import de.telekom.de.bigBankingBrojekt.Interfaces.Zahlungen;

/**
 * ZahlungenImpl hat 2 eigene Methoden (multiImport und multiExport) extends
 * GenericListImpl<Zahlung> = Erweiterung der ZahlungenImpl um Attribute und
 * Methoden der GenericList + der Konkretisierung das es sich ab hier nicht mehr
 * ein Template ist, sondern eine ausgefülltes Tamplate "Zahlungen" handelt
 * implements Zahlungen = wir benutzen über das Interface die Methoden der
 * ZahlungenImpl
 */
public class ZahlungenImpl extends GenericListImpl<Zahlung> implements Zahlungen {
	
	private int counter = 0;    /** das ist der Counter, er zählt die Position mit, 
								*   dem wir später einem neu erzeugten Zahlungsobjekt mitgeben wollen */
	
	
	/**
	 * /* Zahlungen this * / = unsichtbarer Parameter der beim Aufruf der Methode
	 * mit übergeben wird im konkreten Fall wird die Methode von MenuViewImpl
	 * aufgerufen, da in der MenuViewImpl zahlungen in der Zeile 15 initialisiert
	 * wurden, wird in der Zeile 44 beim Aufruf der Methode dieser Wert mitgegeben
	 * und kann somit in Zeile 37 (hier) verwendet werden
	 */
	public void multiImport(/** Zahlungen this */) throws IOException {
		String line = "";
		String trenner = ";";
		try {
			String filePath = "/home/sea003/eclipse-workspace/super-Projekt/super-Projekt/testInput.csv";
			FileReader fileReader = new FileReader(filePath);

			BufferedReader reader = new BufferedReader(fileReader);
			while ((line = reader.readLine()) != null) {
				String[] ausgeleseneWert = line.split(trenner);
				// Zahlung temp = new ZahlungImpl(ausgeleseneWert[0], " ", " ", Double.parseDouble(ausgeleseneWert[1]),
					//	ausgeleseneWert[2], " ", this.counter);
				// this.add(temp);
				counter++;
								
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Generelle Variable um den Namen für die Export an einer zentralen Stelle
	 * festzulegen
	 */
	private final static String CSV_FILE_NAME = "ExportAll.csv";

	public void multiExport(Zahlungen zahlungen) throws Exception, IOException {

		File csvOutputFile = new File(CSV_FILE_NAME);
		try (PrintWriter pw = new PrintWriter(
				csvOutputFile)) /**
								 * beinhaltet durch die () das close, try > try with ressources
								 */
		{
			pw.write("Empfaenger; Betrag; Waehrung\n");
			for (Object multiPayment : zahlungen) {
				Zahlung tempZahlung = (Zahlung) multiPayment;
				String tempSaveLine = tempZahlung.getEmpfaenger() + ";" + tempZahlung.getBetrag() + ";"
						+ tempZahlung.getWaehrung() + "\n";
				pw.write(tempSaveLine);
			}

		}

	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
}
