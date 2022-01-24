package ru.job4j.cars.utils;

import java.io.File;
import java.util.List;

public class FileUtils {
    private static final String DIRECTORY = "c:\\images\\";

    public static File getFilesFromFolder(String id) {
        File targetFile = null;
        for (File file : new File(DIRECTORY + id + "\\").listFiles()) {
            if ((id + ".jpg").equalsIgnoreCase(file.getName())
                    || (id + ".png").equalsIgnoreCase(file.getName())) {
                targetFile = file;
                break;
            }
        }
        return targetFile;
    }


    public static void main(String[] args) {
        File file = FileUtils.getFilesFromFolder("66");
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getName());
    }
}
