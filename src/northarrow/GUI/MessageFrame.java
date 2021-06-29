/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package northarrow.GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import keeptoo.Drag;

/**
 *
 * @author Richard
 */
public class MessageFrame extends javax.swing.JFrame
{

    /**
     * Creates new form MessageFrame
     */
    public MessageFrame()
    {
        initComponents();
        setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));//set background to tranparent for drop shadow effect
        GUIManager.addKeyBind(jpParent, "ENTER", closeEnter);
        this.setCursor(GUIManager.cursor());
    }

    //overloaded to get text from other classes
    public MessageFrame(String msg)
    {
        initComponents();
        setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));//set background to tranparent for drop shadow effect
        display.setText(msg);//display the text fom other classes
        GUIManager.addKeyBind(jpParent, "ENTER", closeEnter);// key kinding to close the window
        this.setCursor(GUIManager.cursor());
    }

    private Action closeEnter = new AbstractAction() //key binding action
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            dispose();//close the window
        }
    };

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jpParent = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        Close = new javax.swing.JLabel();
        Minimize = new javax.swing.JLabel();
        DragBar = new javax.swing.JLabel();
        display = new javax.swing.JLabel();
        backGround = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jpParent.setBackground(new java.awt.Color(242, 242, 242));
        jpParent.setOpaque(false);
        jpParent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        title.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setText("Message Box");
        title.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        jpParent.add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, -1, -1));

        Close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/northarrow/Images/Close.png"))); // NOI18N
        Close.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                CloseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                CloseMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                CloseMouseReleased(evt);
            }
        });
        jpParent.add(Close, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, -1, 32));

        Minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/northarrow/Images/minimize.png"))); // NOI18N
        Minimize.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                MinimizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                MinimizeMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                MinimizeMouseReleased(evt);
            }
        });
        jpParent.add(Minimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, -1, -1));

        DragBar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        DragBar.setForeground(new java.awt.Color(0, 102, 255));
        DragBar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/northarrow/Images/message bar.png"))); // NOI18N
        DragBar.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        DragBar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter()
        {
            public void mouseDragged(java.awt.event.MouseEvent evt)
            {
                DragBarMouseDragged(evt);
            }
        });
        DragBar.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mousePressed(java.awt.event.MouseEvent evt)
            {
                DragBarMousePressed(evt);
            }
        });
        jpParent.add(DragBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 40));

        display.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        display.setForeground(new java.awt.Color(0, 102, 255));
        display.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jpParent.add(display, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 280, 150));

        backGround.setIcon(new javax.swing.ImageIcon(getClass().getResource("/northarrow/Images/messagebg.png"))); // NOI18N
        jpParent.add(backGround, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -10, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpParent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpParent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
//hover effect starts
    private void MinimizeMouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_MinimizeMouseEntered
    {//GEN-HEADEREND:event_MinimizeMouseEntered
        Minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/northarrow/Images/minimize hover.png")));
    }//GEN-LAST:event_MinimizeMouseEntered

    private void MinimizeMouseExited(java.awt.event.MouseEvent evt)//GEN-FIRST:event_MinimizeMouseExited
    {//GEN-HEADEREND:event_MinimizeMouseExited
        Minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/northarrow/Images/minimize.png")));
    }//GEN-LAST:event_MinimizeMouseExited

    private void CloseMouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_CloseMouseEntered
    {//GEN-HEADEREND:event_CloseMouseEntered
        Close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/northarrow/Images/close hover.png")));
    }//GEN-LAST:event_CloseMouseEntered

    private void CloseMouseExited(java.awt.event.MouseEvent evt)//GEN-FIRST:event_CloseMouseExited
    {//GEN-HEADEREND:event_CloseMouseExited
        Close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/northarrow/Images/close.png")));
    }//GEN-LAST:event_CloseMouseExited
//hover effect ends
    private void DragBarMouseDragged(java.awt.event.MouseEvent evt)//GEN-FIRST:event_DragBarMouseDragged
    {//GEN-HEADEREND:event_DragBarMouseDragged
        new Drag(DragBar).moveWindow(evt);
        //drag function without default boaders,able to move thw window
    }//GEN-LAST:event_DragBarMouseDragged

    private void DragBarMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_DragBarMousePressed
    {//GEN-HEADEREND:event_DragBarMousePressed
        new Drag(DragBar).onPress(evt);
        //drag function without default boaders
    }//GEN-LAST:event_DragBarMousePressed

    private void CloseMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_CloseMouseReleased
    {//GEN-HEADEREND:event_CloseMouseReleased
        dispose();//close the window
    }//GEN-LAST:event_CloseMouseReleased

    private void MinimizeMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_MinimizeMouseReleased
    {//GEN-HEADEREND:event_MinimizeMouseReleased
        this.setState(LoginFrame.ICONIFIED);//minimize the window
    }//GEN-LAST:event_MinimizeMouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(MessageFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(MessageFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(MessageFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(MessageFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new MessageFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Close;
    private javax.swing.JLabel DragBar;
    private javax.swing.JLabel Minimize;
    private javax.swing.JLabel backGround;
    private javax.swing.JLabel display;
    private javax.swing.JPanel jpParent;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
