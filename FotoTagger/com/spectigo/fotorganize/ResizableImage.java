/*
 * Decompiled with CFR 0_110.
 */
package com.spectigo.fotorganize;

import com.spectigo.fotorganize.ScalablePane;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ResizableImage {
    public static void main(String[] args) {
        new com.spectigo.fotorganize.ResizableImage();
    }

    public ResizableImage() {
        EventQueue.invokeLater(new Runnable(){

            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
                catch (ClassNotFoundException var1_1) {
                }
                catch (InstantiationException var1_2) {
                }
                catch (IllegalAccessException var1_3) {
                }
                catch (UnsupportedLookAndFeelException var1_4) {
                    // empty catch block
                }
                try {
                    BufferedImage image = ImageIO.read(new File("C:\\Users\\Tal Sela\\Downloads\\729751-1108-0001s.jpg"));
                    JFrame frame = new JFrame("Test");
                    frame.setDefaultCloseOperation(3);
                    frame.setLayout(new BorderLayout());
                    frame.add(new ScalablePane(image));
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }
                catch (Exception exp) {
                    exp.printStackTrace();
                }
            }
        });
    }

}

