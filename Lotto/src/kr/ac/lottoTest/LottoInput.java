package kr.ac.lottoTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
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
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class LottoInput extends JDialog implements ActionListener{
   public static final int LOTTO_NUM = 45; // 로또번호갯수
   public static final int MAX_INPUT = 5; // 구매 최대갯수
   public static final int MAX_SELECT = 6; // 고를수 있는 갯수
   public static final String[] ORDER = {"A", "B", "C", "D", "E"};
   
   private NumLabel[] numLbls;
   private LottoButton btnBuy;
   private LottoButton[] btnCorrects;
   private LottoButton[] btnAutos;
   private LottoButton btnOk;
   private JLabel lblGame;
   private ImageNumJPanel[] pnlImageNums;
   
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
      numLbls = new NumLabel[LOTTO_NUM];
      for(int i=0;i<LOTTO_NUM;i++){
         numLbls[i] = new NumLabel(i+1);
      }
      btnCorrects = new LottoButton[MAX_INPUT];
      btnAutos = new LottoButton[MAX_INPUT];
      for(int i=0;i<MAX_INPUT;i++){
         if(i<onwer.getTryNum()){
        	 btnCorrects[i] = new LottoButton("수정",true);
        	 btnAutos[i] = new LottoButton("자동",true);
         }else{
        	 btnCorrects[i] = new LottoButton("수정",false);
        	 btnAutos[i] = new LottoButton("자동",false);	 
         }
      }
      btnOk = new LottoButton("확인");
      btnBuy = new LottoButton("구매");
      pnlImageNums = new ImageNumJPanel[MAX_INPUT];
   }
   
   private void setDisplay(){
      JPanel pnlCenter = new JPanel(new GridLayout(0,1));
      pnlCenter.setBorder(new EmptyBorder(new Insets(10,10,5,10)));
      pnlCenter.setBackground(Color.WHITE);
      JPanel pnlCheck = new JPanel(new BorderLayout());
      pnlCheck.add(new JLabel("선택번호 확인"), BorderLayout.CENTER);
      pnlCenter.add(pnlCheck);
      JPanel[] pnls = new JPanel[MAX_INPUT];
      for(int i=0;i<MAX_INPUT;i++){
         pnls[i] = new JPanel(new FlowLayout(FlowLayout.LEFT) );
         pnls[i].add(new JLabel("게임 " +ORDER[i]));
         pnlImageNums[i] = new ImageNumJPanel();
         pnls[i].add(pnlImageNums[i]);
         pnls[i].add(btnCorrects[i]);
         pnls[i].add(btnAutos[i]);
         pnls[i].setBackground(Color.WHITE);
         pnlCenter.add(pnls[i]);
      }
      JPanel pnlNext = new JPanel(new FlowLayout(FlowLayout.RIGHT));
      pnlNext.add(btnBuy);
      pnlNext.setBackground(Color.WHITE);
      pnlCenter.add(pnlNext);
      JPanel pnlInput = new JPanel(new BorderLayout());
      JPanel pnlNum = new JPanel(new GridLayout(0,6));
      pnlNum.setOpaque(true);
      pnlNum.setBackground(Color.WHITE);
      pnlNum.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
      JPanel[] pnlNums = new JPanel[LOTTO_NUM];
      for(int i=0;i<LOTTO_NUM;i++){
         pnlNums[i] = new JPanel();
         pnlNums[i].setOpaque(true);
         pnlNums[i].setBackground(Color.WHITE);
         pnlNums[i].add(numLbls[i]);
         pnlNum.add(pnlNums[i]);
      }
      JPanel pnlWest = new JPanel(new BorderLayout());
      pnlWest.setBackground(Color.WHITE);
      
      pnlInput.add(pnlNum,BorderLayout.SOUTH);
      
      JPanel pnlLable = new JPanel(new BorderLayout());
      JLabel lblCenter = new JLabel("6개 번호 선택", JLabel.CENTER);
      lblCenter.setFont(new Font("Dialog",Font.BOLD,18));
      lblCenter.setOpaque(true);
      lblCenter.setBackground(new Color(0xF15F5F));
      lblCenter.setForeground(Color.WHITE);
      
      pnlLable.add(lblCenter,BorderLayout.CENTER);
      pnlLable.setPreferredSize(new Dimension(50,30));
      
      lblGame = new JLabel("A",JLabel.CENTER);
      lblGame.setForeground(Color.RED);
      lblGame.setFont(new Font("Dialog",Font.BOLD,20));
      lblGame.setOpaque(true);
      lblGame.setBackground(Color.WHITE);
      lblGame.setPreferredSize(new Dimension(50, 30));
      lblGame.setBorder(new LineBorder(new Color(0xF15F5F), 2));
      pnlLable.add(lblGame,BorderLayout.WEST);
      pnlWest.setBorder(new LineBorder(new Color(0xF15F5F), 2));
      pnlInput.add(pnlLable, BorderLayout.CENTER);
      JPanel pnlOk = new JPanel(new FlowLayout());
      pnlOk.add(btnOk);
      pnlOk.setBackground(Color.WHITE);
      pnlWest.add(pnlOk, BorderLayout.SOUTH);
      pnlWest.add(pnlInput, BorderLayout.CENTER);
      
      JPanel pnlMain = new JPanel();
      pnlMain.add(pnlWest);
      pnlMain.add(pnlCenter);
      pnlMain.setBackground(Color.GRAY);
      add(pnlMain, BorderLayout.CENTER);
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
     MouseListener mListener = new MouseAdapter (){
    	  @Override
    		public void mousePressed(MouseEvent me){
    		  JComponent c = (JComponent)me.getComponent();
    		  if(!isMaxTry()){
    			  NumLabel numlabel = (NumLabel)c;
    			  if(!numlabel.isSelect()){
		    		  if(choiceNum<MAX_SELECT){
		    			  choiceNum++;
			    		  numlabel.setSelect(true);
		    		  }else{
		    			  JOptionPane.showMessageDialog(
	    			               LottoInput.this,
	    			               "이미 6개 선택하셨습니다.",
	    			               "경고",
	    			               JOptionPane.WARNING_MESSAGE
	    			      );
		    		  }
    			  }else{
    				  choiceNum--;
	    			  numlabel.setSelect(false);
    			  }
    		  }else{
    			  JOptionPane.showMessageDialog(
			               LottoInput.this,
			               "시도횟수를 모두 사용하셨습니다.",
			               "경고",
			               JOptionPane.WARNING_MESSAGE
			    );
    		  }
    	  }
     };
     btnOk.addActionListener(this);
     for(int i = 0; i<5; i++){
    	 btnCorrects[i].addActionListener(this);
    	 btnAutos[i].addActionListener(this);
     }
     for(int i =0; i<numLbls.length; i++){
    	 numLbls[i].addMouseListener(mListener);
    	 numLbls[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
     }
     btnBuy.addActionListener(this);
   }
   @Override 
   public void actionPerformed(ActionEvent ae){
		Object src = ae.getSource();
		if(src == btnOk){
			if(isMaxTry()){
				JOptionPane.showMessageDialog(
			               LottoInput.this,
			               "시도횟수를 모두 사용하셨습니다.",
			               "경고",
			               JOptionPane.WARNING_MESSAGE
			    );
			}else{
				HashSet<Integer> selectNum = new HashSet<Integer>();
				boolean flag = true;
				if((choiceNum==MAX_SELECT)){
					for(int i =0; (i< numLbls.length); i++){
						if(numLbls[i].isSelect()){
							selectNum.add(numLbls[i].getOrder());
						}
					}
					Integer[] num = selectNum.toArray(new Integer[0]);
					MyNums n = new MyNums(num);
					onwer.getSome(tryNum, n);
					pnlImageNums[tryNum].setSelectNum(n);
					selcetReset();
					findNext();
				}else{
					JOptionPane.showMessageDialog(
				               LottoInput.this,
				               "6개를 선택해주세요",
				               "경고",
				               JOptionPane.WARNING_MESSAGE
				     );
				}
			}
		}
		for(int i = 0; i<5; i++){
			if(src == btnCorrects[i]){
				selcetReset();
				onwer.removeSome(i);
				pnlImageNums[i].setResetNum();
				lblGame.setText(ORDER[i]);
				tryNum=i;
			}
		}
		if(src == btnBuy){
			if(isMaxTry()){
				onwer.makeLottoNums();
				onwer.showResult();
				new LottoInfo(onwer);
			}else{
				JOptionPane.showMessageDialog(
			               LottoInput.this,
			               "시도횟수가 남아있습니다.",
			               "경고",
			               JOptionPane.WARNING_MESSAGE
			    );
			}
		}
		for(int i = 0; i<5; i++){
			if(src == btnAutos[i]){
				MyNums n = new MyNums();
				onwer.getSome(i, n);
				pnlImageNums[i].setSelectNum(n);
				if(lblGame.getText().equals(ORDER[i])){
					selcetReset();
					findNext();
				}
			}
		}
   }
   private boolean isMaxTry(){
	   return lblGame.getText().equals("");
   }
   private void selcetReset(){
	   for(int i =0; i< numLbls.length; i++){
			numLbls[i].setSelect(false);
		}
	   choiceNum = 0;
   }
   private void findNext(){
	   boolean flag = true;
		for(int j = 0;j<onwer.getTryNum()&&flag ;j++){
			if(!pnlImageNums[j].isSelect()){ 
				flag = false;
				tryNum = j;
			}
		}
		lblGame.setText(ORDER[tryNum]);
		if(flag){
			lblGame.setText("");
		}
   }
   
   private void showDlg(){
      pack();
      setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
      setLocationRelativeTo(null);
      setResizable(false);
      setVisible(true);
   }
  
}