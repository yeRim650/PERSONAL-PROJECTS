package kr.ac.MovieReview;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

public class LoginForm extends JFrame implements ActionListener {

	private JPasswordField pfPw;	
	private JButton btnLogin;
	private JLabel lblImage;
	private JMenuBar mBar;
	private JMenu mEdit;
	private JMenuItem miChangePw;
	
	private boolean change = false;
	
	private File userPw;
	private String pw = null;
	private JButton btnJoin;
	public LoginForm() {
			init();
			setDisplay();
			addListener();
			showFrame();
			loadData();
			FileCheck2();
			if(!userPw.exists()) {
				JOptionPane.showMessageDialog(this, "비밀번호를 설정해주세요.");
			}
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw){
		this.pw = pw;
		change = true;
	}
	private void init() {
		mBar = new JMenuBar();
		mEdit = new JMenu("설정");
		miChangePw = new JMenuItem("패스워드 변경");
		mBar.add(mEdit);
		
		lblImage = new JLabel();
		
		ImageIcon icon = new ImageIcon("logo.png");
		Image img = icon.getImage();
		setIconImage(img);

		pfPw = (JPasswordField)MyReviewUtils.getTextComponent(MyReviewUtils.PASSWORD);///
		
		btnLogin = MyReviewUtils.getButton("Login");
		btnJoin = MyReviewUtils.getButton("비번설정");///
		
		userPw = new File("userPW.dat");///
	}
	
	private void setDisplay() {
		setJMenuBar(mBar);
		
		JPanel pnlText = new JPanel(new GridLayout(0, 1));
		JPanel pnlInput = new JPanel(new GridLayout(0,1));
		
		pnlText.add(MyReviewUtils.getLabel("PASSWORD"));
		
		lblImage.setIcon(new ImageIcon("image.png"));
		
	
		JPanel pnlPw = new JPanel(new FlowLayout(FlowLayout.LEFT));//수정
		pnlPw.add(pfPw);
		pnlInput.add(pnlPw);
		
		JPanel pnlNorth = new JPanel();
		pnlNorth.add(lblImage);
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.add(btnLogin);
		pnlSouth.add(btnJoin);
		
		JPanel pnlMain = new JPanel(new BorderLayout());
		pnlMain.add(pnlNorth, BorderLayout.NORTH);
		pnlMain.add(pnlText, BorderLayout.WEST);
		pnlMain.add(pnlInput, BorderLayout.CENTER);
		pnlMain.add(pnlSouth, BorderLayout.SOUTH);
		
		pnlMain.setBorder(new EmptyBorder(5, 10, 5, 10));
		
		add(pnlMain, BorderLayout.CENTER);
	}
	public void loadData() {
		FileInputStream fis = null;
		DataInputStream dis = null;
		if(userPw.exists()) {
			try {
				fis = new FileInputStream(userPw);
				dis = new DataInputStream(fis);
	
				pw = dis.readUTF();
			}catch(EOFException e){
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				MyReviewUtils.closeAll(dis, fis);
			}
		} 
	}
	public void saveData() {
		FileOutputStream fos = null;
		DataOutputStream dos = null;
		try {
			fos = new FileOutputStream(userPw);
			dos = new DataOutputStream(fos);
			dos.writeUTF(pw);
			dos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			MyReviewUtils.closeAll(dos, fos);
		}
	}
	public void FileCheck2() {
		if(pw!=null) { 
			btnLogin.setVisible(true);
			btnJoin.setVisible(false);
			pfPw.setEnabled(true);
			setJMenuBar(mBar);
			miChangePw.setEnabled(true);
			mEdit.add(miChangePw);
		}else{
			btnLogin.setVisible(false);	
			pfPw.setEnabled(false);
			miChangePw.setEnabled(false);
		}
		mEdit.add(miChangePw);
	}
	private void addListener() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				int result = JOptionPane.showConfirmDialog(
						LoginForm.this, "종료하시겠습니까?", "확인",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if(result == JOptionPane.YES_OPTION) {
					if(pw!=null&&change){
						saveData();
					}
					System.exit(0);
				}
			}
		});
		
		///
		btnJoin.addActionListener(this);
		btnLogin.addActionListener(this);
			
		pfPw.addActionListener(this);
		mEdit.addActionListener(this);
		miChangePw.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src == btnLogin || src == pfPw) {
			if (pw.equals(pfPw.getText())) {
				new MyList();
				saveData();
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "비밀번호가 맞지 않습니다.");
			}
		} else if (src == btnJoin) {
			new PwSetting(this);
			FileCheck2();
		} else {
			new ChangePw(this);
		}
	}
	
	
	private void showFrame() {
		setTitle("My Review");
		pack();
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	public static void main(String[] args) {
		new LoginForm();
	}

}