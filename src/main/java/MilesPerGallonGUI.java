/**
 * File: MilesPerGallonGUI.java
 * Class: CSCI 1302
 * Author: Brian Abbott
 * Created on: April 17, 2026
 * Last Modified: April 17, 2026
 * Description: JavaFX GUI fuel efficiency calculator with functionality to toggle between MPG and Liter per 100km
 */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MilesPerGallonGUI extends Application {
	// TextFields for user input and result display
	private TextField tfMiles = new TextField();
	private TextField tfGallons = new TextField();
	private TextField tfMilesPerGallon = new TextField();

	// Named Labels so their text can be updated when toggling modes
	private Label lblDistance = new Label("Miles:");
	private Label lblFuel = new Label("Gallons:");
	private Label lblResult = new Label("Miles Per Gallon:");

	// Buttons for calculating and resetting
	private Button btCalculate = new Button("Calculate");
	private Button btReset = new Button("Reset");

	// RadioButtons for toggling between MPG and L/100KM
	private RadioButton rbMPG = new RadioButton("MPG");
	private RadioButton rbLPer100KM = new RadioButton("L/100KM");

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		// Create UI with Labels, TextFields, RadioButtons, and Buttons
		GridPane gridPane = new GridPane();
		gridPane.setHgap(5);
		gridPane.setVgap(5);

		// Add RadioButtons to row 0
		gridPane.add(rbMPG, 0, 0);
		gridPane.add(rbLPer100KM, 1, 0);

		// Add Labels and TextFields to rows 1-3
		gridPane.add(lblDistance, 0, 1);
		gridPane.add(tfMiles, 1, 1);
		gridPane.add(lblFuel, 0, 2);
		gridPane.add(tfGallons, 1, 2);
		gridPane.add(lblResult, 0, 3);
		gridPane.add(tfMilesPerGallon, 1, 3);

		// Add Reset and Calculate buttons to row 4
		gridPane.add(btReset, 0, 4);
		gridPane.add(btCalculate, 1, 4);

		// Add both RadioButtons to the same ToggleGroup
		ToggleGroup toggleGroup = new ToggleGroup();
		rbMPG.setToggleGroup(toggleGroup);
		rbLPer100KM.setToggleGroup(toggleGroup);

		// MPG selected by default
		rbMPG.setSelected(true);

		// Set properties for UI
		gridPane.setAlignment(Pos.CENTER);
		tfMiles.setAlignment(Pos.BOTTOM_RIGHT);
		tfGallons.setAlignment(Pos.BOTTOM_RIGHT);
		tfMilesPerGallon.setAlignment(Pos.BOTTOM_RIGHT);
		tfMilesPerGallon.setEditable(false);
		GridPane.setHalignment(btCalculate, HPos.RIGHT);
		GridPane.setHalignment(btReset, HPos.LEFT);

		// Process events with lambda expressions
		btCalculate.setOnAction(e -> calculateMPG());
		tfMiles.setOnAction(e -> calculateMPG());
		tfGallons.setOnAction(e -> calculateMPG());

		// Reset button clears all TextFields
		btReset.setOnAction(e -> resetFields());

		// RadioButton events update Labels and reset fields
		rbMPG.setOnAction(e -> updateLabels());
		rbLPer100KM.setOnAction(e -> updateLabels());

		// Create a scene and place it in the stage
		Scene scene = new Scene(gridPane, 400, 250);
		primaryStage.setTitle("MPG Calculator"); // Set title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}

	// Reset all TextFields to empty
	private void resetFields() {
		tfMiles.setText("");
		tfGallons.setText("");
		tfMilesPerGallon.setText("");
	}

	// Update Labels based on which RadioButton is selected
	private void updateLabels() {
		if (rbMPG.isSelected()) {
			// Set labels for MPG mode
			lblDistance.setText("Miles:");
			lblFuel.setText("Gallons:");
			lblResult.setText("Miles Per Gallon:");
		} else {
			// Set labels for L/100KM mode
			lblDistance.setText("Kilometers:");
			lblFuel.setText("Liters:");
			lblResult.setText("Liters Per 100KM:");
		}
		// Reset fields when switching modes
		resetFields();
	}

	// Calculate fuel efficiency based on selected mode
	private void calculateMPG() {
		// Make sure there are no blank or un-entered values
		if (tfMiles.getText() != null && !tfMiles.getText().isEmpty() &&
				tfGallons.getText() != null && !tfGallons.getText().isEmpty()) {

			if (rbMPG.isSelected()) {
				// MPG calculation: miles / gallons
				double miles = Double.parseDouble(tfMiles.getText());
				double gallons = Double.parseDouble(tfGallons.getText());
				double result = miles / gallons;
				tfMilesPerGallon.setText(String.format("%.2f", result));
			} else {
				// L/100KM calculation: liters / (kilometers / 100)
				double kilometers = Double.parseDouble(tfMiles.getText());
				double liters = Double.parseDouble(tfGallons.getText());
				double result = liters / (kilometers / 100);
				tfMilesPerGallon.setText(String.format("%.2f", result));
			}
		}
	}

	/**
	 * The main method is only needed for the IDE with limited
	 * JavaFX support. Not needed for running from the command line.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
