package controller.input;

import model.Model;

/**
 * 
 * @author giuli
 *
 */
public class Recharge implements Command {

    private final CommandType type = CommandType.RECHARGE;

    @Override
    public final void execute(final Model model) {
        model.recharge();
    }

    @Override
    public final CommandType getType() {
        return this.type;
    }

}
