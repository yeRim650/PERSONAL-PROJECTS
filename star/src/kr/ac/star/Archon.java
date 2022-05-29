package kr.ac.star;

public class Archon extends WalkUnit implements IProtossUnit{
	private LandAttack landAttack;
	private AirAttack airAttack;
	private int shield;

	public Archon(User user, Point point, int hp, int maxHp, LandAttack landAttack, AirAttack airAttack, int shield){
		super(user, point, maxHp, maxHp);
		setLandAttack(landAttack);
		setAirAttack(airAttack);
		setShield(shield);
	}

	public Archon(User user, Point point, int hp, int maxHp, int landPower, int airPower, int shield){
		super(user, point, maxHp, maxHp);
		setLandAttack(new LandAttack(landPower));
		setAirAttack(new AirAttack(airPower));
		setShield(shield);
	}
	
	public LandAttack getLandAttack() {
		return landAttack;
	}
	public void setLandAttack(LandAttack landAttack) {
		this.landAttack = landAttack;
	}
	public AirAttack getAirAttack() {
		return airAttack;
	}
	public void setAirAttack(AirAttack airAttack) {
		this.airAttack = airAttack;
	}	
	public int getShield() {
		return shield;
	}	
	public void setShield(int shield) {
		this.shield = shield;
	}
	
	public void attack(Unit target){
		if(getUser().equals(target.getUser())){
			if(target instanceof WalkUnit){
				WalkUnit temp = (WalkUnit)target;
				landAttack.landAttack(temp);
			}else{
				FlyUnit temp = (FlyUnit)target;
				airAttack.airAttack(temp);
			}
		}
	}
	public void attack(Structure target){
		if(getUser().equals(target.getUser())){
			landAttack.landAttack(target);
		}
	}
	
	
}
