package kr.ac.star;

public abstract class Unit {
	private User user;
	private int hp;
	private Point point;
	public final int MAX_HP;
	public static final int MIN_HP = 0;
	
	public Unit(User user, Point point, int hp, int maxHp){
		setUser(user);
		setPoint(point);
		setHp(hp);
		this.MAX_HP = maxHp;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public Point getPoint() {
		return point;
	}
	public void setPoint(Point point) {
		this.point = point;
	}
	
	
}
