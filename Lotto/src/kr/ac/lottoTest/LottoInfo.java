package kr.ac.lottoTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class LottoInfo extends JDialog implements ActionListener{
   private JLabel lbl1; 
   private JLabel lbl2; 
   private Lotto owner;
   private JButton btnNo2;
   
   
   public LottoInfo(Lotto owner) {
      super(owner,"�ζ� ���", true);
      this.owner = owner;
      init();
      setDisplay();
      addListener();
      showFrame();
   }
   
   private void init() {
      lbl1 = new JLabel("��÷��ȣ :" +owner.getLottoNums() ,JLabel.CENTER);
      lbl2 = new JLabel("****������ ��ȣ�� ���ʽ� ��ȣ�Դϴ�.****", JLabel.CENTER);
      
      
      btnNo2 = new JButton("2���̻��� �Ƿ���?");
      
   }
   
   private void setDisplay(){
      JPanel pnl1 = new JPanel(new GridLayout(0,1));
      pnl1.add(lbl2);
      pnl1.add(lbl1);
      
      JPanel pnl2 = new JPanel(new GridLayout(1,0));
      pnl2.setBorder(new TitledBorder
            (new LineBorder(Color.GRAY,1),
            "��������"   
            ));
      
      JPanel pnlLeft = new JPanel(new GridLayout(0,1));
      JPanel pnlRight = new JPanel(new GridLayout(0,1));
      
      JLabel[] lblGame = new JLabel[owner.getTryNum()];
      JLabel[] lblMyNums = new JLabel[owner.getTryNum()];
      JLabel[] lblnums = new JLabel[owner.getTryNum()];
      JLabel[] lblRank = new JLabel[owner.getTryNum()];
      
      for(int idx=0; idx<lblGame.length; idx++) {
         lblGame[idx] = new JLabel("����"+ (idx + 1));
         lblMyNums[idx] = new JLabel(owner.getMyNums(idx));
         lblnums[idx] = new JLabel("��ġ�� ��ȣ" + owner.getMyLists(idx));
         lblRank[idx] = new JLabel("���"+ owner.getRank(idx));
         
         pnlLeft.add(lblGame[idx]);
         pnlLeft.add(lblMyNums[idx]);
         pnlRight.add(lblnums[idx]);
         pnlRight.add(lblRank[idx]);
         
      }
      pnl2.add(pnlLeft);
      pnl2.add(pnlRight);
      
      JPanel pnl4 = new JPanel();
      pnl4.add(btnNo2);
     
      
      add(pnl1,BorderLayout.NORTH);
      add(pnl2,BorderLayout.CENTER);
      add(pnl4,BorderLayout.SOUTH);
   }
   
   private void addListener() {
         addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent we){
                  dispose();//~��������
            }
         });
         btnNo2.addActionListener(this);
   }
   @Override
   public void actionPerformed(ActionEvent ae) {
	   JOptionPane.showMessageDialog(
			   LottoInfo.this,
			   owner.rank2(),
               "2���̻��� �Ƿ���?",
               JOptionPane.INFORMATION_MESSAGE
      );
   }
   
   private void showFrame() {
	  pack();
      setLocationRelativeTo(null);
      setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
      setResizable(false);//~��������
      setVisible(true);
   }
}