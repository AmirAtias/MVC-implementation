
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;

public class CircleModel2 implements CircleEvents {
	private SimpleDoubleProperty radius = new SimpleDoubleProperty(25);
	private SimpleBooleanProperty filled = new SimpleBooleanProperty();
	private SimpleBooleanProperty calculateArea = new SimpleBooleanProperty();
	private ObjectProperty<Color> theColor = new SimpleObjectProperty<>(Color.BLACK);
	private ObservableMap<eventType, ObservableList<EventHandler<MyActionEvent>>> circleHashMap = FXCollections
			.observableHashMap();
	private int circleCounter;

	public CircleModel2(int circleCounter) {
		this.circleCounter = circleCounter;
		for (eventType et : eventType.values())
			circleHashMap.put(et, FXCollections.observableArrayList());
	}

	public int getCircleCounter() {
		return circleCounter;
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

	public boolean getCalculateArea() {
		return calculateArea.get();
	}

	public void setRadius(double radius) {
		this.radius.set(radius);
		porcessEvent(eventType.RADIUS, new MyActionEvent(this, eventType.RADIUS.toString()));
	}

	public void setColor(Color theColor) {
		this.theColor.setValue(theColor);
		porcessEvent(eventType.COLOR, new MyActionEvent(this, eventType.COLOR.toString()));
	}

	public void setfilled(boolean filled) {
		this.filled.setValue(filled);
		porcessEvent(eventType.FILLED, new MyActionEvent(this, eventType.FILLED.toString()));
	}

	public void setCalculateArea(boolean calculateArea) {
		this.calculateArea.setValue(calculateArea);
		porcessEvent(eventType.AREA, new MyActionEvent(this, eventType.AREA.toString()));
	}

	public synchronized void addActionListener(EventHandler<MyActionEvent> l, eventType et) {
		ObservableList<EventHandler<MyActionEvent>> al;
		al = circleHashMap.get(et);
		if (al == null)
			al = FXCollections.observableArrayList();
		al.add(l);
		circleHashMap.put(et, al);
	}

	public synchronized void RemoveActionListener(EventHandler<MyActionEvent> l, eventType et) {
		ObservableList<EventHandler<MyActionEvent>> al;
		al = circleHashMap.get(et);
		if (al != null && al.contains(l))
			al.remove(l);
		circleHashMap.put(et, al);
	}

	private void porcessEvent(eventType et, MyActionEvent e) {
		ObservableList<EventHandler<MyActionEvent>> al;
		synchronized (this) {
			al = circleHashMap.get(et);
			if (al == null)
				return;
		}
		System.out.println("mode Number: " + (getCircleCounter() + 1) + " actionCommand " + e.getMsg()
				+ " array size is : " + al.size());
		for (int i = 0; i < al.size(); i++) {
			EventHandler<MyActionEvent> listener = al.get(i);
			listener.handle(e);
		}
	}

}
