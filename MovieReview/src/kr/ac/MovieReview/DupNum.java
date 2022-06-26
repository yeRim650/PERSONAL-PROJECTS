package kr.ac.MovieReview;

public class DupNum implements Comparable<DupNum>{
	private String dupObject;
	private int num;
	
	public DupNum(String genre) {
		super();
		this.dupObject = genre;
	}
	public DupNum(String genre, int num) {
		super();
		this.dupObject = genre;
		this.num = num;
	}
	public String getDupObject() {
		return dupObject;
	}
	public void setDupObject(String genre) {
		this.dupObject = genre;
	}
	public int getNum() {
		return num;
	}
	public void plusNum(){
		num++;
	}
	@Override
	public String toString() {
		return "Genrelist [genre=" + dupObject + ", num=" + num + "]";
	}
	@Override
	public int compareTo(DupNum target){
		return target.getNum()-num;
	}
	
	@Override
	public boolean equals(Object o){
		if(o==null || !(o instanceof DupNum)){
				return false;
		}
		DupNum temp = (DupNum)o;

		return dupObject.equals(temp.getDupObject());
	}
	
}
