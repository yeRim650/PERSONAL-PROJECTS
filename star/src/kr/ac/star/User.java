package kr.ac.star;

public class User {
	private String name;
	private int mineral;
	private int vespeneGas;
	public final int RACE;
	
	public static final int ZERRG = 0;
	public static final int TERRAN = 1;
	public static final int PROTOSS = 2;
	
	public User(String name, int mineral, int vespeneGass, int race){
		setName(name);
		setMineral(mineral);
		setVespeneGas(vespeneGass);
		this.RACE = race;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMineral() {
		return mineral;
	}
	public void setMineral(int mineral){
		if(mineral >= 0){
			this.mineral = mineral;
		}
	}
	public int getVespeneGas(){
		return vespeneGas;
	}
	public void setVespeneGas(int vespeneGas){
		if(mineral >= 0){
			this.vespeneGas = vespeneGas;
		}
	}
	
	public void consumMineral(int minusMineral) throws Exception{
		if(getMineral() >= minusMineral){
			setMineral(getMineral()-minusMineral);
		}else{
			LackOfMineralsException e = new LackOfMineralsException();
			throw e;
		}
	}
	public void gatherMineral(int plusMineral){
		setMineral(getMineral()+plusMineral);
	}
	public void cunsumVenspeneGass(int minusVG) throws Exception{
		if(getMineral() >= minusVG){
			setVespeneGas(getVespeneGas()-minusVG);
		}else{
			LackOfMineralsException e = new LackOfMineralsException();
			throw e;
		}
	}
	public void gatherVenspeneGas(int plusVG){
		setVespeneGas(getVespeneGas()+plusVG);
	}
	
	@Override
	public boolean equals(Object o){
		if(o == null || !(o instanceof User)) {
			return false;
		}
		User tmpe = (User)o;
		return name.equals(tmpe.getName());
	}
}
