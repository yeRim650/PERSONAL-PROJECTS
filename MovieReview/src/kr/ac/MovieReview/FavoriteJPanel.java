package kr.ac.MovieReview;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class FavoriteJPanel extends JPanel {
	
	private JTextArea taFavoritesTa;
	private JLabel lblImg1;
	private MovieReview m;
	
	public FavoriteJPanel(MovieReview m) {
		super(new BorderLayout());
		this.m = m;
		init();
		setDisplay();
	}
	private void init() {
		lblImg1 = new JLabel(new ImageIcon("heart-icon.png"),JLabel.CENTER);
		String result =  "영화 제목: "+ m.getMovieTitle()+"\n";
		result += "장르: "+ m.getGenre()+"\n";
		result += "영화 감독: "+ m.getGenre()+"\n";
		result += "평점: "+ m.getAmount()+"\n";
		String review = m.getReview();
		if(review.contains("\n")){
			review = review.replaceAll("\n", " ");
		}
		result += "리뷰 내용: "+ review;
		taFavoritesTa = new JTextArea(result,5,30);
		taFavoritesTa.setEditable(false);
		taFavoritesTa.setLineWrap(true);
		taFavoritesTa.setBorder(new LineBorder(Color.GRAY));
		
	}
	private void setDisplay() {
		add(lblImg1,BorderLayout.WEST);
		add(taFavoritesTa,BorderLayout.EAST);
		
	}

}
