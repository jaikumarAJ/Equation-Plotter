/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equationPlotter.PlottingFrame;

/**
 *
 * @author dell
 */

import equationPlotter.InputFrame.InputFrame;
import java.awt.Color;
import javax.swing.*;

public class GraphFrame extends javax.swing.JFrame
{
    public GraphFrame(String[] funct, Double[] constants, Color[] colors)
    {
        GraphFrame.functions = new String[3];
        GraphFrame.constants = new Double[12];
        GraphFrame.colors = new Color[3];
        
        System.arraycopy(funct, 0, functions, 0, funct.length);
        System.arraycopy(constants, 0, GraphFrame.constants, 0, constants.length);
        System.arraycopy(colors, 0, GraphFrame.colors, 0, colors.length);
    
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    private void initComponents()
    {
        setTitle("2D GRAPH");
        setSize(900,700);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        add(new GraphPlotter(GraphFrame.functions, GraphFrame.constants, GraphFrame.colors));
    }   
    
     public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            javax.swing.UIManager.LookAndFeelInfo[] installedLookAndFeels=javax.swing.UIManager.getInstalledLookAndFeels();
            for (UIManager.LookAndFeelInfo installedLookAndFeel : installedLookAndFeels) {
                if ("Nimbus".equals(installedLookAndFeel.getName())) {
                    javax.swing.UIManager.setLookAndFeel(installedLookAndFeel.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InputFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InputFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InputFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InputFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GraphFrame(functions, constants, colors).setVisible(true);
            }
        });
    }

  // Variables declaration - do not modify                     
   // private javax.swing.JButton zoomIn_Button;
   // private javax.swing.JButton zoomOut_Button;
    //private javax.swing.JPanel graphPanel;
    private static String []functions;
    private static Double []constants;
    private static Color []colors;
    // End of variables declaration   
}