package kr.ac.MovieReview;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.jws.Oneway;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class PwSetting extends JDialog implements ActionListener {

	private JButton btnJoin;
	private JButton btnCancel;
	private LoginForm owner;

	private JPasswordField pfPw;
	private JPasswordField pfCheckPw;

	private JLabel lblPw;
	private JLabel lblCheckPw;

	public PwSetting(LoginForm owner) {
		super(owner, "PwSetting", true);
		this.owner = owner;
		init();
		setDisplay();
		addListener();
		showDlg();
	}

	private void init() {
		btnJoin = MyReviewUtils.getButton("Join");
		btnCancel = MyReviewUtils.getButton("Cancel");

		lblPw = MyReviewUtils.getLabel("비밀번호");
		lblCheckPw = new JLabel("비밀번호 확인" ,  JLabel.LEFT);
		lblCheckPw.setPreferredSize(new Dimension(110,25));

		pfPw = (JPasswordField) MyReviewUtils.getTextComponent(MyReviewUtils.PASSWORD);
		pfCheckPw = (JPasswordField) MyReviewUtils.getTextComponent(MyReviewUtils.PASSWORD);
	
	}

	private void setDisplay() {

		JPanel pnlText = new JPanel(new GridLayout(0, 1));
		pnlText.add(lblPw);
		pnlText.add(lblCheckPw);

		JPanel pnlComponent = new JPanel(new GridLayout(0, 1));
		pnlComponent.add(pfPw);
		pnlComponent.add(pfCheckPw);

		JPanel pnl = new JPanel();
		pnl.add(pnlText);
		pnl.add(pnlComponent);
		
		JPanel pnlButton = new JPanel(new GridLayout(1, 0));
		pnlButton.add(btnJoin);
		pnlButton.add(btnCancel);
		
		add(pnl, BorderLayout.NORTH);
		add(pnlButton , BorderLayout.CENTER);
		
	}

	private void addListener() {
		btnCancel.addActionListener(this);
		btnJoin.addActionListener(this);
		pfCheckPw.addActionListener(this);
	}

	private void showDlg() {
		pack();
		setLocationRelativeTo(owner);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src == btnJoin || src == pfCheckPw){
			String inputPW = pfPw.getText();
			String inputCheck = pfCheckPw.getText();
			if(inputPW.trim().length() != 0 && inputPW != null) {
				if(inputCheck.trim().length() != 0 && inputCheck != null) {
					if(inputPW.equals(inputCheck)){
						owner.setPw(inputPW);
						JOptionPane.showMessageDialog(this, "비밀번호 설정 완료.");
						dispose();
					}else{
						JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다.");
					}
				} else {
					JOptionPane.showMessageDialog(this, "비밀번호 확인이 입력되지 않았습니다.");
				}
			}else{
				JOptionPane.showMessageDialog(this, "설정될 비밀번호가 입력되지 않았습니다.");
			}
		}else{
			dispose();
		}
	}
}
