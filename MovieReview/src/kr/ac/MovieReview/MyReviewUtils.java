package kr.ac.MovieReview;

import java.awt.Dimension;
import java.io.Closeable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class MyReviewUtils {
	public static final int PASSWORD = 1;
	private static final Dimension LABEL_SIZE =
			new Dimension(250, 20);//����
	private static final Dimension FIELD_SIZE =
			new Dimension(110, 20);
	private static final Dimension BTN_SIZE =
			new Dimension(90, 23);
	public static JLabel getLabel(String str) {
		JLabel lbl = new JLabel(str, JLabel.LEFT);
		lbl.setPreferredSize(LABEL_SIZE);
		return lbl;
	}
	public static JButton getButton(String str) {
		JButton btn = new JButton(str);
		btn.setPreferredSize(BTN_SIZE);
		return btn;
	}
	public static JTextComponent getTextComponent(int kind) {
			JTextComponent text = null;
			if(kind == PASSWORD) {
					text = new JPasswordField();
			} else {
					text = new JTextField();
			}
			text.setPreferredSize(FIELD_SIZE);
			return text;
	}
	public static boolean isEmpty(JTextComponent input) {
		String text = input.getText().trim();
		return (text.length() == 0) ? true : false;
	}
	public static void closeAll(Closeable...c){
		for(Closeable temp : c){
			try{
				temp.close();
			}catch(Exception e){}
		}
	}
}