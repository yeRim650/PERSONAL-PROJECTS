package kr.ac.MovieReview;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;

public class Edit extends JDialog {
	public static final int MOVIENAME = 0;
	public static final int DIRECTOR = 1;
	public static final int GENRE = 2;
	public static final int VIEWDATE = 3;
	private JLabel[] lbl;
	private String[] names = { "영화제목", "감독", "장르", "영화감상일" };

	private JTextField[] inputs;
	private JButton btnDate;
	
	private JLabel lblImg1;
	
	private JTextArea taMovieReview;
	
	private JLabel lblTitle;
	private JButton btnSave;
	private JButton btnClose;
	private JComboBox cBox;
	private MyList owner;
	private int modifyIdx;
	
	private boolean like = false;
	
	public Edit(MyList owner) {
		super(owner, "리뷰 등록", true);
		this.owner = owner;
		init();
		setDisplay();
		addListeners();
		showFrame();
	}
	public Edit(MyList owner,MovieReview m, int idx) {
		super(owner, "수정 및 확인", true);
		this.owner = owner;
		modifyIdx=idx;
		init();
		setMovieReview(m);
		setDisplay();
		addListeners();
		showFrame();
	}
	private void init() {
		lbl = new JLabel[names.length];
		for (int i = 0; i < names.length; i++) {
			lbl[i] = new JLabel(names[i]);
		}
		lblImg1 = new JLabel(new ImageIcon("empty_heart-icon.png"),JLabel.CENTER);
		inputs = new JTextField[names.length];
		for(int i =0; i <inputs.length; i++){
			inputs[i] = new JTextField(19);
		}
		inputs[VIEWDATE] = new JTextField(15);
		Date d = new Date();//수정
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일");//수정
		inputs[VIEWDATE].setText(sdf.format(d));//수정
		inputs[VIEWDATE].setEditable(false);//수정
		taMovieReview = new JTextArea(7, 20);
		taMovieReview.setLineWrap(true);
		btnDate = new JButton("달력");//수정
		
		btnSave = new JButton("등록");
		btnClose = new JButton("닫기");
		
		cBox = new JComboBox<String>();
		cBox.setPrototypeDisplayValue("XXXXXXXXXXXXXXX");
		//cBox.addItem(rating);
		for (int i = 1; i < 6; i++) {
			cBox.addItem(MyList.AMOUNT[i]);
		}
	}
	
	private void setDisplay() {
		JPanel pnlLeft = new JPanel(new GridLayout(0, 1));
		for (int i = 0; i < names.length; i++) {
			pnlLeft.add(lbl[i]);
		}
		JPanel pnltfMovieName = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnltfMovieName.add(inputs[MOVIENAME]);
		pnltfMovieName.add(lblImg1);

		JPanel pnltfDirector = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnltfDirector.add(inputs[DIRECTOR]);


		JPanel pnltfGenre = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnltfGenre.add(inputs[GENRE]);

		JPanel pnlReadStartCal = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlReadStartCal.add(inputs[VIEWDATE]);
		pnlReadStartCal.add(btnDate);

		JPanel pnlRight = new JPanel(new GridLayout(0, 1));
		pnlRight.add(pnltfMovieName);
		pnlRight.add(pnltfDirector);
		pnlRight.add(pnltfGenre);
		pnlRight.add(pnlReadStartCal);
		
		JPanel pnlNorth = new JPanel(new BorderLayout());
		pnlNorth.add(pnlLeft, BorderLayout.WEST);
		pnlNorth.add(pnlRight, BorderLayout.CENTER);

		JPanel pnlCenter = new JPanel(new BorderLayout());
		
		JScrollPane scroll = new JScrollPane(taMovieReview);
		
		TitledBorder tBorder = new TitledBorder("리뷰 등록");
		tBorder.setTitlePosition(TitledBorder.ABOVE_TOP);
		scroll.setBorder(tBorder);

		
		
		pnlCenter.add(scroll, BorderLayout.CENTER);
		JPanel pnl = new JPanel();
		pnl.add(cBox);
		pnlCenter.add(pnl, BorderLayout.SOUTH);



		//JPanel pnlCBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
		//pnlCBox.add(cBox);

		JPanel pnlSouth = new JPanel();
		
		pnlSouth.add(btnSave);
		pnlSouth.add(btnClose);

		JPanel pnlMain = new JPanel(new BorderLayout());
		pnlMain.add(pnlNorth, BorderLayout.NORTH);
		pnlMain.add(pnlCenter, BorderLayout.CENTER);
		pnlMain.add(pnlSouth, BorderLayout.SOUTH);
		

		
		add(pnlMain, BorderLayout.CENTER);
		pnlMain.setBorder(new EmptyBorder(10, 30, 10, 30));
	}
	
	private void addListeners() {
		lblImg1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(like != true){
					lblImg1.setIcon(new ImageIcon("heart-icon.png"));
					like = true;
				}else{
					lblImg1.setIcon(new ImageIcon("empty_heart-icon.png"));
					like = false;
				}
			}
		});
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean flag = true;
				String msg = "등록되었습니다";
				//입력 확인
				for(int idx=0; flag && idx<inputs.length; idx++){
					if(MyReviewUtils.isEmpty(inputs[idx])){
						flag = false;
						msg = names[idx] + "입력해주세요.";
						inputs[idx].requestFocus();
					}
				}
				if(flag){
					 MovieReview movieReview = new  MovieReview(
						inputs[MOVIENAME].getText(),
						inputs[DIRECTOR].getText(),
						inputs[GENRE].getText(),
						inputs[VIEWDATE].getText(),
						taMovieReview.getText(),
						cBox.getSelectedItem().toString(),
						like
					);
					if(getTitle().equals( "리뷰 등록")){
						int idx = inputs[VIEWDATE].getText().indexOf("년");
						String year = inputs[VIEWDATE].getText().substring(0, idx);
						owner.addMovieReview(movieReview,year);
					}else{
						owner.modify(modifyIdx,movieReview);
						msg = "수정되었습니다";
					}
					
					dispose();
					
				}
				JOptionPane.showMessageDialog(
					Edit.this,
					msg,
					"Information",
					JOptionPane.INFORMATION_MESSAGE
				);
			}
		});
		//~
		btnDate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnDate) {
					new MyCalendar(Edit.this);
				}
			}
		});
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent we) {
				dispose();
			}
		});
	}
	private void setMovieReview(MovieReview m){
		inputs[MOVIENAME].setText(m.getMovieTitle());
		inputs[DIRECTOR].setText(m.getDirector());
		inputs[GENRE].setText(m.getGenre());
		inputs[VIEWDATE].setText(m.getDate());
		taMovieReview.setText(m.getReview());
		cBox.setSelectedItem(m.getAmount());
		like = m.isLike();
		if(like){
			lblImg1 = new JLabel(new ImageIcon("heart-icon.png"),JLabel.CENTER);
		}
		btnSave.setText("수정");
	}
	private void showFrame() {
		pack();
		setResizable(false);
		setLocationRelativeTo(owner);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}
	public void setViewDate(String text){
		inputs[VIEWDATE].setText(text);
	}
}
