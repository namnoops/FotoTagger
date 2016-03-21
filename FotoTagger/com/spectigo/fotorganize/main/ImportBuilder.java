package com.spectigo.fotorganize.main;

import com.spectigo.fotorganize.ImportData;
import com.spectigo.fotorganize.csv.ShopifyCSV;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ImportBuilder extends JFrame {
    private static final long serialVersionUID = 898881706700219754L;
    private ImportData data = new ImportData();
    private JButton jButton1;
    private JButton jButton2;
    private JProgressBar jProgressBar1;
    private JTextField jTextField1;

    public ImportBuilder() {
        this.setTitle("Import Builder");
        this.setLocation(400, 400);
        this.initComponents();
    }

    private void initComponents() {
        this.jTextField1 = new JTextField();
        this.jButton1 = new JButton();
        this.jButton2 = new JButton();
        this.jProgressBar1 = new JProgressBar();
        this.setDefaultCloseOperation(3);
        this.jTextField1.setEditable(false);
        this.jButton1.setText("...");
        this.jButton1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
            	ImportBuilder.this.jButton1ActionPerformed(evt);
            }
        });
        this.jButton2.setText(">>");
        this.jButton2.setEnabled(false);
        this.jButton2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
            	ImportBuilder.this.jButton2ActionPerformed(evt);
            }
        });
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jProgressBar1, -1, -1, 32767).addGroup(layout.createSequentialGroup().addComponent(this.jTextField1, -2, 275, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton1, -2, 44, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton2))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jTextField1, -2, -1, -2).addComponent(this.jButton1).addComponent(this.jButton2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jProgressBar1, -2, -1, -2).addContainerGap(-1, 32767)));
        this.pack();
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
        JFileChooser jfc = new JFileChooser();
        jfc.setCurrentDirectory(new File(String.valueOf(System.getProperty("user.home")) + File.separator + "Desktop"));
        jfc.setDialogTitle("Select a directory containing the photos");
        jfc.setFileSelectionMode(1);
        jfc.setAcceptAllFileFilterUsed(false);
        if (jfc.showOpenDialog(this) == 0) {
            File dir = jfc.getSelectedFile();
            try {
                this.data.initialize(dir, new ShopifyCSV(), false);
                this.jButton2.setEnabled(true);
                this.jProgressBar1.setMaximum(this.data.getNumberOfPhotos());
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Invalid directory", 0);
                this.jButton2.setEnabled(false);
            }
            this.jTextField1.setText(dir.getAbsolutePath());
        }
    }

    private void jButton2ActionPerformed(ActionEvent evt) {
        this.doWork();
    }

    public void doWork() {
        Runnable r = new Runnable(){

            @Override
            public void run() {
                int i = 0;
                while (i < ImportBuilder.this.data.getNumberOfPhotos()) {
                	ImportBuilder.this.data.handlePhoto(i);
                	ImportBuilder.this.jProgressBar1.setValue(i + 1);
                    ++i;
                }
            }
        };
        Thread t = new Thread(r);
        t.start();
    }

    public static void main(String[] args) {
        try {
            UIManager.LookAndFeelInfo[] arrlookAndFeelInfo = UIManager.getInstalledLookAndFeels();
            int n = arrlookAndFeelInfo.length;
            int n2 = 0;
            while (n2 < n) {
                UIManager.LookAndFeelInfo info = arrlookAndFeelInfo[n2];
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
                ++n2;
            }
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(ImportBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex) {
            Logger.getLogger(ImportBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex) {
            Logger.getLogger(ImportBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ImportBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        EventQueue.invokeLater(new Runnable(){

            @Override
            public void run() {
                new ImportBuilder().setVisible(true);
            }
        });
    }

}

