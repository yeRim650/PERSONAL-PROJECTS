package kr.ac.Login;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;

public class JoinForm extends JDialog implements ActionListener{
	public static final int ID = 0;
	public static final int PW = 1;
	public static final int RE = 2;
	public static final int NAME = 3;
	public static final int NICK = 4;
	public String[] names = {"ID", "Password", " Retry", "Name", "NickName"};
	
	private JButton btnJoin;
	private JButton btnCancel;
	private JTextComponent[] inputs;
	private JRadioButton rbtnMale;
	private JRadioButton rbtnFemale;
	private LoginForm owner;
	
	public JoinForm(LoginForm owner){
		super(owner, "Join", true);
		//LoginForm가 owner 객체
		//타이틀 "Join"
		//모달창 속성 true(모달창이 띄어있으면 LoginForm 이용 못함)
		this.owner = owner;
		init();
		setDisplay();
		addListener();
		showDlg();
	}
	
	private void init(){
		btnJoin = LoginUtils.getButton("Join");
		btnCancel = LoginUtils.getButton("Cancel");
		inputs = new JTextComponent[]{
			LoginUtils.getTextComponent(LoginUtils.TEXT),
			LoginUtils.getTextComponent(LoginUtils.PASSWORD),
			LoginUtils.getTextComponent(LoginUtils.PASSWORD),
			LoginUtils.getTextComponent(LoginUtils.TEXT),
			LoginUtils.getTextComponent(LoginUtils.TEXT)
		};
		ButtonGroup group = new ButtonGroup();
		rbtnMale = new JRadioButton("Male", true);
		rbtnFemale = new JRadioButton("Female");
		group.add(rbtnMale);
		group.add(rbtnFemale);
	}
	private void setDisplay(){
		JPanel pnlMain = new JPanel(new BorderLayout());
		
		JPanel pnlNorth = new JPanel(new GridLayout(0,1));
		for(int i=0; i<inputs.length; i++){
			JPanel pnl = new JPanel();
			pnl.add(LoginUtils.getLabel(names[i]));
			pnl.add(inputs[i]);
			pnlNorth.add(pnl);
		}
		JPanel pnlGender = new JPanel();
		pnlGender.add(rbtnMale);
		pnlGender.add(rbtnFemale);
		pnlGender.setBorder(new TitledBorder("Gender"));
		
		JPanel pnlBtns = new JPanel();
		pnlBtns.add(btnJoin);
		pnlBtns.add(btnCancel);
		
		pnlMain.add(pnlNorth, BorderLayout.NORTH);
		pnlMain.add(pnlGender, BorderLayout.CENTER);
		pnlMain.add(pnlBtns, BorderLayout.SOUTH);
		
		TitledBorder tBorder = new TitledBorder(
			new EmptyBorder(5,10,5,10),
			"- Input your information"
		);
		tBorder.setTitleFont(new Font(Font.DIALOG, Font.BOLD, 13));
		pnlMain.setBorder(tBorder);
		
		add(pnlMain, BorderLayout.CENTER);
	}
	private void addListener(){
		btnJoin.addActionListener(this);
		//생성된 LoginForm 객체가 ActionListener를 구현하고 있어 this가 ActionListener가 된다
		btnCancel.addActionListener(this);
		addWindowListener(new WindowAdapter(){
			//창을 종료할때 closeDlg 메서드효출
			@Override
			public void windowClosing(WindowEvent we){
				closeDlg();
			}
		});
	}
	private void showDlg(){
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}
	private void closeDlg(){
		//LoginForm을 볼수 있게 만들고 해당창을 종료한다.
		owner.setVisible(true);	
		dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent ae){
		// btnJoin 클릭시 입력 확인, 아이디 체크, 비번 체크하고 상황에 따른 showMessageDialog 파라미터 변경
		// 조건을 ture로 모두 통과하면 새로운 User가 생성하여 owner의 addUser를 호출하여
		// Vector<User> list에 user를 추가하고 closeDlg 호출
		// btnCancel 클릭시 closeDlg호출
		Object s = ae.getSource();
		if(s== btnJoin){
			boolean flag = true;
			String msg = "Join OK";
			//입력 확인
			for(int idx=0; flag && idx<inputs.length; idx++){
				if(LoginUtils.isEmpty(inputs[idx])){
					flag = false;
					msg = "missing input : " + names[idx];
					inputs[idx].requestFocus();
				}
			}
			//아이디 체크
			if(flag){
				User user = owner.findUser(inputs[ID].getText());
				if(user != null){
					flag = false;
					msg = "invalid ID : already existed";
					inputs[ID].requestFocus();
				}
			}
			//비번 체크
			if(flag){
				String pw1 = inputs[PW].getText();
				String pw2 = inputs[RE].getText();
				if(!pw1.equals(pw2)){
					flag = false;
					msg = " check your password";
					inputs[PW].requestFocus();
				}
			}
			if(flag){
				User user = new User(
					inputs[ID].getText(),
					inputs[PW].getText(),
					inputs[NAME].getText(),
					inputs[NICK].getText(),
					rbtnMale.isSelected() ? "mals" : "female"
				);
				owner.addUser(user);
				closeDlg();
				
			}
			JOptionPane.showMessageDialog(
				this,
				msg,
				"Information",
				JOptionPane.INFORMATION_MESSAGE
			);
		}else{
			closeDlg();
		}
	}
}