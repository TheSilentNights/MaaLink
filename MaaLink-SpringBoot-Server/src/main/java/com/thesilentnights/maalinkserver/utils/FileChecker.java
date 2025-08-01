package com.thesilentnights.maalinkserver.utils;

import java.io.File;
import java.io.IOException;

public class FileChecker {
    public static File checkFile(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return file;
    }

    public static File checkFile(String filePath) {
        return checkFile(new File(filePath));
    }


}
