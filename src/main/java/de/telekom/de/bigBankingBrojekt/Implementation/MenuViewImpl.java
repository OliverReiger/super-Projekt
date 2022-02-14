package de.telekom.de.bigBankingBrojekt.Implementation;

import java.io.IOException;
import java.util.Scanner;

import de.telekom.de.bigBankingBrojekt.Interfaces.MenuView;
import de.telekom.de.bigBankingBrojekt.Interfaces.ZahlungView;
import de.telekom.de.bigBankingBrojekt.Interfaces.Zahlungen;
import de.telekom.de.bigBankingBrojekt.Interfaces.ZahlungenView;

public class MenuViewImpl implements MenuView {

	ZahlungView zahlungView = new ZahlungViewImpl();
	ZahlungenView zahlungenView = new ZahlungenViewImpl();
	Zahlungen zahlungen = new ZahlungenImpl();
	ZahlungenDBImpl zahlungenNeu = new ZahlungenDBImpl();
	
	public void menu() throws Exception {
		String eingabe = "";
		Scanner scanner = new Scanner(System.in);
		/** Scanner für Eingabe geöffnet */
		
		// while (!eingabe.equals("exit")) {
			System.out.println("");
			System.out.println("Bitte wähle die gewünschte Aktion aus:");
			System.out.println("");
			System.out.println("--- WÄHRUNG --------------------------");
			System.out.println("10: Neue Währung anlegen");
			System.out.println("11: Vorhandene Währung löschen");
			System.out.println("12: Alle Währungen anzeigen");
			System.out.println("");
			System.out.println("--- EMPFÄNGER ------------------------");
			System.out.println("20: Neuen Empfänger anlegen");
			System.out.println("21: Vorhandenen Empfänger löschen");
			System.out.println("22: Alle Empfänger anzeigen");
			System.out.println("");
			System.out.println("--- IBAN -----------------------------");
			System.out.println("30: Neue IBAN anlegen");
			System.out.println("31: Vorhandenen IBAN löschen");
			System.out.println("32: Alle IBANs anzeigen");
			System.out.println("");
			System.out.println("--- BIC ------------------------------");
			System.out.println("40: Neue BIC anlegen");
			System.out.println("41: Vorhandenen BIC löschen");
			System.out.println("42: Alle BICs anzeigen");
			System.out.println("");
			System.out.println("--- ZAHLUNGEN ------------------------");
			System.out.println("50: Neue Zahlung anlegen");
			System.out.println("51: Vorhandenen Zahlung löschen");
			System.out.println("52: Alle Zahlungen anzeigen (einfach)");
			System.out.println("53: Detailansicht einer Zahlung");
			System.out.println("");
			System.out.println("exit: Programm verlassen");
			System.out.println("");
			eingabe = scanner.nextLine();

			switch (eingabe) {
			// Aktive Menüauswahl
			case "10":
				System.out.println("Gib den Namen der neuen Währung ein, die der Datenbank hinzugefügt werden soll:");
				String inputWaehrung = scanner.nextLine();
				zahlungenNeu.insertOneWaehrung(inputWaehrung);
				break;
			case "11":
				System.out.println("Gib die ID der zu löschenden Währung ein:");
				System.out.println("Beachte, dass du nur Währungen löschen kannst, die derzeit NICHT AKTIV in einer Zahlung verwendet wurden!");
				int deleteWaehrung = scanner.nextInt();
				zahlungenNeu.deleteOneWaehrung(deleteWaehrung);
				break;
			case "12":
				zahlungenNeu.readAllWaehrungen();
				break;
			case "20":
				System.out.println("Gib den Namen des Empfängers ein:");
				String inputEmpfaenger = scanner.nextLine();
				System.out.println("Gib die zugehörige IBAN ID für diesen Empfänger ein");
				System.out.println("Beachte das die IBAN ID auch existieren muss.");
				int inputEmpfaengerIbanId = scanner.nextInt();
				zahlungenNeu.addOneEmpfaenger(inputEmpfaenger, inputEmpfaengerIbanId);
				break;
			case "21":
				System.out.println("Gib die ID des zu löschenden Empfängers ein:");
				System.out.println("Beachte, dass du nur Empfänger löschen kannst, die derzeit NICHT AKTIV in einer Zahlung verwendet wurden!");
				int deleteEmpfaenger = scanner.nextInt();
				zahlungenNeu.deleteOneEmpfaenger(deleteEmpfaenger);
				break;
			case "22":
				zahlungenNeu.readAllWaehrungen();
				break;
			case "30":
				System.out.println("Gib die neue IBAN ein (max. 12 Zeichen):");
				String inputIBAN = scanner.nextLine();
				zahlungenNeu.addOneIban(inputIBAN);
				break;
			case "31":
				System.out.println("Gib die ID der zu löschenden IBAN ein:");
				System.out.println("Beachte, dass du nur IBANs löschen kannst, die derzeit NICHT AKTIV in einer Zahlung verwendet oder einem Empfänger zugeordnet wurden!");
				int deleteIBAN = scanner.nextInt();
				zahlungenNeu.deleteOneIban(deleteIBAN);
				break;
			case "32":
				zahlungenNeu.readAllIban();
				break;
			case "40":
				System.out.println("Gib die neue BIC ein (max. 8 Zeichen):");
				String inputBIC = scanner.nextLine();
				System.out.println("Gib den dazugehörigen Banknamen ein:");
				String inputBankname = scanner.nextLine();
				zahlungenNeu.addOneBic(inputBIC, inputBankname);
				break;
			case "41":
				System.out.println("Gib die ID der zu löschenden BIC ein:");
				System.out.println("Beachte, dass du nur BICs löschen kannst, die derzeit NICHT AKTIV in einer Zahlung verwendet oder einem Empfänger zugeordnet wurden!");
				int deleteBic = scanner.nextInt();
				zahlungenNeu.deleteOneBic(deleteBic);
				break;
			case "42":
				zahlungenNeu.readAllbic();
				break;
			case "50":
				System.out.println("Gib den Empfänger ein:");
				String inputZahlungEmpfaenger = scanner.nextLine();
				System.out.println("Gib die IBAN des Empfängers ein:");
				String inputZahlungIBAN = scanner.nextLine();
				System.out.println("Gibt die BIC des Empfängers ein (max. 8 Zeichen):");
				String inputZahlungBIC = scanner.nextLine();
				System.out.println("Gib den dazugehörigen Banknamen ein:");
				String inputZahlungBankname = scanner.nextLine();
				System.out.println("Gib den Verwendungszweck an:");
				String inputZahlungZweck = scanner.nextLine();
				System.out.println("Gib die Währung ein:");
				String inputZahlungWaehrung = scanner.nextLine();
				System.out.println("Gib den Betrag ein:");
				Double inputZahlungBetrag = scanner.nextDouble();
				zahlungenNeu.addOneZahlung(inputZahlungEmpfaenger, inputZahlungIBAN, inputZahlungBIC, inputZahlungBankname, inputZahlungBetrag, inputZahlungWaehrung, inputZahlungZweck);
				break;
			case "52":
				System.out.println("Du hast dich für die Übersicht aller Überweisung entschieden. Hier bitte: \n ");
				// zahlungenView.multiOutput(zahlungen);
				zahlungenNeu.readAll();
				break;
			case "53":
				System.out.println("Du hast dich für die Anzeige einer Überweisung entschieden. gib bitte die Position an: \n ");
				int input = scanner.nextInt();
				// System.out.println(zahlungView.detailOutput(zahlungen, input));
				zahlungenNeu.readOne(input);
				break;
			case "exit":
				System.out.println("Schön, dass du da warst. Tschüss \n ");
				scanner.close(); /** !!!Scanner für Eingabe bis zum Neustart des Programms geschlossen */
				break;
				
			// Nicht mehr genutzte Menüpunkte	
			case "exportAll":
				System.out.println("Ihre Daten wurden erfolgreich in einer externen Datei gespeichert.  \n ");
				zahlungen.multiExport(zahlungen);
				break;
			case "importAll":
				System.out.println("Deine Daten wurden aus einer Datei importiert. \n ");
				zahlungen.multiImport();
				break;

			}
		}
	}

// }
