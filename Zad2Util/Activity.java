// klasa aktywności (zajęcia dydaktyczne, konsultacje), zawiera dzień tygodnia i godzinę rozpoczęcia, czas trwania, nazwę aktywności oraz adres (budynek i nr sali)

package Zad2Util;
import java.io.Serializable;
import java.time.*;

public class Activity implements Serializable{
	private WeekDay activityDay;
	private LocalTime activityStart;
	private String asctivityName;
	private Duration activityDuration;
	private UniAddress location;
	
	
	// set methods
	public void setName(String name){
		this.asctivityName = name;
	}
	
	public void setDuration(Duration duration){
		this.activityDuration = duration;
	}
	
	public void setStart(LocalTime beginningTime){
		this.activityStart = beginningTime;
	}
	
	public void setWeekDay(WeekDay day){
		this.activityDay = day;
	}
	
	public void setLocation (UniAddress location){
		this.location = location;
	}
	
	public void setTime(WeekDay day, LocalTime beginningTime, Duration duration){
		this.activityDay = day;
		this.activityStart = beginningTime;
		this.activityDuration = duration;
	}
	
	// get methods
	public String getName(){
		return this.asctivityName;
	}
	
	public Duration getDuration(){
		return this.activityDuration;
	}
	
	public LocalTime getStart(){
		return this.activityStart;
	}
	
	public WeekDay getWeekDay(){
		return this.activityDay;
	}
	
	public UniAddress getLocation (){
		return this.location;
	}
	
	// pozostałe metody
	public String toString(){
		return this.asctivityName+": "+this.activityDay+" "+this.activityStart+"-"+(this.activityStart.plus(this.activityDuration)+", p. "+this.location);
	}
	
	// konstruktory
	public Activity(WeekDay day, LocalTime beginningTime, Duration duration, String name, UniAddress location){
		this.activityDay = day;
		this.activityStart = beginningTime;
		this.activityDuration = duration;
		this.asctivityName = name;
		this.location = location;
	}
}