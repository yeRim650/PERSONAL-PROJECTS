package kr.ac.lottoTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class LottoInput extends JDialog implements ActionListener{
   public static int LOTTO_NUM = 45; // 로또번호갯수
   public static int MAX_INPUT = 5; // 구매 최대갯수
   public static int MAX_SELECT = 6; // 고를수 있는 갯수
   public static String[] ORDER = {"1", "2", "3", "4", "5"};
   
   private NumLabel[] numLbls;
   private JTextField[] tfInputs;
   private JButton btnBuy;
   private JButton[] btnCorrects;
   private JButton btnOk;
   private JLabel lblGame;
   
   private Lotto onwer;
   private int choiceNum = 0;
   private int tryNum = 0;

   public LottoInput(Lotto onwer){
      super(onwer, "Lotto", true);
      this.onwer = onwer;
      init();
      setDisplay();
      addListener();
      showDlg();
   }
   
   private void init(){
	  //레이블 45개 만들기
      numLbls = new NumLabel[LOTTO_NUM];
      for(int i=0;i<LOTTO_NUM;i++){
         numLbls[i] = new NumLabel(i+1);
      }
      tfInputs = new JTextField[MAX_INPUT]; // ~ 수정
      btnCorrects = new JButton[MAX_INPUT];
      for(int i=0;i<MAX_INPUT;i++){
         tfInputs[i] = new JTextField(15);
         tfInputs[i].setEditable(false);
         btnCorrects[i] = new JButton("수정");
         if(i<onwer.getTryNum()){
         }else{
        	 btnCorrects[i].setEnabled(false);
        	 tfInputs[i].setText("-");
         }
         btnCorrects[i].setPreferredSize(new Dimension(60, 23));
      }
      btnOk = new JButton("확인");
      btnBuy = new JButton("구매");
   }
   
   private void setDisplay(){
      JPanel pnlCenter = new JPanel(new GridLayout(0,1));
      JPanel[] pnls = new JPanel[MAX_INPUT];
      for(int i=0;i<MAX_INPUT;i++){
         pnls[i] = new JPanel();
         pnls[i].add(new JLabel("게임" +ORDER[i])); // string 배열 가져 오기
         pnls[i].add(tfInputs[i]);
         pnls[i].add(btnCorrects[i]); // 수정버튼배열
         pnlCenter.add(pnls[i]);
      }
      JPanel pnlNext = new JPanel();
      pnlNext.add(btnOk);
      pnlNext.add(btnBuy);
      pnlCenter.add(pnlNext);
      JPanel pnlInput = new JPanel(new BorderLayout());
      JPanel pnlNum = new JPanel(new GridLayout(0,5));
      pnlNum.setOpaque(true);
      pnlNum.setBorder(new LineBorder(Color.RED, 1));
      pnlNum.setBackground(Color.WHITE);
      JPanel[] pnlNums = new JPanel[LOTTO_NUM];
      for(int i=0;i<LOTTO_NUM;i++){
         pnlNums[i] = new JPanel();
         pnlNums[i].setOpaque(true);
         pnlNums[i].setBackground(Color.WHITE);
         pnlNums[i].add(numLbls[i]);
         pnlNum.add(pnlNums[i]);
      }
      JPanel pnlWest = new JPanel();
      pnlWest.setBackground(Color.WHITE);
      
      pnlInput.add(pnlNum,BorderLayout.SOUTH);
      
      JPanel pnlLable = new JPanel(new BorderLayout());
      JLabel lblCenter = new JLabel("6개 번호 선택", JLabel.CENTER);
      lblCenter.setOpaque(true);
      lblCenter.setBackground(new Color(0xF15F5F));
      lblCenter.setForeground(Color.WHITE);
      
      pnlLable.add(lblCenter,BorderLayout.CENTER);
      
      lblGame = new JLabel("1",JLabel.CENTER);
      lblGame.setOpaque(true);
      lblGame.setBackground(Color.WHITE);
      lblGame.setPreferredSize(new Dimension(50, 30)); //크기 지정
      pnlLable.add(lblGame,BorderLayout.WEST);
      pnlInput.setBorder(new LineBorder(Color.RED, 1));
      pnlInput.add(pnlLable, BorderLayout.CENTER);
      pnlWest.add(pnlInput);
      
      
      add(pnlCenter, BorderLayout.CENTER);
      add(pnlWest, BorderLayout.WEST);
   }
   
   private void addListener() {
      addWindowListener(new WindowAdapter(){
         @Override
         public void windowClosing(WindowEvent we){
            int result = JOptionPane.showConfirmDialog(
               LottoInput.this,
               "1등기회 놓치실겁니까??",
               "종료하시겠습니까?",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE
            );
            if(result == JOptionPane.YES_OPTION){
            	dispose();
            }
         }
      });
     MouseListener mListener = new MouseAdapter (){ //~ 45개의 numlabel에 적용된다 MouseListener
    	  @Override
    		public void mousePressed(MouseEvent me){  // 마우스 누르면
    		  JComponent c = (JComponent)me.getComponent(); // ????? ~ MouseEvent가 발생한 대상me를 JComponent로 형변환
    		  if(!isMaxTry()){ // MaxTry가 false면 ~ 시도 횟수가 남아있으면
    			  NumLabel numlabel = (NumLabel)c; // 형변환
    			  if(!numlabel.isSelect()){ // numlabel이 선택이 안되있으면
		    		  if(choiceNum<MAX_SELECT){ // 0 < MAX_SELECT ~ 선택할 수 있는 번호객수 6을 넘지 않으면
		    			  choiceNum++; // choiceNum + 1
			    		  numlabel.setSelect(true); //~numlabel의 상태를 선택된 상태로 변경 false -> true
		    		  }else{ // MAX_SELECT이상으로 누를시
		    			  JOptionPane.showMessageDialog(
	    			               LottoInput.this,
	    			               "이미 6개 선택하셨습니다.",
	    			               "경고",
	    			               JOptionPane.WARNING_MESSAGE
	    			      );
		    		  }
    			  }else{ // numlabel선택시 ~ numlabel 선택된 상태면
    				  choiceNum--;
	    			  numlabel.setSelect(false);//~numlabel의 상태를 선택이 해제된 상태로 변경 true -> false
    			  }
    		  }else{ //MaxTry가 true면 ~ 최대횟수를 사용하면
    			  JOptionPane.showMessageDialog(
			               LottoInput.this,
			               "시도횟수를 모두 사용하셨습니다.",
			               "경고",
			               JOptionPane.WARNING_MESSAGE
			    );
    		  }
    	  }
     };
     // 확인버튼 액션
     btnOk.addActionListener(this);
     //수정버튼 배열 액션
     for(int i = 0; i<5; i++){
    	 btnCorrects[i].addActionListener(this);
     }
     //레이블 45개 액션
     for(int i =0; i<numLbls.length; i++){
    	 numLbls[i].addMouseListener(mListener);//~수정사항 numLbls에 마우스를 대면 커서 변경
    	 numLbls[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
     }
     // 구매버튼 액션
     btnBuy.addActionListener(this);
   }
   @Override 
   public void actionPerformed(ActionEvent ae){
		Object src = ae.getSource(); // 무슨 버튼을 눌렀는가?
		if(src == btnOk){ // 확인 버튼이다?
			if(isMaxTry()){
				JOptionPane.showMessageDialog(
			               LottoInput.this,
			               "시도횟수를 모두 사용하셨습니다.",
			               "경고",
			               JOptionPane.WARNING_MESSAGE
			    );
			}else{ // isMaxTry가 아니다?
				HashSet<Integer> selectNum = new HashSet<Integer>();
				boolean flag = true;
				if((choiceNum==MAX_SELECT)){ // 선택번호가 모두 눌러졋다? ~ 6개를 다 누른 상태에서 번호를 집어넣을 수 있다.
					for(int i =0; (i< numLbls.length); i++){
						if(numLbls[i].isSelect()){
							selectNum.add(numLbls[i].getOrder()); // numLbls idx번호 집어넣기
						}
					}
					Integer[] num = selectNum.toArray(new Integer[0]); // 배열 만들어주기
					MyNums n = new MyNums(num);
					onwer.getSome(tryNum, n);
					
					tfInputs[tryNum].setText(n.toString()); 
					//~첫번째 시도면 tryNum = 0 
					// ~JTextField는 tfInputs[0]~tfInputs[4] 순서대로 5개
					// ~첫번째 시도이므로 tfInputs[0]에 번호를 표시
					// ~방금 생성된 MyNums n의 toString을 넣는다
					
					selcetReset();
					
					for(int j = 0;j<onwer.getTryNum()&&flag ;j++){ //~빈공간 tryNum 찾기 빈공간을 못찾으면 flag는 ture로 나간다
						if(tfInputs[j].getText().equals("")){ 
							flag = false;
							tryNum = j;
						}
					}
					lblGame.setText(ORDER[tryNum]);//~찾은 빈공간의 게임 번호를 lbl에 표시(lbl의 위치는 번호판 위)
					if(flag){//~더이상 빈공간이 없으므로 lbl에 아무것도 표시되지 않는다.
						lblGame.setText("");
					}
				}else{//~ 6개번호가 안눌러진 경우
					JOptionPane.showMessageDialog(
				               LottoInput.this,
				               "6개를 선택해주세요",
				               "경고",
				               JOptionPane.WARNING_MESSAGE
				     );
				}
			}
		}
		for(int i = 0; i<5; i++){// ~ 게임1 수정 버튼인 경우 ~ 게임5 수정 버튼인 경우 fo문으로 if문 5개를 만든다.
			if(src == btnCorrects[i]){// ~ 수정 버튼을 누르면 번호판 상단 lbl에 번호가 바뀌고 기존 저장된 정보는 삭제
				selcetReset();
				onwer.removeSome(i);//~ 수정사항 : 기존 저장된 정보는 삭제
				tfInputs[i].setText("");//~ 수정사항 : tfInputs[i]를 비워둔다
				lblGame.setText(ORDER[i]);// ~ 게임 i의 lbl로 변경
				tryNum=i;// ~ 몇번 게임을 다시 할건지 결정
			}
		}
		if(src == btnBuy){//~구매버튼을 누르면
			if(isMaxTry()){//~최대횟수를 사용하면 당첨번호를 만들고 비교해서 등수와 일치한 번호를 가져와 LottoInfo창에 표시
				onwer.makeLottoNums();
				onwer.showResult();
				new LottoInfo(onwer);
			}else{//횟수가 남아있으면
				JOptionPane.showMessageDialog(
			               LottoInput.this,
			               "시도횟수가 남아있습니다.",
			               "경고",
			               JOptionPane.WARNING_MESSAGE
			    );
			}
		}
   }
   //시도횟수를 다썼는지?
   private boolean isMaxTry(){
	   return lblGame.getText().equals("");
   }
   //번호 판 초기화
   private void selcetReset(){
	   for(int i =0; i< numLbls.length; i++){
			numLbls[i].setSelect(false);
		}
	   choiceNum = 0;
   }
   
   private void showDlg(){
      pack();
      setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
      setLocationRelativeTo(null);
      setResizable(false);//~ 수정사항 : 크기가 고정
      setVisible(true);
   }
  
}