package kr.ac.lottoTest;

class LottoNums extends MyNums {
	public static final int BONUS = 6;	// 인덱스 6번 번호는 보너스번호
	// 생성자 : autoSelect에 파라미터 7넣어서 nums 배열 만듦
	public LottoNums() {
		setNums(autoSelect(7));
	}
	// 생성자 : Integer가 여러개 담는 배열
	public LottoNums(Integer... nums) {
		super(nums);
	}
	// 보너스번호
	public Integer getBonusNum() {
		return getNums()[BONUS];
	}
	@Override
	public String toString() {
		return super.toString() + " - "+getNums()[BONUS].intValue();
	}
}