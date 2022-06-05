package kr.ac.Lotto;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

class MyNums {
	// �ζ� ��ȣ ���� Integer�迭
	private Integer[] nums;
	// ������ : nums�迭�� autoSelect(6) ����
	public MyNums() {
		nums = autoSelect(6);
	}
	// ������ : Integer �迭�� ������ ���
	public MyNums(Integer[] nums) {
		
		Arrays.sort(nums);	// nums �迭�� ����
		this.nums = nums;
	}
	// �ڵ���ȣ�� �̾Ƽ� Integer �迭�� ��ȯ 
	protected Integer[] autoSelect(int count) {
		Random r = new Random();
		HashSet<Integer> set = new HashSet<Integer>();	// �ߺ��� ����� �ϹǷ� HashSet
		// set�� ����� count���� ���� ���� 1-46 �����߿� �������� �̾Ƽ� set�� �߰� 
		while(set.size() < count) { 
			set.add(r.nextInt(45)+1);
		}
		// set�� Integer �迭�� �ٲ�
		Integer[] nums = set.toArray(new Integer[0]);
		Arrays.sort(nums, 0, 6);	// nums �迭�� �ε��� 0������ 5������ ����
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