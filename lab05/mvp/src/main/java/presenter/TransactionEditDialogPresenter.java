package presenter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;
import model.Category;
import model.Transaction;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;

public class TransactionEditDialogPresenter {

	private Transaction transaction;

	@FXML
	private TextField dateTextField;

	@FXML
	private TextField payeeTextField;

	@FXML
	private TextField categoryTextField;

	@FXML
	private TextField inflowTextField;
	
	private Stage dialogStage;
	
	private boolean approved;
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void setData(Transaction transaction) {
		this.transaction = transaction;
		updateControls();
	}
	
	public boolean isApproved() {
		return approved;
	}
	
	@FXML
	private void handleOkAction(ActionEvent event) {
		this.updateModel();
		this.approved = true;
		this.dialogStage.close();
	}
	
	@FXML
	private void handleCancelAction(ActionEvent event) {
		this.dialogStage.close();
	}
	
	private void updateModel() {
		String pattern = "yyyy-MM-dd";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		LocalDateStringConverter converter = new LocalDateStringConverter(formatter, formatter);
		transaction.setDate(converter.fromString(this.dateTextField.getText()));

		transaction.setPayee(this.payeeTextField.getText());

		transaction.setCategory(new Category(this.categoryTextField.getText()));

		DecimalFormat decimalFormatter = new DecimalFormat();
		decimalFormatter.setParseBigDecimal(true);
		try {
			transaction.setInflow((BigDecimal) decimalFormatter.parse(inflowTextField.getText()));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void updateControls() {
		String pattern = "yyyy-MM-dd";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		LocalDateStringConverter converter = new LocalDateStringConverter(formatter, formatter);
		this.dateTextField.setText(converter.toString(this.transaction.getDate()));

		this.payeeTextField.setText(transaction.getPayee());

		this.categoryTextField.setText(transaction.getCategory().toString());

		this.inflowTextField.setText(transaction.getInflowProperty().getValue().toString());
	}
}
