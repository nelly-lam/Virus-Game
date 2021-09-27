package Model;

import java.util.ArrayList;

public class VirusCloud {

	///////////////////// ATTRIBUTES /////////////////////////
    ArrayList<Virus> virusCloud;

    ///////////////////// CONSTRUCTOR ///////////////////////
	public VirusCloud() {
		virusCloud = new ArrayList<Virus>();
	}
	
	
    ///////////////////// METHODS ///////////////////////

	public void addVirus(Virus virus) { virusCloud.add(virus); }
	public void removeVirus(int i) { virusCloud.remove(i); }
	
	public int getSize() { return virusCloud.size(); }
	
	public Virus getVirus(int i) { return virusCloud.get(i); }

}
