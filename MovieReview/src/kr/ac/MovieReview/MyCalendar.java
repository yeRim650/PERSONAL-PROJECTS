package kr.ac.MovieReview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

class MyCalendar extends JDialog implements ActionListener {
	
	private Container container = getContentPane();
	private String[] yoil = {"일", "월", "화", "수", "목", "금", "토"};	
	private CalendarPrint cp = new CalendarPrint();
	private JPanel pnlNorth;
	private JPanel pnlCenter;
	private JToggleButton[] btns;
	private JButton btnBefore;
	private JButton btnAfter;
	private JLabel lblWhen;
	private JButton btnConfirm;
	protected Object selectedBtn;
	public AbstractButton tfViewDate;
	
	private Edit owner;
	
	private ButtonGroup buttonGroup = new ButtonGroup();
	private String selectBtn = "";
	
	public MyCalendar(Edit owner) {
		super(owner, "달력", true);
		this.owner = owner;
		init();
		setDisplay();
		addListeners();
		showFrame();		
	}
	
	private void init() {
		btns = new JToggleButton[49];
		pnlNorth = new JPanel();
		pnlCenter = new JPanel();
		btnBefore = new JButton("◀");
		btnAfter = new JButton("▶");
		btnConfirm = new JButton("ok");
		lblWhen = new JLabel(" 00년 0월 ");
	}
	
	private void setDisplay() {
		 container.setLayout(new BorderLayout());
		 container.add("North", pnlNorth);
		 container.add("Center", pnlCenter);
		 JPanel pnlNorth = new JPanel(new FlowLayout());
		 JPanel pnlCenter = new JPanel(new GridLayout(7, 7, 5, 5));
		 JPanel pnlSouth = new JPanel();
		 
		 pnlNorth.add(btnBefore);
		 pnlNorth.add(lblWhen);
		 pnlNorth.add(btnAfter);
		 
		 pnlSouth.add(btnConfirm);
		 btnConfirm.setBackground(Color.white);
		 
		 add(pnlNorth, BorderLayout.NORTH);
		 add(pnlCenter, BorderLayout.CENTER);
		 add(pnlSouth, BorderLayout.SOUTH);
		 
		 lblWhen.setText(cp.getCalText());
		 for(int i = 0; i < btns.length; i++) {
			 
			 btns[i] = new JToggleButton();
			 buttonGroup.add(btns[i]);
	
			 pnlCenter.add(btns[i]);
			 
			 btns[i].setFont(new Font(Font.DIALOG, Font.BOLD, 10));
			 btns[i].setBackground(Color.white);
			 
			 if(i < 7) {
				 btns[i].setText(yoil[i]);
				 btns[i].setEnabled(false);			
			 }	 
			 
			 if(i%7 == 0) {
				 btns[i].setForeground(Color.RED);
			 }
			 if(i%7 == 6) {
				 btns[i].setForeground(Color.BLUE);
			 }
		 }
		 cp.setButtons(btns);
		 cp.calSet();
	}
	class DateActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			tfViewDate.setText(tfViewDate.getText() + e.getActionCommand());
		}
	}

	private void addListeners() {	
		
		btnAfter.addActionListener(this);
		btnBefore.addActionListener(this);
		
		btnConfirm.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy년M월d일");
				Date other = null;
				Date d = new Date();
				try {
					other = sdf.parse(lblWhen.getText()+selectBtn+"일");
				} catch (ParseException e1) {}
				sdf = new SimpleDateFormat("yyyy년MM월dd일");
				try{
					if(d.compareTo(other)>=0){
						owner.setViewDate(sdf.format(other));
						dispose();
					}else{
						JOptionPane.showMessageDialog(
								MyCalendar.this,
								"미래는 선택하실 수 없습니다",
								"Information",
								JOptionPane.INFORMATION_MESSAGE
						);
					}
				}catch(NullPointerException ne){
					JOptionPane.showMessageDialog(
							MyCalendar.this,
							"날짜를 선택하지 않으셨습니다",
							"Information",
							JOptionPane.INFORMATION_MESSAGE
					);
				}
			}
		});
		ActionListener aListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JToggleButton btn = (JToggleButton) e.getSource();
				selectBtn = btn.getText();
			}
		};
		for(int i =0; i<btns.length;i++){
			btns[i].addActionListener(aListener);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int gap = 0;
		if(e.getSource() == btnAfter) {				
			gap = 1;
		} else if(e.getSource() == btnBefore ) {		
			gap = -1;
		}
		cp.init(gap);
		lblWhen.setText(cp.getCalText());			
	}
	

	private void showFrame() {
		setSize(370, 300);
		setResizable(false);
		setLocationRelativeTo(owner);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);	
	}
}