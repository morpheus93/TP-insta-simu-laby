package userInterface;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Angie
 */
public class CreditsInterface implements Initializable, ControlledScreen {

    ScreensController myController;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    @Override
    public void init() {
    }

    @FXML
    private void goToScreen1(ActionEvent event){
       //myController.setScreen();
    }
    
    @FXML
    private void goToScreen2(ActionEvent event){
       //myController.setScreen();
    }
}
