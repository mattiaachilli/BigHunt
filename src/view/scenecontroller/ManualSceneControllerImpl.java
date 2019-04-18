package view.scenecontroller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 * Sample Skeleton for 'Manual.fxml' Controller Class.
 */
public class ManualSceneControllerImpl extends AbstractSecondarySceneController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="infiniteammologo"
    private ImageView infiniteammologo; // Value injected by FXMLLoader

    @FXML // fx:id="doublescorelogo"
    private ImageView doublescorelogo; // Value injected by FXMLLoader

    @FXML // fx:id="yellowducklogo"
    private ImageView yellowducklogo; // Value injected by FXMLLoader

    @FXML // fx:id="slowdownlogo"
    private ImageView slowdownlogo; // Value injected by FXMLLoader

    @FXML // fx:id="backBtn"
    private Button backBtn; // Value injected by FXMLLoader

    @FXML // fx:id="pinkducklogo"
    private ImageView pinkducklogo; // Value injected by FXMLLoader

    @FXML // fx:id="killallducklogo"
    private ImageView killallducklogo; // Value injected by FXMLLoader

    @FXML // fx:id="orangeducklogo"
    private ImageView orangeducklogo; // Value injected by FXMLLoader

    @FXML // fx:id="standardducklogo"
    private ImageView standardducklogo; // Value injected by FXMLLoader

    /**
     * Initialize.
     */
    @FXML // This method is called by the FXMLLoader when initialization is complete
    protected final void initialize() {
        assert infiniteammologo != null : "fx:id=\"infiniteammologo\" was not injected: check your FXML file 'Manual.fxml'.";
        assert doublescorelogo != null : "fx:id=\"doublescorelogo\" was not injected: check your FXML file 'Manual.fxml'.";
        assert yellowducklogo != null : "fx:id=\"yellowducklogo\" was not injected: check your FXML file 'Manual.fxml'.";
        assert slowdownlogo != null : "fx:id=\"scoredownlogo\" was not injected: check your FXML file 'Manual.fxml'.";
        assert backBtn != null : "fx:id=\"backBtn\" was not injected: check your FXML file 'Manual.fxml'.";
        assert pinkducklogo != null : "fx:id=\"pinkducklogo\" was not injected: check your FXML file 'Manual.fxml'.";
        assert killallducklogo != null : "fx:id=\"killallducklogo\" was not injected: check your FXML file 'Manual.fxml'.";
        assert orangeducklogo != null : "fx:id=\"orangeducklogo\" was not injected: check your FXML file 'Manual.fxml'.";
        assert standardducklogo != null : "fx:id=\"standardducklogo\" was not injected: check your FXML file 'Manual.fxml'.";

    }
}
