package kr.ac.star;

public class Zealot extends WalkUnit implements IProtossUnit{
	private LandAttack landAttack;
	private int shield;
	
	public Zealot(User user, Point point){
		super(user, point, 100, 100);
		setShield(60);
		setLandAttack(new LandAttack(8));
		
	}
	public Zealot(User user, Point point, int hp, int maxHp, int shield, LandAttack landAttack){
		super(user, point, hp, maxHp);
		setShield(shield);
		setLandAttack(landAttack);
	}
	public Zealot(User user, Point point, int hp, int maxHp, int shield, int landPower){
		super(user, point, hp, maxHp);
		setShield(shield);
		setLandAttack(new LandAttack(landPower));
	}

	public LandAttack getLandAttack() {
		return landAttack;
	}
	public void setLandAttack(LandAttack landAttack) {
		this.landAttack = landAttack;
	}
	public int getShield() {
		return shield;
	}
	public void setShield(int shield) {
		this.shield = shield;
	}
	
	public void attack(WalkUnit target){
		if(getUser().equals(target.getUser())){
			landAttack.landAttack(target);
		}
	}
	public void attack(Structure target){
		if(getUser().equals(target.getUser())){
			landAttack.landAttack(target);
		}
	}
}
