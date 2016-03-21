/*
 * Decompiled with CFR 0_110.
 */
package com.spectigo.fotorganize;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Output {
    private FileWriter fw = null;

    public Output(File path) throws IOException {
        this.fw = new FileWriter(new File(String.valueOf(path.getAbsolutePath()) + File.separator + "tags.txt"));
    }

    public /* varargs */ void write(String ... elements) throws IOException {
        int i = 0;
        while (i < elements.length - 1) {
            this.fw.write(String.valueOf(elements[i]) + "|");
            ++i;
        }
        this.fw.write(String.valueOf(elements[elements.length - 1]) + "\n");
        this.fw.flush();
    }

    public void close() throws IOException {
        this.fw.close();
    }
}

