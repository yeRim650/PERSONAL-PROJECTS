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
	//JFrame를 상속받고 ActionListener를 구현한다.
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
		//생성된 LoginForm 객체가 ActionListener를 구현하고 있어 this가 ActionListener가 된다
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
			//btnLoin 클릭시 tfId가 비어있는지 pfPw가 비어있는지 확인하고
			//둘다 입력되어 있으면 ID일치여부 pw일치여부를 확인
			//상황에 따라 Message를 모달창에 띄어주고 해당 TextComponent 포커스
			//ID,PW가 일치하면  tfId,pfPw 내용을 삭제하고 헤당 LoginForm을  보이지 않게 만들고
			//InformationForm 객체 생성
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
			//btnJoin 클릭시
			//tfId,pfPw 내용을 삭제
			//해당 LoginForm을  보이지 않게 만들고
			//JoinForm 객체 생성
			clear();
			//setVisible(false);
			new JoinForm(this);
		}
	}
	private void clear(){//tfId,pfPw 내용을 삭제
		tfId.setText("");
		pfPw.setText("");
	}
	
	public User findUser(String userId){
		//Id를 가지고 Vector<User> list에서 원하는 User를 찾는다
		int idx = list.indexOf(new User(userId));
		if(idx>=0){
			return list.get(idx);
		}else{
			return null;
		}
	}
	public void addUser(User user){
		//Vector<User> list에 user추가
		list.add(user);
	}
	public void removerUser(User user){
		//Vector<User> list에 user삭제
		list.remove(user);
	}
	public static void main(String[] args){
		new LoginForm();
	}
}
