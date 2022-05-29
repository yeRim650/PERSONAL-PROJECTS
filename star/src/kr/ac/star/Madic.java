package kr.ac.star;

public class Madic extends WalkUnit implements ITerranUnit{
	private int energy;
	private int maxEnergy;
	private int amountOfHeal;
	private int useEnergyForHeal;
	
	public Madic(User user, Point point){
		super(user, point, 60, 60);
		setEnergy(200);
		setMaxEnergy(200);
		setAmountOfHeal(19);
		setUseEnergyForHeal(1);
	}
	
	public Madic(User user, Point point, int hp, int maxHp, int energy, int maxEnergy, int amountOfHeal, int useEnergyForHeal){
		super(user, point, hp, maxHp);
		setEnergy(energy);
		setMaxEnergy(maxEnergy);
		setAmountOfHeal(amountOfHeal);
		setUseEnergyForHeal(useEnergyForHeal);
	}
	
	public int getEnergy() {
		return energy;
	}
	public void setEnergy(int energy) {
		if(energy>=0){
			this.energy = energy;
		}
	}
	public int getMaxEnergy() {
		return maxEnergy;
	}
	public void setMaxEnergy(int maxEnergy) {
		this.maxEnergy = maxEnergy;
	}
	public int getAmountOfHeal() {
		return amountOfHeal;
	}
	public void setAmountOfHeal(int amountOfHeal) {
		this.amountOfHeal = amountOfHeal;
	}
	public int getUseEnergyForHeal() {
		return useEnergyForHeal;
	}
	public void setUseEnergyForHeal(int useEnergyForHeal) {
		this.useEnergyForHeal = useEnergyForHeal;
	}
	
	public void heal(ITerranUnit target) throws Exception{
		if(getUser().equals(target.getUser())){
			if(getEnergy()>=getUseEnergyForHeal()){
				target.setHp(getHp()+getAmountOfHeal());
			}
		}
	}
}
