package Model;

import java.util.ArrayList;

public class VirusCloud {

    ArrayList<Virus> virusCloud;

	public VirusCloud() {
		virusCloud = new ArrayList<Virus>();
	}
	
	public void addVirus(Virus virus) {
		virusCloud.add(virus);
	}
	
	
	public int getSize() {
		return virusCloud.size();
	}

}
