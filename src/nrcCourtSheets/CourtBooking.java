package nrcCourtSheets;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.scene.control.Button;

public class CourtBooking 
{

	boolean isNew = true;
	Button button;
	int courtNumber;
	private LocalDateTime date;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	
	final String dateFormat = "EEEE MMMM, dd yyyy";
	final String timeFormat = "HH:mm a";
	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat);
	DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(timeFormat);
	
	
	int row;
	int col;
	int lessonType;
	final static int JDP = 1;
	final static int RESERVATION = 2;
	final static int LESSON_COURT = 3;
	String who;
	
	public CourtBooking(LocalDateTime startTime, int courtNumber)
	{
		this.startTime = startTime;
		endTime = startTime.plusMinutes(30);
		this.courtNumber = courtNumber;
		//TODO: temp who value
		who = "Brian";
		
		button = new Button(who);
		button.setMaxWidth(Double.MAX_VALUE);
		button.setMaxHeight(Double.MAX_VALUE);
		
		button.setOnAction(b -> {
			
		});
	}
	
	public long getRowSpan(LocalDateTime startTime, LocalDateTime endTime)
	{
		
	    long diffInMinutes = java.time.Duration.between(startTime, endTime).toMinutes();
	    if(diffInMinutes > 1440) // 1440 min is one day
	    {
	    	//TODO: Court time too long
	    }
	    int diffInHalfHourInc = (int)diffInMinutes / 30;
	
		return diffInHalfHourInc;
	}
	
	public void setStartTime(LocalDateTime time)
	{
		startTime = time;
		setRowFromStartTime(startTime);
		System.out.println("Setting row from CourtBooking setRowFromStartTime method: " + row);
	}
	
	public void setEndTime(LocalDateTime time)
	{
		endTime = time;
	}
	public String startTimeToString()
	{
		String st = startTime.format(timeFormatter);
		return st;
	}
	public LocalDateTime getStartTime()
	{
		return startTime;
	}
	public String endTimeToString()
	{
		String st = startTime.format(timeFormatter);
		return st;
	}
	public LocalDateTime getEndTime()
	{
		return endTime;
	}
	
	private void setRowFromStartTime(LocalDateTime startTime)
	{
		if(startTime.equals(LocalDateTime.of(LocalDate.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth()), LocalTime.of(7, 0))))
		{
			row = 0;
		}
		else if(startTime.equals(LocalDateTime.of(LocalDate.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth()), LocalTime.of(7, 30))))
		{
			row = 1;
		}
		else if(startTime.equals(LocalDateTime.of(LocalDate.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth()), LocalTime.of(8, 0))))
		{
			row = 2;
		}
		else if(startTime.equals(LocalDateTime.of(LocalDate.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth()), LocalTime.of(8, 30))))
		{
			row = 3;
		}
		else if(startTime.equals(LocalDateTime.of(LocalDate.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth()), LocalTime.of(9, 0))))
		{
			row = 4;
		}
		else if(startTime.equals(LocalDateTime.of(LocalDate.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth()), LocalTime.of(9, 30))))
		{
			row = 5;
		}
		else if(startTime.equals(LocalDateTime.of(LocalDate.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth()), LocalTime.of(10, 0))))
		{
			row = 6;
		}
		else if(startTime.equals(LocalDateTime.of(LocalDate.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth()), LocalTime.of(10, 30))))
		{
			row = 7;
		}
		else if(startTime.equals(LocalDateTime.of(LocalDate.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth()), LocalTime.of(11, 0))))
		{
			row = 8;
		}
		else if(startTime.equals(LocalDateTime.of(LocalDate.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth()), LocalTime.of(11, 30))))
		{
			row = 9;
		}
		else if(startTime.equals(LocalDateTime.of(LocalDate.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth()), LocalTime.of(12, 0))))
		{
			row = 10;
		}
		else if (startTime.equals(LocalDateTime.of(LocalDate.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth()), LocalTime.of(12, 30))))
		{
			row = 11;
		}
		else if(startTime.equals(LocalDateTime.of(LocalDate.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth()), LocalTime.of(13, 0))))
		{
			row = 12;
		}
		else if(startTime.equals(LocalDateTime.of(LocalDate.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth()), LocalTime.of(13, 30))))
		{
			row = 13;
		}
		else if(startTime.equals(LocalDateTime.of(LocalDate.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth()), LocalTime.of(14, 0))))
		{
			row = 14;
		}
		else if(startTime.equals(LocalDateTime.of(LocalDate.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth()), LocalTime.of(14, 30))))
		{
			row = 15;
		}
		else if(startTime.equals(LocalDateTime.of(LocalDate.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth()), LocalTime.of(15, 0))))
		{
			row = 16;
		}
		else if(startTime.equals(LocalDateTime.of(LocalDate.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth()), LocalTime.of(15, 30))))
		{
			row = 17;
		}
		else if(startTime.equals(LocalDateTime.of(LocalDate.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth()), LocalTime.of(16, 0))))
		{
			row = 18;
		}
		else if(startTime.equals(LocalDateTime.of(LocalDate.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth()), LocalTime.of(16, 30))))
		{
			row = 19;
		}
		else if(startTime.equals(LocalDateTime.of(LocalDate.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth()), LocalTime.of(17, 0))))
		{
			row = 20;
		}
		else if(startTime.equals(LocalDateTime.of(LocalDate.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth()), LocalTime.of(17, 30))))
		{
			row = 21;
		}
		else if(startTime.equals(LocalDateTime.of(LocalDate.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth()), LocalTime.of(18, 0))))
		{
			row = 22;
		}
		else if(startTime.equals(LocalDateTime.of(LocalDate.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth()), LocalTime.of(18, 30))))
		{
			row = 23;
		}
		else if(startTime.equals(LocalDateTime.of(LocalDate.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth()), LocalTime.of(19, 0))))
		{
			row = 24;
		}
		else if(startTime.equals(LocalDateTime.of(LocalDate.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth()), LocalTime.of(19, 30))))
		{
			row = 25;
		}
		else if(startTime.equals(LocalDateTime.of(LocalDate.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth()), LocalTime.of(20, 0))))
		{
			row = 26;
		}
		else if(startTime.equals(LocalDateTime.of(LocalDate.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth()), LocalTime.of(20, 30))))
		{
			row = 27;
		}
		else if(startTime.equals(LocalDateTime.of(LocalDate.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth()), LocalTime.of(21, 0))))
		{
			row = 28;
		}
		else if(startTime.equals(LocalDateTime.of(LocalDate.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth()), LocalTime.of(21, 30))))
		{
			row = 29;
		}
		else if(startTime.equals(LocalDateTime.of(LocalDate.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth()), LocalTime.of(22, 0))))
		{
			row = 30;
		}
		else if(startTime.equals(LocalDateTime.of(LocalDate.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth()), LocalTime.of(22, 30))))
		{
			row = 31;
		}
	}
	
	public void setCourtNumber(double x, double cellWidth)
	{
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
	}

	public void setValues(CourtBooking values) {
		this.col = values.col;
		this.row = values.row;
		this.courtNumber = values.courtNumber;
		this.startTime = values.startTime;
		this.endTime = values.endTime;
		this.lessonType = values.lessonType;
		
				
		
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
}
