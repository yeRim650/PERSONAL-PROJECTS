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
   public static int LOTTO_NUM = 45; // �ζǹ�ȣ����
   public static int MAX_INPUT = 5; // ���� �ִ밹��
   public static int MAX_SELECT = 6; // ���� �ִ� ����
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
	  //���̺� 45�� �����
      numLbls = new NumLabel[LOTTO_NUM];
      for(int i=0;i<LOTTO_NUM;i++){
         numLbls[i] = new NumLabel(i+1);
      }
      tfInputs = new JTextField[MAX_INPUT]; // ~ ����
      btnCorrects = new JButton[MAX_INPUT];
      for(int i=0;i<MAX_INPUT;i++){
         tfInputs[i] = new JTextField(15);
         tfInputs[i].setEditable(false);
         btnCorrects[i] = new JButton("����");
         if(i<onwer.getTryNum()){
         }else{
        	 btnCorrects[i].setEnabled(false);
        	 tfInputs[i].setText("-");
         }
         btnCorrects[i].setPreferredSize(new Dimension(60, 23));
      }
      btnOk = new JButton("Ȯ��");
      btnBuy = new JButton("����");
   }
   
   private void setDisplay(){
      JPanel pnlCenter = new JPanel(new GridLayout(0,1));
      JPanel[] pnls = new JPanel[MAX_INPUT];
      for(int i=0;i<MAX_INPUT;i++){
         pnls[i] = new JPanel();
         pnls[i].add(new JLabel("����" +ORDER[i])); // string �迭 ���� ����
         pnls[i].add(tfInputs[i]);
         pnls[i].add(btnCorrects[i]); // ������ư�迭
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
      JLabel lblCenter = new JLabel("6�� ��ȣ ����", JLabel.CENTER);
      lblCenter.setOpaque(true);
      lblCenter.setBackground(new Color(0xF15F5F));
      lblCenter.setForeground(Color.WHITE);
      
      pnlLable.add(lblCenter,BorderLayout.CENTER);
      
      lblGame = new JLabel("1",JLabel.CENTER);
      lblGame.setOpaque(true);
      lblGame.setBackground(Color.WHITE);
      lblGame.setPreferredSize(new Dimension(50, 30)); //ũ�� ����
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
               "1���ȸ ��ġ�ǰ̴ϱ�??",
               "�����Ͻðڽ��ϱ�?",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE
            );
            if(result == JOptionPane.YES_OPTION){
            	dispose();
            }
         }
      });
     MouseListener mListener = new MouseAdapter (){ //~ 45���� numlabel�� ����ȴ� MouseListener
    	  @Override
    		public void mousePressed(MouseEvent me){  // ���콺 ������
    		  JComponent c = (JComponent)me.getComponent(); // ????? ~ MouseEvent�� �߻��� ���me�� JComponent�� ����ȯ
    		  if(!isMaxTry()){ // MaxTry�� false�� ~ �õ� Ƚ���� ����������
    			  NumLabel numlabel = (NumLabel)c; // ����ȯ
    			  if(!numlabel.isSelect()){ // numlabel�� ������ �ȵ�������
		    		  if(choiceNum<MAX_SELECT){ // 0 < MAX_SELECT ~ ������ �� �ִ� ��ȣ���� 6�� ���� ������
		    			  choiceNum++; // choiceNum + 1
			    		  numlabel.setSelect(true); //~numlabel�� ���¸� ���õ� ���·� ���� false -> true
		    		  }else{ // MAX_SELECT�̻����� ������
		    			  JOptionPane.showMessageDialog(
	    			               LottoInput.this,
	    			               "�̹� 6�� �����ϼ̽��ϴ�.",
	    			               "���",
	    			               JOptionPane.WARNING_MESSAGE
	    			      );
		    		  }
    			  }else{ // numlabel���ý� ~ numlabel ���õ� ���¸�
    				  choiceNum--;
	    			  numlabel.setSelect(false);//~numlabel�� ���¸� ������ ������ ���·� ���� true -> false
    			  }
    		  }else{ //MaxTry�� true�� ~ �ִ�Ƚ���� ����ϸ�
    			  JOptionPane.showMessageDialog(
			               LottoInput.this,
			               "�õ�Ƚ���� ��� ����ϼ̽��ϴ�.",
			               "���",
			               JOptionPane.WARNING_MESSAGE
			    );
    		  }
    	  }
     };
     // Ȯ�ι�ư �׼�
     btnOk.addActionListener(this);
     //������ư �迭 �׼�
     for(int i = 0; i<5; i++){
    	 btnCorrects[i].addActionListener(this);
     }
     //���̺� 45�� �׼�
     for(int i =0; i<numLbls.length; i++){
    	 numLbls[i].addMouseListener(mListener);//~�������� numLbls�� ���콺�� ��� Ŀ�� ����
    	 numLbls[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
     }
     // ���Ź�ư �׼�
     btnBuy.addActionListener(this);
   }
   @Override 
   public void actionPerformed(ActionEvent ae){
		Object src = ae.getSource(); // ���� ��ư�� �����°�?
		if(src == btnOk){ // Ȯ�� ��ư�̴�?
			if(isMaxTry()){
				JOptionPane.showMessageDialog(
			               LottoInput.this,
			               "�õ�Ƚ���� ��� ����ϼ̽��ϴ�.",
			               "���",
			               JOptionPane.WARNING_MESSAGE
			    );
			}else{ // isMaxTry�� �ƴϴ�?
				HashSet<Integer> selectNum = new HashSet<Integer>();
				boolean flag = true;
				if((choiceNum==MAX_SELECT)){ // ���ù�ȣ�� ��� ��������? ~ 6���� �� ���� ���¿��� ��ȣ�� ������� �� �ִ�.
					for(int i =0; (i< numLbls.length); i++){
						if(numLbls[i].isSelect()){
							selectNum.add(numLbls[i].getOrder()); // numLbls idx��ȣ ����ֱ�
						}
					}
					Integer[] num = selectNum.toArray(new Integer[0]); // �迭 ������ֱ�
					MyNums n = new MyNums(num);
					onwer.getSome(tryNum, n);
					
					tfInputs[tryNum].setText(n.toString()); 
					//~ù��° �õ��� tryNum = 0 
					// ~JTextField�� tfInputs[0]~tfInputs[4] ������� 5��
					// ~ù��° �õ��̹Ƿ� tfInputs[0]�� ��ȣ�� ǥ��
					// ~��� ������ MyNums n�� toString�� �ִ´�
					
					selcetReset();
					
					for(int j = 0;j<onwer.getTryNum()&&flag ;j++){ //~����� tryNum ã�� ������� ��ã���� flag�� ture�� ������
						if(tfInputs[j].getText().equals("")){ 
							flag = false;
							tryNum = j;
						}
					}
					lblGame.setText(ORDER[tryNum]);//~ã�� ������� ���� ��ȣ�� lbl�� ǥ��(lbl�� ��ġ�� ��ȣ�� ��)
					if(flag){//~���̻� ������� �����Ƿ� lbl�� �ƹ��͵� ǥ�õ��� �ʴ´�.
						lblGame.setText("");
					}
				}else{//~ 6����ȣ�� �ȴ����� ���
					JOptionPane.showMessageDialog(
				               LottoInput.this,
				               "6���� �������ּ���",
				               "���",
				               JOptionPane.WARNING_MESSAGE
				     );
				}
			}
		}
		for(int i = 0; i<5; i++){// ~ ����1 ���� ��ư�� ��� ~ ����5 ���� ��ư�� ��� fo������ if�� 5���� �����.
			if(src == btnCorrects[i]){// ~ ���� ��ư�� ������ ��ȣ�� ��� lbl�� ��ȣ�� �ٲ�� ���� ����� ������ ����
				selcetReset();
				onwer.removeSome(i);//~ �������� : ���� ����� ������ ����
				tfInputs[i].setText("");//~ �������� : tfInputs[i]�� ����д�
				lblGame.setText(ORDER[i]);// ~ ���� i�� lbl�� ����
				tryNum=i;// ~ ��� ������ �ٽ� �Ұ��� ����
			}
		}
		if(src == btnBuy){//~���Ź�ư�� ������
			if(isMaxTry()){//~�ִ�Ƚ���� ����ϸ� ��÷��ȣ�� ����� ���ؼ� ����� ��ġ�� ��ȣ�� ������ LottoInfoâ�� ǥ��
				onwer.makeLottoNums();
				onwer.showResult();
				new LottoInfo(onwer);
			}else{//Ƚ���� ����������
				JOptionPane.showMessageDialog(
			               LottoInput.this,
			               "�õ�Ƚ���� �����ֽ��ϴ�.",
			               "���",
			               JOptionPane.WARNING_MESSAGE
			    );
			}
		}
   }
   //�õ�Ƚ���� �ٽ����?
   private boolean isMaxTry(){
	   return lblGame.getText().equals("");
   }
   //��ȣ �� �ʱ�ȭ
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
      setResizable(false);//~ �������� : ũ�Ⱑ ����
      setVisible(true);
   }
  
}