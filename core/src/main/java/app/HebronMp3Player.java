package app;

import exception.NoSongsException;
import lombok.Getter;
import util.Mp3FromStreamPlayer;
import util.Mp3Loader;
import util.Mp3Searcher;

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


    HebronMp3Player(Codec codec, String mp3Directory) throws NoSongsException {
        this.codec = codec;
        this.mp3Directory = mp3Directory;
        searcher = new Mp3Searcher(mp3Directory, true);
    }

    public void play() {
        rawPlayer.playMp3(
                loader.loadMp3(
                        mp3Directory, searcher.getNext())
                        .getMp3AsStream()
        );
    }

    public void stop() {
        rawPlayer.stopMp3();
    }

    public void next() {
        stop();
        String nextTrackFileName = searcher.getNext();
        rawPlayer.playMp3(
                loader.loadMp3(mp3Directory, nextTrackFileName)
                .getMp3AsStream()
        );
    }

    public void previous() {
        // TODO :
    }

}
