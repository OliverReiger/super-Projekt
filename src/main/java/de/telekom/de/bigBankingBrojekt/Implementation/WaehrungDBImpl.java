package de.telekom.de.bigBankingBrojekt.Implementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WaehrungDBImpl {
	
	private Statement statement;
	private Connection connection;
	
	MariaDBConnectorImpl mdbci = new MariaDBConnectorImpl();
	
	// HILFSMETHODE zum Abfragen der DB über alle vorhandenen Währungen
	public ResultSet select(String query) throws SQLException {
		statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(query);
		return rs;
	}
				
	public void insertWaehrung(String waehrung) throws SQLException {
		PreparedStatement pst = null;
		pst = connection.prepareStatement("INSERT INTO waehrung (waehrung) values ( ? )");
		pst.setString(1, waehrung);
		pst.executeUpdate();
	}
	
	public void deleteWaehrung(int idWaehrung) throws SQLException {
		PreparedStatement pst = null;
		pst = connection.prepareStatement("DELETE FROM waehrung where id_waehrung = ( ? )");
		pst.setInt(1, idWaehrung);
		pst.executeUpdate();
	}
	
	
	
	
	
	/** Diese Methode öffent eine Datenbank Connection, wenn noch keine besteht **/
	public Connection startDBConnectionWaehrung() {
		if (connection == null) {
			try {
				connection = (Connection) DriverManager.getConnection(mdbci.getUrl(), mdbci.getUser(), mdbci.getPass());
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
	public void stopDBConnectionWaehrung() {
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

}
