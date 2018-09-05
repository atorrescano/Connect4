/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connect4.java.ui;

import javax.swing.JDialog;
import javax.swing.JTextArea;

/**
 *
 * @author Aaron
 */
public class Connect4JFrame extends javax.swing.JFrame {

    /**
     * Creates new form Connect4JFrame
     */
    public Connect4JFrame() {
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

        mainMenu = new javax.swing.JMenuBar();
        gameMenu = new javax.swing.JMenu();
        newGame2Player = new javax.swing.JMenuItem();
        newGame1Player = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        exitMenuItem = new javax.swing.JMenuItem();
        aboutMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        gameMenu.setMnemonic('G');
        gameMenu.setText("Game");
        gameMenu.setToolTipText("");

        newGame2Player.setMnemonic('2');
        newGame2Player.setText("New 2 player");
        newGame2Player.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGame2PlayerActionPerformed(evt);
            }
        });
        gameMenu.add(newGame2Player);

        newGame1Player.setMnemonic('1');
        newGame1Player.setText("New 1 player");
        gameMenu.add(newGame1Player);
        gameMenu.add(jSeparator1);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        gameMenu.add(exitMenuItem);

        mainMenu.add(gameMenu);

        aboutMenu.setMnemonic('A');
        aboutMenu.setText("About");
        aboutMenu.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                aboutMenuMenuSelected(evt);
            }
        });
        mainMenu.add(aboutMenu);

        setJMenuBar(mainMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newGame2PlayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGame2PlayerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newGame2PlayerActionPerformed

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void aboutMenuMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_aboutMenuMenuSelected
        // TODO add your handling code here:
        JDialog aboutDialog = new JDialog(this, "About Connect 4", true);
                JTextArea aboutText = new JTextArea("Java Again!\n\nGame of Connect 4\nCoded by:\natorrescano@gmail.com\nguadalupe.chilton@gmail.com");
                aboutDialog.add(aboutText);
                aboutDialog.pack();
                aboutDialog.setLocationRelativeTo(this);
                aboutDialog.setVisible(true);
    }//GEN-LAST:event_aboutMenuMenuSelected

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
            java.util.logging.Logger.getLogger(Connect4JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Connect4JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Connect4JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Connect4JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Connect4JFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu aboutMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu gameMenu;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JMenuBar mainMenu;
    private javax.swing.JMenuItem newGame1Player;
    private javax.swing.JMenuItem newGame2Player;
    // End of variables declaration//GEN-END:variables
}
