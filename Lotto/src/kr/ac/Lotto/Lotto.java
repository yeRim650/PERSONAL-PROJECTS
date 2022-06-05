package kr.ac.Lotto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Lotto extends JFrame implements ActionListener {
   private JLabel lblTry;
   private JLabel lblImage;
   private JButton btnPlus;
   private JButton btnMinus;
   private JButton btnStart;

   private MyNums[] nums;

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
	   Toolkit kit = Toolkit.getDefaultToolkit();
	   Image imgLotto = kit.getImage("Lotto.png");
	   Image newImgLotto = imgLotto.getScaledInstance(530,200,Image.SCALE_SMOOTH);
	   lblImage = new JLabel(new ImageIcon(newImgLotto));
	   lblTry = new JLabel("1");
	   btnMinus = new JButton(new ImageIcon("Back-icon.png"));
	   btnMinus.setPreferredSize(new Dimension(20,20));
	   btnMinus.setBorderPainted(false);
	   btnMinus.setFocusPainted(false);
	   btnMinus.setContentAreaFilled(false);
	   btnPlus = new JButton(new ImageIcon("Next-icon.png"));
	   btnPlus.setPreferredSize(new Dimension(20,20)); 
	   btnPlus.setBorderPainted(false);
	   btnPlus.setFocusPainted(false);
	   btnPlus.setContentAreaFilled(false);
       btnStart = new JButton("시작하기");
       btnStart.setContentAreaFilled(false);
       btnStart.setPreferredSize(new Dimension(100,25));
   }
   
   private void setDisplay() {
        JPanel pnlNorth = new JPanel(new BorderLayout());
        pnlNorth.add(lblImage, BorderLayout.NORTH);
        JPanel pnlLbl = new JPanel(new GridLayout(0,1));
        JLabel lbl = new JLabel("구매 횟수",JLabel.CENTER);
        lbl.setFont(new Font("굴림",Font.BOLD,15));
        pnlLbl.add(lbl,JLabel.CENTER);
        pnlLbl.add(new JLabel("<최대 5회>",JLabel.CENTER));
        pnlNorth.add(pnlLbl, BorderLayout.CENTER);
        JPanel pnlTry = new JPanel();
        pnlTry.add(btnMinus);
        pnlTry.add(lblTry);
        pnlTry.add(btnPlus);
        pnlNorth.add(pnlTry, BorderLayout.SOUTH);
        JPanel pnlCenter = new JPanel();
        pnlNorth.setBackground(Color.WHITE);
        pnlLbl.setBackground(Color.WHITE);
        pnlTry.setBackground(Color.WHITE);
        pnlCenter.setBackground(Color.WHITE);
        pnlCenter.add(btnStart);
        add(pnlNorth, BorderLayout.NORTH);
        add(pnlCenter, BorderLayout.CENTER);
         
   }
   
   private void addListener() {
       btnStart.addActionListener(this);
       btnPlus.addActionListener(this);
       btnMinus.addActionListener(this);
       addWindowListener(new WindowAdapter(){
           @Override
           public void windowClosing(WindowEvent we){
              int result = JOptionPane.showConfirmDialog(
                 Lotto.this,
                 "종료하시겠습니까?",
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
	   	Object src = ae.getSource();
	   	maxTryNum = Integer.parseInt(lblTry.getText());
   		if(src == btnMinus && maxTryNum>1){
   			lblTry.setText(String.valueOf(maxTryNum-1));
   		}else if(src == btnPlus && maxTryNum<5){
   			lblTry.setText(String.valueOf(maxTryNum+1));
   		}
	   	if(src == btnStart){
	   		nums = new MyNums[maxTryNum];
	   		new LottoInput(this);
	   	}
   }
   public void showResult() {
         Integer[] excBonus = Arrays.copyOfRange(
              loNums.getNums(), 0, LottoNums.BONUS
         );
         ArrayList<Integer> goalList = new ArrayList<Integer>(
               Arrays.asList(excBonus)
         );
         myLists = new ArrayList<ArrayList<Integer>>();
         ranks = new ArrayList<Integer>();

         for(MyNums temp : nums){
            ArrayList<Integer> myList =new ArrayList<Integer>(Arrays.asList(temp.getNums()));
            myList.retainAll(goalList);

            int count = myList.size();
            
            myLists.add(myList);
            int rank = 0;
            
            switch(count) {
            case 6 :
               rank = 1;
               break;
            case 5 :
               int indexOfBonus = Arrays.binarySearch(
                  temp.getNums(), loNums.getBonusNum()
               );
               if(indexOfBonus >= 0) {
                  rank = 2;
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
   public ImageNumJPanel getLottoNums() {
	   return new ImageNumJPanel(loNums);
   }
   public ImageNumJPanel getNumsResult(int idx){
	   return new ImageNumJPanel(nums[idx],myLists.get(idx),ranks.get(idx));
   }
   public void getSome(int idx,MyNums m){
      nums[idx] = m;
   }
   public void removeSome(int idx){
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
		   idx1 = ranks.indexOf(new Integer(1));
		   idx2 = ranks.indexOf(new Integer(2));
		   count++;
		   if(!(idx1==-1)||!(idx2==-1)){
			   flag = false;
		   }
	   }
	   if(idx1==-1){
		   return count+"개 사시면 2등 게임 "+LottoInput.ORDER[idx2];
	   }else{
		   return count+"개 사시면 1등 게임 "+LottoInput.ORDER[idx1];
	   }
   }
   private void showFrame() {
         setTitle("로또를 시작합니다");
         pack();
         setLocationRelativeTo(null);
         setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
         setResizable(false);
         setVisible(true);
   }
   public static void main(String[] args) {
      new Lotto();
   }
}
