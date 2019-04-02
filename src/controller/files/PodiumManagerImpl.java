package controller.files;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import model.data.Podium;
import model.data.PodiumImpl;

/**
 * The manager of the podium.
 * 
 * @author simone
 *
 */
public class PodiumManagerImpl implements PodiumManager {

    @Override
    public final Optional<Podium> loadStoryPodium() {
        return this.load(FilesHomeManager.STORY_SCORES);
    }

    @Override
    public final Optional<Podium> loadSurvivalPodium() {
        return this.load(FilesHomeManager.SURVIVAL_SCORES);
    }

    @Override
    public final boolean saveStoryHighScores(final Podium podium) {
        return this.save(podium, FilesHomeManager.STORY_SCORES);
    }

    @Override
    public final boolean saveSurvivalHighScores(final Podium podium) {
        return this.save(podium, FilesHomeManager.SURVIVAL_SCORES);
    }

    private Optional<Podium> load(final String path) {
        if (!Files.exists(Paths.get(path))) {
            return Optional.of(new PodiumImpl());
        }
        try (InputStream file = new FileInputStream(path);
        InputStream buffStream = new BufferedInputStream(file);
        ObjectInputStream objectStream = new ObjectInputStream(buffStream);) {
            return Optional.of((Podium) objectStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            return Optional.empty();
        }
    }

    private boolean save(final Podium podium, final String path) {
        try (OutputStream file = new FileOutputStream(path);
        OutputStream buffStream = new BufferedOutputStream(file);
        ObjectOutputStream objectStream = new ObjectOutputStream(buffStream);) {
            objectStream.writeObject(podium);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
