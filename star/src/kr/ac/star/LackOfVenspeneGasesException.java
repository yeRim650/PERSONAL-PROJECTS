package kr.ac.star;

public class LackOfVenspeneGasesException extends Exception{
	public LackOfVenspeneGasesException(){
		super("LackOfVenspeneGas");
	}
	public LackOfVenspeneGasesException(String msg){
		super(msg);
	}
}
