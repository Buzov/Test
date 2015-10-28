package javafxapplication1;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
/*w  w  w.j  ava  2s  .c om*/
public class Main extends Application {
  @Override
  public void start(final Stage stage) {
    stage.setWidth(1200);
    stage.setHeight(500);
    Scene scene = new Scene(new Group());


    final WebView browser = new WebView();
    browser.setMinWidth(1200);
    final WebEngine webEngine = browser.getEngine();

    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(browser);

    webEngine.getLoadWorker().stateProperty()
        .addListener(new ChangeListener<State>() {
          @Override
          public void changed(ObservableValue ov, State oldState, State newState) {

            if (newState == Worker.State.SUCCEEDED) {
              stage.setTitle(webEngine.getLocation());
            }

          }
        });
    webEngine.load("https://docs.unrealengine.com/latest/INT/Engine/QuickStart/1/index.html");

    scene.setRoot(scrollPane);

    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}