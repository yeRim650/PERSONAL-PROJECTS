package kr.ac.star;

import java.util.Vector;

public class Overlord extends FlyUnit implements IZergUnit{
	private Vector<IZergUnit> zergUnits;
	private int maxLoadNum;
	
	public Overlord(User user, Point point, int hp, int maxHp, int maxLoadNum){
		super(user, point, hp, maxHp);
		setMaxLoadNum(maxLoadNum);
	}

	public Vector<IZergUnit> getZergUnits() {
		return zergUnits;
	}
	public void setZergUnits(Vector<IZergUnit> zergUnits) {
		this.zergUnits = zergUnits;
	}
	public int getMaxLoadNum() {
		return maxLoadNum;
	}
	public void setMaxLoadNum(int maxLoadNum) {
		this.maxLoadNum = maxLoadNum;
	}
	
	public void load(IZergUnit target){
		if(zergUnits.size()<maxLoadNum){
			if(getUser().equals(target.getUser())){
				zergUnits.add(target);
			}
		}
	}
	public IZergUnit unLoad(int idx){
		return zergUnits.get(idx);
	}
	
}
