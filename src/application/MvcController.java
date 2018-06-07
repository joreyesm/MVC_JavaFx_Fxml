package application;

import java.net.URL;
import java.util.ResourceBundle;

import interfaces.IController;
import interfaces.IModel;
import interfaces.IView;
import interfaces.impl.IncController;
import interfaces.impl.IncModel;
import javafx.beans.InvalidationListener;
import javafx.beans.WeakInvalidationListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class MvcController implements IView, Initializable {

	
	private IView view = this;
	private IModel modelo= new IncModel();
	private	IController controlador= new IncController(view,modelo);
	
	@FXML private TextField valor;
	
	public void incrementar() {
		controlador.incvalue();
	}

	@Override
	public void setController(IController controller) {
		this.controlador = controller;
	}

	@Override
	public void setValue(String value) {
        valor.setText(value);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		valor.focusedProperty().addListener(new WeakInvalidationListener(txtValueListener));

	}

	InvalidationListener txtValueListener = (event) -> {
		 if( !valor.isFocused() ) {
			 
			 try {
				 controlador.setModelValue(Integer.parseInt(valor.getText()));
			 }
			 catch (NumberFormatException e ) {
			 Alert alert = new Alert(AlertType.ERROR);
			 alert.setTitle("Error");
			 alert.setHeaderText("No ha ingresado un número entero");
			 alert.setContentText("Se requiere un número entero a partir del cual se reinicia el conteo.");
			 alert.show();}
		 };
		};
}
