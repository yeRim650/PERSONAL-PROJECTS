package kr.ac.star;

public class AirAttack {
	private int airPower;
	
	public AirAttack(int airPower){
		setAirPower(airPower);
	}

	public int getAirPower() {
		return airPower;
	}
	public void setAirPower(int airPower) {
		this.airPower = airPower;
	}
	
	public void airAttack(FlyUnit target){
		if(target instanceof IProtossUnit){
			IProtossUnit temp = (IProtossUnit)target;
			if(temp.getShield()>0){
				if(temp.getShield()<getAirPower()){
					temp.setShield(IProtossUnit.MIN_SHIELD);
					temp.setHp(temp.getHp()-(getAirPower()-temp.getShield()));
				}else{
					temp.setShield(temp.getShield()-getAirPower());
				}
			}else{
				temp.setHp(temp.getHp()-getAirPower());
			}
		}else{
			target.setHp(target.getHp()-getAirPower());
		}
	}
}
