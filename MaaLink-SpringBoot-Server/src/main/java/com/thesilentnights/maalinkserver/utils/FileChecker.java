package com.thesilentnights.maalinkserver.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileChecker {
    public static File checkFile(File file, String configFormat) {
        if (!file.exists()) {
            try {
                file.createNewFile();

                BufferedWriter bufferedWriter;
                bufferedWriter = new BufferedWriter(new FileWriter(file));
                bufferedWriter.write(configFormat);
                bufferedWriter.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return file;
    }

    public static File checkFile(String filePath, String configFormat) {
        return checkFile(new File(filePath),configFormat);
    }


}
