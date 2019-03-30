package controller.input;

import model.Model;

/**
 * 
 * @author giuli
 *
 */
public interface Command {

    /**
     * Executes the command.
     * @param model
     *          the model passed through the controller.
     */
    void execute(Model model);

}
