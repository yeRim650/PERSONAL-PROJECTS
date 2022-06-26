package kr.ac.MovieReview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class MyList extends JFrame implements Comparator<MovieReview>{
	public static final String[] AMOUNT = {
			"ALL","★★★★★", "★★★★☆", "★★★☆☆", "★★☆☆☆", "★☆☆☆☆"
	};
	public Vector<String> years  = new Vector<String>();;
	private JButton btnRecent;
	private JButton btnScore;
	private JLabel lblHowMany;
	private JComboBox<String> cbScore;
	private JComboBox<String> cbYear;
	private JButton btnFavorite;
	private JButton btnsearch;
	private JButton btnDelete;
	private JButton btnWrite;
	private JTextField tfInput;
	private JButton btnMyStyle;
	
	private JPopupMenu pMenu;
	private JMenuItem miCorrection;
	
	private JLabel lblSearch;
	
	private HashMap<String, DefaultListModel<MovieReview>> modelYear  = new HashMap<String, DefaultListModel<MovieReview>>();
	private JList<MovieReview> list;
	private DefaultListModel<MovieReview> modelAll;
	private DefaultListModel<MovieReview> model;
	private Vector<MovieReview> favorites;
	
	private String year;
	
	public static final int ORDER_BY_DATE_ASC = 1;
	public static final int ORDER_BY_DATE_DESC = 2;
	public static final int ORDER_BY_AMOUNT_ASC = 3;
	public static final int ORDER_BY_AMOUNT_DESC = 4;
	
	private int mode;
	
	private boolean change = false;
	
	public MyList() {
		loadReview();
		init();
		setDisplay();
		addListener();
		showFrame();
	}
	private void init() {
		model = new DefaultListModel<MovieReview>();
		list = new JList<MovieReview>(modelAll);
		list.setPrototypeCellValue(new MovieReview("movieTitle","genre","director","review","date","amount",true));//수정
		list.setVisibleRowCount(10);
		JScrollPane scroll = new JScrollPane(list);
		
		pMenu = new JPopupMenu();
		miCorrection = new JMenuItem("수정 및 확인");
		pMenu.add(miCorrection);
	
		btnRecent = new JButton("시청날짜순");
		btnScore = new JButton("평점순");
		btnFavorite = new JButton("즐겨찾기");
		btnsearch = new JButton("영화 제목 검색");
		btnsearch.setPreferredSize(new Dimension(120,25));
		
		btnDelete = new JButton("삭제");
		btnWrite = new JButton("작성");
		lblHowMany = new JLabel("총 "+modelAll.size()+" 건의 리뷰가 있습니다.");
		lblHowMany.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
		tfInput = new JTextField(12);
		cbScore = new JComboBox<String>(AMOUNT);
		cbYear = new JComboBox<String>(years);
		cbYear.setSelectedItem(years.get(years.size()-1));
		
		btnMyStyle = new JButton("취향분석");
		
		
		list.setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION
		);
		
		findFavorites();

	}
	private void setDisplay() {
		JPanel pnlNorth = setPnlNorth(new JPanel(new BorderLayout()));
		JPanel pnlCenter = new JPanel(new BorderLayout());
		JPanel pnlSouth = setPnlSouth(new JPanel(new BorderLayout()));
		
		JLabel lblMovieTitle = getLalHeader("영화 제목",new Dimension(203, 30));
		JLabel lblMovieGenre = getLalHeader("장르",new Dimension(50, 30));
		JLabel lblDirector = getLalHeader("영화 감독", new Dimension(100, 30));
		JLabel lblReview = getLalHeader("리뷰내용",new Dimension(200, 30));
		JLabel lblReviewDate = getLalHeader("시청 날짜",new Dimension(100, 30));
		JLabel lblAmunt = getLalHeader("평점",new Dimension(100, 30));
		JPanel pnlInfo = new JPanel();
		pnlInfo.add(lblMovieTitle);
		pnlInfo.add(lblMovieGenre);
		pnlInfo.add(lblDirector);
		pnlInfo.add(lblReview);
		pnlInfo.add(lblReviewDate);
		pnlInfo.add(lblAmunt);
		
		pnlCenter.add(pnlInfo,BorderLayout.NORTH);
		pnlCenter.add(new JScrollPane(list),BorderLayout.CENTER);
		
		
		JPanel pnlMain = new JPanel(new BorderLayout());//수정
		pnlMain.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
		pnlMain.add(pnlNorth, BorderLayout.NORTH);
		pnlMain.add(pnlCenter, BorderLayout.CENTER);
		pnlMain.add(pnlSouth, BorderLayout.SOUTH);
		
		add(pnlMain);
	}
	// North 패널 
	private JPanel setPnlNorth(JPanel pnlNorth) {
		JPanel pnlInNorth = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlInNorth.add(cbScore);
		pnlInNorth.add(cbYear);
			
		JPanel pnlInNorth2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pnlInNorth2.add(lblHowMany);
		
		JPanel pnlInNorth3 = new JPanel(new BorderLayout());
		
		JPanel pnlInNorth3Left = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel pnlInNorth3Right = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel lbl = new JLabel("전체 리스트",JLabel.LEFT);
		lbl.setPreferredSize(new Dimension(510, 25));//수정
		
		pnlInNorth3.add(pnlInNorth3Left, BorderLayout.CENTER);
		pnlInNorth3.add(pnlInNorth3Right, BorderLayout.EAST);
		
		pnlInNorth3Left.add(lbl);
		pnlInNorth3Right.add(btnRecent);
		pnlInNorth3Right.add(btnScore);
		
	
		pnlNorth.add(pnlInNorth, BorderLayout.WEST);
		pnlNorth.add(pnlInNorth2, BorderLayout.CENTER);
		pnlNorth.add(pnlInNorth3, BorderLayout.SOUTH);
		
		return pnlNorth;
	}
