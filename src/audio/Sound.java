package audio;

import java.applet.Applet;
import java.applet.AudioClip;

/**
 * 
 * @author giuli
 *
 */
public final class Sound {

    private Sound() {
        //Never called
    }

    /**
     * All the audio clips.
     */
    public static final AudioClip CAPTURED_DUCK = Applet.newAudioClip(Sound.class.getResource("/sounds/capturedDuck.wav"));
    /**
     * Dog's bark.
     */
    public static final AudioClip DOG = Applet.newAudioClip(Sound.class.getResource("/sounds/dog.wav"));
    /**
     * Dog's laugh.
     */
    public static final AudioClip DOG_LAUGH = Applet.newAudioClip(Sound.class.getResource("/sounds/dogLaugh.wav"));
    /**
     * Duck's entrance noise.
     */
    public static final AudioClip DUCK_CALL = Applet.newAudioClip(Sound.class.getResource("/sounds/duckCall.wav"));
    /**
     * Duck's death noise.
     */
    public static final AudioClip DUCK_DEAD = Applet.newAudioClip(Sound.class.getResource("/sounds/duckDead.wav"));
    /**
     * End of the game music.
     */
    public static final AudioClip GAME_CLEAR = Applet.newAudioClip(Sound.class.getResource("/sounds/gameClear.wav"));
    /**
     * Game intro music.
     */
    public static final AudioClip GAME_INTRO = Applet.newAudioClip(Sound.class.getResource("/sounds/gameIntro.wav"));
    /**
     * Shooting sound.
     */
    public static final AudioClip SHOOT = Applet.newAudioClip(Sound.class.getResource("/sounds/shoot.wav"));

}
