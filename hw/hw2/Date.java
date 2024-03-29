/* Date.java */

import java.io.*;

class Date {

  /* Put your private data fields here. */

	int month;
	int day;
	int year;
  /** Constructs a date with the given month, day and year.   If the date is
   *  not valid, the entire program will halt with an error message.
   *  @param month is a month, numbered in the range 1...12.
   *  @param day is between 1 and the number of days in the given month.
   *  @param year is the year in question, with no digits omitted.
   */
  public Date(int month, int day, int year) {
		if(isValidDate(month, day, year)){
			this.month = month;
			this.day = day;
			this.year = year;
		} else {
			System.exit(0);
			throw new Error("The date that you entered is incorrect.");
		}
  }

  /** Constructs a Date object corresponding to the given string.
   *  @param s should be a string of the form "month/day/year" where month must
   *  be one or two digits, day must be one or two digits, and year must be
   *  between 1 and 4 digits.  If s does not match these requirements or is not
   *  a valid date, the program halts with an error message.
   */
  public Date(String s) {
		int divOne = s.indexOf('/');
		int divTwo = s.lastIndexOf('/');
		month = Integer.parseInt(s.substring(0, divOne));
		day = Integer.parseInt(s.substring(divOne + 1, divTwo));
		year = Integer.parseInt(s.substring(divTwo + 1, s.length()));
		if(!isValidDate(month, day, year)) {
			throw new Error("This date is not a valid date.");
		}
  }

  /** Checks whether the given year is a leap year.
   *  @return true if and only if the input year is a leap year.
   */
  public static boolean isLeapYear(int year) {
		if(year % 4 == 0) {
			if(year % 100 == 0) {
				if(year % 400 == 0) {
					return true;
				}
				return false;
			}
			return true;
		}
		return false;
  }

  /** Returns the number of days in a given month.
   *  @param month is a month, numbered in the range 1...12.
   *  @param year is the year in question, with no digits omitted.
   *  @return the number of days in the given month.
   */
  public static int daysInMonth(int month, int year) {
		switch(month) {
			case 1:	
			case 3:	
			case 5:	
			case 7:
			case 8:
			case 10:
			case 12:
				return 31;
			case 2:
				if(isLeapYear(year)) {
					return 29;
				} else {
					return 28;
				}
			default:
				return 30;
		}
  }

  /** Checks whether the given date is valid.
   *  @return true if and only if month/day/year constitute a valid date.
   *
   *  Years prior to A.D. 1 are NOT valid.
   */
  public static boolean isValidDate(int month, int day, int year) {
		if (year < 1 || month < 1 || day < 1 || month > 12 || day > daysInMonth(month, year) || year > 2 * year) {
			return false;
		}
		return true;
  }

  /** Returns a string representation of this date in the form month/day/year.
   *  The month, day, and year are expressed in full as integers; for example,
   *  12/7/2006 or 3/21/407.
   *  @return a String representation of this date.
   */
  public String toString() {
		return month + "/" + day + "/" + year;
  }

  /** Determines whether this Date is before the Date d.
   *  @return true if and only if this Date is before d. 
   */
  public boolean isBefore(Date d) {
		return (this.year < d.year || this.year == d.year && this.month < d.month || this.year == d.year && this.month == d.month && this.day < d.day);
  }

  /** Determines whether this Date is after the Date d.
   *  @return true if and only if this Date is after d. 
   */
  public boolean isAfter(Date d) {
		return (!this.isBefore(d) && !this.equals(d));
  }

	/**
	 *  Checks the equality of the Date objects.
	 *  @param d
	 *  @return 0 if equal, 1 if not.
	 */
	public boolean equals(Date d) {
		return (this.year == d.year && this.month == d.month && this.day == d.day);
	}
  /** Returns the number of this Date in the year.
   *  @return a number n in the range 1...366, inclusive, such that this Date
   *  is the nth day of its year.  (366 is used only for December 31 in a leap
   *  year.)
   */
  public int dayInYear() {
		int totalDays = 0;
		for (int i = 0; i < month; i++) {
			totalDays += daysInMonth(i, year); // For each month that's passed, get the number of days that month had.
		}
		totalDays += day; // Add today to that count. Since it return an integer -- say 17 -- we simply add 17 to the sum of days.
    return totalDays;
  }

