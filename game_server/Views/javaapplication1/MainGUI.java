/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import javax.swing.*;
import java.awt.*;


public class MainGUI extends javax.swing.JFrame {

    /**
     * Creates new form MainGUI
     */
    public MainGUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonDraw = new javax.swing.JButton();
        Deck = new javax.swing.JLabel();
        PlayerCard1 = new javax.swing.JLabel();
        PlayerCard2 = new javax.swing.JLabel();
        PlayerCard4 = new javax.swing.JLabel();
        PlayerCard3 = new javax.swing.JLabel();
        Player2Card1 = new javax.swing.JLabel();
        Player2Card2 = new javax.swing.JLabel();
        Player2Card3 = new javax.swing.JLabel();
        labelDiscard = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textGameLog = new javax.swing.JTextArea();
        buttonDiscard = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        buttonPeek = new javax.swing.JButton();
        Player2Card4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonDraw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication1/Cards/13.png"))); // NOI18N
        buttonDraw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDrawActionPerformed(evt);
            }
        });
        getContentPane().add(buttonDraw, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 250, 137, 177));

        Deck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication1/Deck/13_1.png"))); // NOI18N
        getContentPane().add(Deck, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        PlayerCard1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication1/Cards/13.png"))); // NOI18N
        getContentPane().add(PlayerCard1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 560, -1, 168));

        PlayerCard2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication1/Cards/13.png"))); // NOI18N
        getContentPane().add(PlayerCard2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 560, 140, 168));

        PlayerCard4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication1/Cards/13.png"))); // NOI18N
        getContentPane().add(PlayerCard4, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 560, 140, 168));

        PlayerCard3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication1/Cards/13.png"))); // NOI18N
        getContentPane().add(PlayerCard3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 560, 140, 168));

        Player2Card1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication1/Cards/13.png"))); // NOI18N
        getContentPane().add(Player2Card1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 136, 168));

        Player2Card2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication1/Cards/13.png"))); // NOI18N
        getContentPane().add(Player2Card2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 140, 168));

        Player2Card3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication1/Cards/13.png"))); // NOI18N
        getContentPane().add(Player2Card3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 140, 168));

        labelDiscard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication1/Cards/4.png"))); // NOI18N
        getContentPane().add(labelDiscard, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 250, 140, 170));

        textGameLog.setColumns(20);
        textGameLog.setRows(5);
        jScrollPane1.setViewportView(textGameLog);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 226, 292));

        buttonDiscard.setText("Discard");
        buttonDiscard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDiscardActionPerformed(evt);
            }
        });
        getContentPane().add(buttonDiscard, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 430, 90, 40));

        jLabel1.setText("Click to Draw from Deck");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 430, -1, -1));

        jLabel3.setText("Card 4");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 540, -1, -1));

        jLabel4.setText("Card 1");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 540, -1, -1));

        jLabel5.setText("Card 2");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 540, -1, -1));

        jLabel6.setText("Card 3");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 540, -1, -1));

        jLabel7.setText("Card 1");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, -1, -1));

        jLabel8.setText("Card 2");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, -1, -1));

        jLabel9.setText("Card 3");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 190, -1, -1));

        jLabel10.setText("Card 4");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 190, -1, -1));

        buttonPeek.setText("Peek");
        buttonPeek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPeekActionPerformed(evt);
            }
        });
        getContentPane().add(buttonPeek, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 490, 75, -1));

        Player2Card4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication1/Cards/13.png"))); // NOI18N
        getContentPane().add(Player2Card4, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, 140, 168));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonDrawActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDrawActionPerformed
        // This section would then draw a card from the deck constructor
        /*
         * Draw from the deck here;
         * Card drawnCard = Deck.Draw();
         * labelDiscard.setIcon(new Javax.swing.ImageIcon(getClass().getResource(drawnCard.Image)));
         */
    }//GEN-LAST:event_buttonDrawActionPerformed

    private void buttonDiscardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDiscardActionPerformed
        // This is when a card is discarded to the pile and the label changes image to display the discarded card
        /*
         * Power cards should be discarded automatically after use, so just change the label to whichever card it was
         * If there's an object for current card or something like that, then the label would change to its image
         * labelDiscard.setIcon(new Javax.swing.ImageIcon(getClass().getResource(currentCard.Image)));
         */
    }//GEN-LAST:event_buttonDiscardActionPerformed

    private void buttonPeekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPeekActionPerformed
        // For peeking during tutorial mode
        /*
         * Each of the labels are called Player1Card1 and so forth, so each would then be set to their actual value of the card and changed to whichever image
         * Example:
         * Player1Card1.setIcon(new Javax.swing.ImageIcon(getClass().getResource(Player1Card1.Image)));
         */
    }//GEN-LAST:event_buttonPeekActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainGUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Deck;
    private javax.swing.JLabel Player2Card1;
    private javax.swing.JLabel Player2Card2;
    private javax.swing.JLabel Player2Card3;
    private javax.swing.JLabel Player2Card4;
    private javax.swing.JLabel PlayerCard1;
    private javax.swing.JLabel PlayerCard2;
    private javax.swing.JLabel PlayerCard3;
    private javax.swing.JLabel PlayerCard4;
    private javax.swing.JButton buttonDiscard;
    private javax.swing.JButton buttonDraw;
    private javax.swing.JButton buttonPeek;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelDiscard;
    private javax.swing.JTextArea textGameLog;
    // End of variables declaration//GEN-END:variables
}
