package kr.ac.star;

public abstract class WalkUnit extends Unit {
	public WalkUnit(User user, Point point, int hp, int maxHp){
		super(user, point, hp, maxHp);
	}
	public void walk(Point point){
		setPoint(point);
	}
}
