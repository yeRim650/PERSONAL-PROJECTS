package kr.ac.Login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class InformationForm extends JDialog implements ActionListener{
	//JDialog를 상속받고 ActionListener를 구현한다.
	private JTextArea taInfo;
	private JButton btnLogout;
	private JButton btnWithdraw;
	private User user;
	private LoginForm owner;
	public InformationForm(LoginForm owner, User user){
		super(owner, "Information", true);
		//LoginForm가 owner 객체
		//타이틀 "Information"
		//모달창 속성 true(모달창이 띄어있으면 LoginForm 이용 못함)
		this.owner = owner;
		this.user = user;
		init();
		setDisplay();
		addListener();
		showDlg();
	}
	
	private void init(){
		btnLogout = LoginUtils.getButton("Logout");
		btnWithdraw = LoginUtils.getButton("Withdraw");
		
		taInfo = new JTextArea(10, 40);
		taInfo.setEditable(false);
		TitledBorder tBorder = new TitledBorder(
			new LineBorder(Color.GRAY, 1),
			"Check your Information"
		);
		tBorder.setTitleFont(new Font(Font.DIALOG, Font.BOLD, 13));
		taInfo.setBorder(tBorder);
		taInfo.setText(user.toString());
	}
	private void setDisplay(){
		JPanel pnlBtns = new JPanel();
		pnlBtns.add(btnLogout);
		pnlBtns.add(btnWithdraw);
		
		add(taInfo, BorderLayout.CENTER);
		add(pnlBtns, BorderLayout.SOUTH);
	}
	private void addListener(){
		//창을 종료할때 logout 메서드효출
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				logout();
				
			}
		});
		
		btnLogout.addActionListener(this);
		//생성된 LoginForm 객체가 ActionListener를 구현하고 있어 this가 ActionListener가 된다
		btnWithdraw.addActionListener(this);
	}
	private void  showDlg(){
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}
	
	private void logout(){
		//logout 확인 모달창을 띄운다.
		//YES_OPTION를 받으면 LoginForm을 볼수 있게 만들고 해당창을 종료한다.
		int result = JOptionPane.showConfirmDialog(
			this,
			"logout : are you sure?",
			"Qusetion",
			JOptionPane.YES_NO_OPTION,
			JOptionPane.QUESTION_MESSAGE
		);
		if(result == JOptionPane.YES_OPTION){
			owner.setVisible(true);
			dispose();
		}
	}
	private void withdraw(){
		//withdraw 확인 모달창을 띄운다.
		//YES_OPTION를 받으면 password 입력받을 수 있는 모달창을 띄어 user의 pw와 일치하면
		//LoginForm owner의 removerUser 메서드로 Vector<User> list에서 user삭제하고
		//good bye~ 메시지 모달창을 띄우고 LoginForm을 볼수 있게 만들고 해당창을 종료
		//pw가 일치하지 않으면 check password 메시지 모달창을 띄운다.
		int result = JOptionPane.showConfirmDialog(
				this,
				"do you really want to withdraw?",
				"Question",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE
		);
		if(result == JOptionPane.YES_OPTION){
			String password = JOptionPane.showInputDialog(
				this,
				"input your password",
				"check password",
				JOptionPane.INFORMATION_MESSAGE
			);
			String msg = "good bye~";
			
			boolean isCorrect = password.equals(user.getUpw());
			if(!isCorrect){
				msg = "wrong password";
			}
			JOptionPane.showMessageDialog(
				this,
				msg,
				"Message",
				JOptionPane.INFORMATION_MESSAGE
			);
			if(isCorrect){
				owner.removerUser(user);
				owner.setVisible(true);
				dispose();
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent ae){//btnLogout 클릭시 logout()호출 이니면 withdraw()호출
		if(ae.getSource() == btnLogout){
			logout();
		}else{
			withdraw();
		}
	}
}
