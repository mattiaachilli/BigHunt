import java.io.File;

import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class ProvaAudio {
    
    private void getFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        System.out.println(classLoader.getResource("sounds/dog.wav").getPath());

        Media sound = new Media(new File(classLoader.getResource("sounds/capturedDuck.wav").getFile()).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }
    public static void main(String[] args) {
        new ProvaAudio().getFile();
    }
}
