package app;

import exception.NoSongsException;

public class HebronMp3PlayerBuilder {

    private HebronMp3Player.Codec codec = HebronMp3Player.Codec.NATIVE_JAVA_TOOLS;
    private String mp3Directory = "mp3/";

    public HebronMp3PlayerBuilder with(HebronMp3Player.Codec codec) {
        this.codec = codec;
        return this;
    }

    public HebronMp3PlayerBuilder from(String mp3Directory) {
        this.mp3Directory = mp3Directory;
        return this;
    }

    public HebronMp3Player build() throws NoSongsException {
        return new HebronMp3Player(codec, mp3Directory);
    }
}
