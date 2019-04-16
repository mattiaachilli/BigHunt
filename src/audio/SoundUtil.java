package audio;

import java.applet.Applet;
import java.applet.AudioClip;

/**
 * Class about sound of the game.
 */
public final class SoundUtil {

    /**
     * All the audio clips.
     */
    public static final AudioClip CAPTURED_DUCK = Applet.newAudioClip(SoundUtil.class.getResource("/sounds/capturedDuck.wav"));
    /**
     * Dog's bark.
     */
    public static final AudioClip DOG = Applet.newAudioClip(SoundUtil.class.getResource("/sounds/dog.wav"));
    /**
     * Dog's laugh.
     */
    public static final AudioClip DOG_LAUGH = Applet.newAudioClip(SoundUtil.class.getResource("/sounds/dogLaugh.wav"));
    /**
     * Duck's entrance noise.
     */
    public static final AudioClip DUCK_CALL = Applet.newAudioClip(SoundUtil.class.getResource("/sounds/duckCall.wav"));
    /**
     * Duck's death noise.
     */
    public static final AudioClip DUCK_DEAD = Applet.newAudioClip(SoundUtil.class.getResource("/sounds/duckDead.wav"));
    /**
     * End of the game music.
     */
    public static final AudioClip GAME_CLEAR = Applet.newAudioClip(SoundUtil.class.getResource("/sounds/gameClear.wav"));
    /**
     * Game intro music.
     */
    public static final AudioClip GAME_INTRO = Applet.newAudioClip(SoundUtil.class.getResource("/sounds/gameIntro.wav"));

    /**
     * Shooting sound.
     */
    public static final AudioClip SHOOT = Applet.newAudioClip(SoundUtil.class.getResource("/sounds/shoot.wav"));

    
    private SoundUtil() {
        //Never called
    }

}
