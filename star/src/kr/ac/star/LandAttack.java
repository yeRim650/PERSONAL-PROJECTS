package kr.ac.star;

public class LandAttack {
	private int landPower;

	public LandAttack(int landPower){
		setLandPower(landPower);
	}
	
	public int getLandPower() {
		return landPower;
	}
	public void setLandPower(int landPower) {
		this.landPower = landPower;
	}
	
	public void landAttack(WalkUnit target){
		if(target instanceof IProtossUnit){
			IProtossUnit temp = (IProtossUnit)target;
			if(temp.getShield()>0){
				if(temp.getShield()<getLandPower()){
					temp.setShield(IProtossUnit.MIN_SHIELD);
					temp.setHp(temp.getHp()-(getLandPower()-temp.getShield()));
				}else{
					temp.setShield(temp.getShield()-getLandPower());
				}
			}else{
				temp.setHp(temp.getHp()-getLandPower());
			}
		}else{
			target.setHp(target.getHp()-getLandPower());
		}
	}
	public void landAttack(Structure target){
		if(target instanceof IProtossStructure){
			IProtossUnit temp = (IProtossUnit)target;
			if(temp.getShield()>0){
				if(temp.getShield()<getLandPower()){
					temp.setShield(IProtossStructure.MIN_SHIELD);
					temp.setHp(temp.getHp()-(getLandPower()-temp.getShield()));
				}else{
					temp.setShield(temp.getShield()-getLandPower());
				}
			}else{
				temp.setHp(temp.getHp()-getLandPower());
			}
		}else{
			target.setHp(target.getHp()-getLandPower());
		}
	}
}
