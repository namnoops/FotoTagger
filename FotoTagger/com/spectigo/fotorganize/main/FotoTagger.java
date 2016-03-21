/*
 * Decompiled with CFR 0_110.
 */
package com.spectigo.fotorganize.main;

import com.spectigo.fotorganize.Data;
import com.spectigo.fotorganize.Photo;
import com.spectigo.fotorganize.ScalablePane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.accessibility.AccessibleContext;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class FotoTagger
extends JFrame {
    private static final long serialVersionUID = 2189780806323701062L;
    Data data = new Data();
    private JButton applyButton;
    private JTextField avgPaceField;
    private JLabel avgPaceLabel;
    private JTextField currentBatchCompField;
    private JTextField currentBatchField;
    private JLabel currentBatchLabel;
    private JProgressBar currentBatchPB;
    private JTextField currentPaceField;
    private JLabel currentPaceLabel;
    private JButton deleteButton;
    private JLabel descriptionLabel;
    private JTextArea descriptionText;
    private JMenu fileMenu;
    private JTextField fileNameField;
    private JLabel fileNameLabel;
    private JMenuBar jMenuBar1;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JMenuItem openLocationMI;
    private JLabel perHour1Label;
    private JLabel perHour2Label;
    private JPanel photoPanel;
    private JTextField photographerField;
    private JLabel photographerLabel;
    private JTextField priceField;
    private JLabel priceLabel;
    private JTextField prodCatField;
    private JLabel prodCatLabel;
    private JTextField prodIndexField;
    private JTextField prodNameField;
    private JLabel prodNameLabel;
    private JLabel slash1Label;
    private JLabel slash2Label;
    private JTextField tagsField;
    private JPanel tagsPanel;
    private JTextField totalImagesCompField;
    private JTextField totalImagesField;
    private JLabel totalImagesLabel;
    private JProgressBar totalImagesPB;
    private JButton undoButton;

    public FotoTagger() {
        this.initComponents();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        this.photoPanel = new JPanel();
        this.jPanel1 = new JPanel();
        this.fileNameLabel = new JLabel();
        this.fileNameField = new JTextField();
        this.prodNameLabel = new JLabel();
        this.prodNameField = new JTextField();
        this.prodIndexField = new JTextField();
        this.prodCatLabel = new JLabel();
        this.prodCatField = new JTextField();
        this.photographerLabel = new JLabel();
        this.photographerField = new JTextField();
        this.priceLabel = new JLabel();
        this.priceField = new JTextField();
        this.tagsPanel = new JPanel();
        this.tagsField = new JTextField();
        this.undoButton = new JButton();
        this.deleteButton = new JButton();
        this.applyButton = new JButton();
        this.totalImagesLabel = new JLabel();
        this.totalImagesCompField = new JTextField();
        this.slash1Label = new JLabel();
        this.totalImagesField = new JTextField();
        this.totalImagesPB = new JProgressBar();
        this.currentBatchLabel = new JLabel();
        this.currentBatchCompField = new JTextField();
        this.slash2Label = new JLabel();
        this.currentBatchField = new JTextField();
        this.currentBatchPB = new JProgressBar();
        this.currentPaceLabel = new JLabel();
        this.currentPaceField = new JTextField();
        this.perHour1Label = new JLabel();
        this.avgPaceLabel = new JLabel();
        this.avgPaceField = new JTextField();
        this.perHour2Label = new JLabel();
        this.descriptionLabel = new JLabel();
        this.jScrollPane1 = new JScrollPane();
        this.descriptionText = new JTextArea();
        this.jMenuBar1 = new JMenuBar();
        this.fileMenu = new JMenu();
        this.openLocationMI = new JMenuItem();
        this.setDefaultCloseOperation(3);
        this.setResizable(false);
        this.photoPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
        this.photoPanel.setMinimumSize(new Dimension(666, 660));
        this.photoPanel.setPreferredSize(new Dimension(666, 660));
        this.photoPanel.setLayout(new BorderLayout());
        this.jPanel1.setBackground(new Color(120, 120, 120));
        this.jPanel1.setForeground(new Color(120, 120, 120));
        GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 660, 32767));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 654, 32767));
        this.photoPanel.add((Component)this.jPanel1, "Center");
        this.fileNameLabel.setText("File name:");
        this.fileNameField.setEditable(false);
        this.prodNameLabel.setText("Product name:");
        this.prodNameField.setText("HCM2015");
        this.prodIndexField.setText("10001");
        this.prodCatLabel.setText("Product category:");
        this.prodCatField.setText("Helsinki City Marathon 2015");
        this.photographerLabel.setText("Photographer:");
        this.priceLabel.setText("Price:");
        this.tagsPanel.setBorder(BorderFactory.createTitledBorder("Tags"));
        GroupLayout tagsPanelLayout = new GroupLayout(this.tagsPanel);
        this.tagsPanel.setLayout(tagsPanelLayout);
        tagsPanelLayout.setHorizontalGroup(tagsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(tagsPanelLayout.createSequentialGroup().addContainerGap().addComponent(this.tagsField).addContainerGap()));
        tagsPanelLayout.setVerticalGroup(tagsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(tagsPanelLayout.createSequentialGroup().addContainerGap().addComponent(this.tagsField, -2, -1, -2).addContainerGap(-1, 32767)));
        this.undoButton.setText("<<");
        this.undoButton.setEnabled(false);
        this.undoButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                FotoTagger.this.undoButtonActionPerformed(evt);
            }
        });
        this.deleteButton.setText("X");
        this.deleteButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                FotoTagger.this.deleteButtonActionPerformed(evt);
            }
        });
        this.applyButton.setText(">>");
        this.applyButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                FotoTagger.this.applyButtonActionPerformed(evt);
            }
        });
        this.tagsField.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                FotoTagger.this.tagsFieldActionPerformed(evt);
            }
        });
        this.tagsField.addKeyListener(new KeyAdapter(){

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                JTextField parent = (JTextField)e.getComponent();
                if (e.getKeyCode() == 88) {
                    FotoTagger.this.finalizePhoto(false);
                    e.consume();
                } else if (e.getKeyCode() == 85) {
                    if (FotoTagger.this.undoButton.isEnabled()) {
                        FotoTagger.this.undoPhoto();
                    }
                    e.consume();
                } else if (e.getKeyCode() == 68) {
                    parent.selectAll();
                }
            }
        });
        this.totalImagesLabel.setText("Total images:");
        this.totalImagesCompField.setEditable(false);
        this.slash1Label.setText("/");
        this.totalImagesField.setEditable(false);
        this.currentBatchLabel.setText("Current batch:");
        this.currentBatchCompField.setEditable(false);
        this.slash2Label.setText("/");
        this.currentBatchField.setEditable(false);
        this.currentPaceLabel.setText("Current pace:");
        this.currentPaceField.setEditable(false);
        this.perHour1Label.setText("/ hr");
        this.avgPaceLabel.setText("Avg. pace:");
        this.avgPaceField.setEditable(false);
        this.perHour2Label.setText("/ hr");
        this.descriptionLabel.setText("Description:");
        this.descriptionText.setColumns(20);
        this.descriptionText.setRows(5);
        this.jScrollPane1.setViewportView(this.descriptionText);
        this.fileMenu.setText("File");
        this.openLocationMI.setText("Open location");
        this.openLocationMI.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                FotoTagger.this.openLocationMIActionPerformed(evt);
            }
        });
        this.fileMenu.add(this.openLocationMI);
        this.jMenuBar1.add(this.fileMenu);
        this.setJMenuBar(this.jMenuBar1);
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.photoPanel, -2, -1, -2).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.photographerLabel).addComponent(this.priceLabel)).addGap(27, 27, 27).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.photographerField).addGroup(layout.createSequentialGroup().addComponent(this.priceField, -2, 60, -2).addGap(0, 0, 32767)))).addComponent(this.tagsPanel, -1, -1, 32767).addGroup(layout.createSequentialGroup().addGap(0, 0, 32767).addComponent(this.undoButton).addGap(18, 18, 18).addComponent(this.deleteButton).addGap(18, 18, 18).addComponent(this.applyButton)).addComponent(this.totalImagesPB, -1, -1, 32767).addComponent(this.currentBatchPB, -1, -1, 32767).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.prodCatLabel).addComponent(this.descriptionLabel)).addGap(10, 10, 10).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.prodCatField).addComponent(this.jScrollPane1))).addGroup(layout.createSequentialGroup().addComponent(this.prodNameLabel).addGap(27, 27, 27).addComponent(this.prodNameField, -2, 120, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.prodIndexField, -1, 65, 32767)).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.currentBatchLabel).addGap(18, 18, 18).addComponent(this.currentBatchCompField, -2, 60, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.slash2Label).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.currentBatchField, -2, 60, -2)).addGroup(layout.createSequentialGroup().addComponent(this.totalImagesLabel).addGap(26, 26, 26).addComponent(this.totalImagesCompField, -2, 60, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.slash1Label).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.totalImagesField, -2, 60, -2)).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.currentPaceLabel).addComponent(this.avgPaceLabel)).addGap(22, 22, 22).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.avgPaceField, -2, 60, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.perHour2Label)).addGroup(layout.createSequentialGroup().addComponent(this.currentPaceField, -2, 60, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.perHour1Label))))).addGap(0, 0, 32767)).addGroup(layout.createSequentialGroup().addComponent(this.fileNameLabel).addGap(48, 48, 48).addComponent(this.fileNameField))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.totalImagesLabel).addComponent(this.totalImagesCompField, -2, -1, -2).addComponent(this.slash1Label).addComponent(this.totalImagesField, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.totalImagesPB, -2, -1, -2).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.currentBatchLabel).addComponent(this.currentBatchCompField, -2, -1, -2).addComponent(this.slash2Label).addComponent(this.currentBatchField, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.currentBatchPB, -2, -1, -2).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.currentPaceLabel).addComponent(this.currentPaceField, -2, -1, -2).addComponent(this.perHour1Label)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.avgPaceLabel).addComponent(this.avgPaceField, -2, -1, -2).addComponent(this.perHour2Label)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.fileNameLabel, GroupLayout.Alignment.TRAILING).addComponent(this.fileNameField, GroupLayout.Alignment.TRAILING, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.prodNameField, -2, -1, -2).addComponent(this.prodNameLabel).addComponent(this.prodIndexField, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane1, -2, 84, -2).addComponent(this.descriptionLabel)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.prodCatField, -2, -1, -2).addComponent(this.prodCatLabel, GroupLayout.Alignment.TRAILING)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.photographerField, -2, -1, -2).addComponent(this.photographerLabel)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.priceLabel).addComponent(this.priceField, -2, -1, -2)).addGap(18, 18, 18).addComponent(this.tagsPanel, -2, -1, -2).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.undoButton).addComponent(this.deleteButton).addComponent(this.applyButton))).addComponent(this.photoPanel, -1, -1, 32767)).addContainerGap()));
        this.photoPanel.getAccessibleContext().setAccessibleName("");
        this.pack();
    }

    private void undoButtonActionPerformed(ActionEvent evt) {
        this.undoPhoto();
    }

    private void deleteButtonActionPerformed(ActionEvent evt) {
    }

    private void applyButtonActionPerformed(ActionEvent evt) {
        this.finalizePhoto(true);
    }

    private void tagsFieldActionPerformed(ActionEvent evt) {
        this.finalizePhoto(true);
    }

    private void undoPhoto() {
        this.undoButton.setEnabled(false);
        Photo previousPhoto = this.data.undo();
        this.updateFields(false);
        this.updatePhotoPanel(previousPhoto);
    }

    private void finalizePhoto(boolean save) {
        boolean done;
        this.undoButton.setEnabled(true);
        if (save) {
            String prodName = this.prodNameField.getText();
            String prodIndex = this.prodIndexField.getText();
            String prodCat = this.prodCatField.getText();
            String description = this.descriptionText.getText();
            String photographer = this.photographerField.getText();
            String price = this.priceField.getText();
            String tags = this.tagsField.getText().replaceAll("[a-z]*", "").replaceAll("\\+", ",").replaceAll("^,*", "").replaceAll(",*$", "");
            done = this.data.processPhoto(prodName, prodIndex, prodCat, description, photographer, price, tags);
        } else {
            done = this.data.deletePhoto();
        }
        if (!done) {
            this.loadNextPhoto();
        } else {
            this.data.finalize();
        }
        this.updateFields(save);
    }

    private void openLocationMIActionPerformed(ActionEvent evt) {
        JFileChooser jfc = new JFileChooser();
        jfc.setCurrentDirectory(new File(String.valueOf(System.getProperty("user.home")) + File.separator + "Desktop"));
        jfc.setDialogTitle("Select a directory containing the photos");
        jfc.setFileSelectionMode(1);
        jfc.setAcceptAllFileFilterUsed(false);
        if (jfc.showOpenDialog(this) != 0) {
            System.out.println("No Selection ");
            return;
        }
        this.data.initialize(jfc.getSelectedFile());
        int numPhotos = this.data.getTotalNumberOfPhotos();
        this.totalImagesField.setText("" + numPhotos);
        this.totalImagesPB.setMaximum(numPhotos);
        this.currentBatchField.setText("5");
        this.currentBatchPB.setMaximum(5);
        this.prodIndexField.setEditable(false);
        Photo nextPhoto = this.loadNextPhoto();
        this.fileNameField.setText(nextPhoto.getPhotoName());
    }

    private Photo loadNextPhoto() {
        Photo nextPhoto = this.data.getPhoto();
        if (nextPhoto == null) {
            return null;
        }
        this.updatePhotoPanel(nextPhoto);
        return nextPhoto;
    }

    private void updatePhotoPanel(Photo photo) {
        try {
            this.photoPanel.add(new ScalablePane(photo.getDisplayImage()));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        this.pack();
    }

    private void updateFields(boolean increaseIndex) {
        Photo current = this.data.getCurrentPhoto();
        this.fileNameField.setText(current.getPhotoName());
        int totalDone = this.data.getNumberOfCompletedPhotos();
        int batchDone = this.data.getNumberOfCompletedPhotosInBatch();
        this.totalImagesCompField.setText(Integer.toString(totalDone));
        this.currentBatchCompField.setText(Integer.toString(batchDone));
        this.totalImagesPB.setValue(totalDone);
        this.currentBatchPB.setValue(batchDone);
        if (current.isProcessed()) {
            System.out.println("It's processed!");
            this.prodNameField.setText(current.getProductName());
            this.prodIndexField.setText(current.getProductIndex());
            this.prodCatField.setText(current.getProductCategory());
            this.photographerField.setText(current.getPhotographer());
            this.priceField.setText(current.getPrice());
            this.tagsField.setText(current.getTags());
        } else {
            this.tagsField.setText("");
            if (increaseIndex) {
                this.prodIndexField.setText("" + (Integer.parseInt(this.prodIndexField.getText()) + 1));
            }
        }
        this.currentPaceField.setText(this.data.getCurrentPace());
        this.avgPaceField.setText(this.data.getAvgPace());
        this.tagsField.selectAll();
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
            Logger.getLogger(FotoTagger.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex) {
            Logger.getLogger(FotoTagger.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex) {
            Logger.getLogger(FotoTagger.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(FotoTagger.class.getName()).log(Level.SEVERE, null, ex);
        }
        EventQueue.invokeLater(new Runnable(){

            @Override
            public void run() {
                new FotoTagger().setVisible(true);
            }
        });
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt(max - min + 1) + min;
        return randomNum;
    }

}

