package kr.ac.Login;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

public class LoginForm extends JFrame implements ActionListener{
	//JFrame�� ��ӹް� ActionListener�� �����Ѵ�.
	private JTextComponent tfId;
	private JTextComponent pfPw;
	
	private JButton btnLogin;
	private JButton btnJoin;
	
	private Vector<User> list;
	
	public LoginForm(){
		init();
		setDisplay();
		addListener();
		showFrame();
	}
	
	private void init(){
		list = new Vector<User>();
		tfId = LoginUtils.getTextComponent(LoginUtils.TEXT);
		pfPw = LoginUtils.getTextComponent(LoginUtils.PASSWORD);
		
		btnJoin = LoginUtils.getButton("Join");
		btnLogin = LoginUtils.getButton("Login");
	}
	
	private void setDisplay(){
		JPanel  pnlText = new JPanel(new GridLayout(0,1));
		JPanel pnlInput = new JPanel(new GridLayout(0,1));
		
		pnlText.add(LoginUtils.getLabel("ID"));
		pnlText.add(LoginUtils.getLabel("Password"));
		
		JPanel pnlId = new JPanel();
		pnlId.add(tfId);
		JPanel pnlPw = new JPanel();
		pnlPw.add(pfPw);
		pnlInput.add(pnlId);
		pnlInput.add(pnlPw);
		JPanel pnlSouth = new JPanel();
		pnlSouth.add(btnLogin);
		pnlSouth.add(btnJoin);
		
		JPanel pnlMain = new JPanel(new BorderLayout());
		pnlMain.add(pnlText, BorderLayout.WEST);
		pnlMain.add(pnlInput, BorderLayout.CENTER);
		pnlMain.add(pnlSouth, BorderLayout.SOUTH);
		
		pnlMain.setBorder(new EmptyBorder(5,10,5,10));
		
		add(pnlMain, BorderLayout.CENTER);
		
	}
	
	private void addListener(){
		btnLogin.addActionListener(this);
		//������ LoginForm ��ü�� ActionListener�� �����ϰ� �־� this�� ActionListener�� �ȴ�
		btnJoin.addActionListener(this);
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent we){
				int result = JOptionPane.showConfirmDialog(
					LoginForm.this,
					"exit?",
					"question",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE
				);
				if(result == JOptionPane.YES_OPTION){
					System.exit(0);
				}
			}
		});
	}
	private void showFrame(){
		setTitle("Login");
		pack();
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		Object src = e.getSource();
		if(src == btnLogin){
			//btnLoin Ŭ���� tfId�� ����ִ��� pfPw�� ����ִ��� Ȯ���ϰ�
			//�Ѵ� �ԷµǾ� ������ ID��ġ���� pw��ġ���θ� Ȯ��
			//��Ȳ�� ���� Message�� ���â�� ����ְ� �ش� TextComponent ��Ŀ��
			//ID,PW�� ��ġ�ϸ�  tfId,pfPw ������ �����ϰ� ��� LoginForm��  ������ �ʰ� �����
			//InformationForm ��ü ����
			JTextComponent input = null;
			String msg = "welcome!!";
			User user = null;
			if(LoginUtils.isEmpty(tfId)){
				msg = "input your ID";
				input = tfId;
			}else{
				if(LoginUtils.isEmpty(pfPw)){
					msg = "input your password";
					input = pfPw;
				}else{
					String uid =tfId.getText();
					String upw = pfPw.getText();
					user = findUser(uid);
					if(user == null){
						msg ="check your ID";
						input = tfId;
					}else{
						if(!upw.equals(user.getUpw())){
							msg = "check your password";
							input = pfPw;
						}
					}
				}
			}
			JOptionPane.showMessageDialog(
				this,
				msg,
				"Information",
				JOptionPane.INFORMATION_MESSAGE
			);
			if(input != null){
				input.requestFocus();
			}else{
				clear();
				//setVisible(false);
				new InformationForm(this,user);
			}
		}else{
			//btnJoin Ŭ����
			//tfId,pfPw ������ ����
			//�ش� LoginForm��  ������ �ʰ� �����
			//JoinForm ��ü ����
			clear();
			//setVisible(false);
			new JoinForm(this);
		}
	}
	private void clear(){//tfId,pfPw ������ ����
		tfId.setText("");
		pfPw.setText("");
	}
	
	public User findUser(String userId){
		//Id�� ������ Vector<User> list���� ���ϴ� User�� ã�´�
		int idx = list.indexOf(new User(userId));
		if(idx>=0){
			return list.get(idx);
		}else{
			return null;
		}
	}
	public void addUser(User user){
		//Vector<User> list�� user�߰�
		list.add(user);
	}
	public void removerUser(User user){
		//Vector<User> list�� user����
		list.remove(user);
	}
	public static void main(String[] args){
		new LoginForm();
	}
}
