package audio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;


/**
 * Class about sound of the game.
 */
public final class SoundUtil {

    private static List<Pair<Clip, Optional<AudioInputStream>>> listClip;
    private static List<Pair<Optional<AudioInputStream>, Integer>> framePosition;

    /**
     * Sound made when a dead duck is captured by the dog.
     */
    private static AudioInputStream capturedDuck;
    /**
     * Dog's bark.
     */
    private static AudioInputStream dog;
    /**
     * Dog's laugh.
     */
    private static AudioInputStream dogLaugh;
    /**
     * Duck's entrance noise.
     */
    private static AudioInputStream duckCall;
    /**
     * Duck's death noise.
     */
    private static AudioInputStream duckDead;
    /**
     * End of the game music.
     */
    private static AudioInputStream gameClear;
    /**
     * Game intro music.
     */
    private static AudioInputStream gameIntro;

    /**
     * Shooting sound.
     */
    private static AudioInputStream shoot;


    private SoundUtil() {
    }

    /**
     * Initialize the sounds of the game.
     */
    public static void initAudio() {
        try {
            listClip = new ArrayList<>();
            framePosition = new ArrayList<>();
            capturedDuck = AudioSystem.getAudioInputStream(SoundUtil.class.getResource("/sounds/capturedDuck.wav"));
            dog = AudioSystem.getAudioInputStream(SoundUtil.class.getResource("/sounds/dog.wav"));
            dogLaugh = AudioSystem.getAudioInputStream(SoundUtil.class.getResource("/sounds/dogLaugh.wav"));
            duckCall = AudioSystem.getAudioInputStream(SoundUtil.class.getResource("/sounds/duckCall.wav"));
            duckDead = AudioSystem.getAudioInputStream(SoundUtil.class.getResource("/sounds/duckDead.wav"));
            gameClear = AudioSystem.getAudioInputStream(SoundUtil.class.getResource("/sounds/gameClear.wav"));
            gameIntro = AudioSystem.getAudioInputStream(SoundUtil.class.getResource("/sounds/gameIntro.wav"));
            shoot = AudioSystem.getAudioInputStream(SoundUtil.class.getResource("/sounds/shoot.wav"));
        } catch (final IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } 
    }

    /**
     * Play the input sound.
     * @param sound
     *          sound to play.
     */
    public static void playSound(final AudioInputStream sound) {
        try {
            if (!checkPlayDuplicateSound(sound, 0)) {
                final Clip clip = AudioSystem.getClip();
                listClip.add(new ImmutablePair<>(clip, Optional.of(sound)));
                clip.open(sound);
                clip.start();
            }
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean checkPlayDuplicateSound(final AudioInputStream sound, final int frame) {
        try {
            for (final Pair<Clip, Optional<AudioInputStream>> audioStream: listClip) {
                if (audioStream.getRight().isPresent() && audioStream.getRight().get().equals(sound)) {
                    final Clip clip = audioStream.getLeft();
                    clip.stop();
                    clip.setFramePosition(frame);
                    clip.start();
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Pause all sounds.
     */
    public static void pauseAllSounds() {
        for (final Pair<Clip, Optional<AudioInputStream>> audioStream: listClip) {
            if (audioStream.getRight().isPresent() && audioStream.getLeft().isRunning()) {
                framePosition.add(new ImmutablePair<>(audioStream.getRight(), audioStream.getLeft().getFramePosition()));
            }
        }
        for (final Pair<Clip, Optional<AudioInputStream>> audioStream: listClip) {
            audioStream.getLeft().stop();
        }
    }

    /**
     * Unpause all sounds.
     */
    public static void unpauseAll() {
        for (final Pair<Optional<AudioInputStream>, Integer> audioStream: framePosition) {
            if (audioStream.getLeft().isPresent()) {
                checkPlayDuplicateSound(audioStream.getLeft().get(), audioStream.getRight());
            }
        }
        framePosition.clear();
    }

    /**
     * Clear all sounds.
     */
    public static void clearAudio() {
        for (final Pair<Clip, Optional<AudioInputStream>> audioStream: listClip) {
            audioStream.getLeft().stop();
        }
        listClip.clear();
    }

    /**
     * Get audio of duck capture.
     * @return duckCapture audio.
     */
    public static AudioInputStream getCaptureDuckAudio() {
        return capturedDuck;
    }

    /**
     * Get audio of dog.
     * @return dog audio.
     */
    public static AudioInputStream getDogAudio() {
        return dog;
    }

    /**
     * Get audio of dog laugh.
     * @return duckCapture audio.
     */
    public static AudioInputStream getDogLaughAudio() {
        return dogLaugh;
    }

    /**
     * Get audio of duck call.
     * @return duckCall audio.
     */
    public static AudioInputStream getDuckCall() {
        return duckCall;
    }

    /**
     * Get audio of duck dead.
     * @return duckDead audio.
     */
    public static AudioInputStream getDuckDeadAudio() {
        return duckDead;
    }

    /**
     * Get audio of game clear.
     * @return gameclear audio.
     */
    public static AudioInputStream getGameClearAudio() {
        return gameClear;
    }

    /**
     * Get audio of game intro.
     * @return gameintro audio.
     */
    public static AudioInputStream getGameIntroAudio() {
        return gameIntro;
    }

    /**
     * Get audio of shoot.
     * @return shoot audio.
     */
    public static AudioInputStream getShootAudio() {
        return shoot;
    }
}
