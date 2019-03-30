package controller.input;

import java.util.LinkedList;
import java.util.Queue;

import model.Model;

/**
 * 
 * @author giuli
 *
 */
public class InputController implements CommandReader, CommandWriter {

    private final Queue<Command> commands;

    /**
     * Initializes a new InputController.
     */
    public InputController() {
        this.commands = new LinkedList<>();
    }

    @Override
    public final void setCommand(final Command command) {
        this.commands.add(command);
    }

    @Override
    public final void removeCommand(final Command command) {
        if (this.commands.contains(command)) {
            this.commands.remove(command);
        }
    }

    @Override
    public final void executeCommand(final Model model) {
        if (!this.commands.isEmpty()) {
            this.commands.poll().execute(model);
        }
    }

}