// South 패널 
	private JPanel setPnlSouth(JPanel pnlSouth) {
		JPanel pnlInSouth = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlInSouth.add(btnFavorite);
		pnlInSouth.add(btnMyStyle);
		
		JPanel pnlInSouth3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pnlInSouth3.add(btnDelete);
		pnlInSouth3.add(btnWrite);
		
		JPanel pnlInSouth2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlInSouth2.setBorder(new EmptyBorder(new Insets(0, 100, 0, 0)));
		pnlInSouth2.add(tfInput);
		pnlInSouth2.add(btnsearch);
		lblSearch = new JLabel("");
		lblSearch.setForeground(Color.GRAY);
		pnlInSouth2.add(lblSearch);
				
		pnlSouth.add(pnlInSouth, BorderLayout.WEST);
		pnlSouth.add(pnlInSouth2, BorderLayout.CENTER);
		pnlSouth.add(pnlInSouth3, BorderLayout.EAST);

		return pnlSouth;			
	}
	
	private void addListener() {
		btnWrite.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Edit(MyList.this);
				
			}
		});
		
		list.setCellRenderer(new DefaultListCellRenderer(){
			@Override
			public Component getListCellRendererComponent(
				JList list,
				Object value,
				int idx,
				boolean isSelected,
				boolean cellHasFocus
			){
				
				MovieReview r = (MovieReview)value;
				JLabel lblMovieTitle = getLalInfo(r.getMovieTitle(),new Dimension(200, 20));
				JLabel lblMovieGenre = getLalInfo(r.getGenre(),new Dimension(50, 20));
				JLabel lblDirector = getLalInfo(r.getDirector(),new Dimension(100, 20));
				String review = r.getReview();
				int i = r.getReview().indexOf("\n");
				if(i >= 0){
					review = review.replaceAll("\n", " ");
				}
				JLabel lblReview = getLalInfo(review,new Dimension(200, 20));
				JLabel lblReviewDate = getLalInfo(r.getDate(),new Dimension(100, 20));
				JLabel lblAmunt = getLalInfo(r.getAmount(),new Dimension(80, 20));
				JPanel pnlInfo = new JPanel();

				pnlInfo.add(lblMovieTitle);
				pnlInfo.add(lblMovieGenre);
				pnlInfo.add(lblDirector);
				pnlInfo.add(lblReview);
				pnlInfo.add(lblReviewDate);
				pnlInfo.add(lblAmunt);
				
				if(isSelected){
					pnlInfo.setBackground(new Color(0xB2CCFF));
				}else{
					pnlInfo.setBackground(Color.WHITE);
				}
				
				return pnlInfo;
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeReview();
				change = true;
			}
		});
		
		btnScore.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(mode != ORDER_BY_AMOUNT_DESC){
					sortReview(ORDER_BY_AMOUNT_DESC);
				}else{
					sortReview(ORDER_BY_AMOUNT_ASC);
				}
			}
		});
		btnRecent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(mode != ORDER_BY_DATE_ASC){
					sortReview(ORDER_BY_DATE_ASC);
				}else{
					sortReview(ORDER_BY_DATE_DESC);
				}
			}
		});
		cbScore.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					filterScore();
				}
			}
		});
		cbYear.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e){
				if(e.getStateChange() == ItemEvent.SELECTED){
					String text = cbYear.getSelectedItem().toString();
					if(!modelYear.containsKey(text)){
						loadReviewYear(cbYear.getSelectedIndex());
					}else{
						modelAll = modelYear.get(text);
					}
					System.out.println(year);
					year = text;
					totalReview(modelAll.size());
					cbScore.setSelectedIndex(0);
					list.setModel(modelAll);
				}
			}
		});
		btnsearch.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				if(MyReviewUtils.isEmpty(tfInput)){
					filterScore();
					lblSearch.setText("");
					tfInput.setText("");
				}else{
					searchReview(tfInput.getText());
					if(model.size() == 0){
						JOptionPane.showMessageDialog(
								MyList.this,
								"검색결과 없습니다",
								"Information",
								JOptionPane.INFORMATION_MESSAGE
								);
					}else{
						lblSearch.setText("검색취소");
					}
				}
			}
		});
		lblSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(lblSearch.getText().equals("검색취소")){
					lblSearch.setText("");
					filterScore();
					tfInput.setText("");
				}
			}
		});
		btnMyStyle.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				new MyStyle(MyList.this);
			}
		});
		btnFavorite.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				new FavoritesList(MyList.this);
				
			}
		});
		list.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent me){
				showPopup(me);
			}
			@Override
			public void mouseReleased(MouseEvent me){
				showPopup(me);
			}
			private void showPopup(MouseEvent me){
				if(me.isPopupTrigger()){
					int x = me.getX();
					int y = me.getY();
					int idx = list.locationToIndex(
						new Point(x, y)	
					);
					if(idx >= 0 && list.getSelectedIndex() >=0){
						pMenu.show(list, x,y);
					}
				}
			}
		});
		miCorrection.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int idx = list.getSelectedIndex();
				if(!isAll()||lblSearch.getText().equals("검색취소")){
					idx = modelAll.indexOf(model.getElementAt(idx));
				}
				new Edit(MyList.this,modelAll.getElementAt(idx),idx);
			}
		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				if(change){
					int result = JOptionPane.showConfirmDialog(
							MyList.this, "변경사항이 있습니다 저장 후 종료하시겠습니까?", "확인",
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE);
					if(result == JOptionPane.YES_OPTION) {
						boolean flag = saveReview();
						if(flag){
							System.exit(0);
						}
					}else if(result == JOptionPane.NO_OPTION){
						System.exit(0);
					}
				}else{
					int result = JOptionPane.showConfirmDialog(
							MyList.this, "종료하시겠습니까?", "확인",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);
					if(result == JOptionPane.YES_OPTION) {
						System.exit(0);
					}
				}
			}
		});
		
	}
	private void showFrame() {
		setSize(820,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	private void sortReview(int mode){
		Object[] temp = null;
		temp = modelAll.toArray();
		MovieReview[] arr =  new MovieReview[temp.length];
		for(int i=0; i<temp.length; i++){
			arr[i] = (MovieReview)temp[i];
		}
		this.mode = mode;
		Arrays.sort(arr, this);
		modelAll.clear();
		for(MovieReview a : arr){
			modelAll.add(0,a);
		}
		filterScore();
	}
	private void removeReview(){
		int idx = list.getSelectedIndex();
		if(!isAll()||lblSearch.getText().equals("검색취소")){
			modelYear.get(year).removeElement(model.getElementAt(idx));
			modelAll.removeElement(model.getElementAt(idx));
			totalReview(model.size());
		}else{
			modelYear.get(year).remove(idx);
			System.out.println(modelAll.size());
			totalReview(modelAll.size());
		}
	}
	private void filterScore(){
		String text = cbScore.getSelectedItem().toString();
		if(isAll()){
			list.setModel(modelAll);
			totalReview(modelAll.size());
		}else{
			model.clear();
			for(int i = 0; i < modelAll.size(); i++){
				if(modelAll.getElementAt(i).getAmount().equals(text)){
					model.addElement(modelAll.getElementAt(i));
				}
			}
			list.setModel(model);
			totalReview(model.size());
		}
		if(lblSearch.getText().equals("검색취소")){
			searchReview(tfInput.getText());
		}
	}
	private void searchReview(String text){
		if(isAll()){
			model.clear();
			for(int i = 0; i < modelAll.size(); i++){
				if(modelAll.getElementAt(i).getMovieTitle().contains(text)){
					model.addElement(modelAll.getElementAt(i));
				}
			}
			list.setModel(model);
		}else{
			for(int i = model.size()-1; i >= 0; i--){
				if(!model.getElementAt(i).getMovieTitle().contains(text)){
					model.remove(i);
				}
			}
		}
		totalReview(model.size());
	}
	private boolean saveReview(){
		FileWriter fw = null;
		PrintWriter pw = null;	
		try{
			fw = new FileWriter("year.txt");
			pw = new PrintWriter(fw);
			for(int i = years.size()-1;i>=0;i--){
				if(modelYear.containsKey(years.get(i))){
					if(modelYear.get(years.get(i)).size()==0){
						years.remove(years.get(i));
					}
				}
			}
			System.out.println(modelYear.get("2022").size());
			System.out.println(years);
			if(!(years.size()==0)){
				String[] arrYear = new String[years.size()];
				for(int i=0;i<arrYear.length;i++){
					arrYear[i] = years.get(i);
				}
				Arrays.sort(arrYear);
				for(String year : arrYear){
					pw.println(year);
				}
				pw.flush();
			}
		}catch(IOException e){
			JOptionPane.showMessageDialog(
				this,
				"정보 저장 중 문제가 발생했습니다. 나중에 다시 시도하세요.",
				"알림",
				JOptionPane.WARNING_MESSAGE
			);
			return false;
		}finally{
			MyReviewUtils.closeAll(pw, fw);
		}
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			for(String year : years){
				if(modelYear.containsKey(year)){
					fos = new FileOutputStream(year+"MyReview.dat");
					oos = new ObjectOutputStream(fos);
					oos.writeObject(modelYear.get(year));
					oos.flush();
					oos.reset();
				}
			}
			return true;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(
					this,
					"정보 저장 중 문제가 발생했습니다. 나중에 다시 시도하세요.",
					"알림",
					JOptionPane.WARNING_MESSAGE
			);
			return false;
		}finally{
			MyReviewUtils.closeAll(oos, fos);
		}
	}
	private void loadReview(){
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader("year.txt");
			br = new BufferedReader(fr);
			String line = null;
			while((line = br.readLine())!=null){
				years.add(line);
			}
		}catch(FileNotFoundException e){
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			MyReviewUtils.closeAll(br, fr);
		}
		if(years.size()==0){
			Calendar c = Calendar.getInstance();
			years.add(String.valueOf(c.get(Calendar.YEAR)));
		}
		loadReviewYear(years.size()-1);
		year = years.get(years.size()-1);
	}
	private void loadReviewYear(int idx){
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(years.get(idx)+"MyReview.dat");
			ois = new ObjectInputStream(fis);
			modelAll = (DefaultListModel<MovieReview>)ois.readObject();
		}catch(FileNotFoundException e){
			modelAll = new DefaultListModel<MovieReview>();
		}catch(IOException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}finally{
			MyReviewUtils.closeAll(ois, fis);
		}
		modelYear.put(years.get(idx), modelAll);
	}
	private JLabel getLalHeader(String text,Dimension d){
		JLabel lbl = new JLabel(text,JLabel.CENTER);
		lbl.setPreferredSize(d);
		Color c = new Color(0x8C8C8C);
		lbl.setBorder(new LineBorder(c));
		lbl.setOpaque(true);
		lbl.setBackground(Color.WHITE);
		return lbl;
	}
	private JLabel getLalInfo(String text,Dimension d){
		JLabel lbl = new JLabel(text);
		lbl.setPreferredSize(d);
		return lbl;
	}
	private boolean isAll(){
		return AMOUNT[0].equals(cbScore.getSelectedItem().toString());
	}
	public void addMovieReview(MovieReview movieReview,String year){
		if(!years.contains(year)){
			years.add(year);
			String[] arrYear = new String[years.size()];
			for(int i =0;i<years.size();i++){
				arrYear[i]=years.get(i);
			}
			Arrays.sort(arrYear);
			
			years.removeAllElements();
			for(String y : arrYear){
				years.add(y);
			}
		}
		cbYear.setSelectedItem(year);
		this.year = year;
		modelAll.addElement(movieReview);
		modelYear.put(year, modelAll);
		totalReview(modelAll.size());
		cbScore.setSelectedIndex(0);
		list.setModel(modelAll);
		change = true;
	}	
	public void totalReview(int modelSize){
		lblHowMany.setText("총 "+modelSize+" 건의 리뷰가 있습니다.");
	}	
	@Override
	public int compare(MovieReview review1, MovieReview review2){
		int result = 0;
		if(mode == ORDER_BY_DATE_ASC){//등록일을 오름차순 정렬한다.
			result = review1.getDate().compareTo(review2.getDate());
		}else if(mode == ORDER_BY_DATE_DESC){//등록일을 내림차순 정렬한다.
			result = - review1.getDate().compareTo(review2.getDate());
		}else if(mode == ORDER_BY_AMOUNT_ASC){//별점을 오름차순 정렬한다.
			result = review1.getAmount().compareTo(review2.getAmount());
		}else if(mode == ORDER_BY_AMOUNT_DESC){//별점을 내림순 정렬한다.
			result = - review1.getAmount().compareTo(review2.getAmount());
		}
		if(result==0&&mode==ORDER_BY_AMOUNT_ASC&&mode==ORDER_BY_AMOUNT_DESC){
			result = review1.getDate().compareTo(review2.getDate());
		}else{
			result = review1.getMovieTitle().compareTo(review2.getMovieTitle());
		}
		return result;
	}
	public String directorList(int idx){//감독 순위
		try{
			Vector<DupNum> list = new Vector<DupNum>();
			for(int i=0;i<modelAll.size();i++){
				int j = list.indexOf(new DupNum(modelAll.getElementAt(i).getDirector()));
				if(j>=0){
					list.get(j).plusNum();
				}else{
					list.add(new DupNum(modelAll.getElementAt(i).getDirector(),1));
				}
			}
			DupNum[] arr = list.toArray(new DupNum[0]);
			Arrays.sort(arr);
			return arr[idx].getDupObject()+"("+arr[idx].getNum()+"편)";
		}catch(ArrayIndexOutOfBoundsException e){
			return "-";
		}
	}
	public String bestMovie(int idx){
		Object[] temp = modelAll.toArray();
		MovieReview[] arr =  new MovieReview[temp.length];
		for(int i=0; i<temp.length; i++){
			arr[i] = (MovieReview)temp[i];
		}
		mode = ORDER_BY_AMOUNT_ASC;
		Arrays.sort(arr, this);
		String result = "-";
		try{
			if(arr[idx].getAmount().equals(AMOUNT[1])){
				result = arr[idx].getMovieTitle();
			}
		}catch(ArrayIndexOutOfBoundsException e){}
		return result;
	}
	public String genrelist(int idx){//장르 순위
		try{
			Vector<DupNum> list = new Vector<DupNum>();
			for(int i=0;i<modelAll.size();i++){
				int j = list.indexOf(new DupNum(modelAll.getElementAt(i).getGenre()));
				if(j>=0){
					list.get(j).plusNum();
				}else{
					list.add(new DupNum(modelAll.getElementAt(i).getGenre(),1));
				}
			}
			DupNum[] arr = list.toArray(new DupNum[0]);
			Arrays.sort(arr);
			return arr[idx].getDupObject()+"("+arr[idx].getNum()+"편)";
		}catch(ArrayIndexOutOfBoundsException e){
			return "-";
		}
	}
	public void findFavorites(){
		favorites = new Vector<MovieReview>();
		for(int i=0;i<modelAll.size();i++){
			if(modelAll.getElementAt(i).isLike()){
				favorites.add(modelAll.getElementAt(i));
			}
		}
	}
	public MovieReview getFavorites(int idx){
		if(change){
			findFavorites();
		}
		String result =  "영화 제목: "+ favorites.get(idx).getDirector()+"\n";
		result += "장르: "+ favorites.get(idx).getGenre()+"\n";
		result += "평점: "+ favorites.get(idx).getAmount()+"\n";
		result += "리뷰 내용: "+ favorites.get(idx).getReview();
		return favorites.get(idx);
	}
	public int getFavoritesSize(){
		if(change){
			findFavorites();
		}
		return favorites.size();
	}
	public void modify(int idx, MovieReview m){
		modelYear.get(year).set(idx, m);
		modelAll.set(idx, m);
		filterScore();
		change = true;
	}
	public int getYear(){
		return Integer.parseInt(year);
	}
}
