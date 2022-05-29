package kr.ac.star;

import java.util.Iterator;
import java.util.Vector;

public class Reaver extends WalkUnit implements IProtossUnit{
	private int shield;
	private Vector<Scarab> scarabs;
	private int maxScarabNum;
	
	public Reaver(User user, Point point, int hp, int maxHp, LandAttack landAttack, int shield){
		super(user, point, hp, maxHp);
		setShield(shield);
	}
	public Reaver(User user, Point point, int hp, int maxHp, int landPower, int shield){
		super(user, point, hp, maxHp);
		setShield(shield);
	}

	public int getShield() {
		return shield;
	}
	public void setShield(int shield) {
		this.shield = shield;
	}
	public Vector<Scarab> getScarabs() {
		return scarabs;
	}
	public void setScarabs(Vector<Scarab> scarabs) {
		this.scarabs = scarabs;
	}
	public int getMaxScarabNum() {
		return maxScarabNum;
	}
	public void setMaxScarabNum(int maxScarabNum) {
		this.maxScarabNum = maxScarabNum;
	}
	
	public void buildScarab(int mineral) throws Exception{
		if(scarabs.size()<getMaxScarabNum()){
			getUser().consumMineral(mineral);
			scarabs.add(new Scarab(getPoint()));
		}
	}
	public void attack(WalkUnit target){
		if(getUser().equals(target.getUser())){
			Iterator<Scarab> itr = scarabs.iterator();
			if(itr.hasNext()) {
				Scarab element = itr.next();
				element.attack(target);
			}
		}
	}
	public void attack(Structure target){
		if(getUser().equals(target.getUser())){
			Iterator<Scarab> itr = scarabs.iterator();
			if(itr.hasNext()) {
				Scarab element = itr.next();
				element.attack(target);
			}
		}
	}
}
