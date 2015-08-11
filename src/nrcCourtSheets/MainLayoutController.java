package nrcCourtSheets;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;

public class MainLayoutController implements Initializable
{		
	@FXML 
	private DatePicker datePicker;
	
	@FXML
	private Label dateLabel;
	
	@FXML
	private GridPane gridPane;
	
	@FXML
	private Label detailsType;
	
	@FXML
	private Label detailsCourt;
	
	@FXML
	private Label detailsDate;
	
	@FXML
	private Label detailsStartTime;
	
	@FXML 
	private Label detailsEndTime;
	
	@FXML
	private Label detailsWho;
	
	@FXML 
	private Button detailsEditButton;
	
	
	final Button gridPaneButton = new Button("+");
	
	private NRCMain application;
	
	final String dateFormat = "EEEE MMMM, dd yyyy";
	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat);
	
	final String timeFormat = "h:mm a";
	DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(timeFormat);
	
	LocalDate date;
	LocalTime time; 
	private int col = -1;
	private int row = -1;
	boolean buttonAdded = false;
	String textDate;
	
	private int courtNumber;
	double x;
	double y;
	
	private LocalDateTime startTime;
//	private LocalDateTime endTime;

	public void setApp(NRCMain application)
	{
		this.application = application;
//		CourtInfo values = application.getCourtInfo();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		assert datePicker != null : "fx:id=\"datePicker\" was not injected: check your FXML file 'MainLayout.fxml'.";
		assert dateLabel != null : "fx:id=\"dateLabel\" was not injected: check your FXML file 'MainLayout.fxml'.";
		assert gridPane != null : "fx:id=\"gridPane\" was not injected: check your FXML file 'MainLayout.fxml'.";
		assert detailsType != null : "fx:id=\"detailsType\" was not injected: check your FXML file 'MainLayout.fxml'.";
		assert detailsCourt != null : "fx:id=\"detailsCourt\" was not injected: check your FXML file 'MainLayout.fxml'.";
		assert detailsDate != null : "fx:id=\"detailsDate\" was not injected: check your FXML file 'MainLayout.fxml'.";
		assert detailsStartTime != null : "fx:id=\"detailsStartTime\" was not injected: check your FXML file 'MainLayout.fxml'.";
		assert detailsEndTime != null : "fx:id=\"detailsEndTime\" was not injected: check your FXML file 'MainLayout.fxml'.";
		assert detailsWho != null : "fx:id=\"detailsWho\" was not injected: check your FXML file 'MainLayout.fxml'.";
		assert detailsEditButton != null : "fx:id=\"detailsEditButton\" was not injected: check your FXML file 'MainLayout.fxml'.";
		
		
		// When a date is selected the label above the grid pane of lessons will reflect the date
		// selected and if that date is today it will prefix the date with *TODAY*
		
		datePicker.setValue(LocalDate.now());
		date = datePicker.getValue();
		textDate = date.format(dateFormatter);
		dateLabel.setText("*TODAY* " + textDate);
		
		datePicker.setOnAction(e -> 
		{
			if(buttonAdded = true)
			{
				gridPane.getChildren().remove(gridPaneButton);
			}
			date = datePicker.getValue();
			textDate = date.format(dateFormatter);
			if(date.equals(LocalDate.now()))
			{
				textDate = "*TODAY* " + textDate;
			}
				
			dateLabel.setText(textDate);
		});
		
		
		datePicker.setConverter(new StringConverter<LocalDate>()
			{
			
			@Override
			public String toString(LocalDate date) {
				if(date != null)
				{
					return dateFormatter.format(date);
				}
				else
				{
					return "";
				}
			}

			@Override
			public LocalDate fromString(String string) 
			{
				if(string != null && !string.isEmpty())
				{
					return LocalDate.parse(string, dateFormatter);
				}
				else
				{
					return null;
				}
			}
		});
		
		//Create a button in the cell that is clicked, if that button is clicked then open the edit court dialogue
		gridPane.setOnMouseClicked( m ->
		{
			gridPaneButton.setMaxWidth(Double.MAX_VALUE);
			gridPaneButton.setMaxHeight(Double.MAX_VALUE);
			gridPaneButton.setStyle("-fx-font: 18 arial; -fx-base: #FF4D4D;");
			gridPaneButton.disarm();
			

			x = m.getX();
			y = m.getY();
			
			final double cellWidth = (gridPane.getWidth() - 100) / 8;
			final double cellHeight = gridPane.getHeight() / 32;
			
			
			if(x > 100)
			{
				
				//Determine what column was pressed
				if(x < cellWidth + 100)
				{
					col = 1;
					courtNumber = 1;
				}
				else if(x < (cellWidth * 2) + 100)
				{
					col = 2;
					courtNumber = 2;
				}
				else if(x < (cellWidth * 3) + 100)
				{
					col = 3;
					courtNumber = 3;
				}
				else if(x < (cellWidth * 4) + 100)
				{
					col = 4;
					courtNumber = 4;
				}
				else if(x < (cellWidth * 5) + 100)
				{
					col = 5;
					courtNumber = 5;
				}
				else if(x < (cellWidth * 6)+ 100)
				{
					col = 6;
					courtNumber = 6;
				}
				else if(x < (cellWidth * 7) + 100)
				{
					col = 7;
					courtNumber = 7;
				}
				else
				{
					col = 8;
					courtNumber = 8;
				}
				
		
				//Determine what row was pressed
				setRowAndStartTime(y, cellHeight);
				
				
				System.out.println("Col: " + col + " Row: " + row);

				if(buttonAdded == false)
				{
					gridPane.getChildren().remove(gridPaneButton);
					gridPane.add(gridPaneButton, col, row);
					
				}
				else
				{
					gridPane.add(gridPaneButton, col, row);
					buttonAdded = true;
				}

//				gridPane.add(gridPaneButton, col, row);
				
				gridPaneButton.setOnAction(b -> {
					//Open the court manager window
					try {
						CourtBooking booking = new CourtBooking(startTime, courtNumber);
						booking.row = row;
						booking.col = col;
						application.gotoEditCourt(booking, this);
					} catch (Exception e1) {
						e1.printStackTrace();
					}

				});
			}
			
		});
	}

	public void createLesson(CourtBooking values)
	{
		
		int numberRows = (int)values.getRowSpan(values.getStartTime(), values.getEndTime());
		
		System.out.println("col: " + values.col + "row: " + values.row);
		GridPane.setRowSpan(values.button, numberRows);
		if(gridPane.getChildren().contains(values.button))
		{
			gridPane.getChildren().remove(values.button);
			gridPane.add(values.button, values.col, values.row);
		}
		else
		{
			gridPane.add(values.button, values.col, values.row);
			values.setDate(this.startTime);
		}

		
		values.button.setOnAction(lb -> {
			
			detailsCourt.setText("Court #" + values.courtNumber);
			detailsCourt.setVisible(true);
			
			String lessonDate = values.getDate().format(dateFormatter);
			detailsDate.setText(lessonDate);
			detailsDate.setVisible(true);
			
			String lessonStartTime = values.getStartTime().format(timeFormatter);
			detailsStartTime.setText("Start Time: " + lessonStartTime);
			detailsStartTime.setVisible(true);
			
			String lessonEndTime = values.getEndTime().format(timeFormatter);
			detailsEndTime.setText("End Time: " + lessonEndTime);
			detailsEndTime.setVisible(true);
			
			detailsWho.setText(values.who);
			detailsWho.setVisible(true);
			
			detailsEditButton.setVisible(true);
			detailsEditButton.setOnAction(deb -> {
				try
				{
					application.gotoEditCourt(values, this);
				}
				catch(Exception e)
				{
					System.out.println("Error when pressing detailsEditButton.");
					e.printStackTrace();
				}
			});
	
			switch (values.lessonType)
			{
				case CourtBooking.JDP:
				{
					detailsType.setText("JDP");
					detailsType.setVisible(true);
				} break;
				case CourtBooking.RESERVATION:
				{
					detailsType.setText("Reservation");
					detailsType.setVisible(true);
				} break;
				case CourtBooking.LESSON_COURT:
				{
					detailsType.setText("Lesson Court");
					detailsType.setVisible(true);
				}
				default: break;
			}
		});
	}
	
	public void updateCourtInfo(CourtBooking values)
	{
		
	}
	
	public void setRowAndStartTime(double y, double cellHeight)
	{
		
		if(y < cellHeight)
		{
			row = 0;
			startTime = LocalDateTime.of(this.date, LocalTime.of(7, 0));
		}
		else if(y < cellHeight * 2)
		{
			row = 1;
			startTime = LocalDateTime.of(this.date, LocalTime.of(7, 30));
		}
		else if(y < cellHeight * 3)
		{
			row = 2;
			startTime = LocalDateTime.of(this.date, LocalTime.of(8, 0));
		}
		else if(y < cellHeight * 4)
		{
			row = 3;
			startTime = LocalDateTime.of(this.date, LocalTime.of(8, 30));
		}
		else if(y < cellHeight * 5)
		{
			row = 4;
			startTime = LocalDateTime.of(this.date, LocalTime.of(9, 0));
		}
		else if(y < cellHeight * 6)
		{
			row = 5;
			startTime = LocalDateTime.of(this.date, LocalTime.of(9, 30));
		}
		else if(y < cellHeight * 7)
		{
			row = 6;
			startTime = LocalDateTime.of(this.date, LocalTime.of(10, 0));
		}
		else if(y < cellHeight * 8)
		{
			row = 7;
			startTime = LocalDateTime.of(this.date, LocalTime.of(10, 30));
		}
		else if(y < cellHeight * 9)
		{
			row = 8;
			startTime = LocalDateTime.of(this.date, LocalTime.of(11, 0));
		}
		else if(y < cellHeight * 10)
		{
			row = 9;
			startTime = LocalDateTime.of(this.date, LocalTime.of(11, 30));
		}
		else if(y < cellHeight * 11)
		{
			row = 10;
			startTime = LocalDateTime.of(this.date, LocalTime.of(12, 0));
		}
		else if(y < cellHeight * 12)
		{
			row = 11;
			startTime = LocalDateTime.of(this.date, LocalTime.of(12, 30));
		}
		else if(y < cellHeight * 13)
		{
			row = 12;
			startTime = LocalDateTime.of(this.date, LocalTime.of(13, 0));
		}
		else if(y < cellHeight * 14)
		{
			row = 13;
			startTime = LocalDateTime.of(this.date, LocalTime.of(13, 30));
		}
		else if(y < cellHeight * 15)
		{
			row = 14;
			startTime = LocalDateTime.of(this.date, LocalTime.of(14, 0));
		}
		else if(y < cellHeight * 16)
		{
			row = 15;
			startTime = LocalDateTime.of(this.date, LocalTime.of(14, 30));
		}
		else if(y < cellHeight * 17)
		{
			row = 16;
			startTime = LocalDateTime.of(this.date, LocalTime.of(15, 0));
		}
		else if(y < cellHeight * 18)
		{
			row = 17;
			startTime = LocalDateTime.of(this.date, LocalTime.of(15, 30));
		}
		else if(y < cellHeight * 19)
		{
			row = 18;
			startTime = LocalDateTime.of(this.date, LocalTime.of(16, 0));
		}
		else if(y < cellHeight * 20)
		{
			row = 19;
			startTime = LocalDateTime.of(this.date, LocalTime.of(16, 30));
		}
		else if(y < cellHeight * 21)
		{
			row = 20;
			startTime = LocalDateTime.of(this.date, LocalTime.of(17, 0));
		}
		else if(y < cellHeight * 22)
		{
			row = 21;
			startTime = LocalDateTime.of(this.date, LocalTime.of(17, 30));
		}
		else if(y < cellHeight * 23)
		{
			row = 22;
			startTime = LocalDateTime.of(this.date, LocalTime.of(18, 0));
		}
		else if(y < cellHeight * 24)
		{
			row = 23;
			startTime = LocalDateTime.of(this.date, LocalTime.of(18, 30));
		}
		else if(y < cellHeight * 25)
		{
			row = 24;
			startTime = LocalDateTime.of(this.date, LocalTime.of(19, 0));
		}
		else if(y < cellHeight * 26)
		{
			row = 25;
			startTime = LocalDateTime.of(this.date, LocalTime.of(19, 30));
		}
		else if(y < cellHeight * 27)
		{
			row = 26;
			startTime = LocalDateTime.of(this.date, LocalTime.of(20, 0));
		}
		else if(y < cellHeight * 28)
		{
			row = 27;
			startTime = LocalDateTime.of(this.date, LocalTime.of(20, 30));
		}
		else if(y < cellHeight * 29)
		{
			row = 28;
			startTime = LocalDateTime.of(this.date, LocalTime.of(21, 0));
		}
		else if(y < cellHeight * 30)
		{
			row = 29;
			startTime = LocalDateTime.of(this.date, LocalTime.of(21, 30));
		}
		else if(y < cellHeight * 31)
		{
			row = 30;
			startTime = LocalDateTime.of(this.date, LocalTime.of(22, 0));
		}
		else
		{
			row = 31;
			startTime = LocalDateTime.of(this.date, LocalTime.of(22, 30));
		}
	}
}