  /** Determines the difference in days between d and this Date.  For example,
   *  if this Date is 12/15/2012 and d is 12/14/2012, the difference is 1.
   *  If this Date occurs before d, the result is negative.
   *  @return the difference in days between d and this date.
   */
  public int difference(Date d) {
		Date first = null;
		Date second = null; //This is going to be the bigger one.
		int days = 0;
		if (this.isBefore(d)) {
			first = this;
			second = d;
		} else {
			first = d;
			second = this;
		}
		if (first.year == second.year) {
			return (second.dayInYear() - first.dayInYear());
		}
		if (isLeapYear(first.year)) {
			days += (366 - first.dayInYear());
		} else {
			days += (365 - first.dayInYear());
		}
		for (int i = first.year + 1; i < second.year; i++) {
			if(isLeapYear(i)){
				days += 366;
			} else {
				days += 365;
			}
		}
		days += second.dayInYear();
		if ((first.year < 0) && (second.year > 0)) {
			days -= 366; // There is not a year = 0 AD
		}
		if (first == this) {
			return -days;
		} else {
			return days;
		}
	}

  public static void main(String[] argv) {
    System.out.println("\nTesting constructors.");
    Date d1 = new Date(1, 1, 1);
    System.out.println("Date should be 1/1/1: " + d1);
    d1 = new Date("2/4/2");
    System.out.println("Date should be 2/4/2: " + d1);
    d1 = new Date("2/29/2000");
    System.out.println("Date should be 2/29/2000: " + d1);
    d1 = new Date("2/29/1904");
    System.out.println("Date should be 2/29/1904: " + d1);

    d1 = new Date(12, 31, 1975);
    System.out.println("Date should be 12/31/1975: " + d1);
    Date d2 = new Date("1/1/1976");
    System.out.println("Date should be 1/1/1976: " + d2);
    Date d3 = new Date("1/2/1976");
    System.out.println("Date should be 1/2/1976: " + d3);

    Date d4 = new Date("2/27/1977");
    Date d5 = new Date("8/31/2110");

    /* I recommend you write code to test the isLeapYear function! */

    System.out.println("\nTesting before and after.");
    System.out.println(d2 + " after " + d1 + " should be true: " + 
                       d2.isAfter(d1));
    System.out.println(d3 + " after " + d2 + " should be true: " + 
                       d3.isAfter(d2));
    System.out.println(d1 + " after " + d1 + " should be false: " + 
                       d1.isAfter(d1));
    System.out.println(d1 + " after " + d2 + " should be false: " + 
                       d1.isAfter(d2));
    System.out.println(d2 + " after " + d3 + " should be false: " + 
                       d2.isAfter(d3));

    System.out.println(d1 + " before " + d2 + " should be true: " + 
                       d1.isBefore(d2));
    System.out.println(d2 + " before " + d3 + " should be true: " + 
                       d2.isBefore(d3));
    System.out.println(d1 + " before " + d1 + " should be false: " + 
                       d1.isBefore(d1));
    System.out.println(d2 + " before " + d1 + " should be false: " + 
                       d2.isBefore(d1));
    System.out.println(d3 + " before " + d2 + " should be false: " + 
                       d3.isBefore(d2));

    System.out.println("\nTesting difference.");
    System.out.println(d1 + " - " + d1  + " should be 0: " + 
                       d1.difference(d1));
    System.out.println(d2 + " - " + d1  + " should be 1: " + 
                       d2.difference(d1));
    System.out.println(d3 + " - " + d1  + " should be 2: " + 
                       d3.difference(d1));
    System.out.println(d3 + " - " + d4  + " should be -422: " + 
                       d3.difference(d4));
    System.out.println(d5 + " - " + d4  + " should be 48762: " + 
                       d5.difference(d4));
  }
}
