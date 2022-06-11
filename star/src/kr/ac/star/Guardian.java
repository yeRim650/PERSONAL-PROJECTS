package kr.ac.star;

public class Guardian extends FlyUnit implements IZergUnit{
	private LandAttack landAttack;
	
	public Guardian(User user, Point point, int hp, int maxHp, LandAttack landAttack){
		super(user, point, maxHp, maxHp);
		setLandAttack(landAttack);
	}
	public Guardian(User user, Point point, int hp, int maxHp, int landPower){
		super(user, point, maxHp, maxHp);
		setLandAttack(new LandAttack(landPower));
	}
	
	public LandAttack getLandAttack() {
		return landAttack;
	}
	public void setLandAttack(LandAttack landAttack) {
		this.landAttack = landAttack;
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
