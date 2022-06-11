package kr.ac.star;

public class LackOfMineralsException extends Exception {
	public LackOfMineralsException(){
		super("LackOfMinerals");
	}
	public LackOfMineralsException(String msg){
		super(msg);
	}
}
