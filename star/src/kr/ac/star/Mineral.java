package kr.ac.star;

public class Mineral {
	private Point point;
	private int mineral;
	public static final int MIN_MINERAL = 0;
	
	public Mineral(Point point, int mineral){
		setPoint(point);
		setMineral(mineral);
	}

	public Point getPoint() {
		return point;
	}
	public void setPoint(Point point) {
		this.point = point;
	}
	public int getMineral() {
		return mineral;
	}
	public void setMineral(int mineral) {
		if(mineral>=Mineral.MIN_MINERAL){
			this.mineral = mineral;
		}else{
			this.mineral = Mineral.MIN_MINERAL;
		}
	}
	
	public int decreaseMineral(int minus){
		if(minus> getMineral()){
			setMineral(getMineral() - minus);
			return minus;
		}else{
			return getMineral();
		}
	}
}
