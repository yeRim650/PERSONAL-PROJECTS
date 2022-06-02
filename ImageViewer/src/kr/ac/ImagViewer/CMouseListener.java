package kr.ac.ImagViewer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.border.LineBorder;

public class CMouseListener<W extends Container> extends MouseAdapter {
	//MouseAdapter ��ӹ޴� MouseListener Ŭ����
	//owner�� Container�� ��� �޴� ��ü�� ���� ������ �� �ִ�.
	private W owner;
	public CMouseListener(W owner){
		this.owner = owner;
	}
	@Override
	public void mouseEntered(MouseEvent me){//Ŀ���� �ø��� MouseEvent ��ü setBorder ȣ��
		setBorder(me, true);
	}
	@Override
	public void mouseExited(MouseEvent me){//Ŀ���� ������ MouseEvent ��ü setBorder ȣ��
		setBorder(me, false);
	}
	@Override
	public void mousePressed(MouseEvent me){//������ showPopup ȣ��
		showPopup(me);
	}
	@Override
	public void mouseReleased(MouseEvent me){
		//������ ���콺 ������ Ŭ���� showPopupȣ��
		//�ƴϸ� owner Viewer�� ����ȯ�� clickImg ȣ��
		//owner�� Viewer�� �ƴϸ� ����ȯ�� clickImg ȣ��
		if(me.getButton() == MouseEvent.BUTTON3){
			showPopup(me);
		}else{
			Component c = (Component)me.getSource();
			if(owner instanceof Viewer){
				((Viewer)owner).clickImg(c);
			}else{
				Dimension size = c.getSize();
				((ImgDlg)owner).clickImg(size.width/2 - me.getX());
			}
		}
	}
	private void showPopup(MouseEvent me){
		////�˾���ȣ �´��� Ȯ��
		if(me.isPopupTrigger()){
			if(owner instanceof ImgDlg){//owner�� ImgDlg�� ����ȭ �� showPopup ȣ��
				((ImgDlg)owner).showPopup(me);
			}
		}
	}
	private void setBorder(MouseEvent me, boolean flag){
		JComponent c = (JComponent)me.getComponent();
		//flag�� true�� lineBorder�� setBorder
		//false�� unll�� setBorder
		if(flag){
			c.setBorder(new LineBorder(Color.GREEN, 2, true));
		}else{
			c.setBorder(null);
		}
	}
}
