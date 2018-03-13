package util;

import java.io.FileInputStream;
import java.io.IOException;

import sun.audio.*;

public class Mp3FromStreamPlayer {

    public void playMp3(FileInputStream stream) {
        // TODO: Play mp3 having stream
        try {
            AudioStream audioStream = new AudioStream(stream);
            AudioPlayer.player.start(audioStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void stopMp3(AudioStream audioStream){
        // TODO: Stop mp3 playing rightHere
        AudioPlayer.player.stop(audioStream);
    }
}
