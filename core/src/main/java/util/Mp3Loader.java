package util;

import java.io.FileInputStream;

public class Mp3Loader {

    private FileInputStream stream;

    public Mp3Loader loadMp3(String from, String name) {
        // TODO : Transform mp3 to stream
        return this;
    }

    public FileInputStream getMp3AsStream() {
        return stream;
    }
}
