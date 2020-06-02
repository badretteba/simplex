/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplex;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.TextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author iss
 */
public class Tableau {

    Double[][] table;
    int ligne;
    int colonne;
    int VarEntrant;
    int VarSortant;
    int[] varBase;
    int[] varHorsBase;
    boolean init;
    JFrame f;

    public Tableau(Double[][] tableau, int VarEntrant, int VarSortant, boolean init) {
        this.table = tableau;
        this.ligne = tableau.length;
        this.colonne = tableau[0].length;
        this.VarEntrant = VarEntrant;
        this.VarSortant = VarSortant;
        this.init = init;
        varBase = new int[colonne - ligne];
        varHorsBase = new int[colonne - ligne - 1];
        f = new JFrame("Tableau Simplex");
        f.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        JPanel p2 = new JPanel();

        JLabel l1[] = new JLabel[colonne];
        l1[0] = new JLabel();
        l1[0].setText("Z");
        p2.add(l1[0]);
    

        for (int k = 1; k < colonne - 1; k++) {

            l1[k] = new JLabel();
            l1[k].setText("X" + k);
            int nbr0 = 0, nbr1 = 0;
            for (int i = 0; i < ligne; i++) {
                if (this.table[i][k] == 0) {
                    nbr0++;
                }
                if (this.table[i][k] == 1) {
                    nbr1++;
                }
                
            }
            if (nbr0 + nbr1 == ligne) {
                l1[k].setForeground(Color.green);
            } else {
                l1[k].setForeground(Color.red);
            }
           
            p2.add(l1[k]);

        }
        JLabel l = new JLabel();
        l.setText("=");
        p2.add(l);

        JLabel l2[][] = new JLabel[this.ligne][this.colonne];
        for (int i = 0; i < this.ligne; i++) {
           
            for (int j = 0; j < this.colonne; j++) {
                
                l2[i][j] = new JLabel();
                
                l2[i][j].setText(table[i][j].toString());
                
               int [] a=getPivot(table);
                 if (j==a[1] & i==a[0]){
                     l2[i][j].setForeground(Color.BLUE);
                 }

                p2.add(l2[i][j]);
            }

        }

        p2.setLayout(new GridLayout(this.ligne + 1, this.colonne));

        if(!isPositiv()){
            JButton b1 = new JButton("Suivant");
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });
        JPanel panel=new JPanel();
        panel.add(b1);
        f.add(panel, BorderLayout.SOUTH);
        }
        JPanel p1 = new JPanel();
        
        f.add(p2, BorderLayout.CENTER);

        f.setSize(681, 366);
        f.setVisible(true);

    }

    private int[] getPivot(Double[][] tableau) {
        Double Min = 99990.;
        int min = 0;
        int min2 = 0;
        int[] ij = new int[2];
        int i = 1;
        for (i = 1; i < tableau[0].length - 1; i++) {

            if (tableau[0][i] < Min) {
                Min = tableau[0][i];
                min = i;
            };

        }

        for (i = 1; i < tableau.length - 1; i++) {

            if (tableau[i][tableau[0].length - 1] / tableau[i][min] < tableau[i + 1][tableau[0].length - 1] / tableau[i + 1][min]) {
                min2 = i;
            } else if (tableau[i][tableau[0].length - 1] / tableau[i][min] > tableau[i + 1][tableau[0].length - 1] / tableau[i + 1][min]) {
                min2 = i + 1;
            }
           
        }

        ij[0] = min2;
        ij[1] = min;

        return ij;
    }

    private boolean isPositiv() {

        for (int i = 0; i < this.table[0].length; i++) {
            if (this.table[0][i] < 0) {
                return false;
            }

        }

        return true;
    }

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {
        
            int[] a = new int[2];
            Double[][] matrice = new Double[this.table.length][this.table[0].length];

            a = getPivot(this.table);
            
            for (int i = 0; i < matrice[0].length; i++) {
               
                matrice[a[0]][i] = this.table[a[0]][i] / this.table[a[0]][a[1]];

            }
          
            for (int i = 0; i < matrice.length; i++) {
                for (int j = 0; j < matrice[0].length; j++) {
                    if (i != a[0]) {
                        matrice[i][j] = -this.table[i][a[1]] * matrice[a[0]][j] + this.table[i][j];

                    }
                }

            }

           
            this.f.dispose();

            Tableau tableau2 = new Tableau(matrice, a[1], a[0], false);
    }
}
