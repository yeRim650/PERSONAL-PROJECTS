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
	//MouseAdapter 상속받는 MouseListener 클래스
	//owner를 Container를 상속 받는 객체로 임의 지정할 수 있다.
	private W owner;
	public CMouseListener(W owner){
		this.owner = owner;
	}
	@Override
	public void mouseEntered(MouseEvent me){//커서를 올리면 MouseEvent 객체 setBorder 호출
		setBorder(me, true);
	}
	@Override
	public void mouseExited(MouseEvent me){//커서를 내리면 MouseEvent 객체 setBorder 호출
		setBorder(me, false);
	}
	@Override
	public void mousePressed(MouseEvent me){//누르면 showPopup 호출
		showPopup(me);
	}
	@Override
	public void mouseReleased(MouseEvent me){
		//뗐을때 마우스 오른쪽 클릭시 showPopup호출
		//아니면 owner Viewer시 형변환후 clickImg 호출
		//owner가 Viewer가 아니면 형변환후 clickImg 호출
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
		////팝업신호 맞는지 확인
		if(me.isPopupTrigger()){
			if(owner instanceof ImgDlg){//owner가 ImgDlg시 형변화 후 showPopup 호출
				((ImgDlg)owner).showPopup(me);
			}
		}
	}
	private void setBorder(MouseEvent me, boolean flag){
		JComponent c = (JComponent)me.getComponent();
		//flag가 true면 lineBorder로 setBorder
		//false면 unll로 setBorder
		if(flag){
			c.setBorder(new LineBorder(Color.GREEN, 2, true));
		}else{
			c.setBorder(null);
		}
	}
}
