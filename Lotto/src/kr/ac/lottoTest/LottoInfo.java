package kr.ac.lottoTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class LottoInfo extends JDialog implements ActionListener{
   private ImageNumJPanel pnlImageLottoNum;
   private ImageNumJPanel[] pnlImageNumResults;
   private JLabel lbl2; 
   private Lotto owner;
   private JButton btnNo2;
   private JLabel lblImage;
   
   
   public LottoInfo(Lotto owner) {
      super(owner,"로또 결과", true);
      this.owner = owner;
      init();
      setDisplay();
      addListener();
      showFrame();
   }
   
   private void init() {
	  Toolkit kit = Toolkit.getDefaultToolkit();
	  Image imgLotto = kit.getImage("Lotto.png");
	  Image newImgLotto = imgLotto.getScaledInstance(265,100,Image.SCALE_SMOOTH);
	  lblImage = new JLabel(new ImageIcon(newImgLotto));
	  pnlImageLottoNum = owner.getLottoNums();
      lbl2 = new JLabel("****마지막 번호가 보너스 번호입니다.****", JLabel.CENTER);
      btnNo2 = new JButton("2등이상이 되려면?");
      
   }
   
   private void setDisplay(){
      JPanel pnl1 = new JPanel(new BorderLayout());
      pnl1.add(lblImage,BorderLayout.NORTH);
      pnl1.add(lbl2,BorderLayout.CENTER);
      pnl1.add(pnlImageLottoNum,BorderLayout.SOUTH);
      pnl1.setBackground(Color.WHITE);
      
      JPanel pnl2 = new JPanel(new GridLayout(0,1));
      pnl2.setBorder(new TitledBorder
            (new LineBorder(Color.GRAY,1),
            "게임정보"   
            ));
      pnl2.setBackground(Color.WHITE);
      pnlImageNumResults = new ImageNumJPanel[owner.getTryNum()];
      JPanel[] pnl = new JPanel[owner.getTryNum()];
      for(int idx=0; idx<owner.getTryNum(); idx++){
    	  pnl[idx] = new JPanel();
    	  pnl[idx].add(new  JLabel("게임 "+ LottoInput.ORDER[idx]));
    	  pnlImageNumResults[idx]=owner.getNumsResult(idx);
    	  pnl[idx].add(pnlImageNumResults[idx]);
    	  pnl[idx].setBackground(Color.WHITE);
          pnl2.add(pnl[idx]);
      }
      JPanel pnl4 = new JPanel();
      pnl4.add(btnNo2);
      pnl4.setBackground(Color.WHITE);
     
      
      add(pnl1,BorderLayout.NORTH);
      add(pnl2,BorderLayout.CENTER);
      add(pnl4,BorderLayout.SOUTH);
   }
   
   private void addListener() {
         addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent we){
                  dispose();
            }
         });
         btnNo2.addActionListener(this);
   }
   @Override
   public void actionPerformed(ActionEvent ae) {
	   JOptionPane.showMessageDialog(
			   LottoInfo.this,
			   owner.rank2(),
               "2등이상이 되려면?",
               JOptionPane.INFORMATION_MESSAGE
      );
   }
   
   private void showFrame() {
	  pack();
      setLocationRelativeTo(null);
      setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
      setResizable(false);
      setVisible(true);
   }
}