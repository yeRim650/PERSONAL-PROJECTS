package kr.ac.MovieReview;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.TitledBorder;

public class ChangePw extends JDialog implements ActionListener{
     private JLabel lblPw;
     private JLabel lblNewPw;
     private JLabel lblNewCheckPw;

     private JPasswordField tfPw;
     private JPasswordField tfNewPw;
     private JPasswordField tfNewCheckPw;

     private JButton btnChange;
     private JButton btnCancle;

     private LoginForm owner;

     public ChangePw(LoginForm owner){
          super(owner, true);
          this.owner = owner;
          init();
          setDisplay();
          addListeners();
          showFrame();
     }
     private void init(){
          lblPw = new JLabel("���� ��й�ȣ");
          lblNewPw = new JLabel("������ ��й�ȣ");
          lblNewCheckPw = new JLabel("��й�ȣ Ȯ��");

          tfPw = new JPasswordField();
          tfPw.setPreferredSize(new Dimension(100, 20));
          tfNewPw = new JPasswordField();
          tfNewPw.setPreferredSize(new Dimension(100, 20));
          tfNewCheckPw = new JPasswordField();
          tfNewCheckPw.setPreferredSize(new Dimension(100, 20));

          btnChange = new JButton("����");
          btnCancle = new JButton("���");
           
     }
     private void setDisplay(){
          JPanel pnlLeft = new JPanel(new GridLayout(0, 1));
          JPanel pnlRight = new JPanel(new GridLayout(0, 1));
          JPanel pnlCenter = new JPanel(new GridLayout(1, 0));

          JPanel pnl1 = new JPanel();
          JPanel pnl2 = new JPanel();
          JPanel pnl3 = new JPanel();
          JPanel pnl4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
          JPanel pnl5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
          JPanel pnl6 = new JPanel(new FlowLayout(FlowLayout.LEFT));

          pnl1.add(tfPw);
          pnl2.add(tfNewPw);
          pnl3.add(tfNewCheckPw);
          pnl4.add(lblPw);

          pnl5.add(lblNewPw);
          pnl6.add(lblNewCheckPw);

          pnlLeft.add(pnl4);
          pnlLeft.add(pnl5);
          pnlLeft.add(pnl6);

          pnlRight.add(pnl1);
          pnlRight.add(pnl2);
          pnlRight.add(pnl3);

          pnlCenter.add(pnlLeft);
          pnlCenter.add(pnlRight);

          JPanel pnlSouth = new JPanel(new FlowLayout());
          pnlSouth.add(btnChange);
          pnlSouth.add(btnCancle);

          add(pnlCenter, BorderLayout.CENTER);
          add(pnlSouth, BorderLayout.SOUTH);
          pnlCenter.setBorder(new TitledBorder("��й�ȣ ����"));
     }
     private void addListeners(){
          btnChange.addActionListener(this);
          btnCancle.addActionListener(this);
          tfNewCheckPw.addActionListener(this);
     }
     @Override
     public void actionPerformed(ActionEvent e){
          Object src = e.getSource();
          if(src == btnChange || src == tfNewCheckPw){
               String currentPw = owner.getPw();
               if(currentPw.equals(tfPw.getText())){
                    String changePw = tfNewPw.getText();
                    String changePwCheck = tfNewCheckPw.getText();
                    if (!currentPw.equals(changePw)){
					     if (changePw.trim().length() != 0 
                              && changePwCheck.trim().length() != 0 && changePw != null
							     && changePwCheck != null){
                              if (changePw.equals(changePwCheck)){
                            	  	owner.setPw(changePw);
                                   JOptionPane.showMessageDialog(this, "��й�ȣ�� ���������� ����Ǿ����ϴ�");
                                   dispose();
                              }else{
                                   JOptionPane.showMessageDialog(this, "����� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
                              }
                         }else{
                              JOptionPane.showMessageDialog(this, "����� ��й�ȣ�� �Էµ��� �ʾҽ��ϴ�.");
                         }
                    }else{
                         JOptionPane.showMessageDialog(this, "����� ��й�ȣ�� ���� ��й�ȣ�� ���� �� �����ϴ�.");
                    }
               }else{
                    JOptionPane.showMessageDialog(this, "���� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
               }
          }else{
               dispose();
          }
     }

     private void showFrame(){
          setTitle("Change Password");
          setSize(250, 200);
          setLocationRelativeTo(owner);
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
          setVisible(true);
     }
}