package de.telekom.de.bigBankingBrojekt.Implementation;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.Entity;
import javax.persistence.Table;

import de.telekom.de.bigBankingBrojekt.Interfaces.Zahlung;

@Entity
@ManyToOne
@Table(name="zahlung")
public class ZahlungenDBImpl {
	
	// Instanz für DB Connection zur MariaDB
	MariaDBConnectorImpl db = new MariaDBConnectorImpl();
	WaehrungDBImpl wdb = new WaehrungDBImpl();
	
	public void readAllWaehrungen() {
		try {
			wdb.startDBConnectionWaehrung();
			ResultSet rs = wdb.select("SELECT * FROM waehrung");
			String ueb1 = String.format("|%-5s|", "ID");
			String ueb2 = String.format("%-30s|","Waehrung");
			System.out.println( ueb1 + ueb2);
				while (rs.next()) {
					String str1 = String.format("|%-5d|", rs.getInt(1));    // id_waehrung
					String str2 = String.format("%-30s|", rs.getString(2)); // waehrung
					System.out.println( str1 + str2);
				}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
			wdb.stopDBConnectionWaehrung();
	}
	
	
	public void insertOneWaehrung(String waehrung) {
		try {
			wdb.startDBConnectionWaehrung();
			wdb.insertWaehrung(waehrung);
			wdb.stopDBConnectionWaehrung();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void deleteOneWaehrung(int idWaehrung) {
		try {
			wdb.startDBConnectionWaehrung();
			wdb.deleteWaehrung(idWaehrung);
			wdb.stopDBConnectionWaehrung();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*--------------------------------------------------------
	 * METHODEN ZUM ANZEIGEN, EINFÜGEN, LÖSCHEN VON WÄHRUNGEN
	 *--------------------------------------------------------*/
	
	public void addOneWaehrung(String waehrung) throws SQLException {
		db.startDBConnection();
		db.insertWaehrungPrepared("INSERT INTO waehrung (waehrung) values ( ? )", waehrung);
		System.out.println("Die Waehrung wurde erfasst.");
		db.stopDBConnection();
	}
	
	public void deleteOneWaehrungOld(int id_waehrung) throws SQLException {
		db.startDBConnection();
		db.deleteWaehrungPrepared("DELETE FROM waehrung WHERE id_waehrung =  ? ", id_waehrung);
		System.out.println("Die Waehrung wurde gelöscht.");
		db.stopDBConnection();
	}
	
	public void readAllOldWaehrungen() {
		try {
			db.startDBConnection();
			ResultSet rs = db.selectWaehrung("SELECT * FROM waehrung");
			System.out.println("");
			System.out.println("-------------------------------------");
			String ueb1 = String.format("|%-5s|", "ID");
			String ueb2 = String.format("%-30s|","Empfaenger");
			System.out.println(ueb1 + ueb2);
			System.out.println("-------------------------------------");
			while (rs.next()) {
				String str1 = String.format("|%-5d|", rs.getInt(1));        // id_waehrung
				String str2 = String.format("%-30s|", rs.getString(2));     // waehrung
				System.out.println( str1 + str2);
		}
			System.out.println("-------------------------------------");
			System.out.println("");
			db.stopDBConnection();
		}	catch (SQLException e) {
			System.out.println(e.getMessage());
			}
	}
	
	/*--------------------------------------------------------
	 * METHODEN ZUM ANZEIGEN, EINFÜGEN, LÖSCHEN VON EMPFÄNGERN
	 *--------------------------------------------------------*/
	
	public void addOneEmpfaenger(String empfaenger, int iban_id) throws SQLException {
		db.startDBConnection();
		db.insertEmpfaengerPrepared("INSERT INTO empfaenger (empfaenger, iban_id) values ( ?, ? )", empfaenger, iban_id);
		System.out.println("Die Waehrung wurde erfasst.");
		db.stopDBConnection();
	}
	
	public void deleteOneEmpfaenger(int id_empfaenger) throws SQLException {
		db.startDBConnection();
		db.deleteEmpfaengerPrepared("DELETE FROM empfaenger WHERE id_empfaenger =  ? ", id_empfaenger);
		System.out.println("Die Waehrung wurde gelöscht.");
		db.stopDBConnection();
	}
	
	public void readAllEmpfaenger() {
		try {
			db.startDBConnection();
			ResultSet rs = db.selectEmpfaenger("SELECT * FROM empfaenger");
			System.out.println("");
			System.out.println("------------------------------------------------");
			String ueb1 = String.format("|%-5s|", "ID");
			String ueb2 = String.format("%-30s|","Empfaenger");
			String ueb3 = String.format("%-10s|","IBAN_ID");
			System.out.println(ueb1 + ueb2 + ueb3);
			System.out.println("------------------------------------------------");
			while (rs.next()) {
				String str1 = String.format("|%-5d|", rs.getInt(1));        // id_waehrung
				String str2 = String.format("%-30s|", rs.getString(2));     // waehrung
				String str3 = String.format("%-10d|", rs.getInt(3));         // IBAN_id
				System.out.println( str1 + str2 + str3);
		}
			System.out.println("-----------------------------------------------");
			System.out.println("");
			db.stopDBConnection();
		}	catch (SQLException e) {
			System.out.println(e.getMessage());
			}
	}
	
	
	
	/*--------------------------------------------------------
	 * METHODEN ZUM ANZEIGEN, EINFÜGEN, LÖSCHEN VON IBANs
	 *--------------------------------------------------------*/
	
	public void addOneIban(String iban) throws SQLException {
		db.startDBConnection();
		db.insertIbanPrepared("INSERT INTO iban (iban) values ( ? )", iban);
		System.out.println("Die IBAN wurde erfasst.");
		db.stopDBConnection();
	}
	
	public void deleteOneIban(int id_iban) throws SQLException {
		db.startDBConnection();
		db.deleteIbanPrepared("DELETE FROM iban WHERE id_iban =  ? ", id_iban);
		System.out.println("Die IBAN wurde gelöscht.");
		db.stopDBConnection();
	}
	
	public void readAllIban() {
		try {
			db.startDBConnection();
			ResultSet rs = db.selectIban("SELECT * FROM iban");
			System.out.println("");
			System.out.println("--------------------");
			String ueb1 = String.format("|%-5s|", "ID");
			String ueb2 = String.format("%-12s|","IBAN");
			System.out.println(ueb1 + ueb2);
			System.out.println("--------------------");
			while (rs.next()) {
				String str1 = String.format("|%-5d|", rs.getInt(1));        // id_waehrung
				String str2 = String.format("%-12s|", rs.getString(2));     // IBAN
				System.out.println( str1 + str2);
		}
			System.out.println("--------------------");
			System.out.println("");
			db.stopDBConnection();
		}	catch (SQLException e) {
			System.out.println(e.getMessage());
			}
	}
	
	
	/*--------------------------------------------------------
	 * METHODEN ZUM ANZEIGEN, EINFÜGEN, LÖSCHEN VON BICs
	 *--------------------------------------------------------*/
	
	public void addOneBic(String bic, String bankname) throws SQLException {
		db.startDBConnection();
		db.insertIbanPrepared("INSERT INTO bic (bic, bankname) values ( ?, ? )", bic, bankname);
		System.out.println("Die BIC wurde erfasst.");
		db.stopDBConnection();
	}
	
	public void deleteOneBic(int id_bic) throws SQLException {
		db.startDBConnection();
		db.deleteIbanPrepared("DELETE FROM bic WHERE id_bic =  ? ", id_bic);
		System.out.println("Die BIC wurde gelöscht.");
		db.stopDBConnection();
	}
	
	public void readAllbic() {
		try {
			db.startDBConnection();
			ResultSet rs = db.selectBic("SELECT * FROM bic");
			System.out.println("");
			System.out.println("----------------------------------------------");
			String ueb1 = String.format("|%-5s|", "ID");
			String ueb2 = String.format("%-8s|","BIC");
			String ueb3 = String.format("%-30s|","Bankname");
			System.out.println(ueb1 + ueb2 + ueb3);
			System.out.println("----------------------------------------------");
			while (rs.next()) {
				String str1 = String.format("|%-5d|", rs.getInt(1));        // id_bic
				String str2 = String.format("%-8s|", rs.getString(2));     // BIC
				String str3 = String.format("%-30s|", rs.getString(3));     // Bankname
				System.out.println( str1 + str2 + str3);
		}
			System.out.println("----------------------------------------------");
			System.out.println("");
			db.stopDBConnection();
		}	catch (SQLException e) {
			System.out.println(e.getMessage());
			}
	}
	
	
	/*--------------------------------------------------------
	 * METHODEN ZUM ANZEIGEN, EINFÜGEN, LÖSCHEN VON ZAHLUNGEN
	 *--------------------------------------------------------*/
	
	public void addOneZahlung(String empfaenger, String iban, String bic, String bankname, Double betrag, String waehrung, String verwendungszweck) throws SQLException {
		db.startDBConnection();
		db.insertZahlungPrepared(empfaenger, iban, bic, bankname, betrag, waehrung, verwendungszweck);
		System.out.println("Die BIC wurde erfasst.");
		db.stopDBConnection();
			}
	
	public void readOne(int zahlung_ID) throws SQLException {
		db.startDBConnection();
		ResultSet rs = db.selectPrepared("SELECT * FROM allDataViewNew2 WHERE id_zahlung = ( ? )", zahlung_ID);
		
		System.out.println("");
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
			String ueb1 = String.format("|%-5s|", "ID"); 
			String ueb2 = String.format("%-30s|","Empfaenger");
			String ueb3 = String.format("%-12s|","IBAN");
			String ueb4 = String.format("%-8s|","BIC");
			String ueb5 = String.format("%-15s|","Bankname");
			String ueb6 = String.format("%-10s|","Betrag");
			String ueb7 = String.format("%-20s|","Waehrung");
			String ueb8 = String.format("%-25s|","Verwendungszweck");
		System.out.println(ueb1 + ueb2 + ueb3 + ueb4 + ueb5 + ueb6 + ueb7 + ueb8);
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
		while (rs.next()) {
			String str1 = String.format("|%-5d|", rs.getInt(1));        // id_zahlung
			String str2 = String.format("%-30s|", rs.getString(2));     // Empfänger
			String str3 = String.format("%-12s|", rs.getString(3));     // IBAN
			String str4 = String.format("%-8s|", rs.getString(4));      // BIC
			String str5 = String.format("%-15s|", rs.getString(5));     // Bankname
			String str6 = String.format("%10.2f|", rs.getDouble(6));    // Betrag
			String str7 = String.format("%-20s|", rs.getString(7));     // Waehrung
			String str8 = String.format("%-25s|", rs.getString(8));     // Verwendungswzeck
			System.out.println( str1 + str2 + str3 + str4 + str5 + str6 + str7 + str8);
		
			// ZahlungImpl zahlungImpl = new ZahlungImpl(rs.getString(2), rs.getString(3), )
			
		}
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("");
		
		db.stopDBConnection();
		
	}
	
	
	/** Die Methode liest alle vorhandenen Daten der Transaktionen aus der allDataView aus **/
	public void readAll() {
		try {
			// Verbindung zur Datenbank aufbauen
			db.startDBConnection();
			
			// SQL Abfrage durchführen und Result under Variablen rs speichern
			ResultSet rs = db.select("SELECT * FROM allDataShortView");
			
			// SQL Ergebnis verarbeiten und ausgeben
			System.out.println("");
			System.out.println("-----------------------------------------------------------------");
				String ueb1 = String.format("|%-5s|", "ID"); 
				String ueb5 = String.format("%-10s|","Betrag");
				String ueb6 = String.format("%-20s|","Waehrung");
				String ueb7 = String.format("%-25s|","Verwendungszweck");
			System.out.println(ueb1 + ueb5 + ueb6 + ueb7);
			System.out.println("-----------------------------------------------------------------");
			while (rs.next()) {
				String str1 = String.format("|%-5d|", rs.getInt(1));       // id_zahlung
				String str5 = String.format("%10.2f|", rs.getDouble(2));    // Betrag
				String str6 = String.format("%-20s|", rs.getString(3));     // Waehrung
				String str7 = String.format("%-25s|", rs.getString(4));     // Verwendungswzeck
				System.out.println( str1 + str5 + str6 + str7);
			}
			System.out.println("-----------------------------------------------------------------");
			System.out.println("");
			
			// CleanUp - Datenbank Connection wieder schließen
			db.stopDBConnection();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}		
	}

	public void updateOne() {
		// more code is comming....   (prepare und update SQL)
	}
	
	public void removeOne() {
		// more code is comming		  (irgendwie mit delete statement)
	}
	
	
}
