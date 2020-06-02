/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplex;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.ScrollPane;
import java.awt.TextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author iss
 */
public class eq {
     TextField[] t;
     TextField[] t22;
     TextField[] t2;
     TextField[] t3;
      JFrame f ;
    eq(int i, int j) {
        JLabel[] l2 = new JLabel[i];
        JLabel[] l3=new JLabel[j];
        JLabel[] l4=new JLabel[j];
        t22=new TextField[i*j]; 
        t2= new TextField[i];
        t3=new TextField[j];
         f = new JFrame("Coéfficient du PL");
        f.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        ScrollPane sp1 = new ScrollPane();
        ScrollPane sp2 = new ScrollPane();
        JPanel p1 = new JPanel();
        JPanel p2[]=new JPanel[j];
        t = new TextField[i];
        JLabel[] l = new JLabel[i];
        JLabel fct = new JLabel("Z =");
        p1.add(fct);
        for (int k = 0; k < i; k++) {
            t[k] = new TextField();
            t[k].setText("");
           
            l[k] = new JLabel();
            l[k].setText("X" + (k+1));
            p1.add(t[k]);
            p1.add(l[k]);
          }
          
        sp1.add(p1);
       
          JPanel p3 = new JPanel();
          p3.setLayout(new GridLayout(j, i));
          try{
              int p=0;
       for (int z=0;z<j;z++){
           
           l3[z] = new JLabel();
            l3[z].setText("C" + (z+1)+" : ");
           p2[z] =new JPanel();
            p2[z].add(l3[z]);
        for (int k = 0; k < i ; k++) {
            t2[k] = new TextField();
            t2[k].setText("");
           
            l2[k] = new JLabel();
            l2[k].setText("X" + (k+1));
            t22[p]=t2[k];
            p++;
            p2[z].add(t2[k]);
            p2[z].add(l2[k]);
            }
//            t2[i] = new TextField();
//            t2[i].setText("");
//            l2[i] = new Label();
//            l2[i].setText("X" + (i+z+1));
//            p2[z].add(t2[i]);
//            p2[z].add(l2[i]);
            
            l4[z] = new JLabel();
            l4[z].setText(" ≤ ");
            p2[z].add(l4[z]);
           
             t3[z] = new TextField();
            t3[z].setText("");
            
             p2[z].add(t3[z]);
         p3.add(p2[z]);
       }
       JButton b1=new JButton("Valider"); 
       JPanel p4 = new JPanel();
       p4.add(b1);
       
       sp2.add(p3);
        f.add(sp1, BorderLayout.NORTH);
        f.add(sp2, BorderLayout.CENTER);
        f.add(p4,BorderLayout.SOUTH);
        f.setSize(681, 366);
        
         
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });
        f.setVisible(true);
          }
          catch(NullPointerException e){
              e.printStackTrace();
          }
    }
 private void b1ActionPerformed(java.awt.event.ActionEvent evt) { 
     Double[] tab1=new Double[t.length];
     for (int i=0;i<t.length;i++){
     tab1[i]=Double.parseDouble(t[i].getText());
    
             }
     
     Double[] tab2=new Double[t22.length];
     
     for (int i=0;i<t22.length;i++){
     tab2[i]=Double.parseDouble(t22[i].getText());
   
             }
     
      Double[] tab3=new Double[t3.length];
     for (int i=0;i<t3.length;i++){
     tab3[i]=Double.parseDouble(t3[i].getText());
     
             }
      
      
     Double[][] tab4=new Double[t3.length+1][t3.length];
     int k=1;
     for(int i=0;i<t3.length+1;i++){
         
         for(int j=0;j<(t3.length);j++){
             
             if (k==t3.length+1) {
                 tab4[i][j]=1.; 
                k=1;}
             else {
               tab4[i][j]=0.;
                k++;}
            
         }
          
 }
     
    Double[][] matrice= new Double[t3.length+1][t.length+t3.length+2];
     matrice[0][0]=1.;
     
    for(int i=1;i<=t3.length;i++){
        matrice[i][0]=0.;
       
      }
   
    for(int i=1;i<=t.length;i++){
        matrice[0][i]=-tab1[i-1];
        
      }
    matrice[0][t.length+t3.length+1]=0.;
    
    for(int i=1;i<t3.length+1;i++){
        matrice[i][t.length+t3.length+1]=tab3[i-1];
         
      }
    
    int s=0;
    for (int i=1;i<t3.length+1;i++){
        for (int j=1;j<t.length+1;j++){
         matrice[i][j]=tab2[s];
         s++;
        }
    }
    for (int i=0;i<t3.length+1;i++){
        for (int j=t.length+1;j<t.length+1+t3.length;j++){
         matrice[i][j]=tab4[i][j-(t.length+1)];
        }
    }
    
   for (int i=0;i<t3.length+1;i++){
        for (int j=0;j<t.length+t3.length+2;j++){
                 
        }
           
        }
   int varBase=t.length;
   int varHorsBase=t3.length;
        Tableau tableau = new Tableau(matrice,varBase,varHorsBase,true);
       
       f.dispose();
   } 
}
