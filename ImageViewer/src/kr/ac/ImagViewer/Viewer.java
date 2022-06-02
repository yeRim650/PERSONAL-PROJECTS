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
		//해당 Viewer 객체를 owner로 가지는 CMouseListener<Viewer> 생성
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		//커서 형태 변경
		lblImgs = new ImgLabel[NAMES.length];
		//이미지와 idx를 가지는 ImgLabel 생성
		for(int idx=0; idx<lblImgs.length; idx++){
			lblImgs[idx] = new ImgLabel(
				new ImageIcon(getSrcPath(idx)),idx
			);
			lblImgs[idx].setToolTipText(MSG + NAMES[idx]);
			//lblImgs에 커서를 올리면 text를 보여준다
		}
	}
	public static ImageIcon getImageIcon(String srcPath, Dimension size){
		//원하는 크기로 ImageIcon 리턴
		return new ImageIcon(
			Toolkit.getDefaultToolkit()
			.getImage(srcPath)
			.getScaledInstance(
				size.width,
				size.height, Image.SCALE_DEFAULT)
		);
	}
	public String getSrcPath(int idx){
		//이미지를 가져올 때 필요한 경로를 string 가저온다
		return NAMES[idx] + EXT;
	}
	private void setDisplay(){
		setLayout(new GridLayout(0,4));
		for(JLabel lbl : lblImgs){
			add(lbl);
		}
	}
	private void addListeners(){
		//JLabel MouseListener 설정
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
		//Label을 클릭하면
		//해당 Viewer창을 보이지 않게 하고
		//ImgDlg 생성
		ImgLabel lbl = (ImgLabel)c;
		setVisible(false);
		new ImgDlg(this, lbl.getOrder());
	}
	public int getImageCount(){
		//이미지 갯수를 리턴
		return NAMES.length;
	}
	public static void main(String[] args) {
		new Viewer();
	}
	
}
