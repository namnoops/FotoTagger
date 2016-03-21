/*
 * Decompiled with CFR 0_110.
 */
package com.spectigo.fotorganize;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class ScalablePane
extends JPanel {
    private static final long serialVersionUID = -3572802299001486135L;
    private Image master;
    private boolean toFit;
    private Image scaled;

    public ScalablePane(Image master) {
        this(master, true);
        this.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
    }

    public ScalablePane(Image master, boolean toFit) {
        this.master = master;
        this.setToFit(toFit);
        this.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
    }

    @Override
    public Dimension getPreferredSize() {
        return this.master == null ? super.getPreferredSize() : new Dimension(this.master.getWidth(this), this.master.getHeight(this));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image toDraw = null;
        if (this.scaled != null) {
            toDraw = this.scaled;
        } else if (this.master != null) {
            toDraw = this.master;
        }
        if (toDraw != null) {
            int x = (this.getWidth() - toDraw.getWidth(this)) / 2;
            int y = (this.getHeight() - toDraw.getHeight(this)) / 2;
            g.drawImage(toDraw, x, y, this);
        }
    }

    @Override
    public void invalidate() {
        this.generateScaledInstance();
        super.invalidate();
    }

    public boolean isToFit() {
        return this.toFit;
    }

    public void setToFit(boolean value) {
        if (value != this.toFit) {
            this.toFit = value;
            this.invalidate();
        }
    }

    protected void generateScaledInstance() {
        this.scaled = null;
        this.scaled = this.isToFit() ? this.getScaledInstanceToFit(this.master, this.getSize()) : this.getScaledInstanceToFill(this.master, this.getSize());
    }

    protected BufferedImage toBufferedImage(Image master) {
        Dimension masterSize = new Dimension(master.getWidth(this), master.getHeight(this));
        BufferedImage image = this.createCompatibleImage(masterSize);
        Graphics2D g2d = image.createGraphics();
        g2d.drawImage(master, 0, 0, this);
        g2d.dispose();
        return image;
    }

    public Image getScaledInstanceToFit(Image master, Dimension size) {
        Dimension masterSize = new Dimension(master.getWidth(this), master.getHeight(this));
        return this.getScaledInstance(this.toBufferedImage(master), this.getScaleFactorToFit(masterSize, size), RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
    }

    public Image getScaledInstanceToFill(Image master, Dimension size) {
        Dimension masterSize = new Dimension(master.getWidth(this), master.getHeight(this));
        return this.getScaledInstance(this.toBufferedImage(master), this.getScaleFactorToFill(masterSize, size), RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
    }

    public Dimension getSizeToFit(Dimension original, Dimension toFit) {
        double factor = this.getScaleFactorToFit(original, toFit);
        Dimension size = new Dimension(original);
        size.width = (int)((double)size.width * factor);
        size.height = (int)((double)size.height * factor);
        return size;
    }

    public Dimension getSizeToFill(Dimension original, Dimension toFit) {
        double factor = this.getScaleFactorToFill(original, toFit);
        Dimension size = new Dimension(original);
        size.width = (int)((double)size.width * factor);
        size.height = (int)((double)size.height * factor);
        return size;
    }

    public double getScaleFactor(int iMasterSize, int iTargetSize) {
        return (double)iTargetSize / (double)iMasterSize;
    }

    public double getScaleFactorToFit(Dimension original, Dimension toFit) {
        double dScale = 1.0;
        if (original != null && toFit != null) {
            double dScaleWidth = this.getScaleFactor(original.width, toFit.width);
            double dScaleHeight = this.getScaleFactor(original.height, toFit.height);
            dScale = Math.min(dScaleHeight, dScaleWidth);
        }
        return dScale;
    }

    public double getScaleFactorToFill(Dimension masterSize, Dimension targetSize) {
        double dScaleWidth = this.getScaleFactor(masterSize.width, targetSize.width);
        double dScaleHeight = this.getScaleFactor(masterSize.height, targetSize.height);
        return Math.max(dScaleHeight, dScaleWidth);
    }

    public BufferedImage createCompatibleImage(Dimension size) {
        return this.createCompatibleImage(size.width, size.height);
    }

    public BufferedImage createCompatibleImage(int width, int height) {
        GraphicsConfiguration gc = this.getGraphicsConfiguration();
        if (gc == null) {
            gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        }
        BufferedImage image = gc.createCompatibleImage(width, height, 3);
        image.coerceData(true);
        return image;
    }

    protected BufferedImage getScaledInstance(BufferedImage img, double dScaleFactor, Object hint, boolean bHighQuality) {
        BufferedImage imgScale = img;
        int iImageWidth = (int)Math.round((double)img.getWidth() * dScaleFactor);
        int iImageHeight = (int)Math.round((double)img.getHeight() * dScaleFactor);
        imgScale = dScaleFactor <= 1.0 ? this.getScaledDownInstance(img, iImageWidth, iImageHeight, hint, bHighQuality) : this.getScaledUpInstance(img, iImageWidth, iImageHeight, hint, bHighQuality);
        return imgScale;
    }

    protected BufferedImage getScaledDownInstance(BufferedImage img, int targetWidth, int targetHeight, Object hint, boolean higherQuality) {
        int type = img.getTransparency() == 1 ? 1 : 2;
        BufferedImage ret = img;
        if (targetHeight > 0 || targetWidth > 0) {
            int h;
            int w;
            if (higherQuality) {
                w = img.getWidth();
                h = img.getHeight();
            } else {
                w = targetWidth;
                h = targetHeight;
            }
            do {
                if (higherQuality && w > targetWidth && (w /= 2) < targetWidth) {
                    w = targetWidth;
                }
                if (higherQuality && h > targetHeight && (h /= 2) < targetHeight) {
                    h = targetHeight;
                }
                BufferedImage tmp = new BufferedImage(Math.max(w, 1), Math.max(h, 1), type);
                Graphics2D g2 = tmp.createGraphics();
                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
                g2.drawImage(ret, 0, 0, w, h, null);
                g2.dispose();
                ret = tmp;
            } while (w != targetWidth || h != targetHeight);
        } else {
            ret = new BufferedImage(1, 1, type);
        }
        return ret;
    }

    protected BufferedImage getScaledUpInstance(BufferedImage img, int targetWidth, int targetHeight, Object hint, boolean higherQuality) {
        int h;
        int w;
        int type = 2;
        BufferedImage ret = img;
        if (higherQuality) {
            w = img.getWidth();
            h = img.getHeight();
        } else {
            w = targetWidth;
            h = targetHeight;
        }
        do {
            if (higherQuality && w < targetWidth && (w *= 2) > targetWidth) {
                w = targetWidth;
            }
            if (higherQuality && h < targetHeight && (h *= 2) > targetHeight) {
                h = targetHeight;
            }
            BufferedImage tmp = new BufferedImage(w, h, type);
            Graphics2D g2 = tmp.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
            g2.drawImage(ret, 0, 0, w, h, null);
            g2.dispose();
            ret = tmp;
            tmp = null;
        } while (w != targetWidth || h != targetHeight);
        return ret;
    }
}

