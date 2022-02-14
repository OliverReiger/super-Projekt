package de.telekom.de.bigBankingBrojekt.Implementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MariaDBConnectorImpl {

	private String url = "jdbc:mariadb://localhost:3306/bbb";
	private String user = "admin";
	private String pass = "sea333";
	private Connection connection;
	private Statement statement;
	
	/*------------------------------------------------------------
	 * HILFSMETHODEN ZUR STEUERUNG DER DB CONNECTIONS
	 *-----------------------------------------------------------*/
	
	public String getUrl() {
		return url;
	}

	public String getUser() {
		return user;
	}

	public String getPass() {
		return pass;
	}

	/** Diese Methode öffent eine Datenbank Connection, wenn noch keine besteht **/
	public Connection startDBConnection() {
		if (connection == null) {
			try {
				connection = (Connection) DriverManager.getConnection(this.url, this.user, this.pass);
				System.out.println("Verbindung erfolgreich hergestellt.");
			} catch (SQLException e) {
                e.printStackTrace();
            }
		} else {
			System.out.println("Es bestand bereits eine aktive Verbindung.");
		}
		return connection;	
	}
	
	/** Diese Methode schließt eine Datenbank Connection, wenn eine aktive besteht **/
	public void stopDBConnection() {
		if (connection != null) {
			try {
				connection.close();
				connection = null;
				System.out.println("Die Verbindungen zur Datenbank wurden erfolgreich geschlossen.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/*------------------------------------------------------------
	 * HILFSMETHODEN ZUM ANZEIGEN, EINFÜGEN, LÖSCHEN VON ZAHLUNGEN
	 *-----------------------------------------------------------*/
	
	/** Diese Methode bekommt ein SQL Query vom Typ Select übergeben, führt es aus und speichert
	 *  das ResultSet unter dem Variablen Namen rs **/
	public ResultSet select(String query) throws SQLException {
		statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(query);
		return rs;
	}
	
	
	/** Diese Methode bereitet ein Select Statement zur Abfrage mit der übergebenende
	 *  ID vor, sendet es an die Datenbank und gibt als rs das ResultSet zurück**/
	public ResultSet selectPrepared(String query, int zahlung_ID) throws SQLException {
		PreparedStatement pstmt = null;
		pstmt = connection.prepareStatement(query);
		pstmt.setInt(1, zahlung_ID);
		ResultSet rs = pstmt.executeQuery();
		return rs;	
	}
	
	
	/** Diese Methode bereitet ein INSERT Statement zum Einfügen einer Zahlung
	 *  mit dem übergebenenden Werten vor und sendet es an die Datenbank.**/
		public void insertZahlungPrepared(String empfaenger, String iban, 
				String bic, String bankname, Double betrag, String waehrung, 
				String verwendungszweck) throws SQLException {
	
		// IBAN Eintrag durchführen und ID zurückholen
		PreparedStatement pstmt_iban = null;
		pstmt_iban = connection.prepareStatement("INSERT INTO iban (iban) values ( ? )",Statement.RETURN_GENERATED_KEYS);
		pstmt_iban.setString(1, iban);
		pstmt_iban.executeUpdate();
		ResultSet rs_iban = pstmt_iban.getGeneratedKeys();
		rs_iban.next();
		int iban_id = rs_iban.getInt(1);
		System.out.println("Es wurde eine neue IBAN unter der ID:" + iban_id + " angelegt");

		// Währung Eintrag durchführen und ID zurückholen
		PreparedStatement pstmt_waehrung = null;
		pstmt_waehrung = connection.prepareStatement("INSERT INTO waehrung (waehrung) values ( ? )",Statement.RETURN_GENERATED_KEYS);
		pstmt_waehrung.setString(1, waehrung);
		pstmt_waehrung.executeUpdate();
		ResultSet rs_waehrung = pstmt_waehrung.getGeneratedKeys();
		rs_waehrung.next();
		int id_waehrung = rs_waehrung.getInt(1);
		System.out.println("Es wurde eine neue Währung unter der ID:" + id_waehrung + " angelegt");
		
		// BIC Eintrag durchführen und ID zurückholen
		PreparedStatement pstmt_bic = null;
		pstmt_bic = connection.prepareStatement("INSERT INTO bic (bic, Bankname) values ( ?, ? )",Statement.RETURN_GENERATED_KEYS);
		pstmt_bic.setString(1, bic);
		pstmt_bic.setString(2, bankname);
		pstmt_bic.executeUpdate();
		ResultSet rs_bic = pstmt_bic.getGeneratedKeys();
		rs_bic.next();
		int id_bic = rs_bic.getInt(1);
		System.out.println("Es wurde eine neue BIC unter der ID:" + id_bic + " angelegt");
		
		// Empfänger Eintrag durchführen und ID zurückholen
		PreparedStatement pstmt_empfaenger = null;
		pstmt_empfaenger = connection.prepareStatement("INSERT INTO empfaenger (empfaenger, iban_id) values ( ?, ? )");
		pstmt_empfaenger.setString(1, empfaenger);
		pstmt_empfaenger.setInt(2, iban_id);
		pstmt_empfaenger.executeUpdate();
		ResultSet rs_empfaenger = pstmt_empfaenger.getGeneratedKeys();
		rs_empfaenger.next();
		int id_empfaenger = rs_empfaenger.getInt(1);
		System.out.println("Es wurde ein neuer Empfänger unter der ID:" + id_empfaenger + " angelegt");
		
		// Zahlungseintrag vorbereiten
		PreparedStatement pstmt_zahlung = null;
		pstmt_zahlung = connection.prepareStatement("INSERT INTO zahlung (id_empfaenger, id_iban, id_bic, betrag, id_waehrung, verwendungszweck) values ( ?, ?, ?, ?, ?, ? )");
		pstmt_zahlung.setInt(1, id_empfaenger);
		pstmt_zahlung.setInt(2, iban_id);
		pstmt_zahlung.setInt(3, id_bic);
		pstmt_zahlung.setDouble(4, betrag);
		pstmt_zahlung.setInt(5, id_waehrung);
		pstmt_zahlung.setString(6, verwendungszweck);
		pstmt_zahlung.executeUpdate();
	}
	
	
	/*------------------------------------------------------------
	 * HILFSMETHODEN ZUM ANZEIGEN, EINFÜGEN, LÖSCHEN VON WÄHRUNGEN
	 *-----------------------------------------------------------*/
	
	/** Diese Methode bekommt ein SQL Query vom Typ Select übergeben, führt es aus und speichert
	 *  das ResultSet unter dem Variablen Namen rs **/
	public ResultSet selectWaehrung(String query) throws SQLException {
		statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(query);
		return rs;
	}
	
	/** Diese Methode bereitet ein Select Statement zum Einfügen einer Währhung
	 *  mit dem übergebenenden Namen vor und sendet es an die Datenbank.**/
	public void insertWaehrungPrepared(String query, String waehrung) throws SQLException {
		PreparedStatement pstmt = null;
		pstmt = connection.prepareStatement(query);
		pstmt.setString(1, waehrung);
		pstmt.executeQuery();
	}
	
	/** Diese Methode bereitet ein Delete Statement zum Löschen einer Währung
	 * mit der vom User übergebenen id_waehrung vor und sendet es an die Datenbank. **/
	public void deleteWaehrungPrepared(String query, int id_waehrung) throws SQLException {
		PreparedStatement pstmt = null;
		pstmt = connection.prepareStatement(query);
		pstmt.setInt(1, id_waehrung);
		pstmt.executeQuery();
	}
	
	
	/*-------------------------------------------------------------
	 * HILFSMETHODEN ZUM ANZEIGEN, EINFÜGEN, LÖSCHEN VON EMPFÄNGERN
	 *------------------------------------------------------------*/
	
	/** Diese Methode bekommt ein SQL Query vom Typ Select übergeben, führt es aus und speichert
	 *  das ResultSet unter dem Variablen Namen rs **/
	public ResultSet selectEmpfaenger(String query) throws SQLException {
		statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(query);
		return rs;
	}
	
	/** Diese Methode bereitet ein Insert Statement zum Einfügen einer Währhung
	 *  mit dem übergebenenden Namen vor und sendet es an die Datenbank.**/
	public void insertEmpfaengerPrepared(String query, String empfaenger, int IBAN_id) throws SQLException {
		PreparedStatement pstmt = null;
		pstmt = connection.prepareStatement(query);
		pstmt.setString(1, empfaenger);
		pstmt.setInt(2, IBAN_id);
		pstmt.executeQuery();
	}
	
	/** Diese Methode bereitet ein Delete Statement zum Löschen einer Währung
	 * mit der vom User übergebenen id_waehrung vor und sendet es an die Datenbank. **/
	public void deleteEmpfaengerPrepared(String query, int id_empfaenger) throws SQLException {
		PreparedStatement pstmt = null;
		pstmt = connection.prepareStatement(query);
		pstmt.setInt(1, id_empfaenger);
		pstmt.executeQuery();
	}
	
	
	/*-------------------------------------------------------------
	 * HILFSMETHODEN ZUM ANZEIGEN, EINFÜGEN, LÖSCHEN VON IBANs
	 *------------------------------------------------------------*/
	
	/** Diese Methode bekommt ein SQL Query vom Typ Select übergeben, führt es aus und speichert
	 *  das ResultSet unter dem Variablen Namen rs und gibt es zurück **/
	public ResultSet selectIban(String query) throws SQLException {
		statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(query);
		return rs;
	}
	
	/** Diese Methode bereitet ein Insert Statement zum Einfügen einer IBAN
	 *  mit dem übergebenenden String vor und sendet es an die Datenbank.**/
	public void insertIbanPrepared(String query, String iban) throws SQLException {
		PreparedStatement pstmt = null;
		pstmt = connection.prepareStatement(query);
		pstmt.setString(1, iban);
		pstmt.executeQuery();
	}
	
	/** Diese Methode bereitet ein Delete Statement zum Löschen einer IBAN
	 *  mit der vom User übergebenen id_iban vor und sendet es an die Datenbank. **/
	public void deleteIbanPrepared(String query, int id_iban) throws SQLException {
		PreparedStatement pstmt = null;
		pstmt = connection.prepareStatement(query);
		pstmt.setInt(1, id_iban);
		pstmt.executeQuery();
	}
	
	/*-------------------------------------------------------------
	 * HILFSMETHODEN ZUM ANZEIGEN, EINFÜGEN, LÖSCHEN VON BICs
	 *------------------------------------------------------------*/
	
	/** Diese Methode bekommt ein SQL Query vom Typ Select übergeben, führt es aus und speichert
	 *  das ResultSet unter dem Variablen Namen rs und gibt es zurück **/
	public ResultSet selectBic(String query) throws SQLException {
		statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(query);
		return rs;
	}
	
	/** Diese Methode bereitet ein Insert Statement zum Einfügen einer Bic
	 *  mit dem übergebenenden String vor und sendet es an die Datenbank.**/
	public void insertIbanPrepared(String query, String bic, String bankname) throws SQLException {
		PreparedStatement pstmt = null;
		pstmt = connection.prepareStatement(query);
		pstmt.setString(1, bic);
		pstmt.setString(2, bankname);
		pstmt.executeQuery();
	}
	
	/** Diese Methode bereitet ein Delete Statement zum Löschen einer BIC
	 *  mit der vom User übergebenen id_iban vor und sendet es an die Datenbank. **/
	public void deleteBicPrepared(String query, int id_bic) throws SQLException {
		PreparedStatement pstmt = null;
		pstmt = connection.prepareStatement(query);
		pstmt.setInt(1, id_bic);
		pstmt.executeQuery();
	}
	
}
