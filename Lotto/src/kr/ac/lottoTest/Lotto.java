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
   //내 번호
   private MyNums[] nums;
   //당첨번호
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
           
         lblHoits = new JLabel("구매횟수");
         //라디오 버튼 배열 5개 만들기
         rbtns = new JRadioButton[5]; 
         
         ButtonGroup group1 = new ButtonGroup();
         for(int i = 0; i < 5; i++ ) {
            rbtns[i] = new JRadioButton(String.valueOf(i + 1)); //int -> string으로 변환
            group1.add(rbtns [i]);
         }
         //구매횟수 1을 선택으로 
         rbtns[0].setSelected(true);
         
         rbtnJa = new JRadioButton("자동", true); //자동버튼 누른채로
         rbtnSoo = new JRadioButton("수동");
         
         ButtonGroup group2 = new ButtonGroup();
         group2.add(rbtnJa);
         group2.add(rbtnSoo);
         
         btnStart = new JButton("시작하기");
         lblHoits.setIcon(new ImageIcon("Lotto.png"));
      }
   
   private void setDisplay() {
     
         JPanel pnlNorth = new JPanel(new GridLayout(0, 1));
         JPanel pnlRb = new JPanel();
         
         pnlRb.setToolTipText("횟수를 지정하세요");
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
       //x표시 누르면 경고창
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
	   	boolean flag = false;
         int i = 0;
         while(!flag) { //true가 들어오면 ~ 선택된 라디오 버튼 찾는 반복문 찾으면 반복은 끝난다
            flag = rbtns[i].isSelected(); //1~5까지 라디오 버튼 뭐가 선택 됬는지
            if(flag) {//~선택된 라디오 버튼 찾으면
            	maxTryNum = Integer.parseInt(rbtns[i].getText());//라디오버튼 int로 변환 하고 가져온다
               nums = new MyNums[maxTryNum]; //nums로 집어넣는다
            }
            i++;
         }
         if(rbtnJa.isSelected()) { //자동 버튼이 선택되면
            loNums = new LottoNums();
            for(int idx =0; idx < nums.length; idx++) { //선택된 nums크기 만큼 배열생성
               nums[idx] = new MyNums();
            }
            showResult(); 
            new LottoInfo(this); //자동 누르면 인포로  
         }else {
            new LottoInput(this); // 수동 누르면 인풋으로
       }
    }
   public void showResult() {
         Integer[] excBonus = Arrays.copyOfRange( // ~ Arrays.copyOfRange의 파라미터는 3개 복사할 배열 , 복사를 시작할 인덱스, 복사가 끝나는 인덱스
               loNums.getNums(), 0, LottoNums.BONUS // ~ 메서드 Arrays.copyOfRange로 보너스 번호를 제외한 번호를 복사해 가져온다. 
         );
         ArrayList<Integer> goalList = new ArrayList<Integer>(//~retainAll을 쓰기 위해서는 배열을 list로 변경이 필요 array -> list
               Arrays.asList(excBonus)
         );
         myLists = new ArrayList<ArrayList<Integer>>();
         ranks = new ArrayList<Integer>();

         for(MyNums temp : nums){
            ArrayList<Integer> myList =new ArrayList<Integer>(Arrays.asList(temp.getNums()));
            myList.retainAll(goalList);//중복되는 부분 가져오기

            int count = myList.size(); //중복된 부븐 길이에 따라 등수 결정 ex) 6이면 6개가 중복되므로 1등
            
            myLists.add(myList);
            int rank = 0;
            
            switch(count) {
            case 6 :
               rank = 1;
               break;
            case 5 :
               // 구매한 로또번호 에서 보너스 번호의 인덱스 번호 확인 -> 없으면 -1
               int indexOfBonus = Arrays.binarySearch(
                  temp.getNums(), loNums.getBonusNum()
               );
               // 보너스 번호가 존재하면 index번호는 0이상으로 출력됨
               if(indexOfBonus >= 0) {
                  rank = 2;
               // 보너스 번호가 없으면 index번호는 0미만으로 출력됨
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
   public void getSome(int idx,MyNums m){// ~ MyNums 추가
      nums[idx] = m;
   }
   public void removeSome(int idx){// ~ 수정사항 MyNums 삭제
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
			   idx1 = ranks.indexOf(new Integer(1)); // ~ ranks에서 1등을 찾는다
			   idx2 = ranks.indexOf(new Integer(2)); // ranks에 idxex2번을 가져온다 
			   count++;//~ 1, 2등을 찾으면서 반복횟수
			   if(!(idx1==-1)||!(idx2==-1)){ // ~ idx2가 -1이면 ranks에 2등이 없다 idx1가 -1이면 ranks에 1등이 없다
				   flag = false;// ~ ||는 or 1등이나 2등중 하나만이라도 되면 반복문 나감
			   }
		   }
		   return count+"개 사시면 됩니다.";
	   }
   private void showFrame() {
         setTitle("★로또를 시작합니다★");
         pack();
         setLocationRelativeTo(null);
         setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
         setResizable(false);//~수정사항 크기 고정
         setVisible(true);
   }
   
   
   public static void main(String[] args) {
      new Lotto();
   }
}