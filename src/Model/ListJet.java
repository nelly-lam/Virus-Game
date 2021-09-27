package Model;

import java.util.ArrayList;

public class ListJet {
	
	private ArrayList<Jet> listJet;
	
	public ListJet() {
		listJet = new ArrayList<Jet>(10);

	}

	public ArrayList<Jet> getListJet() {
		return listJet;
	}
	
	public int getSize() {
		return listJet.size();
	}
	
	public void addJet(Jet jet) {
		listJet.add(jet);
	}
	
	
	public void removeJet() {
		listJet.remove(listJet.size()-1);
	}
	
	public Jet getJet(int i) { return listJet.get(i); }

}
