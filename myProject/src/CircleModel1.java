
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;

public class CircleModel1  {
	/** Property radius. */
	private SimpleDoubleProperty radius = new SimpleDoubleProperty(25);
	private SimpleBooleanProperty filled = new SimpleBooleanProperty();
	private ObjectProperty<Color> theColor = new SimpleObjectProperty<>(Color.BLACK);
	private ObservableList<EventHandler<MyActionEvent>> eventList = FXCollections.observableArrayList();

	/** Utility field used by event firing mechanism. */
	public void setRadius(double radius) {
		this.radius.setValue(radius);
		processEvent(new MyActionEvent(this, "radius"));
	}

	public void setfilled(boolean value) {
		this.filled.setValue(value);
		processEvent(new MyActionEvent(this, "filled"));

	}

	public void setColor(Color value) {
		this.theColor.setValue(value);
		processEvent(new MyActionEvent(this, "Color"));

	}

	public double getRadius() {
		return radius.get();
	}

	public boolean getFilled() {
		return filled.get();
	}

	public Color getColor() {
		return theColor.get();
	}

	public synchronized void addListener(EventHandler<MyActionEvent> l) {
		if (!eventList.contains(l))
			eventList.add(l);
	}

	public synchronized void removeListener(EventHandler<MyActionEvent> l) {
		eventList.remove(l);
	}

	private void processEvent(MyActionEvent e) {
		System.out.println("size of actionListener list " + eventList.size());
		for (int i = 0; i < eventList.size(); i++) {
			eventList.get(i).handle(e);
			System.out.println("event is:" + e.getMsg());
		}
	}

}
