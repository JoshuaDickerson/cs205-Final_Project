/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;


public class SwapInstructionDialog extends javax.swing.JDialog {

    /**
     * A return status code - returned if Cancel button has been pressed
     */
    public static final int RET_CANCEL = 0;
    /**
     * A return status code - returned if OK button has been pressed
     */
    public static final int RET_OK = 1;

    /**
     * Creates new form SwapInstructionDialog
     */
    public SwapInstructionDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        // Close the dialog when Esc is pressed
        String cancelName = "cancel";
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), cancelName);
        ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                doClose(RET_CANCEL);
            }
        });
    }

    /**
     * @return the return status of this dialog - one of RET_OK or RET_CANCEL
     */
    public int getReturnStatus() {
        return returnStatus;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        okButtonSwap = new javax.swing.JButton();
        scrollSwap = new javax.swing.JScrollPane();
        txtSwap = new javax.swing.JTextArea();
        labelSwapTutorial = new javax.swing.JLabel();
        scrollPowerCard1 = new javax.swing.JScrollPane();
        txtPowerCard1 = new javax.swing.JTextArea();
        labelPictureSwap1 = new javax.swing.JLabel();
        labelPictureSwap2 = new javax.swing.JLabel();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        okButtonSwap.setText("OK");
        okButtonSwap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonSwapActionPerformed(evt);
            }
        });

        txtSwap.setEditable(false);
        txtSwap.setColumns(20);
        txtSwap.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtSwap.setLineWrap(true);
        txtSwap.setRows(5);
        txtSwap.setText("You may trade one of your cards with an opponent's. Neither player may look at the card  values being swapped.");
        txtSwap.setWrapStyleWord(true);
        scrollSwap.setViewportView(txtSwap);

        labelSwapTutorial.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        labelSwapTutorial.setText("You've Drawn a Swap Card!");

        txtPowerCard1.setEditable(false);
        txtPowerCard1.setColumns(20);
        txtPowerCard1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtPowerCard1.setLineWrap(true);
        txtPowerCard1.setRows(5);
        txtPowerCard1.setText("Power cards have no value and if they are in a player's hand at the end of the round. They must be swapped for the top card of the deck.");
        txtPowerCard1.setWrapStyleWord(true);
        scrollPowerCard1.setViewportView(txtPowerCard1);

        labelPictureSwap1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication1/Cards/12.png"))); // NOI18N

        labelPictureSwap2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication1/Cards/12.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(labelSwapTutorial))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(labelPictureSwap2)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollSwap, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrollPowerCard1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(labelPictureSwap1))
            .addGroup(layout.createSequentialGroup()
                .addGap(260, 260, 260)
                .addComponent(okButtonSwap, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(labelSwapTutorial)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelPictureSwap2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scrollSwap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(scrollPowerCard1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelPictureSwap1))
                .addGap(15, 15, 15)
                .addComponent(okButtonSwap, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getRootPane().setDefaultButton(okButtonSwap);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonSwapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonSwapActionPerformed
        doClose(RET_OK);
    }//GEN-LAST:event_okButtonSwapActionPerformed
    
    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        doClose(RET_CANCEL);
    }//GEN-LAST:event_closeDialog
    
    private void doClose(int retStatus) {
        returnStatus = retStatus;
        setVisible(false);
        dispose();
    }

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
            java.util.logging.Logger.getLogger(SwapInstructionDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SwapInstructionDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SwapInstructionDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SwapInstructionDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SwapInstructionDialog dialog = new SwapInstructionDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelPictureSwap1;
    private javax.swing.JLabel labelPictureSwap2;
    private javax.swing.JLabel labelSwapTutorial;
    private javax.swing.JButton okButtonSwap;
    private javax.swing.JScrollPane scrollPowerCard1;
    private javax.swing.JScrollPane scrollSwap;
    private javax.swing.JTextArea txtPowerCard1;
    private javax.swing.JTextArea txtSwap;
    // End of variables declaration//GEN-END:variables
    private int returnStatus = RET_CANCEL;
}
