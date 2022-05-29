package kr.ac.star;

public abstract class Structure {
	private User user;
	private Point point;
	private int hp;
	public final int MAX_HP;
	public static final int MIN_HP = 0;
	
	public Structure(User user, Point point, int hp, int maxHp){
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

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		if(hp>=MIN_HP){
			this.hp = hp;
		}
	}
}
