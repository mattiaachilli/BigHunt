package controller.input;

import model.Model;

/**
 * 
 * @author giuli
 *
 */
public class Pause implements Command {

    @Override
    public void execute(final Model model) {
        //model.pause();
        System.out.println("pause");
    }

}
