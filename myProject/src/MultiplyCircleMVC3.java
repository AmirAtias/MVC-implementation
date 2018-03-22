import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MultiplyCircleMVC3 extends Application {

	private CircleController3 controller;
	private CircleView3 view;
	private CircleModel3 model;
	private Button btControllerView = new Button("show Controller and View");
	int circleCounter = 0;

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
		primaryStage.setOnCloseRequest(e -> Platform.exit());
		primaryStage.setTitle("MultiplyCircleMVC3");
		btControllerView.setOnAction(e -> createNewController());

	}

	public void createNewController() {
		model = new CircleModel3(circleCounter);
		controller = new CircleController3(circleCounter);
		// create new controller
		controller.setModel(model);
		Scene sceneController = new Scene(controller, 375, 225);
		Stage stgConroller = new Stage();
		stgConroller.setTitle("Controller Number " + (circleCounter + 1));
		stgConroller.setScene(sceneController);
		stgConroller.setX(100 + circleCounter * 15);
		stgConroller.setY(100 + circleCounter * 15);
		stgConroller.setAlwaysOnTop(true);
		stgConroller.setResizable(false);
		stgConroller.show();
		stgConroller.setOnCloseRequest(e -> e.consume());
		createNewView(model);
		circleCounter++;
	}

	private void createNewView(CircleModel3 model) {
		// create new view
		view = new CircleView3(circleCounter);
		view.setModel(model);
		Scene sceneView = new Scene(view, 400, 200);
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
