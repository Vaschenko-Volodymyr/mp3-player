package util;

import exception.NoSongsException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Mp3Searcher {

    private List<String> names = new ArrayList<String>();
    private int cursorIndex;
    private boolean recursively;

    public Mp3Searcher(String fromDir, boolean recursively) throws NoSongsException {
        searchAllMp3Files(fromDir);
        this.recursively = recursively;
    }

    public String getFirst() {
        return names.get(0);
    }

    public String getNext() {
        if (cursorIndex == names.size()) {
            if (recursively) {
                cursorIndex = 0;
            } else {
                cursorIndex = names.size();
            }
        } else {
            cursorIndex++;
        }
        return names.get(cursorIndex);
    }

    public String getLast() {
        return names.get(names.size());
    }

    private void searchAllMp3Files(String fromDir) throws NoSongsException {
        File[] arrayFile;
        File file = new File("$user.dir/" + fromDir);

        arrayFile = file.listFiles();

        if (arrayFile == null) {
            throw new NoSongsException("Тут нет песен");
        }
        for (File f : arrayFile){
            String name = f.getName();
            if (name.endsWith(".mp3")){
                names.add(name);
            }
        }
    }
}
