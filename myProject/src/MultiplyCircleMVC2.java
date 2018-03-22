import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MultiplyCircleMVC2 extends Application {
	private ObservableList<CircleController2> circleControllerList = FXCollections.observableArrayList();
	private ObservableList<CircleModel2> circleModelList = FXCollections.observableArrayList();
	private ObservableList<CircleView2> circleViewList = FXCollections.observableArrayList();
	private Button btControllerView = new Button("show Controller and View");
	private int circleCounter = 0;

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane pane = new BorderPane();
		pane.setCenter(btControllerView);
		Scene scene = new Scene(pane, 350, 100);
		primaryStage.setScene(scene);
		primaryStage.show(); // Display the stage
		primaryStage.setAlwaysOnTop(true);
		primaryStage.setResizable(false);
		primaryStage.setResizable(false);
		primaryStage.setTitle("MultiplyCircleMVC2");
		primaryStage.setOnCloseRequest(e -> Platform.exit());
		btControllerView.setOnAction(e -> createNewController());

	}

	public void createNewController() {
		circleModelList.add(new CircleModel2(circleCounter));
		circleControllerList.add(new CircleController2(circleCounter));
		// create new controller
		circleControllerList.get(circleCounter).setModel(circleModelList.get(circleCounter));
		Scene sceneController = new Scene(circleControllerList.get(circleCounter), 375, 225);
		Stage stgConroller = new Stage();
		stgConroller.setTitle("controller Number " + (circleCounter + 1));
		stgConroller.setScene(sceneController);
		stgConroller.setX(100 + circleCounter * 15);
		stgConroller.setY(100 + circleCounter * 15);
		stgConroller.setAlwaysOnTop(true);
		stgConroller.setResizable(false);
		stgConroller.show();
		stgConroller.setOnCloseRequest(e -> e.consume());
		createNewView();
		circleCounter++;
	}

	private void createNewView() {
		// create new view
		circleViewList.add(new CircleView2(circleCounter));
		circleViewList.get(circleCounter).setModel(circleModelList.get(circleCounter));
		Scene sceneView = new Scene(circleViewList.get(circleCounter), 400, 200);
		Stage stgView = new Stage();
		stgView.setScene(sceneView);
		stgView.setTitle("View Number " + (circleCounter + 1));
		stgView.setX(200 + circleCounter * 15);
		stgView.setY(200 + circleCounter * 15);
		stgView.setAlwaysOnTop(true);
		stgView.show();
		stgView.setOnCloseRequest(e -> e.consume());
		stgView.setResizable(true);

	}

	public static void main(String[] args) {
		launch(args);

	}
}
