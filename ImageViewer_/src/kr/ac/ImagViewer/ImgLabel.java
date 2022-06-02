package kr.ac.ImagViewer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImgLabel extends JLabel{
	private int order;
	public ImgLabel(ImageIcon icon, int order){//icon과 인덱스 번호를 가지는 JLabel

		this.order = order;
	}
	public int getOrder(){
		return order;
	}
}
