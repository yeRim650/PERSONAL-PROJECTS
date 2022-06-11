package kr.ac.star;

public class Scarab {
	private Point point;
	private LandAttack landAttack;
	
	public Scarab(Point point){
		setPoint(point);
		setLandAttack(new LandAttack(100));
	}
	public Scarab(Point point, LandAttack landAttack){
		setPoint(point);
		setLandAttack(landAttack);
	}
	public Scarab(Point point, int landPower){
		setPoint(point);
		setLandAttack(new LandAttack(landPower));
	}

	public Point getPoint() {
		return point;
	}
	public void setPoint(Point point) {
		this.point = point;
	}
	public LandAttack getLandAttack() {
		return landAttack;
	}
	public void setLandAttack(LandAttack landAttack) {
		this.landAttack = landAttack;
	}
	
	public void attack(WalkUnit target){
		landAttack.landAttack(target);
	}
	public void attack(Structure target){
		landAttack.landAttack(target);
	}
}
