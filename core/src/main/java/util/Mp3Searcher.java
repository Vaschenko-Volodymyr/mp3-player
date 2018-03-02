package util;

import exception.NoSongsException;
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

    private Mp3Searcher searchAllMp3Files(String fromDir) throws NoSongsException {
        // TODO: init names array - find all files with mp3 extension
        if (names.isEmpty()) {
            throw new NoSongsException("No songs were found in directory");
        }
        return this;
    }
}
