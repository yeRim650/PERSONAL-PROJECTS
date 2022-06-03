package kr.ac.lottoTest;

import java.awt.Color;
import java.awt.Dimension;

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
      setPreferredSize(new Dimension(25, 25));
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
    	  setBackground(new Color(0xABF200));
      }else{
    	  setBackground(Color.WHITE);
      }
   }
}