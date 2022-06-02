package kr.ac.ImagViewer;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Viewer extends JFrame{
	public static final String MSG = "Click the";
	public static final String PATH = "img/";
	public static final String EXT = ".jpg";
	public static final String[] NAMES = {
		"apple","asparagus","banana", "broccoli",
		"cantaloupe", "carrot", "corn", "grapefruit",
		"grapes", "kiwi", "onion", "peach",
		"pear", "pepper", "pickle", "pineapple",
		"raspberry", "strawberry", "tomato", "watermelon"
	};
	private ImgLabel[] lblImgs;
	private CMouseListener<Viewer> mListener;
	
	public Viewer(){
		super("Viewer");
		init();
		setDisplay();
		addListeners();
		showFrame();
	}
	private void init(){
		mListener = new CMouseListener<Viewer>(this);
		//�ش� Viewer ��ü�� owner�� ������ CMouseListener<Viewer> ����
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		//Ŀ�� ���� ����
		lblImgs = new ImgLabel[NAMES.length];
		//�̹����� idx�� ������ ImgLabel ����
		for(int idx=0; idx<lblImgs.length; idx++){
			lblImgs[idx] = new ImgLabel(
				new ImageIcon(getSrcPath(idx)),idx
			);
			lblImgs[idx].setToolTipText(MSG + NAMES[idx]);
			//lblImgs�� Ŀ���� �ø��� text�� �����ش�
		}
	}
	public static ImageIcon getImageIcon(String srcPath, Dimension size){
		//���ϴ� ũ��� ImageIcon ����
		return new ImageIcon(
			Toolkit.getDefaultToolkit()
			.getImage(srcPath)
			.getScaledInstance(
				size.width,
				size.height, Image.SCALE_DEFAULT)
		);
	}
	public String getSrcPath(int idx){
		//�̹����� ������ �� �ʿ��� ��θ� string �����´�
		return NAMES[idx] + EXT;
	}
	private void setDisplay(){
		setLayout(new GridLayout(0,4));
		for(JLabel lbl : lblImgs){
			add(lbl);
		}
	}
	private void addListeners(){
		//JLabel MouseListener ����
		for(JLabel lbl : lblImgs){
			lbl.addMouseListener(mListener);
		}
	}
	private void showFrame(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public void clickImg(Component c){
		//Label�� Ŭ���ϸ�
		//�ش� Viewerâ�� ������ �ʰ� �ϰ�
		//ImgDlg ����
		ImgLabel lbl = (ImgLabel)c;
		setVisible(false);
		new ImgDlg(this, lbl.getOrder());
	}
	public int getImageCount(){
		//�̹��� ������ ����
		return NAMES.length;
	}
	public static void main(String[] args) {
		new Viewer();
	}
	
}
