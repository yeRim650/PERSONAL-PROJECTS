package kr.ac.Lotto;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

class MyNums {
	// 로또 번호 담을 Integer배열
	private Integer[] nums;
	// 생성자 : nums배열에 autoSelect(6) 저장
	public MyNums() {
		nums = autoSelect(6);
	}
	// 생성자 : Integer 배열이 여러개 담김
	public MyNums(Integer[] nums) {
		
		Arrays.sort(nums);	// nums 배열을 정렬
		this.nums = nums;
	}
	// 자동번호를 뽑아서 Integer 배열로 반환 
	protected Integer[] autoSelect(int count) {
		Random r = new Random();
		HashSet<Integer> set = new HashSet<Integer>();	// 중복이 없어야 하므로 HashSet
		// set의 사이즈가 count보다 작은 동안 1-46 정수중에 랜덤으로 뽑아서 set에 추가 
		while(set.size() < count) { 
			set.add(r.nextInt(45)+1);
		}
		// set을 Integer 배열로 바꿈
		Integer[] nums = set.toArray(new Integer[0]);
		Arrays.sort(nums, 0, 6);	// nums 배열을 인덱스 0번부터 5번까지 정렬
		return nums;
	}
	public Integer[] getNums() {
		return nums;
	}
	public void setNums(Integer... nums) {
		this.nums = nums;
	}
	@Override
	public String toString() {
		String info = nums[0].intValue()+"";
		for(int i = 1; i<6; i++){
			info += " - "+nums[i].intValue();
		}
		return info;
	}
}