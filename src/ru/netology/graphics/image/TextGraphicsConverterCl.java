package ru.netology.graphics.image;

import ru.netology.graphics.server.GServer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;

import java.net.URL;


public class TextGraphicsConverterCl implements TextGraphicsConverter {
    private double maxRatio;
    private int maxWidth;
    private int maxHeight;
    TextColorSchema schema = new TextColorSchemaCl();

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        BufferedImage img = ImageIO.read(new URL(url));
        int originWidth = img.getWidth();
        int originHeight = img.getHeight();
        double ratio = originWidth / originHeight;
        if (ratio > maxRatio) throw new BadImageSizeException(ratio, maxRatio);
        int newWigth = originWidth <= maxWidth ? originWidth : (originWidth / (originWidth / maxWidth));
        int newHeigth = originHeight <= maxHeight ? originHeight : (originHeight / (originHeight / maxHeight));
        Image scaledImage = img.getScaledInstance(newWigth, newHeigth, BufferedImage.SCALE_SMOOTH);
        BufferedImage draftImg = new BufferedImage(newWigth, newHeigth, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics = draftImg.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);
        WritableRaster bwRaster = draftImg.getRaster();
        char[][] savedC = new char[newHeigth][newWigth];
        for (int h = 0; h < newHeigth; h++) {
            for (int w = 0; w < newWigth; w++) {
                int color = bwRaster.getPixel(w, h, new int[3])[0];
                char c = schema.convert(color);
                savedC[h][w] = c;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < savedC.length; i++) {
            for (int j = 0; j < savedC[i].length; j++) {
                sb.append(savedC[i][j]);
                sb.append(savedC[i][j]);

            }
            sb.append("\n");
        }
        return sb.toString();
    }



    @Override
    public void setMaxWidth(int width) {
       this.maxWidth = width;
    }


    @Override
    public void setMaxHeight(int height){
        this.maxHeight = height;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {
        this.schema = schema;

    }
}
