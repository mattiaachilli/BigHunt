package controller.input;

import model.Model;

/**
 * 
 * @author giuli
 *
 */
public class Recharge implements Command {

    @Override
    public void execute(final Model model) {
        System.out.println("recharged " + model.getCurrentMagazine().getNumber());
        model.recharge();
    }

}
