package controller.files;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

import model.data.UserData;
import model.data.UserDataImpl;

/**
 * Class that loads and saves users in the File System.
 *
 */
public class UserManagerImpl implements UserManager {

    private static final String SEPARATOR = " ";

    @Override
    public final Optional<UserData> login(final String userName, final String password) {
        final Optional<String[]> account = this.getUsersAndPasswords().filter(u -> u[0].equals(userName))
        .filter(u -> u[1].equals(Integer.toString(password.hashCode()))).findFirst();
        if (account.isPresent()) {
            try {
                return Optional.of(this.load(userName));
            } catch (IOException e) {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    @Override
    public final boolean save(final UserData data) {
        try {
            write(data);
            return true;
        } catch (IOException e) {
            return false;
        }

    }

    @Override
    public final Optional<UserData> register(final String userName, final String password) {
        if (this.getUsersAndPasswords().filter(l -> l[0].equals(userName)).findFirst().isPresent()) {
            return Optional.empty();
        } else {
            try (BufferedWriter write = new BufferedWriter(new FileWriter(FilesHomeManagerUtils.USERS_LIST, true))) {
                write.write(userName + SEPARATOR + password.hashCode());
                write.newLine();
                final UserData newUser = new UserDataImpl(userName);
                this.write(newUser);
                return Optional.of(newUser);
            } catch (IOException e) {
                return Optional.empty();
            }
        }
    }

    private Stream<String[]> getUsersAndPasswords() {
        try {
            return Files.lines(Paths.get(FilesHomeManagerUtils.USERS_LIST)).map(list -> list.split(SEPARATOR));
        } catch (IOException e) {
            return Stream.empty();
        }
    }

    private UserData load(final String userName) throws IOException {
        try (
        ObjectInputStream objectStream = new ObjectInputStream(
            new BufferedInputStream(new FileInputStream(
            FilesHomeManagerUtils.getUserFile(userName))))
        ) {
            return (UserData) objectStream.readObject();
        } catch (IOException | ClassNotFoundException e) {

            throw new IOException();
        }
    }

    private void write(final UserData user) throws IOException {
        try (
        ObjectOutputStream objectStream = new ObjectOutputStream(
            new BufferedOutputStream(new FileOutputStream(
            FilesHomeManagerUtils.getUserFile(user.getName()))))
        ) {
            objectStream.writeObject(user);
        } catch (IOException e) {
            throw new IOException();
        }
    }
}
