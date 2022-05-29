package kr.ac.star;

public class Probe extends WalkUnit implements IProtossUnit{
	private LandAttack landAttack;
	private int shield;
	
	public Probe(User user, Point point, int hp, int maxHp, LandAttack landAttack, int shield){
		super(user, point, hp, maxHp);
		setLandAttack(landAttack);
		setShield(shield);
	}
	public Probe(User user, Point point, int hp, int maxHp, int landPower, int shield){
		super(user, point, hp, maxHp);
		setLandAttack(new LandAttack(landPower));
		setShield(shield);
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
	public void gatherMineral(Mineral mineral, int m){
		getUser().gatherMineral(mineral.decreaseMineral(m));
	}
	public void gatherVenspeneGas(VespeneGas vespeneGas, int vg){
		getUser().gatherVenspeneGas(vespeneGas.decreaseVespeneGas(vg));
	}
	public Barracks buildBarracks(Point point, int mineral)throws Exception{
		getUser().consumMineral(mineral);
		return new Barracks(getUser(), point);
	}
	
}
