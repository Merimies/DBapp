// Jeremias Jokila 1802929

package dbapplication;

public class Character {
	private String firstname;
	private String lastname;
	private String playerrace;
	private String subrace;
	private String characterclass;
	private int characterlevel;

	public Character() {
	}

	public Character(String fname, String lname, String race, String srace, String cclass, int lvl) {
		firstname = fname;
		lastname = lname;
		playerrace = race;
		subrace = srace;
		characterclass = cclass;
		characterlevel = lvl;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPlayerrace() {
		return playerrace;
	}

	public void setPlayerrace(String playerrace) {
		this.playerrace = playerrace;
	}

	public String getSubrace() {
		return subrace;
	}

	public void setSubrace(String subrace) {
		this.subrace = subrace;
	}

	public String getCharacterclass() {
		return characterclass;
	}

	public void setCharacterclass(String characterclass) {
		this.characterclass = characterclass;
	}

	public int getCharacterlevel() {
		return characterlevel;
	}

	public void setCharacterlevel(int characterlevel) {
		this.characterlevel = characterlevel;
	}
}
