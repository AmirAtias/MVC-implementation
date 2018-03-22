import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class SingleCircleMVC1 extends Application {
	private Button btController = new Button("show Controller");
	private Button btView = new Button("Show View");
	private CircleModel1 model = new CircleModel1();

	@Override
	public void start(Stage primaryStage) throws Exception {
		FlowPane pane = new FlowPane();
		pane.setPadding(new Insets(25, 25, 25, 50));
		pane.setHgap(30);
		pane.getChildren().add(btController);
		pane.getChildren().add(btView);
		btController.setOnAction(e -> {
			createNewController();
		});
		btView.setOnAction(e -> createNewView());
		Scene scene = new Scene(pane, 350, 100);
		primaryStage.setScene(scene);
		primaryStage.show(); // Display the stage
		primaryStage.setAlwaysOnTop(true);
		primaryStage.setResizable(false);
		primaryStage.setTitle("MultiplyCircleMVC1");
		primaryStage.setOnCloseRequest(e -> Platform.exit());
	}

	// create view
	private void createNewView() {
		Stage stgView = new Stage();
		CircleView1 view = new CircleView1(model);
		Scene scene = new Scene(view, 600, 100);
		stgView.setScene(scene);
		stgView.show();
		stgView.setAlwaysOnTop(true);
		stgView.setTitle("View");
		stgView.setOnCloseRequest(e -> e.consume());
	}

	// Create controller
	private void createNewController() {
		Stage stgConroller = new Stage();
		CircleController1 controller = new CircleController1();
		controller.setModel(model);
		Scene scene = new Scene(controller, 300, 165);
		stgConroller.setScene(scene);
		stgConroller.show();
		stgConroller.setAlwaysOnTop(true);
		stgConroller.setResizable(false);
		stgConroller.setTitle("Controller");
		stgConroller.setOnCloseRequest(e -> e.consume());
	}

	public static void main(String[] args) {
		launch(args);

	}
}
