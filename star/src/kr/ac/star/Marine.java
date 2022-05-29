package kr.ac.star;

public class Marine extends WalkUnit implements ITerranUnit{
	private LandAttack landAttack;
	private AirAttack airAttack;
	
	public Marine(User user, Point point){
		super(user, point, 40, 40);
		setLandAttack(new LandAttack(6));
		setAirAttack(new AirAttack(6));
	}
	public Marine(User user, Point point, int hp, int maxHp, LandAttack landAttack, AirAttack ariAttack){
		super(user, point, hp, maxHp);
		setLandAttack(landAttack);
		setAirAttack(ariAttack);
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
