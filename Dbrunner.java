// Jeremias Jokila 1802929

package dbapplication;

import java.awt.FlowLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;


public class Dbrunner {
	
	public static void main(String[] args) {

		final Dbmanager db = new Dbmanager();
		final JFrame ikkuna = new JFrame();
		ikkuna.setLayout(new FlowLayout());
		final Connection myconn = db.getConnection();
		if (db.getConnection() != null) {
			System.out.print("myconnection successful ");
			System.out.print(myconn + " ");
		}

		final MenuBar mb = new MenuBar();
		final Menu yll�pito = new Menu("Yll�pito");
		final MenuItem etsi = new MenuItem("etsi tietoja");
		final MenuItem lis�� = new MenuItem("Lis�� tietoja");
		final MenuItem poista = new MenuItem("Poista tietoja");
		final MenuItem drop = new MenuItem("Nollaa");
		final MenuItem sammuta = new MenuItem("Sammuta");
		final JButton p�ivit� = new JButton("p�ivit� taulukko");

		final JTable database = new JTable();

		try {
			database.setModel(db.ResultToTable(db.get(myconn)));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		etsi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Connection con = myconn;
				String mypalkki = JOptionPane.showInputDialog("Anna palkin numero (vasemmalta oikealle):");
				String mytieto = JOptionPane.showInputDialog("Etsitt�v� tieto?");
				JFrame tulos = new JFrame();
				JTable tulosdb = new JTable();
				tulos.add(tulosdb);
				try {
					tulosdb.setModel(db.ResultToTable(db.search(con, mypalkki, mytieto)));
				} catch (SQLException er) {
					JOptionPane.showMessageDialog(null, "Virhe tietojen etsimisess�");
					er.printStackTrace();
				}
				tulos.setSize(1000, 1000);
				tulos.setTitle("Haku");
				tulos.pack();
				tulos.setLocationRelativeTo(null);
				tulos.setVisible(true);
			}
		});

		lis��.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Connection con = myconn;
				String fname = JOptionPane.showInputDialog("First Name?");
				String lname = JOptionPane.showInputDialog("Last Name?");
				String race = JOptionPane.showInputDialog("Race?");
				String srace = JOptionPane.showInputDialog("Subrace?");
				String cclass = JOptionPane.showInputDialog("Class?");
				int lvl = Integer.parseInt(JOptionPane.showInputDialog("Character level?"));
				Character myactor = new Character(fname, lname, race, srace, cclass, lvl);

				db.set(con, myactor);
			}
		});

		poista.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Connection con = myconn;
				int myID = Integer.parseInt(JOptionPane.showInputDialog("Character ID to delete?"));

				db.remove(con, myID);
			}
		});
		
		drop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Connection con = myconn;
				String input = JOptionPane.showInputDialog(
						"Kirjoita KYLL�, jos haluat varmasti poistaa kaiken olemassaolevan datan PYSYV�STI.");
				String deletekey = "KYLL�";
				if (input.toUpperCase().equals(deletekey)) {
					db.reset(con);
				} else {
					JOptionPane.showMessageDialog(null, "Tietoja ei poistettu");
				}
			}
		});

		sammuta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		p�ivit�.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					database.setModel(db.ResultToTable(db.get(myconn)));
				} catch (SQLException er) {
					er.printStackTrace();
				}
			}
		});

		ikkuna.setMenuBar(mb);
		mb.add(yll�pito);
		yll�pito.add(etsi);
		yll�pito.add(lis��);
		yll�pito.add(poista);
		yll�pito.add(drop);
		yll�pito.add(sammuta);
		ikkuna.setSize(1000, 1000);
		ikkuna.add(database);
		ikkuna.add(p�ivit�);
		ikkuna.setTitle("Dnd-hahmoja");
		ikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ikkuna.pack();
		ikkuna.setLocationRelativeTo(null);
		ikkuna.setVisible(true);
		
		
	}

}
