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

public class CircleController2 extends BorderPane implements CircleEvents {
	private CircleModel2 model;
	private int CircleCounter;
	private TextField tfRadius = new TextField();
	private ObservableList<Boolean> options = FXCollections.observableArrayList(new Boolean(false), new Boolean(true));
	private ComboBox<Boolean> fillComBox = new ComboBox<>(options);
	private ComboBox<Boolean> areaComBox = new ComboBox<>(options);
	private ColorPicker chooseColor = new ColorPicker();
	final String INPUT_ERROR = "radius must be > 0.0";

	public CircleController2(int CircleCounter) {
		this.CircleCounter = CircleCounter;
		setPadding(new Insets(15, 50, 15, 15));
		tfRadius.setMaxWidth(125);
		fillComBox.setMaxWidth(125);
		areaComBox.setMaxWidth(125);
		chooseColor.setMaxWidth(125);
		// panel for label
		GridPane panel1 = new GridPane();
		panel1.add(new Label(eventType.RADIUS.toString() + " "), 0, 0);
		panel1.add(new Label(eventType.FILLED.toString()), 0, 1);
		panel1.add(new Label(eventType.AREA.toString()), 0, 2);
		panel1.add(new Label(eventType.COLOR.toString()), 0, 3);
		panel1.setHgap(10);
		panel1.setVgap(30);

		GridPane panel2 = new GridPane();
		panel2.add(tfRadius, 0, 0);
		panel2.add(fillComBox, 0, 1);
		panel2.add(areaComBox, 0, 2);
		panel2.add(chooseColor, 0, 3);
		panel2.setHgap(10);
		panel2.setVgap(20);
		setCenter(panel2);
		setLeft(panel1);
		fillComBox.getSelectionModel().selectFirst();
		areaComBox.getSelectionModel().selectFirst();
		chooseColor.setValue(Color.BLACK);
		// add listeners
		radiusAddListener();
		fillComboBoxAddListener();
		areaComboBoxAddListener();
		chooseColorAddListener();
	}

	private void chooseColorAddListener() {
		// set Color
		chooseColor.setOnAction(e -> {
			if (!checkIfThereIsModel())
				return;
			else
				model.setColor(chooseColor.getValue());
		});

	}

	private void areaComboBoxAddListener() {
		// area comboBox
		areaComBox.setOnAction(e -> {
			if (!checkIfThereIsModel())
				return;
			else
				model.setCalculateArea(areaComBox.getValue());
		});

	}

	private void fillComboBoxAddListener() {
		// fill comboBox
		fillComBox.setOnAction(e -> {
			if (!checkIfThereIsModel())
				return;
			else
				model.setfilled(fillComBox.getValue());
		});

	}

	private void radiusAddListener() {
		// radius button
		tfRadius.setOnAction(e -> {
			if (!checkIfThereIsModel())
				return;
			else
				try {
					double radius = new Double(tfRadius.getText()).doubleValue();
					if (radius <= 0) {
						tfRadius.setText(INPUT_ERROR);
						return;
					}
					model.setRadius(radius);
				} catch (Exception ex) {
					tfRadius.setText(INPUT_ERROR);
					return;
				}

		});

	}

	public boolean checkIfThereIsModel() {
		if (model == null)
			return false;
		return true;
	}

	public void setModel(CircleModel2 model) {
		this.model = model;
	}

	public CircleModel2 getModel() {
		return model;
	}

	public int getCircleCounter() {
		return CircleCounter;
	}
}
