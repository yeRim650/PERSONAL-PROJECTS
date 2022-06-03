package kr.ac.lottoTest;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Lotto extends JFrame implements ActionListener {
   
   private JLabel lblHoits;
   private JRadioButton[] rbtns;
   private JRadioButton rbtnJa;
   private JRadioButton rbtnSoo;
   private JButton btnStart;
   //�� ��ȣ
   private MyNums[] nums;
   //��÷��ȣ
   private LottoNums loNums;
   
   private ArrayList<ArrayList<Integer>> myLists;
   private ArrayList<Integer> ranks;
   private int maxTryNum;
   
   public Lotto() {
      init();
      setDisplay();
      addListener();
      showFrame();
   }
   
   private void init() {
           
         lblHoits = new JLabel("����Ƚ��");
         //���� ��ư �迭 5�� �����
         rbtns = new JRadioButton[5]; 
         
         ButtonGroup group1 = new ButtonGroup();
         for(int i = 0; i < 5; i++ ) {
            rbtns[i] = new JRadioButton(String.valueOf(i + 1)); //int -> string���� ��ȯ
            group1.add(rbtns [i]);
         }
         //����Ƚ�� 1�� �������� 
         rbtns[0].setSelected(true);
         
         rbtnJa = new JRadioButton("�ڵ�", true); //�ڵ���ư ����ä��
         rbtnSoo = new JRadioButton("����");
         
         ButtonGroup group2 = new ButtonGroup();
         group2.add(rbtnJa);
         group2.add(rbtnSoo);
         
         btnStart = new JButton("�����ϱ�");
         lblHoits.setIcon(new ImageIcon("Lotto.png"));
      }
   
   private void setDisplay() {
     
         JPanel pnlNorth = new JPanel(new GridLayout(0, 1));
         JPanel pnlRb = new JPanel();
         
         pnlRb.setToolTipText("Ƚ���� �����ϼ���");
         pnlRb.add(lblHoits);
         for(int i = 0; i < 5; i++) {
            pnlRb.add(rbtns [i]);
         }
         pnlNorth.add(pnlRb);
         JPanel pnlCenter = new JPanel();
         pnlCenter.add(rbtnJa);
         pnlCenter.add(rbtnSoo);
         
         JPanel pnlSouth = new JPanel();
         pnlSouth.add(btnStart);
         
         
         add(pnlNorth, BorderLayout.NORTH);
         add(pnlCenter, BorderLayout.CENTER);
         add(pnlSouth, BorderLayout.SOUTH);
         
      }
   
   private void addListener() {
       btnStart.addActionListener(this);
       //xǥ�� ������ ���â
        addWindowListener(new WindowAdapter(){
           @Override
           public void windowClosing(WindowEvent we){
              int result = JOptionPane.showConfirmDialog(
                 Lotto.this,
                 "�����Ͻðڽ��ϱ�?",
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
   @Override
   public void actionPerformed(ActionEvent ae){
	   	boolean flag = false;
         int i = 0;
         while(!flag) { //true�� ������ ~ ���õ� ���� ��ư ã�� �ݺ��� ã���� �ݺ��� ������
            flag = rbtns[i].isSelected(); //1~5���� ���� ��ư ���� ���� �����
            if(flag) {//~���õ� ���� ��ư ã����
            	maxTryNum = Integer.parseInt(rbtns[i].getText());//������ư int�� ��ȯ �ϰ� �����´�
               nums = new MyNums[maxTryNum]; //nums�� ����ִ´�
            }
            i++;
         }
         if(rbtnJa.isSelected()) { //�ڵ� ��ư�� ���õǸ�
            loNums = new LottoNums();
            for(int idx =0; idx < nums.length; idx++) { //���õ� numsũ�� ��ŭ �迭����
               nums[idx] = new MyNums();
            }
            showResult(); 
            new LottoInfo(this); //�ڵ� ������ ������  
         }else {
            new LottoInput(this); // ���� ������ ��ǲ����
       }
    }
   public void showResult() {
         Integer[] excBonus = Arrays.copyOfRange( // ~ Arrays.copyOfRange�� �Ķ���ʹ� 3�� ������ �迭 , ���縦 ������ �ε���, ���簡 ������ �ε���
               loNums.getNums(), 0, LottoNums.BONUS // ~ �޼��� Arrays.copyOfRange�� ���ʽ� ��ȣ�� ������ ��ȣ�� ������ �����´�. 
         );
         ArrayList<Integer> goalList = new ArrayList<Integer>(//~retainAll�� ���� ���ؼ��� �迭�� list�� ������ �ʿ� array -> list
               Arrays.asList(excBonus)
         );
         myLists = new ArrayList<ArrayList<Integer>>();
         ranks = new ArrayList<Integer>();

         for(MyNums temp : nums){
            ArrayList<Integer> myList =new ArrayList<Integer>(Arrays.asList(temp.getNums()));
            myList.retainAll(goalList);//�ߺ��Ǵ� �κ� ��������

            int count = myList.size(); //�ߺ��� �κ� ���̿� ���� ��� ���� ex) 6�̸� 6���� �ߺ��ǹǷ� 1��
            
            myLists.add(myList);
            int rank = 0;
            
            switch(count) {
            case 6 :
               rank = 1;
               break;
            case 5 :
               // ������ �ζǹ�ȣ ���� ���ʽ� ��ȣ�� �ε��� ��ȣ Ȯ�� -> ������ -1
               int indexOfBonus = Arrays.binarySearch(
                  temp.getNums(), loNums.getBonusNum()
               );
               // ���ʽ� ��ȣ�� �����ϸ� index��ȣ�� 0�̻����� ��µ�
               if(indexOfBonus >= 0) {
                  rank = 2;
               // ���ʽ� ��ȣ�� ������ index��ȣ�� 0�̸����� ��µ�
               } else {
                  rank = 3;
               }
               break;
            case 4 :
               rank = 4;
               break;
            case 3 :
               rank = 5;
               break;
         }
            ranks.add(rank);
     }       
   }
   public String getLottoNums() {
         return loNums.toString();
   }
   public String getMyNums(int idx) {
      return nums[idx].toString();
   }
   public String getMyLists(int idx) {
      return myLists.get(idx).toString();
   }
   public int getRank(int idx) {
      return ranks.get(idx);
   }
   public void getSome(int idx,MyNums m){// ~ MyNums �߰�
      nums[idx] = m;
   }
   public void removeSome(int idx){// ~ �������� MyNums ����
	   nums[idx] = null;
   }
   public int getTryNum(){
	      return maxTryNum;
}
	   public void makeLottoNums(){
	      loNums = new LottoNums();
	   }
	   public String rank2(){
		   int count = 0;
		   int idx1 = 0;
		   int idx2 = 0;
		   boolean flag = true;
		   while (flag) {
			   makeLottoNums();
			   showResult();
			   idx1 = ranks.indexOf(new Integer(1)); // ~ ranks���� 1���� ã�´�
			   idx2 = ranks.indexOf(new Integer(2)); // ranks�� idxex2���� �����´� 
			   count++;//~ 1, 2���� ã���鼭 �ݺ�Ƚ��
			   if(!(idx1==-1)||!(idx2==-1)){ // ~ idx2�� -1�̸� ranks�� 2���� ���� idx1�� -1�̸� ranks�� 1���� ����
				   flag = false;// ~ ||�� or 1���̳� 2���� �ϳ����̶� �Ǹ� �ݺ��� ����
			   }
		   }
		   return count+"�� ��ø� �˴ϴ�.";
	   }
   private void showFrame() {
         setTitle("�ڷζǸ� �����մϴ١�");
         pack();
         setLocationRelativeTo(null);
         setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
         setResizable(false);//~�������� ũ�� ����
         setVisible(true);
   }
   
   
   public static void main(String[] args) {
      new Lotto();
   }
}