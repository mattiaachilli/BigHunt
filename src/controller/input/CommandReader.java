package controller.input;

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

}
