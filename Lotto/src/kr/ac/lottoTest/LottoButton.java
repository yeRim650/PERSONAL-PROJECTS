package kr.ac.lottoTest;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

public class LottoButton extends JButton{
	public LottoButton(String name, boolean enabled){
		super(name);
		if(enabled){
			setBackground(new Color(0x5D5D5D));
			setForeground(Color.WHITE);
			setBorderPainted(false);
			setFocusPainted(false);
		}else{
			setEnabled(false);
		}
		setPreferredSize(new Dimension(60, 23));
	}
	public LottoButton(String name){
		super(name);
		setBackground(new Color(0x5D5D5D));
		setForeground(Color.WHITE);
		setBorderPainted(false);
		setFocusPainted(false);
	}
}
