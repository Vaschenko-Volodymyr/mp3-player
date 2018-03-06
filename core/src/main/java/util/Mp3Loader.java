package util;

import exception.NoSongsException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Mp3Loader {

    private FileInputStream stream;

    public Mp3Loader loadMp3(String directory, String fileName) throws FileNotFoundException {
        File file = new File(directory+"/"+fileName);
        if (!file.exists()) throw new FileNotFoundException(fileName + " was not found in: " + directory);
        stream = new FileInputStream(file);
        return this;
    }

    public FileInputStream getMp3AsStream() {
        return stream;
    }
}
