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

    public String getMonth(int month) {
        switch (month) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            default:
                return "December";
        }
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
	
    public String toString() {
	String hr, min;
	if (hour < 10)
	   hr = "0" + hour;
	else
	   hr = hour + "";
	if (minute < 10)
	   min = "0" + minute;
	else
	   min = minute + "";
        return getMonth(month) + " " + getDay() + ", " + getYear() + " " + hr + ":" + min;
    }
}
