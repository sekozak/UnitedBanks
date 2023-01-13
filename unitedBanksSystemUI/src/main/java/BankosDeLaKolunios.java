
import controller.AppController;
import javafx.application.Application;
import javafx.stage.Stage;


import java.io.IOException;

public class BankosDeLaKolunios extends Application {

    private Stage primaryStage;

    private AppController appController;

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        this.primaryStage.setTitle("BankosDeLaKoluniosApp");
        this.appController = new AppController(primaryStage);
        this.appController.initRootLayout();
    }

}
