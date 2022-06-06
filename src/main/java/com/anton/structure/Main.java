package com.anton.structure;

import com.anton.structure.IO.FileHandler;

public class Main {
    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandler();
        fileHandler.structureDir(args[0]);
    }
}
