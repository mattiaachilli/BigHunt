package controller.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 
 * @author simone
 * A class containing constants used to find files in the user home directory
 */
public final class FilesHomeManager {

    public static final String SEPARATOR = System.getProperty("file.separator");
    
    /**
     * String representing the route path of the user
     */
    public static final String ROUTE_PATH = System.getProperty("user.home")
            + SEPARATOR
            + ".bighunt";
    
    /**
     * String representing the high scores directory
     */
    public static final String HIGH_SCORES_DIR = ROUTE_PATH
            + SEPARATOR
            + "highscores";
    
    /**
     * String representing the users directory
     */
   public static final String USERS_DIR = ROUTE_PATH
           + SEPARATOR
           + "users";
   
   /**
    * String representing the users' list
    */
   public static final String USERS_LIST = USERS_DIR
           + SEPARATOR
           + "userslist";
   
   /**
    * String representing the file containing 
    * high scores for the story mode
    */
   public static final String STORY_SCORES = HIGH_SCORES_DIR
           + SEPARATOR
           + "story.bin";
   
   /**
    * String representing the file containing 
    * high scores for the survival mode
    */
   public static final String SURVIVAL_SCORES = HIGH_SCORES_DIR
           + SEPARATOR
           + "survival.bin";
   
   /**
    * Method to get a user's userName and hashCodePassword
    * 
    * @param userName, the name of the user
    * @return the file of the user that contains his username and his hashcode password
    */
   public static String getUserFile(final String userName) {
       return USERS_DIR
           + SEPARATOR
           + userName
           +".bin";
   }
   
   /**
    * Sets up the application by creating app directories and utility files.
    */
   public static void setupApplication() {
       try {
           Files.createDirectories(Paths.get(HIGH_SCORES_DIR));
           Files.createDirectories(Paths.get(USERS_DIR));
           if (!Files.exists(Paths.get(USERS_LIST))) {
               Files.createFile(Paths.get(USERS_LIST));
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
   
}
