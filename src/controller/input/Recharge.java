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
    public void execute(final Model model) {
        System.out.println("recharged " + model.getCurrentMagazine().getNumber());
        model.recharge();
    }

    @Override
    public CommandType getType() {
        return this.type;
    }

}
