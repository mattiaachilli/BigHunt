package controller.input;

import java.util.Queue;

import model.Model;

/**
 * 
 * @author giuli
 *
 */
public interface CommandReader {

    /**
     * @param model
     *          provided by the controller.
     */
    void executeCommand(Model model);

    /**
     * 
     * @return the next command to be executed.
     */
    Queue<Command> getCommands();

}
