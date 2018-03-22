
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleView1 extends BorderPane implements EventHandler<MyActionEvent> {
	private CircleModel1 model;
	 private Circle theCircle = new Circle();

	public CircleView1(CircleModel1 model) {
		setModel(model);
		if (model != null)
			model.addListener(this);
		paint();
		setCenter(theCircle);

	}

	// paint method
	private void paint() {
		theCircle.setRadius(model.getRadius());
		theCircle.setStroke(model.getColor());
		if (model.getFilled())
			theCircle.setFill(model.getColor());
		else
			theCircle.setFill(Color.TRANSPARENT);
	}

	// private CircleModel1 model;
	private void setModel(CircleModel1 model) {
		this.model = model;
	}

	@Override
	public void handle(MyActionEvent event) {
		paint();

	}
}
