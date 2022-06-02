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
	//JDialog ��� ActionListener ����
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
		//Ŭ���� ImgLabel�� lblImgs �ε����� order�� �޴´�.
		super(owner, "Do u see?", true);
		//Viewer owner Ÿ��Ʋ "Do u see?" ��޼Ӽ� true
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
		miBack = new JMenuItem("�ڷ�");
		miForward = new JMenuItem("������");
		pMenu.add(miBack);
		pMenu.add(miForward);
	}
	private String getStatus(){
		//â ������ ��ġ�Ǵ� �ε���+1
		return (order + 1) + "/" + owner.getImageCount();
	}
	private void setDisplay(){
		add(lblStatus, BorderLayout.NORTH);
		add(lbl, BorderLayout.CENTER);
	}
	private void addListener(){
		miBack.addActionListener(this);
		//������ LoginForm ��ü�� ActionListener�� �����ϰ� �־� this�� ActionListener�� �ȴ�
		miForward.addActionListener(this);
		lbl.addMouseListener(new CMouseListener<ImgDlg>(this));
		//lbl�� CMouseListener<ImgDlg> ��ü�� MouseListener�� ������
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we){
				owner.setVisible(true);
				dispose();
			}
		});
	}
	public void showPopup(MouseEvent me){
		//JPopupMenu pMenu�� �̺�Ʈ�� �߻��ϴ� ��ҿ��� ���콺��ġ�� �����ش�.
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
			//direction�� ���� ������
			//�̹��� �ִ� �ε���(�̹��� ���� -1)�ΰ�� 0 �ƴϸ� order +1
			//�̹��� �ε��� 0�ΰ�� �̹��� �ִ� �ε���(�̹��� ���� -1) �ƴϸ� order -1
			order =(order == owner.getImageCount()-1)? 0 : order+1;
		}else{
			order = (order == 0) ? owner.getImageCount()-1 : order-1;
		}
		ImageIcon icon =
			Viewer.getImageIcon(owner.getSrcPath(order), PIC_SIZE);
		//PIC_SIZE ũ���� �̹����� �����´�,
		lbl.setIcon(icon);
		lblStatus.setText(getStatus());
	}
	@Override
	public void actionPerformed(ActionEvent ae){
		//JPopupMenu���� miBack Ŭ���� �Ķ���� -1�� ���� clickImgȣ��
		//miForward Ŭ���� �Ķ���� 1�� ���� clickImg ȣ��
		if(ae.getSource() == miBack){
			clickImg(PREV);
		}else{
			clickImg(NEXT);
		}
	}
}
