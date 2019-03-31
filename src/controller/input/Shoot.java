package controller.input;

import model.Model;

/**
 * 
 * @author giuli
 *
 */
public class Shoot implements Command {

    @Override
    public void execute(final Model model) {
        System.out.println("shot");
        model.shoot();
    }

}
