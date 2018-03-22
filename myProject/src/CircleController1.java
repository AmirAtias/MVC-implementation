
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class CircleController1 extends BorderPane {
	private CircleModel1 model;
	private TextField tfRadius = new TextField();
	private ObservableList<Boolean> options = FXCollections.observableArrayList(new Boolean(false), new Boolean(true));
	private ComboBox<Boolean> fillComBox = new ComboBox<>(options);
	private ColorPicker chooseColor = new ColorPicker();

	/** Creates new form CircleController */
	public CircleController1() {
		setPadding(new Insets(15, 15, 15, 15));
		GridPane panel1 = new GridPane();
		tfRadius.setMaxWidth(125);
		fillComBox.setMaxWidth(125);
		chooseColor.setMaxWidth(125);
		panel1.add(new Label("Radius "), 0, 0);
		panel1.add(new Label("Filled"), 0, 1);
		panel1.add(new Label("Color"), 0, 2);
		panel1.setHgap(10);
		panel1.setVgap(30);

		GridPane panel2 = new GridPane();
		panel2.add(tfRadius, 0, 0);
		panel2.add(fillComBox, 0, 1);
		panel2.add(chooseColor, 0, 2);
		panel2.setHgap(10);
		panel2.setVgap(20);
		setCenter(panel2);
		setLeft(panel1);
		fillComBox.getSelectionModel().selectFirst();
		chooseColor.setValue(Color.BLACK);
		tfRadius.setOnAction(e -> model.setRadius(new Double(tfRadius.getText()).doubleValue()));
		fillComBox.setOnAction(e -> model.setfilled(fillComBox.getValue()));
		chooseColor.setOnAction(e -> model.setColor(chooseColor.getValue()));

	}

	public void setModel(CircleModel1 model) {
		this.model = model;

	}
}
