package controller.input;
/**
 * 
 * @author giuli
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
