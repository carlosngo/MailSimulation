package Model;

public class DateTime {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    public DateTime(int year, int month, int day, int hour, int minute) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }
    
    /**
     * Takes in a DateTime object and determines whether
     * its date is before or after a specific date
     */
    public boolean isBefore (DateTime d) {
        if (d.year > this.year) {
	    return true;
	} else if (d.year == this.year) {
	    if (d.month > this.month) {
	        return true;
	    } else if (d.month == this.month) {
		if (d.day > this.day) {
	            return true;
		} else if (d.day == this.day) {
		    if (d.hour > this.hour) {
			return true;
		    } else if (d.hour == this.hour) {
			if (d.minute > this.minute)
		 	    return true;
		    }
		}
	    }
	}		
	return false;
    }
	
    public boolean equals (Object obj) {
	DateTime dt = (DateTime) obj;    
	return year == dt.year && month == dt.month && day == dt.day && hour == dt.hour && minute == dt.minute;
    }
}
