package ru.netology.graphics.image;

public class TextColorSchemaCl implements TextColorSchema {
       @Override
    public char convert(int color) {
        char ch;
        if (color <= 32) {
            ch = '#';
        } else if(color > 32 && color <= 64) {
            ch = '$';
        } else if(color > 64 && color <= 96) {
            ch = '@';
        } else if(color > 96 && color <= 128) {
            ch = '%';
        } else if(color > 128 && color <= 160) {
            ch = '*';
        } else if(color > 160 && color <= 192) {
            ch = '+';
        } else if(color > 192 && color <= 224) {
            ch = '-';
        } else {
            ch = '"';
        }
        return ch;
    }

}
