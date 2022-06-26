package kr.ac.MovieReview;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FavoritesList extends JDialog  {
	private MyList owner;
	private FavoriteJPanel[] favorites;
	private JLabel lbl;
	public FavoritesList(MyList owner) {
		super(owner, "즐겨찾기",true);
		this.owner = owner;
		init();
		setDisplay();
		addListener();
		showDlg();
	}
	private void init(){
		favorites = new FavoriteJPanel[owner.getFavoritesSize()];
		if(!(owner.getFavoritesSize()==0)){
			for(int i=0;i<favorites.length;i++){
				favorites[i] = new FavoriteJPanel(owner.getFavorites(i));
			}
		}
		
	}
	private void setDisplay(){
		JPanel pnl = new JPanel(new GridLayout(0,1));
		if(!(owner.getFavoritesSize()==0)){
			for(int i=0;i<favorites.length;i++){
				favorites[i] = new FavoriteJPanel(owner.getFavorites(i));
				pnl.add(favorites[i]);
			}
		}else{
			lbl = new JLabel("즐겨찾기 항목이 없습니다");
			lbl.setPreferredSize(new Dimension(300,100));
			pnl.add(lbl);
		}
		add(pnl);
	}
	private void addListener() {
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent we) {
				dispose();
			}
		});
	}
	private void showDlg() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();		
		Image img = toolkit.getImage("star.jpg");
		setIconImage(img);
		pack();
		setLocationRelativeTo(owner);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}
}
