package kr.ac.Lotto;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImageNumJPanel extends JPanel{
	public static final String EXE = ".png";
	public static final String PATH_COLOR = "lottoc/";
	public static final String PATH_WHITE = "lottow/";
	private boolean select = false;
	private JLabel[] lbls;
	private Toolkit kit = Toolkit.getDefaultToolkit();
	public ImageNumJPanel(){
		lbls = new JLabel[6];
		for(int i = 0; i<6;i++){
			lbls[i] = new JLabel();
			lbls[i].setIcon(setSize("gray.png"));
			add(lbls[i]);
		}
		setBackground(Color.WHITE);
	}
	public ImageNumJPanel(MyNums nums, ArrayList<Integer> myLists, Integer ranks){
		if(ranks == 0){
			add(new JLabel("²Î"));
		}else{
			add(new JLabel(ranks+"µî"));
		}
		lbls = new JLabel[6];
		for(int i = 0; i<6;i++){
			lbls[i] = new JLabel();
			lbls[i].setIcon(setSize(PATH_WHITE+nums.getNums()[i]+EXE));
			for(Integer list : myLists){
				if(nums.getNums()[i]==list){
					lbls[i].setIcon(setSize(PATH_COLOR+nums.getNums()[i]+EXE));
				}
			}
			add(lbls[i]);
		}
		setBackground(Color.WHITE);
	}
	public ImageNumJPanel(LottoNums loNums){
		add(new JLabel("´çÃ·¹øÈ£ "));
		lbls = new JLabel[7];
		for(int i = 0; i<7;i++){
			lbls[i] = new JLabel();
			if(i == 6 ){
				add(new JLabel("+"));
			}
			lbls[i].setIcon(setSize(PATH_COLOR+loNums.getNums()[i]+EXE));
			add(lbls[i]);
		}
		setBackground(Color.WHITE);
	}
	public void setSelectNum(MyNums nums){
		for(int i = 0; i<6;i++){
			lbls[i].setIcon(setSize(PATH_COLOR+nums.getNums()[i]+EXE));
		}
		select = true;
	}
	public void setResetNum(){
		for(int i = 0; i<6;i++){
			lbls[i].setIcon(setSize("gray.png"));
		}
		select = false;
	}
	public boolean isSelect() {
	      return select;
	}
	private ImageIcon setSize(String image){
		Image imgNum = kit.getImage(image);
		Image newImgNum = imgNum.getScaledInstance(40,40,Image.SCALE_SMOOTH);
		return new ImageIcon(newImgNum);
	}
}
