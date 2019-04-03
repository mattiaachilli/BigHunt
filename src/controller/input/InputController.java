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
    public final void clearCommands() {
        this.commands.clear();
    }

    @Override
    public final void removeAll() {
        this.commands.clear();
    }

    @Override
    public final void executeCommand(final Model model) {
        if (!this.commands.isEmpty()) {
            this.commands.poll().execute(model);
        }
    }

    @Override
    public final Queue<Command> getCommands() {
        return this.commands;
    }

}
