package kr.ac.MovieReview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class MyStyle extends JDialog {

	private JTextArea taMyBestMovie;
	private JTextArea taMyFavoriteDirector;
	private JTextArea taMyFavoriteGenre;

	private JButton btnClose;
	private MyList owner;

	public MyStyle(MyList owner) {
		super(owner, "취향 분석",true);
		this.owner = owner;
		init();
		setDisplay();
		addListeners();
		showFrame();
	}
	private void init() {
		taMyBestMovie = new JTextArea(7, 25);
		taMyBestMovie.setLineWrap(true);
		
		taMyFavoriteDirector = new JTextArea(5, 25);
		taMyFavoriteDirector.setLineWrap(true);
		
		taMyFavoriteGenre = new JTextArea(5, 25);
		taMyFavoriteGenre.setLineWrap(true);
		
		btnClose = new JButton("닫기");
	}
	
	private void setDisplay() {
		JPanel pnlMain = new JPanel(new BorderLayout());

		JPanel pnlNorth = new JPanel(new GridLayout(0,1));
		JPanel pnlBestMovie = new JPanel();
		pnlBestMovie.add(new JLabel(owner.bestMovie(0)));
		pnlBestMovie.add(new JLabel("/"));
		pnlBestMovie.add(new JLabel(owner.bestMovie(1)));
		pnlBestMovie.add(new JLabel("/"));
		pnlBestMovie.add(new JLabel(owner.bestMovie(2)));
		pnlBestMovie.setBackground(Color.WHITE);
		pnlNorth.add(pnlBestMovie);
		
		TitledBorder tBorder1 = new TitledBorder("최근에 본 최고의 작품");
		tBorder1.setTitlePosition(TitledBorder.ABOVE_TOP);
		pnlNorth.setBorder(tBorder1);
		
		JPanel pnlCenter = new JPanel(new GridLayout(0,1));
		
		TitledBorder tBorder2 = new TitledBorder("선호 감독");
		tBorder2.setTitlePosition(TitledBorder.ABOVE_TOP);
		pnlCenter.setBorder(tBorder2);
		
		JPanel pnlRank1= new JPanel(new FlowLayout(FlowLayout.LEFT));
		Toolkit kit = Toolkit.getDefaultToolkit();//
		Image imgNum = kit.getImage("medal1.png");//
		Image newImgNum = imgNum.getScaledInstance(30,30,Image.SCALE_SMOOTH);//
		JLabel lbl = new JLabel(new ImageIcon(newImgNum));
		
		pnlRank1.add(lbl);//
		pnlRank1.add(new JLabel(owner.directorList(0)));
		pnlRank1.setBackground(Color.WHITE);
		pnlCenter.add(pnlRank1);
		
		JPanel pnlRank2= new JPanel(new FlowLayout(FlowLayout.LEFT));
		imgNum = kit.getImage("medal2.png");//
		newImgNum = imgNum.getScaledInstance(30,30,Image.SCALE_SMOOTH);//
		lbl = new JLabel();//
		lbl.setIcon(new ImageIcon(newImgNum));//
		
		pnlRank2.add(lbl);//
		pnlRank2.add(new JLabel(owner.directorList(1)));
		pnlRank2.setBackground(Color.WHITE);
		pnlCenter.add(pnlRank2);
		
		JPanel pnlRank3= new JPanel(new FlowLayout(FlowLayout.LEFT));
		imgNum = kit.getImage("medal3.png");//
		newImgNum = imgNum.getScaledInstance(30,30,Image.SCALE_SMOOTH);//
		lbl = new JLabel();//
		lbl.setIcon(new ImageIcon(newImgNum));//
		
		pnlRank3.add(lbl);//
		pnlRank3.add(new JLabel(owner.directorList(2)));
		pnlRank3.setBackground(Color.WHITE);
		pnlCenter.add(pnlRank3);
		
		JPanel pnlSouth = new JPanel(new GridLayout(0,1));
		
		JPanel pnlGenre = new JPanel();
		pnlGenre.add(new JLabel(owner.genrelist(0)));
		pnlGenre.add(new JLabel("/"));
		pnlGenre.add(new JLabel(owner.genrelist(1)));
		pnlGenre.add(new JLabel("/"));
		pnlGenre.add(new JLabel(owner.genrelist(2)));
		pnlGenre.setBackground(Color.WHITE);
		pnlSouth.add(pnlGenre);
		
		TitledBorder tBorder3 = new TitledBorder("영화 선호장르");
		tBorder3.setTitlePosition(TitledBorder.ABOVE_TOP);
		pnlSouth.setBorder(tBorder3);
		
		pnlMain.add(pnlNorth, BorderLayout.NORTH);
		pnlMain.add(pnlCenter, BorderLayout.CENTER);
		pnlMain.add(pnlSouth, BorderLayout.SOUTH);

		JPanel pnlbtn = new JPanel();
		pnlbtn.add(btnClose);
		
		add(pnlMain, BorderLayout.NORTH);
		add(pnlbtn, BorderLayout.SOUTH);
		pnlMain.setBorder(new EmptyBorder(10, 10, 10, 10));
		pnlbtn.setBorder(new EmptyBorder(0, 0, 20, 0));
	}
	
	private void addListeners() {
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent we) {
				dispose();
			}
		});
	}
	private void showFrame() {
		setSize(500,380);
		setResizable(false);
		setLocationRelativeTo(owner);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}
}