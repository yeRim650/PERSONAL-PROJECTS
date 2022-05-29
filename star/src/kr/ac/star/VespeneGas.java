package kr.ac.star;

public class VespeneGas {
	private Point point;
	private int vespeneGas;
	public static final int MIN_VG = 0;
	
	public VespeneGas(Point point, int VespeneGas){
		setPoint(point);
		setVespeneGas(vespeneGas);
	}

	public Point getPoint() {
		return point;
	}
	public void setPoint(Point point) {
		this.point = point;
	}
	public int getVespeneGas() {
		return vespeneGas;
	}
	public void setVespeneGas(int vespeneGas) {
		if(vespeneGas>= VespeneGas.MIN_VG){
			this.vespeneGas = vespeneGas;
		}else{
			this.vespeneGas = VespeneGas.MIN_VG;
		}
	}
	
	public int decreaseVespeneGas(int vg){
		if(vg> getVespeneGas()){
			setVespeneGas(getVespeneGas() - vg);
			return vg;
		}else{
			return getVespeneGas();
		}
	}

}
