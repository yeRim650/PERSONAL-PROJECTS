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
	//JDialog�� ��ӹް� ActionListener�� �����Ѵ�.
	private JTextArea taInfo;
	private JButton btnLogout;
	private JButton btnWithdraw;
	private User user;
	private LoginForm owner;
	public InformationForm(LoginForm owner, User user){
		super(owner, "Information", true);
		//LoginForm�� owner ��ü
		//Ÿ��Ʋ "Information"
		//���â �Ӽ� true(���â�� ��������� LoginForm �̿� ����)
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
		//â�� �����Ҷ� logout �޼���ȿ��
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				logout();
				
			}
		});
		
		btnLogout.addActionListener(this);
		//������ LoginForm ��ü�� ActionListener�� �����ϰ� �־� this�� ActionListener�� �ȴ�
		btnWithdraw.addActionListener(this);
	}
	private void  showDlg(){
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}
	
	private void logout(){
		//logout Ȯ�� ���â�� ����.
		//YES_OPTION�� ������ LoginForm�� ���� �ְ� ����� �ش�â�� �����Ѵ�.
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
		//withdraw Ȯ�� ���â�� ����.
		//YES_OPTION�� ������ password �Է¹��� �� �ִ� ���â�� ��� user�� pw�� ��ġ�ϸ�
		//LoginForm owner�� removerUser �޼���� Vector<User> list���� user�����ϰ�
		//good bye~ �޽��� ���â�� ���� LoginForm�� ���� �ְ� ����� �ش�â�� ����
		//pw�� ��ġ���� ������ check password �޽��� ���â�� ����.
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
				owner.removeUser(user);
				owner.setVisible(true);
				dispose();
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent ae){//btnLogout Ŭ���� logout()ȣ�� �̴ϸ� withdraw()ȣ��
		if(ae.getSource() == btnLogout){
			logout();
		}else{
			withdraw();
		}
	}
}