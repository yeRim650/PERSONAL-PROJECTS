package kr.ac.star;

public class Barracks extends Structure{
	public Barracks(User user, Point point){
		super(user, point, 1000, 1000);
	}
	public Barracks(User user, Point point, int hp, int maxHp){
		super(user, point, hp, maxHp);
	}

	public Marine warpMarine(int mineral)throws Exception{
		getUser().consumMineral(mineral);
		return new Marine(getUser(),this.getPoint());
	}
	public Madic warpMadic(int mineral)throws Exception{
		getUser().consumMineral(mineral);
		return new Madic(getUser(),this.getPoint());
	}
}
