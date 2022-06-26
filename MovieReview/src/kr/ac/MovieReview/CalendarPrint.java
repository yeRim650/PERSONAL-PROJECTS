package kr.ac.MovieReview;

import java.util.Calendar;

import javax.swing.JToggleButton;


public class CalendarPrint {
	JToggleButton[] buttons;
	Calendar cal = Calendar.getInstance();
	int year;
	int month;
	
	public CalendarPrint() {
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;
	}
	public void setButtons(JToggleButton[] buttons) {
		this.buttons = buttons;
	}	
	public String getCalText() {
		return year + "³â" + month + "¿ù";
	}
	public void calSet() {
		
		cal.set(year, month-1, 1);
		int firstDay = cal.get(Calendar.DAY_OF_WEEK);
		firstDay--;
		for(int i = 1; i <= cal.getActualMaximum(cal.DATE); i++) {
			
			buttons[6 + firstDay + i].setText(String.valueOf(i));
		}
	}
	
	public void init(int gap) {
		
		for(int i =7; i < buttons.length; i++) {
			buttons[i].setText("");
		}
		month += gap;
		if(month <= 0) {
			year--;
			month = 12;
		} else if(month >= 13) {
			year++;
			month = 1;
		}
		calSet();
	}
}
