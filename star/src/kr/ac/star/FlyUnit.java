package kr.ac.star;

public abstract class FlyUnit extends Unit{
	public FlyUnit(User user, Point point,int maxHp, int hp){
		super(user, point, hp, maxHp);
	}
	public void Fly(Point point){
		setPoint(point);
	}
}
