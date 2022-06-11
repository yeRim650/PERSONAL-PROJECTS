package kr.ac.star;

public class SCV extends WalkUnit implements ITerranUnit{
	private LandAttack landAttack;
	
	public SCV(User user, Point point, int hp, int maxHp, LandAttack landAttack){
		super(user, point, hp, maxHp);
		setLandAttack(landAttack);
	}
	public SCV(User user, Point point, int hp, int maxHp, int landPower){
		super(user, point, hp, maxHp);
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
	public void gatherMineral(Mineral mineral, int m){
		getUser().gatherMineral(mineral.decreaseMineral(m));
	}
	public void gatherVenspeneGas(VespeneGas vespeneGas, int vg){
		getUser().gatherVenspeneGas(vespeneGas.decreaseVespeneGas(vg));
	}
	public Pylon buildPylon(Point point, int mineral)throws Exception{
		getUser().consumMineral(mineral);
		return new Pylon(getUser(), point);
	}
}
