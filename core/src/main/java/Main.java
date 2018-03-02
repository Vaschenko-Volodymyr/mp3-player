import app.HebronMp3Player;
import app.HebronMp3PlayerBuilder;
import app.Mp3Player;
import exception.NoSongsException;

public class Main {

    // to start you have to build project that
    // from command line
    // java -jar [path to directory with mp3 files]
    // example: java -jar mp3/
    public static void main(String[] args) {
        try {
            Mp3Player player = new HebronMp3PlayerBuilder()
                    .with(HebronMp3Player.Codec.NATIVE_JAVA_TOOLS)
                    .from(args[0])
                    .build();

            player.play();
        }
        catch (NoSongsException e) {
            System.out.println("No songs in directory " + args[0] + ":(");
        }
    }

}
