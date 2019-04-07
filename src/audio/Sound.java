package audio;

import java.applet.Applet;
import java.applet.AudioClip;

/**
 * 
 * @author giuli
 *
 */
public class Sound {

    /**
     * All the audio clips.
     */
    public static final AudioClip CAPTURED_DUCK = Applet.newAudioClip(Sound.class.getResource("/sounds/capturedDuck.wav"));
    public static final AudioClip DOG = Applet.newAudioClip(Sound.class.getResource("/sounds/dog.wav"));
    public static final AudioClip DOG_LAUGH = Applet.newAudioClip(Sound.class.getResource("/sounds/dogLaugh.wav"));
    public static final AudioClip DUCK_CALL = Applet.newAudioClip(Sound.class.getResource("/sounds/duckCall.wav"));
    public static final AudioClip DUCK_DEAD = Applet.newAudioClip(Sound.class.getResource("/sounds/duckDead.wav"));
    public static final AudioClip GAME_CLEAR = Applet.newAudioClip(Sound.class.getResource("/sounds/gameClear.wav"));
    public static final AudioClip GAME_INTRO = Applet.newAudioClip(Sound.class.getResource("/sounds/gameIntro.wav"));
    public static final AudioClip SHOOT = Applet.newAudioClip(Sound.class.getResource("/sounds/shoot.wav"));

}
