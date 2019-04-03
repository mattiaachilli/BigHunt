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
     * 
     * @param command
     *          the command to remove.
     */
    void clearCommands();

    /**
     * Remove all commands in queue.
     */
    void removeAll();

}
