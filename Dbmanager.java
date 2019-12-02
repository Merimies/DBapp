// Jeremias Jokila 1802929

package dbapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Dbmanager {
	// Tietokantayhteyden muodostaminen
	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7314043",
					"sql7314043", "1dFp1QTWg9");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ResultSet search(Connection conn, String palkki, String tieto) {
		try {
			String query = null;
			if (palkki.equals("1")) {
				query = "SELECT * FROM Dndchars WHERE CharID  = '" + tieto + "'";
			} else if (palkki.equals("2")) {
				query = "SELECT * FROM Dndchars WHERE firstname  = '" + tieto + "'";
			} else if (palkki.equals("3")) {
				query = "SELECT * FROM Dndchars WHERE lastname  = '" + tieto + "'";
			} else if (palkki.equals("4")) {
				query = "SELECT * FROM Dndchars WHERE race  = '" + tieto + "'";
			} else if (palkki.equals("5")) {
				query = "SELECT * FROM Dndchars WHERE subrace  = '" + tieto + "'";
			} else if (palkki.equals("6")) {
				query = "SELECT * FROM Dndchars WHERE class  = '" + tieto + "'";
			} else if (palkki.equals("7")) {
				query = "SELECT * FROM Dndchars WHERE charlevel  = '" + tieto + "'";
			}
			System.out.print(query);
			Connection con = conn;
			Statement stmt = con.createStatement();
			ResultSet result = stmt
					.executeQuery(query);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error");
			return null;
		}
	}

	public ResultSet get(Connection conn) {
		try {
			Connection con = conn;
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM Dndchars");
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public void set(Connection conn, Character actor) {
		try {
			Connection con = conn;
			Statement stmt = con.createStatement();
			// ei menny ku vaan sellaset 4h troubleshoottaamista, kiitos SQL ja '
			String query = "INSERT INTO Dndchars(firstname, lastname, race, subrace, class, charlevel)" + "VALUES ( "
					+ "'" + actor.getFirstname() + "'" + ", " + "'" + actor.getLastname() + "'" + ", " + "'"
					+ actor.getPlayerrace() + "'" + ", " + "'" + actor.getSubrace() + "'" + ", " + "'"
					+ actor.getCharacterclass() + "'" + ", " + "'" + actor.getCharacterlevel() + "'" + " )";
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error inserting values");
		}
	}

	public void reset(Connection conn) {
		try {
			Connection con = conn;
			Statement stmt = con.createStatement();
			String query = "DROP TABLE Dndchars";
			String query2 = "CREATE TABLE Dndchars ( CharID int NOT NULL auto_increment, firstname varchar(255) NOT NULL, lastname varchar(255), race varchar(255), subrace varchar(255), class varchar(255), charlevel int, PRIMARY KEY (CharID))";
			stmt.executeUpdate(query);
			stmt.executeUpdate(query2);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error resetting table");
		}
	}

	public void remove(Connection conn, int ID) {
		try {
			Connection con = conn;
			Statement stmt = con.createStatement();
			String query = "DELETE FROM Dndchars WHERE CharID = " + "'" + ID + "'";
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error Deleting row");
		}
	}

	public void addRow(String name, String lname, String race, String subrace, String cclass, String charlevel) {

	}

	public DefaultTableModel ResultToTable(ResultSet rset) throws SQLException {
		DefaultTableModel tableModel = new DefaultTableModel();
		ResultSetMetaData metaData = rset.getMetaData();

		int columnCount = metaData.getColumnCount();
		for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
			tableModel.addColumn(metaData.getColumnLabel(columnIndex));
		}

		Object[] row = new Object[columnCount + 1];

		tableModel.addRow(
				new Object[] { "Character ID", "First Name", "Last Name", "Race", "Subrace", "Class", "Level" });

		while (rset.next()) {
			for (int i = 0; i < columnCount; i++) {
				row[i] = rset.getObject(i + 1);
			}
			tableModel.addRow(row);
		}

		return tableModel;
	}
}