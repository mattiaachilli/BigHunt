package controller.input;

import model.Model;

/**
 * 
 * @author giuli
 *
 */
public class Pause implements Command {

    private final CommandType type = CommandType.PAUSE;

    @Override
    public void execute(final Model model) {
        System.out.println("pause");
    }

    @Override
    public CommandType getType() {
        return this.type;
    }

}
