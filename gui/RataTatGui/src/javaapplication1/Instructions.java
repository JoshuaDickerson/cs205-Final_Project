/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author Tichi
 */
public class Instructions extends javax.swing.JFrame {

    /**
     * Creates new form Instructions
     */
    public Instructions() {
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        buttonPlay = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        textPlayerNumber = new javax.swing.JTextField();
        textDifficulty = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication1/RataTat/rat a tat.jpg"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("Instructions:");

        buttonPlay.setText("Play");
        buttonPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPlayActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Number of Players:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Difficulty: ");

        textPlayerNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textPlayerNumberActionPerformed(evt);
            }
        });

        textDifficulty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textDifficultyActionPerformed(evt);
            }
        });

        jLabel5.setText("For More Game Rules See: http://www.gamewright.com/gamewright/pdfs/Rules/Rat-a-TatCat-RULES.pdf");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("The goal is to have the lowest score at the end of the game.\n\nChoose one player to be the dealer, and a scorekeeper. The scorekeeper will record each player’s score at the end of each round of play.\n\nShuffle the deck. The player to the left of the dealer cuts the cards. \nThe dealer then deals four cards, one at a time and face down, to each player.\nThe remaining cards are placed face down, in the middle of the table, as the\ndraw pile. The top card of the draw pile is turned over to start the discard\npile. If that card is a Power card, it is placed back in the deck and another\ncard is turned over.\n\nWithout looking at his cards, each player places his or her four cards face down in a line on the table in front of them.\n\nDuring the game, players will always have their four cards face down on the\ntable. To begin the game, players peek at their two outer cards once, then\nturn them face down again. Each player now knows the point values of two of\nhis four cards and needs to remember them during the game.\nIf either of the outer cards are Power cards, the player keeps them, but they\ndo not have their powers (described below). Power cards only have their\npowers when they are drawn from the top of the draw pile.  \nThe player to the left of the dealer has the first turn, and play continues in a\nclockwise direction.\n\nFor each turn, a player may:\n\n1. Draw the top card from the discard pile. This card MUST be used to\nreplace one of her cards. The card replaced is then discarded, face up, to the\ndiscard pile.\n\n2. Draw the top card from the draw pile. A player may use it to: \n\t1. Replace one of her cards\n\t2. Peek, Swap, or Draw 2 if it is a Power card (see below)\n\t3. Discard it face up to the discard pile\n\nA player’s choice is based on remembering the values of his four face down\ncards. Keep track of what you have so you won’t accidentally replace your\nlow point cards with high point cards.\nDuring the game, when the draw pile is used up, shuffle the discard pile and\nturn it over for a new draw pile.\n\nPower cards only have their powers when you draw them from the draw pile. If a Power card is dealt to you at the beginning of the game, it cannot be used. Because Power cards have no point value, if one of them is among your cards at the end of the game, you must replace it with a card drawn from the draw pile. If a Power card is discarded, it may not be used again by any player.\n\nThere are three kinds of Power cards:\n1. Peek\n\tWhen you draw a Peek card, show it and then peek at any\n\tone of your cards. Now you will know what you have, or you\n\tcan refresh your memory if you have forgotten what you have.\n\tYour turn is over and you discard the Peek card.\n2. Swap\n\tWhen you draw a Swap card, show the Swap card and \n\tdiscard it. You may now switch any one of your cards with\n\tany card of another player (swapping is optional). Neither player \t\tcan look at either of the cards being swapped. After the swap \t\tyour turn is over.\n3. Draw 2\n\tWhen you draw a Draw 2 card, show the card and then you\n\tmay take two more turns. First you draw the next card from the\n\tdraw pile. You must decide whether to use this card and forfeit\n\tthe second turn OR discard this card and draw a second card.\n\tThis second card may be used or discarded. Your turn is then\n\tover. If either of the cards drawn are another Draw 2 card, the\n\tDraw 2 sequence starts again.\n\nEnding the Round\n\nWhen a player thinks he has the lowest score and can win the round, he or she may end the round by knocking on the table and saying “rat-a-tat cat” at\nthe end of their turn. Once they knock, every other player has one more turn.\nEach player then turns over their cards. Players replace all Power cards by\ndrawing from the draw pile. If another Power card is drawn, the player\ndraws again.\n\nScoring\n\nPlayers add the point values of their four cards. This is each player’s score\nfor the round. The scorekeeper records each player’s score. Remember that\nplayers are trying to get as low a score as possible.\n\nNext Rounds\n\nAll cards are collected and passed to the player to the left of the dealer who\nreshuffles and deals for the next round.\n\nEnding the Game\n\nThe player with the lowest total score at the end of the game is the winner.\n\nA game may be played three ways:\n1. Play for a certain number of rounds.\n2. Play for a specific length of time.\n3. Play to stay in the game and not reach 100 points. When a player \nreaches 100 points, he is out of the game. The last player in the game is \nthe winner. Players may also choose to play to 200, or any other number \nof points.");
        jTextArea1.setWrapStyleWord(true);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication1/Player1Card1/3.png"))); // NOI18N

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication1/Player1Card1/9.png"))); // NOI18N

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication1/Cards/3.png"))); // NOI18N

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication1/Cards/9.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel7))
                                    .addComponent(jLabel1)))
                            .addComponent(jLabel9))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(172, 172, 172)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textPlayerNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textDifficulty, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(buttonPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(198, 198, 198)
                        .addComponent(jLabel6)
                        .addGap(37, 37, 37)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textPlayerNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textDifficulty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(buttonPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPlayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonPlayActionPerformed

    private void textDifficultyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textDifficultyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textDifficultyActionPerformed

    private void textPlayerNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textPlayerNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textPlayerNumberActionPerformed

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
            java.util.logging.Logger.getLogger(Instructions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Instructions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Instructions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Instructions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Instructions().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonPlay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField textDifficulty;
    private javax.swing.JTextField textPlayerNumber;
    // End of variables declaration//GEN-END:variables
}
