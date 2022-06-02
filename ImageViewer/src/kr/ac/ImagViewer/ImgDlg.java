package kr.ac.ImagViewer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class ImgDlg extends JDialog implements ActionListener{
	//JDialog 상속 ActionListener 구현
	public static final int NEXT = -1;
	public static final int PREV = 1;
	public final static Dimension PIC_SIZE = new Dimension(500,500);
	private Viewer owner;
	private JLabel lbl;
	private int order;
	private JLabel lblStatus;
	private JPopupMenu pMenu;
	private JMenuItem miBack;
	private JMenuItem miForward;
	public ImgDlg(Viewer owner, int order){
		//클릭한 ImgLabel의 lblImgs 인덱스를 order로 받는다.
		super(owner, "Do u see?", true);
		//Viewer owner 타이틀 "Do u see?" 모달속성 true
		this.owner = owner;
		this.order = order;
		init();
		setDisplay();
		addListener();
		showDlg();
	}
	
	private void init(){
		lblStatus = new JLabel(getStatus(), JLabel.CENTER);
		lblStatus.setFont(new Font(Font.DIALOG, Font.BOLD,13));
		lbl = new JLabel(
			Viewer.getImageIcon(owner.getSrcPath(order), PIC_SIZE));
		pMenu = new JPopupMenu();
		miBack = new JMenuItem("뒤로");
		miForward = new JMenuItem("앞으로");
		pMenu.add(miBack);
		pMenu.add(miForward);
	}
	private String getStatus(){
		//창 상위에 배치되는 인덱스+1
		return (order + 1) + "/" + owner.getImageCount();
	}
	private void setDisplay(){
		add(lblStatus, BorderLayout.NORTH);
		add(lbl, BorderLayout.CENTER);
	}
	private void addListener(){
		miBack.addActionListener(this);
		//생성된 LoginForm 객체가 ActionListener를 구현하고 있어 this가 ActionListener가 된다
		miForward.addActionListener(this);
		lbl.addMouseListener(new CMouseListener<ImgDlg>(this));
		//lbl이 CMouseListener<ImgDlg> 객체를 MouseListener로 가진다
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we){
				owner.setVisible(true);
				dispose();
			}
		});
	}
	public void showPopup(MouseEvent me){
		//JPopupMenu pMenu를 이벤트가 발생하는 요소에서 마우스위치에 보여준다.
		pMenu.show(me.getComponent(), me.getX(), me.getY());
	}
	private void showDlg(){
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		pack();
		setLocation(200,200);
		setVisible(true);
	}
	public void clickImg(int direction){
		if(direction < 0){
			//direction가 보다 작으면
			//이미지 최대 인덱스(이미지 객수 -1)인경우 0 아니면 order +1
			//이미지 인덱스 0인경우 이미지 최대 인덱스(이미지 객수 -1) 아니면 order -1
			order =(order == owner.getImageCount()-1)? 0 : order+1;
		}else{
			order = (order == 0) ? owner.getImageCount()-1 : order-1;
		}
		ImageIcon icon =
			Viewer.getImageIcon(owner.getSrcPath(order), PIC_SIZE);
		//PIC_SIZE 크기의 이미지를 가져온다,
		lbl.setIcon(icon);
		lblStatus.setText(getStatus());
	}
	@Override
	public void actionPerformed(ActionEvent ae){
		//JPopupMenu에서 miBack 클릭시 파라미터 -1를 가진 clickImg호출
		//miForward 클릭시 파라미터 1을 가진 clickImg 호출
		if(ae.getSource() == miBack){
			clickImg(PREV);
		}else{
			clickImg(NEXT);
		}
	}
}
