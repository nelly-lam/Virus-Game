package Controller;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControllerWin {
    @FXML Text won;
    @FXML Text point;
    @FXML Text level;
    @FXML
    Pane pane;

    private Stage stage;

    public void setPoint(String point) {
        this.point.setText(point);
    }

    public void setWon(String won) {
        this.won.setText(won);
    }

    public Pane getPane() {
        return pane;
    }

    public void setLevel(String level) {
        this.level.setText(level);
    }

    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;
    }
}
