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
    void removeCommand(Command command);

}
