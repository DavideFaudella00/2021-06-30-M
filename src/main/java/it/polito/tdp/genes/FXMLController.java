package it.polito.tdp.genes;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.genes.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	Model model;
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnContaArchi;

	@FXML
	private Button btnRicerca;

	@FXML
	private TextArea txtResult;

	@FXML
	private TextField txtSoglia;

	@FXML
	void doContaArchi(ActionEvent event) {

		Double num = 0.0;
		try {
			num = Double.parseDouble(txtSoglia.getText());
		} catch (NumberFormatException n) {
			throw new NumberFormatException();
		}
		if (num < model.getPesoBasso()) {
			txtResult.appendText("Peso inserito troppo basso \n");
			return;
		}
		if (num > model.getPesoAlto()) {
			txtResult.appendText("Peso inserito troppo alto \n");
			return;
		}
		model.check(num);
		txtResult.appendText("Soglia " + num + " -->");
		txtResult.appendText("Maggiori: " + model.getNumP());
		txtResult.appendText(", Minori: " + model.getNumM() + "\n");
	}

	@FXML
	void doRicerca(ActionEvent event) {

	}

	@FXML
	void initialize() {
		assert btnContaArchi != null : "fx:id=\"btnContaArchi\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnRicerca != null : "fx:id=\"btnRicerca\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtSoglia != null : "fx:id=\"txtSoglia\" was not injected: check your FXML file 'Scene.fxml'.";

	}

	public void setModel(Model model) {
		this.model = model;
		model.creaGrafo();
		txtResult.setText("Grafo creato \n");
		txtResult.appendText("Peso minimo: " + model.getPesoBasso() + "\n");
		txtResult.appendText("Peso massimo: " + model.getPesoAlto() + "\n");
	}
}
