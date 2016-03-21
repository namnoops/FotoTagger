/*
 * Decompiled with CFR 0_110.
 */
package org.imgscalr;

import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.RenderedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr;

public class Main {
    static ConvolveOp OP_ANTIALIAS = new ConvolveOp(new Kernel(2, 2, new float[]{0.25f, 0.25f, 0.25f, 0.25f}), 1, null);

    public static void main(String[] args) throws IOException {
        BufferedImage i = ImageIO.read(Main.class.getResourceAsStream("imgscalr-mac.png"));
        System.setProperty("imgscalr.debug", "true");
        ImageIO.write((RenderedImage)Scalr.resize(i, Scalr.Mode.FIT_EXACT, 500, 250, new BufferedImageOp[0]), "PNG", new FileOutputStream("imgscalr-mac-fit-exact-500x250.png"));
    }
}

