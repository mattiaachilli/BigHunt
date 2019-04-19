package controller.input;
/**
 * 
 * Interface that handles the input commands.
 *
 */
public interface CommandWriter {

    /**
     * 
     * @param command
     *          the command to add.
     */
    void setCommand(Command command);

    /**
     *  Clean all commands.
     */
    void clearCommands();

}
