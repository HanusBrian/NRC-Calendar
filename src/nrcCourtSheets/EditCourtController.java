package nrcCourtSheets;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class EditCourtController implements Initializable
{

	//All controls from the edit court FXML file
	@FXML
	Label courtNumberLabel;
	
	@FXML
	Label dateLabel;
	
	@FXML
	ToggleGroup lessonTypeRadio;
	
	@FXML
	RadioButton jdpToggle;
	@FXML
	VBox jdpOptionsField;
	@FXML
	ChoiceBox<String> jdpProChoiceBox;
	@FXML
	ChoiceBox<String> jdpLevelChoiceBox;
	
	@FXML
	RadioButton reservationToggle;
	@FXML
	VBox reservationOptionsField;
	@FXML
	TextField reservationTextField;
	
	@FXML
	RadioButton lessonCourtToggle;
	@FXML
	VBox lessonCourtOptionsField;
	@FXML
	ChoiceBox<String> lessonCourtProChoiceBox;
	@FXML
	TextField lessonCourtClientTextField;
	
	@FXML
	Button startHoursPlusButton;
	@FXML
	Button startHoursMinusButton;
	@FXML
	TextField startHoursTextField;
	
	@FXML
	Button startMinutesPlusButton;
	@FXML
	Button startMinutesMinusButton;
	@FXML
	TextField startMinutesTextField;
	
	@FXML
	ChoiceBox<String> startChoiceBox;
	
	@FXML
	Button endHoursPlusButton;
	@FXML
	Button endHoursMinusButton;
	@FXML
	TextField endHoursTextField;
	
	@FXML
	Button endMinutesPlusButton;
	@FXML
	Button endMinutesMinusButton;
	@FXML
	TextField endMinutesTextField;
	
	@FXML
	ChoiceBox<String> endChoiceBox;
	
	@FXML
	Button submitButton;
	@FXML
	Button cancelButton;

	private NRCMain application;
	public MainLayoutController mlc;

	private CourtBooking values;
	
	private int lessonType;
	
//	private LocalDateTime startTime;
//	private LocalDateTime endTime;
//	private int courtNumber;
	
	final String dateFormat = "EEEE MMMM, dd yyyy";
	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat);
	String dateLabelText;
	

	public void initialize(URL arg0, ResourceBundle arg1)
	{
		
		assert courtNumberLabel != null : "fx:id=\"courtNumberLabel\" was not injected: check your FXML file 'MainLayout.fxml'.";
		assert dateLabel != null : "fx:id=\"dateLabel\" was not injected: check your FXML file 'MainLayout.fxml'.";
		assert lessonTypeRadio != null : "fx:id=\"lessonTypeRadio\" was not injected: check your FXML file 'MainLayout.fxml'.";
		assert jdpOptionsField != null : "fx:id=\"jdpOptionsField\" was not injected: check your FXML file 'MainLayout.fxml'.";
		assert jdpProChoiceBox != null : "fx:id=\"jdpProChoiceBox\" was not injected: check your FXML file 'MainLayout.fxml'.";
		assert jdpLevelChoiceBox != null : "fx:id=\"jdpLevelChoiceBox\" was not injected: check your FXML file 'MainLayout.fxml'.";
		assert reservationOptionsField != null : "fx:id=\"reservationOptionsField\" was not injected: check your FXML file 'MainLayout.fxml'.";
		assert reservationTextField != null : "fx:id=\"reservationTextField\" was not injected: check your FXML file 'MainLayout.fxml'.";		
		assert lessonCourtOptionsField != null : "fx:id=\"lessonCourtOptionsField\" was not injected: check your FXML file 'MainLayout.fxml'.";		
		assert lessonCourtProChoiceBox != null : "fx:id=\"lessonCourtProChoiceBox\" was not injected: check your FXML file 'MainLayout.fxml'.";		
		assert lessonCourtClientTextField != null : "fx:id=\"lessonCourtClientTextField\" was not injected: check your FXML file 'MainLayout.fxml'.";		
		assert startHoursPlusButton != null : "fx:id=\"startHoursPlusButton\" was not injected: check your FXML file 'MainLayout.fxml'.";
		assert startHoursMinusButton != null : "fx:id=\"startHoursMinusButton\" was not injected: check your FXML file 'MainLayout.fxml'.";
		assert startHoursTextField != null : "fx:id=\"startHoursTextField\" was not injected: check your FXML file 'MainLayout.fxml'.";
		assert startMinutesPlusButton != null : "fx:id=\"startMinutesPlusButton\" was not injected: check your FXML file 'MainLayout.fxml'.";
		assert startMinutesMinusButton != null : "fx:id=\"startMinutesMinusButton\" was not injected: check your FXML file 'MainLayout.fxml'.";
		assert startMinutesTextField != null : "fx:id=\"startMintesTextField\" was not injected: check your FXML file 'MainLayout.fxml'.";
		assert startChoiceBox != null : "fx:id=\"startChoiceBox\" was not injected: check your FXML file 'MainLayout.fxml'.";
		assert endHoursPlusButton != null : "fx:id=\"endHoursPlusButton\" was not injected: check your FXML file 'MainLayout.fxml'.";
		assert endHoursMinusButton != null : "fx:id=\"endHoursMinusButton\" was not injected: check your FXML file 'MainLayout.fxml'.";
		assert endHoursTextField != null : "fx:id=\"endHoursTextField\" was not injected: check your FXML file 'MainLayout.fxml'.";
		assert endMinutesPlusButton != null : "fx:id=\"endMinutesPlusButton\" was not injected: check your FXML file 'MainLayout.fxml'.";
		assert endMinutesMinusButton != null : "fx:id=\"endMinutesMinusButton\" was not injected: check your FXML file 'MainLayout.fxml'.";
		assert endMinutesTextField != null : "fx:id=\"endMintesTextField\" was not injected: check your FXML file 'MainLayout.fxml'.";
		assert endChoiceBox != null : "fx:id=\"endChoiceBox\" was not injected: check your FXML file 'MainLayout.fxml'.";
		assert submitButton != null : "fx:id=\"submitButton\" was not injected: check your FXML file 'MainLayout.fxml'.";
		assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'MainLayout.fxml'.";


	}
	
	//TODO: Display date in edit window and make the time wrap around when 24 hours has been reached
	
	void init(CourtBooking values) 
	{

//		this.values = valuesIn;
		// For editing time slots, plug in saved values into the edit court window
		if(values.isNew == false)
		{

			courtNumberLabel.setText("Court # " + values.courtNumber);
			dateLabelText = values.getStartTime().format(dateFormatter);
			dateLabel.setText(dateLabelText);
			
			displayTime(values.getStartTime(), startHoursTextField, startMinutesTextField, startChoiceBox);
			values.setStartTime(values.getStartTime());
			
			displayTime(values.getEndTime(), endHoursTextField, endMinutesTextField, endChoiceBox);
			values.setEndTime(values.getEndTime());
			
			if(values.lessonType == CourtBooking.JDP)
			{
				jdpToggle.setSelected(true);
			}
			else if(values.lessonType == CourtBooking.RESERVATION)
	
			{
				reservationToggle.setSelected(true);
			}
			else if(values.lessonType == CourtBooking.LESSON_COURT)
			{
				lessonCourtToggle.setSelected(true);
			}

		}
		// For brand new lessons put in default values of the time slot selected and end time of half an hour later
		else
		{
			
			values.setEndTime(values.getStartTime().plusMinutes(30));
			courtNumberLabel.setText("Court # " + values.courtNumber);
			dateLabelText = values.getStartTime().format(dateFormatter);
			dateLabel.setText(dateLabelText);
		
			displayTime(values.getStartTime(), startHoursTextField, startMinutesTextField, startChoiceBox);
			values.setStartTime(values.getStartTime());
	
			values.setEndTime(values.getStartTime().plusMinutes(30));
			displayTime(values.getEndTime(), endHoursTextField, endMinutesTextField, endChoiceBox);
			
		}
		
		//Start time buttons
		startHoursPlusButton.setOnAction(shpb -> {
			values.setStartTime(values.getStartTime().plusHours(1));
			displayTime(values.getStartTime(), startHoursTextField, startMinutesTextField, startChoiceBox);
			checkDateTime(values);
		});
		startHoursMinusButton.setOnAction(shmb -> {
			values.setStartTime(values.getStartTime().minusHours(1));
			displayTime(values.getStartTime(), startHoursTextField, startMinutesTextField, startChoiceBox);
			checkDateTime(values);
		});
		
		startMinutesPlusButton.setOnAction(smpb -> {
			values.setStartTime(values.getStartTime().plusMinutes(30));
			displayTime(values.getStartTime(), startHoursTextField, startMinutesTextField, startChoiceBox);
			checkDateTime(values);
		});
		startMinutesMinusButton.setOnAction(smpb -> {
			values.setStartTime(values.getStartTime().minusMinutes(30));
			displayTime(values.getStartTime(), startHoursTextField, startMinutesTextField, startChoiceBox);
			checkDateTime(values);
		});

		if(values.getStartTime().getHour() < 12)
		{
			startChoiceBox.setValue("AM");
		}
		else
		{
			startChoiceBox.setValue("PM");
		}
		startChoiceBox.getItems().addAll("AM", "PM");
		
		// End time buttons 
		endHoursPlusButton.setOnAction(ehpb -> {
			values.setEndTime(values.getEndTime().plusHours(1));
			displayTime(values.getEndTime(), endHoursTextField, endMinutesTextField, endChoiceBox);
			checkDateTime(values);
	
		});
		endHoursMinusButton.setOnAction(ehmb -> {
			values.setEndTime(values.getEndTime().minusHours(1));
			displayTime(values.getEndTime(), endHoursTextField, endMinutesTextField, endChoiceBox);
			checkDateTime(values);

		});
		endMinutesPlusButton.setOnAction(empb -> {
			values.setEndTime(values.getEndTime().plusMinutes(30));
			displayTime(values.getEndTime(), endHoursTextField, endMinutesTextField, endChoiceBox);
			checkDateTime(values);
		});
		endMinutesMinusButton.setOnAction(empb -> {
			values.setEndTime(values.getEndTime().minusMinutes(30));
			displayTime(values.getEndTime(), endHoursTextField, endMinutesTextField, endChoiceBox);
			checkDateTime(values);
		});

		if(values.getEndTime().getHour() < 12)
		{
			endChoiceBox.setValue("AM");
		}
		else
		{
			endChoiceBox.setValue("PM");
		}
		endChoiceBox.getItems().addAll("AM", "PM");
		
		lessonTypeRadio.selectedToggleProperty().addListener(ltr -> {
			if(lessonTypeRadio.getSelectedToggle() == jdpToggle)
			{
				if(lessonCourtOptionsField != null)
				{
					lessonCourtOptionsField.setVisible(false);
				}
				if(reservationOptionsField != null)
				{
					reservationOptionsField.setVisible(false);
				}
				values.lessonType = CourtBooking.JDP;
				values.button.setStyle("-fx-base: #41CF34");
				jdpOptionsField.setVisible(true);
			}
			else if(lessonTypeRadio.getSelectedToggle() == reservationToggle)
			{
				if(jdpOptionsField != null)
				{
					jdpOptionsField.setVisible(false);
				}
				if(lessonCourtOptionsField != null)
				{
					lessonCourtOptionsField.setVisible(false);
				}
				values.lessonType = CourtBooking.RESERVATION;
				values.button.setStyle("-fx-base: #348FCF");
				reservationOptionsField.setVisible(true);
			}
			else if(lessonTypeRadio.getSelectedToggle() == lessonCourtToggle)
			{
				if(jdpOptionsField != null)
				{
					jdpOptionsField.setVisible(false);
				}
				if(reservationOptionsField != null)
				{
					reservationOptionsField.setVisible(false);
				}
				values.lessonType = CourtBooking.LESSON_COURT;
				values.button.setStyle("-fx-base: #C134CF");
				lessonCourtOptionsField.setVisible(true);
			}
			else 
			{
				lessonType = -1;
			}
		});
		
		//Save nothing and exit
		cancelButton.setOnAction(cb -> {
			application.closePopUpWindow();
		});
		
		//Submit button will take care of start and end time errors.
		submitButton.setOnAction(sb -> {
			System.out.println("Submit button pressed");

			if(values.isNew == true)
			{
				values.isNew = false;
				mlc.createLesson(values);
			}
			else
			{
				
				mlc.createLesson(values);
			}
			application.closePopUpWindow();
		});
	}

	private void displayTime(LocalDateTime time, TextField textFieldHours, TextField textFieldMinutes, ChoiceBox<String> choiceBox)
	{
//		System.out.println(time);
//		System.out.println(time.getHour() > 12);
		if(time.getHour() >= 12)
		{
			if(time.getHour() == 12)
			{
				textFieldHours.setText(String.valueOf(time.getHour()));
			}
			else
			{
				textFieldHours.setText(String.valueOf(time.getHour() - 12));
			}
			textFieldMinutes.setText(String.format("%02d", time.getMinute()));
			choiceBox.setValue("PM");
		}
		else
		{
			if(time.getHour() == 0)
			{
				textFieldHours.setText("12");
				textFieldMinutes.setText(String.format("%02d", time.getMinute()));
				choiceBox.setValue("AM");
			}
			else
			{
				textFieldHours.setText(String.valueOf(time.getHour()));
				textFieldMinutes.setText(String.format("%02d", time.getMinute()));
				choiceBox.setValue("AM");
			}
		}
		dateLabelText = time.format(dateFormatter);
		dateLabel.setText(dateLabelText);
	}
	
	private void checkDateTime(CourtBooking values)
	{
		if(values.getStartTime().compareTo(values.getEndTime()) >= 0)
		{
			values.setEndTime(values.getStartTime().plusMinutes(30));
			displayTime(values.getEndTime(), endHoursTextField, endMinutesTextField, endChoiceBox);
		}
	}
	 
	public void setApp(NRCMain nrcMain) {
		this.application = nrcMain;
		
	}
}
