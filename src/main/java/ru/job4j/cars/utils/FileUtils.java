package ru.job4j.cars.utils;

import java.io.File;

public class FileUtils {
    private static final FileUtils INST = new FileUtils();
    private static final String DIRECTORY = "c:\\images\\cars\\";

    private FileUtils() {
    }

    public static FileUtils instOf() {
        return INST;
    }

    public File getFilesFromFolder(String id) {
        File targetFile = null;
        for (File file : new File(DIRECTORY).listFiles()) {
            if ((id + ".jpg").equalsIgnoreCase(file.getName())
                    || (id + ".png").equalsIgnoreCase(file.getName())) {
                targetFile = file;
                break;
            }
        }
        return targetFile;
    }
}
