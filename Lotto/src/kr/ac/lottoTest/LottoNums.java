package kr.ac.lottoTest;

class LottoNums extends MyNums {
	public static final int BONUS = 6;	// �ε��� 6�� ��ȣ�� ���ʽ���ȣ
	// ������ : autoSelect�� �Ķ���� 7�־ nums �迭 ����
	public LottoNums() {
		setNums(autoSelect(7));
	}
	// ������ : Integer�� ������ ��� �迭
	public LottoNums(Integer... nums) {
		super(nums);
	}
	// ���ʽ���ȣ
	public Integer getBonusNum() {
		return getNums()[BONUS];
	}
	@Override
	public String toString() {
		return super.toString() + " - "+getNums()[BONUS].intValue();
	}
}