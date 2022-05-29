package kr.ac.star;

public class Mutalisk extends FlyUnit implements IZergUnit{
	private LandAttack landAttack;
	private AirAttack airAttack;

	public Mutalisk(User user, Point point, int hp, int maxHp, LandAttack landAttack, AirAttack ariAttack){
		super(user, point, maxHp, maxHp);
		setLandAttack(landAttack);
		setAriAttack(ariAttack);
	}
	public Mutalisk(User user, Point point, int hp, int maxHp, int landPower, int airPower){
		super(user, point, maxHp, maxHp);
		setLandAttack(new LandAttack(landPower));
		setAriAttack(new AirAttack(airPower));
	}
	
	public LandAttack getLandAttack() {
		return landAttack;
	}
	public void setLandAttack(LandAttack landAttack) {
		this.landAttack = landAttack;
	}
	public AirAttack getAriAttack() {
		return airAttack;
	}
	public void setAriAttack(AirAttack ariAttack) {
		this.airAttack = ariAttack;
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
