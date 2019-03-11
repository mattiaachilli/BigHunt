package controller.files;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

import model.data.UserData;
import model.data.UserDataImpl;

/**
 * 
 * @author simone
 * Class that loads and saves users in the File System
 */
public class UserManagerImpl implements UserManager {

    private static final String SEPARATOR = " ";
    
    @Override
    public Optional<UserData> login(String userName, String password) {
        // TODO Auto-generated method stub
        final Optional<String[]> account = this.getUsersAndPasswords()
                .filter(u -> u[0].equals(userName))
                .filter(u -> u[1].equals(Integer.toString(password.hashCode())))
                .findFirst();
        if(account.isPresent()) {
            try {
                return Optional.of(this.load(userName));
            } catch (IOException e) {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean save(UserData data) {
        // TODO Auto-generated method stub
        try {
            write(data);
            return true;
        } catch (IOException e) {
            return false;
        }

    }

    @Override
    public Optional<UserData> register(String userName, String password) {
        // TODO Auto-generated method stub
        if(this.getUsersAndPasswords().filter(l -> l[0].equals(userName)).findFirst().isPresent()) {
            return Optional.empty();
        } else {
            try (BufferedWriter write = new BufferedWriter(new FileWriter(FilesHomeManager.USERS_LIST, true))) {
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
            return Files.lines(Paths.get(FilesHomeManager.USERS_LIST))
                .map(list -> list.split(SEPARATOR));
        } catch (IOException e) {
            return Stream.empty();
        }
    }
    
    private UserData load(final String userName) throws IOException {
        try (InputStream file = new FileInputStream(FilesHomeManager.getUserFile(userName));
        InputStream buffStream = new BufferedInputStream(file);
        ObjectInputStream objectStream = new ObjectInputStream(buffStream);) {
            return (UserData) objectStream.readObject();
        } catch (IOException | ClassNotFoundException e) {

            throw new IOException();
        }
    }
    
    private void write(final UserData user) throws IOException {
        try (OutputStream file = new FileOutputStream(FilesHomeManager.getUserFile(user.getName()));
        OutputStream buffStream = new BufferedOutputStream(file);
        ObjectOutputStream objectStream = new ObjectOutputStream(buffStream);) {
            objectStream.writeObject(user);
        } catch (IOException e) {
            throw new IOException();
        }

    }

}
