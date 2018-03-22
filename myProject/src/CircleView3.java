
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;

public class CircleView3 extends BorderPane implements CircleEvents {
	
	class RadiusEvent implements EventHandler<MyActionEvent> {
		@Override
		public void handle(MyActionEvent e) {
			eventTitle = eventType.RADIUS.toString();
			paint();
		}
	}

	class ColorEvent implements EventHandler<MyActionEvent> {

		@Override
		public void handle(MyActionEvent event) {
			eventTitle = eventType.COLOR.toString();
			paint();

		}
	}

	class FilledEvent implements EventHandler<MyActionEvent> {
		@Override
		public void handle(MyActionEvent event) {
			eventTitle = eventType.FILLED.toString();
			paint();
		}

	}

	class AreaEvent implements EventHandler<MyActionEvent> {

		@Override
		public void handle(MyActionEvent event) {
			eventTitle = eventType.AREA.toString();
			paint();

		}

	}

	private GridPane textPane = new GridPane();
	private Circle theCircle = new Circle();
	private CircleModel3 model;
	private int CircleCounter;
	private String eventTitle = "DEAFAILT";

	public CircleView3(int circleCounter) {
		this.CircleCounter = circleCounter;
		setTop(textPane);
		setCenter(theCircle);
	}

	public int getCircleCounter() {
		return CircleCounter;
	}

	public void setModel(CircleModel3 newModel) {
		model = newModel;
		if (model != null) {
			model.addActionListener(new RadiusEvent(), eventType.RADIUS);
			model.addActionListener(new FilledEvent(), eventType.FILLED);
			model.addActionListener(new ColorEvent(), eventType.COLOR);
			model.addActionListener(new AreaEvent(), eventType.AREA);
		}
		paint();
	}

	public CircleModel3 getModel() {
		return model;

	}

	public void paint() {
		textPane.getChildren().clear();
		// pane for text
		double radius = model.getRadius();
		Text eventText = new Text("Event Type: " + eventTitle);
		Font txtFont = Font.font("courier", FontPosture.REGULAR, 15);
		eventText.setFont(txtFont);
		Text circleText = new Text("circle Number " + (CircleCounter + 1));
		circleText.setFont(txtFont);
		Text circleRaduis = new Text("circle raduis " + radius);
		circleRaduis.setFont(txtFont);
		textPane.add(eventText, 0, 0);
		textPane.add(circleText, 0, 1);
		textPane.add(circleRaduis, 0, 2);
		if (model.getCalculateArea()) {
			double circleArea = radius * radius * Math.PI;
			Text area = new Text("Circle Area " + circleArea);
			area.setFont(txtFont);
			textPane.add(area, 0, 3);
		}
		// create circle
		theCircle.setRadius(model.getRadius());
		theCircle.setStroke(model.getColor());
		if (model.getFilled())
			theCircle.setFill(model.getColor());
		else
			theCircle.setFill(Color.TRANSPARENT);
	}

}
