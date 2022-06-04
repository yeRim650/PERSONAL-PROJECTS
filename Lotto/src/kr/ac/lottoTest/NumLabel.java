package kr.ac.lottoTest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class NumLabel extends JLabel{
   private int order;
   private boolean select;
   public NumLabel(int order){//icon과 인덱스 번호를 가지는 JLabel
      super(String.valueOf(order),JLabel.CENTER);
      this.order = order;
      setSelect(false);
      setBorder(new LineBorder(new Color(0xF15F5F), 1));
      setForeground(Color.RED);
      setOpaque(true);
      setBackground(Color.WHITE);
      setPreferredSize(new Dimension(30, 34));
      setFont(new Font("Dialog",Font.PLAIN,14));
   }
   
   public int getOrder() {
      return order;
   }
   public boolean isSelect() {
      return select;
   }
   public void setSelect(boolean select) {
      this.select = select;
      if(select){
    	  setBackground(new Color(0x5D5D5D));
    	  setForeground(Color.WHITE);
      }else{
    	  setBackground(Color.WHITE);
    	  setForeground(Color.RED);
      }
   }
}