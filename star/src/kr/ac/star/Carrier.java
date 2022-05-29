package kr.ac.star;

import java.util.Iterator;
import java.util.Vector;

public class Carrier extends FlyUnit implements IProtossUnit{
	private int shield;
	private int maxInterceptorNum;
	private Vector<Interceptor> interceptors;
	
	public Carrier(User user, Point point, int hp, int maxHp, int maxInterceptorNum){
		super(user, point, maxHp, maxHp);
		setShield(maxHp);
		setMaxInterceptorNum(maxInterceptorNum);
	}
	
	public int getMaxInterceptorNum() {
		return maxInterceptorNum;
	}
	public void setMaxInterceptorNum(int maxInterceptorNum) {
		this.maxInterceptorNum = maxInterceptorNum;
	}
	public Vector<Interceptor> getInterceptors() {
		return interceptors;
	}
	public void setInterceptors(Vector<Interceptor> interceptors) {
		this.interceptors = interceptors;
	}
	public int getShield() {
		return shield;
	}
	public void setShield(int shield) {
		this.shield = shield;
	}
	
	public void buildInterceptor(int mineral) throws Exception{
		if(interceptors.size()<getMaxInterceptorNum()){
			getUser().consumMineral(mineral);
			interceptors.add(new Interceptor(getUser(),getPoint()));
		}
	}
	public void attack(Unit target){
		Iterator<Interceptor> itr = interceptors.iterator();
		if(itr.hasNext()) {
			Interceptor element = itr.next();
			element.attack(target);
		}
	}
	public void attack(Structure target){
		Iterator<Interceptor> itr = interceptors.iterator();
		if(itr.hasNext()) {
			Interceptor element = itr.next();
			element.attack(target);
		}
	}
}
