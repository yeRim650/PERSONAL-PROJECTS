package kr.ac.star;

public class Barracks extends Structure{
	public Barracks(User user, Point point){
		super(user, point, 1000, 1000);
	}
	public Barracks(User user, Point point, int hp, int maxHp){
		super(user, point, hp, maxHp);
	}

	public Marine warpMarine(Point point, int mineral)throws Exception{
		getUser().consumMineral(mineral);
		return new Marine(getUser(),point);
	}
	public Madic warpMadic(Point point, int mineral)throws Exception{
		getUser().consumMineral(mineral);
		return new Madic(getUser(),point);
	}
}
