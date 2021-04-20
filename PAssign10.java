
/* Ginny Lott-Shaw
 * CSCI 1302
 * Assignment 10
 * This program creates a window that calculates mileage based on user inputs of miles traveled and gallons of gas.  This program
 * also uses the metric units liters and kilometers should the user desire.
 */

package ch14;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PAssign10 extends Application {
	// default values/strings
	private double txtWidth = 125.0;
	private String defaultCalc = String.format("%.2f", 0.00);
	private String defaultEntry = String.format("%.2f", 0.00);
	private String defaultMileage = "Miles";
	private String defaultCapacity = "Gallons";
	private String defaultResult = "MPG";
	private String altMileage = "Kilometers";
	private String altCapacity = "Liters";
	private String altResult = "L/100KM";

	// create UI components split by type
	private Button btnCalc = new Button("Calculate");
	private Button btnReset = new Button("Reset");

	private Label lblDistance = new Label(defaultMileage);
	private Label lblCapacity = new Label(defaultCapacity);
	private Label lblResult = new Label(defaultResult);
	private Label lblEffType = new Label("Efficiency Type");

	private TextField tfDistance = new TextField(defaultEntry);
	private TextField tfCapacity = new TextField(defaultEntry);
	private TextField tfResult = new TextField(defaultCalc);

	private GridPane mainPane = new GridPane();
	private ComboBox<String> cmb1 = new ComboBox<String>();

	public void start(Stage primaryStage) {

		// create ComboBox
		cmb1.getItems().add(defaultMileage);
		cmb1.getItems().add(altResult);

		// ComboBox default
		cmb1.setValue(defaultMileage);

		// make ComboBox editable
		cmb1.setEditable(true);

		// Create action event
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (cmb1.getValue() == altResult) {
					lblDistance.setText(altMileage);
					lblCapacity.setText(altCapacity);
					lblResult.setText(altResult);
					
					// convert numbers
					double conversion1 = Double.parseDouble(tfDistance.getText());
					double answer1 = conversion1 / 0.621371;
					tfDistance.setText(String.format("%.2f", answer1));
					
					double conversion2 = Double.parseDouble(tfCapacity.getText());
					double answer2 = conversion2 / 0.264172;
					tfCapacity.setText(String.format("%.2f", answer2));
					
					double distance = Double.parseDouble(tfDistance.getText());
					double capacity = Double.parseDouble(tfCapacity.getText());
					double result = (distance != 0) ? capacity / (distance / 100.0) : 0;
					tfResult.setText(String.format("%.2f", result));
					
				} else {
					lblDistance.setText(defaultMileage);
					lblCapacity.setText(defaultCapacity);
					lblResult.setText(defaultResult);
					
					// convert numbers
					double conversion3 = Double.parseDouble(tfDistance.getText());
					double answer3 = conversion3 / 1.60934;
					tfDistance.setText(String.format("%.2f", answer3));
					
					double conversion4 = Double.parseDouble(tfCapacity.getText());
					double answer4 = conversion4 / 3.78541;
					tfCapacity.setText(String.format("%.2f", answer4));
					
					double distance = Double.parseDouble(tfDistance.getText());
					double capacity = Double.parseDouble(tfCapacity.getText());
					double result = (capacity != 0) ? distance / capacity : 0;
					tfResult.setText(String.format("%.2f", result));
				}
			}
		};

		// Set on action
		cmb1.setOnAction(event);

		// set preferences for UI components
		tfDistance.setMaxWidth(txtWidth);
		tfCapacity.setMaxWidth(txtWidth);
		tfResult.setMaxWidth(txtWidth);
		tfResult.setEditable(false);

		// create a main grid pane to hold items
		mainPane.setPadding(new Insets(10.0));
		mainPane.setHgap(txtWidth / 2.0);
		mainPane.setVgap(txtWidth / 12.0);

		// add items to mainPane
		mainPane.add(cmb1, 1, 0);
		mainPane.add(lblEffType, 0, 0);
		mainPane.add(lblDistance, 0, 2);
		mainPane.add(tfDistance, 1, 2);
		mainPane.add(lblCapacity, 0, 3);
		mainPane.add(tfCapacity, 1, 3);
		mainPane.add(lblResult, 0, 4);
		mainPane.add(tfResult, 1, 4);
		mainPane.add(btnReset, 0, 5);
		mainPane.add(btnCalc, 1, 5);

		// register action handlers
		btnCalc.setOnAction(e -> calcMileage());
		tfDistance.setOnAction(e -> calcMileage());
		tfCapacity.setOnAction(e -> calcMileage());
		tfResult.setOnAction(e -> calcMileage());
		btnReset.setOnAction(e -> resetForm());

		// create a scene and place it in the stage
		Scene scene = new Scene(mainPane);

		// set and show stage
		primaryStage.setTitle("Mileage Calculator");
		primaryStage.setScene(scene);
		primaryStage.show();

		// stick default focus in first field for usability
		tfDistance.requestFocus();
	}



	/**
	 * Calculate expenses based on entered figures
	 */
	private void calcMileage() {
		// set default values
		double distance = 0.0, capacity = 0.0;

		// make sure to get numeric values only
		if (tfCapacity.getText() != null && !tfCapacity.getText().isEmpty() && tfDistance.getText() != null
				&& !tfDistance.getText().isEmpty()) {
			distance = Double.parseDouble(tfDistance.getText());
			capacity = Double.parseDouble(tfCapacity.getText());
		}

		// check for type of calculation
		double result = 0.0;
		if (cmb1.getValue() != defaultMileage) {
			// liters / 100KM
			result = (distance != 0) ? capacity / (distance / 100.0) : 0;			
		} else {
			// MPG
			result = (capacity != 0) ? distance / capacity : 0;
		}

		// update calculation fields with currency formatting
		tfResult.setText(String.format("%.2f", result));
	}

	/**
	 * Reset all values in the application
	 */
	private void resetForm() {
		// reset all form fields
		cmb1.setValue(defaultResult);
		tfDistance.setText(defaultEntry);
		tfCapacity.setText(defaultEntry);
		tfResult.setText(defaultCalc);
		lblCapacity.setText(defaultCapacity);
		lblDistance.setText(defaultMileage);
		lblResult.setText(defaultResult);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
