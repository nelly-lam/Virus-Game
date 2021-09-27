package Model;

import java.util.ArrayList;

public class ListJet {
	
	///////////////////// ATTRIBUTES /////////////////////////
	private ArrayList<Jet> listJet;
	
    ///////////////////// CONSTRUCTOR ///////////////////////
	public ListJet() {
		listJet = new ArrayList<Jet>(10);

	}
	
    ///////////////////// METHODS ///////////////////////

	public ArrayList<Jet> getListJet() { return listJet; }
	
	public int getSize() { return listJet.size(); }
	
	public void addJet(Jet jet) { listJet.add(jet); }
	public void removeJet(int i) { listJet.remove(i); }
	
	public Jet getJet(int i) { return listJet.get(i); }

}
