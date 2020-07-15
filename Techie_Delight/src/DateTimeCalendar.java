import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTimeCalendar
{
	public static void main(String[] args) throws ParseException 
	{    
	     Date calendarDate = new GregorianCalendar(2020, Calendar.JUNE, 21, 18, 9, 22).getTime();
	     System.out.println(calendarDate);
	     SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
	     System.out.println("Time in 24 Hour format - " + sdf.format(calendarDate));
	     
	     /*Using LocalDate
	     If you want to create only date with no time values then you can use java.time.LocalDate*/
	     LocalDate localDate = LocalDate.of(2019, Month.APRIL, 3);
	     System.out.println("Date - " + localDate); // Date – 2019-04-03
	     DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
	     String formattedDate = localDate.format(dateFormatter);
	     System.out.println(formattedDate);
	     
	     /*Using LocalTime
	     If you want to specify only time values then you can use java.time.LocalTime*/
	     LocalTime localTime = LocalTime.of(17, 8, 32);
	     System.out.println("Time - " + localTime); //Time – 17:08:32
	     DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH mm ss");
	     String formattedTime = localTime.format(timeFormatter);
	     System.out.println(formattedTime);
	     
	     
	     /*Using LocalDateTime
		 If you want to specify both date and time values then you can use java.time.LocalDateTime*/
	     LocalDateTime dateTime = LocalDateTime.of(2018, Month.DECEMBER, 23, 13, 18, 23);
	     System.out.println("Date Time - " + dateTime);//2018-12-23T13:18:23
	     
	     
	     /*Using ZonedDateTime
	     Above mentioned classes doesn’t have time-zone information. If you want time-zone information then use ZonedDateTime and set the appropriate zone id.*/
	     ZonedDateTime zdt = ZonedDateTime.of(LocalDateTime.of(2018, Month.DECEMBER, 23, 13, 18, 23), ZoneId.of("Europe/Paris"));
	     System.out.println("Zoned time - " + zdt); //2018-12-23T13:18:23+01:00[Europe/Paris]
	     
	     MonthDay month = MonthDay.now();  
	     System.out.println(month);
	     LocalDate date = month.atYear(1994);  
	     System.out.println(date);  
	     
	     
	     //Calculate age from DOB
	     LocalDate today = LocalDate.now();                          //Today's date
	     LocalDate birthday = LocalDate.of(1995, Month.DECEMBER, 12);  //Birth date
	     
	     Period p = Period.between(birthday, today);
	     System.out.println(p.getYears()+" year "+ p.getMonths()+" month and "+ p.getDays()+" days");
	}
	
	
} 