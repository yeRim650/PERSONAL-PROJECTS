package kr.ac.star;

public class Gateway extends Structure implements IProtossStructure{
	private int shield;
	
	public Gateway(User user, Point point){
		super(user, point, 750, 750);
		setShield(750);
	}
	public Gateway(User user, Point point, int hp, int maxHp, int shield){
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
	
	public Zealot warpZealot(Point point, int mineral) throws Exception{
		getUser().consumMineral(mineral);
		return new Zealot(getUser(), point);
	}
}
