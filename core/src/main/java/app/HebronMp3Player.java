package app;

import exception.NoSongsException;
import lombok.Getter;
import util.Mp3FromStreamPlayer;
import util.Mp3Loader;
import util.Mp3Searcher;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Getter
public class HebronMp3Player implements Mp3Player {

    @Getter
    public enum Codec {

        NATIVE_JAVA_TOOLS("native"),
        VLC_PLAYER("vlc");

        private String name;

        Codec(String name) {
            this.name = name;
        }
    }

    private Mp3FromStreamPlayer rawPlayer = new Mp3FromStreamPlayer();
    private Mp3Loader loader = new Mp3Loader();
    private Mp3Searcher searcher;

    private Codec codec;
    private String mp3Directory;
    private String currentTrackFileName;


    HebronMp3Player(Codec codec, String mp3Directory) throws NoSongsException {
        this.codec = codec;
        this.mp3Directory = mp3Directory;
        searcher = new Mp3Searcher(mp3Directory, true);
    }

    public void play() {
        try {
            FileInputStream currentTrackStream = currentTrackStream();
            rawPlayer.playMp3(currentTrackStream);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void stop() {
        rawPlayer.stopMp3();
    }

    public void next() {
        try {
            currentTrackFileName = getNextTrackFileName();
            stop();
            FileInputStream stream = nextTrackStream();
            rawPlayer.playMp3(stream);
        } catch (Exception e){
            e.printStackTrace();
        }
    }



    public void previous() {
        try {
            currentTrackFileName = getPreviousTrackFileName();
            stop();
            FileInputStream stream = previousTrackStream();
            rawPlayer.playMp3(stream);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private FileInputStream nextTrackStream() throws FileNotFoundException {
        String nextTrackFileName = searcher.getNext();
        FileInputStream stream = trackStream(nextTrackFileName);
        return stream;
    }

    private FileInputStream previousTrackStream() throws FileNotFoundException {
        String previousTrackFileName = searcher.getLast();
        FileInputStream stream = trackStream(previousTrackFileName);
        return stream;
    }

    private FileInputStream trackStream(String fileName) throws FileNotFoundException {
        FileInputStream stream = loader.loadMp3(mp3Directory, fileName).getMp3AsStream();
        return stream;
    }

    private String getNextTrackFileName(){
        return searcher.getNext();
    }

    private String getPreviousTrackFileName(){
        return searcher.getLast();
    }

    private FileInputStream currentTrackStream() throws FileNotFoundException {
        return trackStream(currentTrackFileName);
    }

}
