package controller;

/**
 * 
 * @author simone
 * A class representing a single command to be executed
 */
@FunctionalInterface
public interface Command {

    /**
     * Runs the command.
     * @param input the input to be executed
     */
    void executeCommand(Input input);
}
