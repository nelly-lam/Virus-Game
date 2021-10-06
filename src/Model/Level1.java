package Model;

import Controller.ControllerLevel1;


public class Level1 extends Level {

    public Level1(ControllerLevel1 c, int p, int n) {
        super(c,p,n);
        addVirusToVirusCloud(new Virus(c.getVirus1(),p));
        addVirusToVirusCloud(new Virus(c.getVirus2(),p));
        addVirusToVirusCloud(new Virus(c.getVirus3(),p));
        addVirusToVirusCloud(new Virus(c.getVirus4(),p));
        addVirusToVirusCloud(new Virus(c.getVirus5(),p));
        addVirusToVirusCloud(new Virus(c.getVirus6(),p));
        addVirusToVirusCloud(new Virus(c.getVirus7(),p));
    }


}