package kr.ac.star;

public class Pylon extends Structure implements IProtossStructure{
	private int shield;
	
	public Pylon(User user, Point point){
		super(user, point, 300, 300);
		setShield(300);
	}
	public Pylon(User user, Point point, int hp, int maxHp, int shield){
		super(user, point, hp, maxHp);
		setShield(shield);
	}
	
	public int getShield() {
		return shield;
	}
	public void setShield(int shield) {
		if(shield >= IProtossStructure.MIN_SHIELD){
			this.shield = shield;
		}
	}
}
