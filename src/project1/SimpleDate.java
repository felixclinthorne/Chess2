package project1;

import java.util.*;
import java.io.*;

/**
 * Stores and analyzes calendar dates.
 * @author clinthof
 * 1/10/2019
 */

public class SimpleDate {

    /**
     * Integer month represents the current month, day represents the current day,
     * and year represents the current year
     */
    private int month, day, year;
    /**
     *  Static variables
     */
    private static int counter = 0;

    private static int numSimpleDates = 0;
    /**
     * Static constants
     */
    private static final int NUM_MONTHS = 12;

    private static final int DAYS_IN_YEAR = 365;

    public static final int MIN_YEAR = 1753;

    private static final SimpleDate MIN_DATE
            = new SimpleDate(1, 1, 1753);

    // Days in each month
    private static final int[] DAYS_IN_MONTH = {0, 31, 28, 31, 30, 31, 30,
            31, 31, 30, 31, 30, 31};
    //Days in each month during a leap year
    private static final int[] LEAP_DAYS_IN_MONTH = {0, 31, 29, 31, 30, 31,
            30, 31, 31, 30, 31, 30, 31};
    //Days passed in the year starting from January 1st
    private static final int[] DAYS_BEFORE = {0, 0, 31, 59, 90, 120, 151,
            181, 212, 243, 273, 304, 334, 365};
    //Days passed in the year starting from January 1st for a leap year
    private static final int[] LEAP_DAYS_BEFORE = {0, 31, 60, 91, 121,
            152, 182, 213, 244, 274, 305, 335, 366};
    //An array holding a set of strings for the month names
    private static final String[] MONTH_NAME = {"", "January ",
            "February ", "March ", "April ", "May ", "June ", "July ",
            "August ", "September ", "October ",
            "November ", "December "};

    /******************************************************************
     * Default constructor initializing month, day, and year at 0 when
     * no values are passed, as well
     * as incrementing the number of simple dates created.
     *****************************************************************/
    public SimpleDate(){

        day = 0;
        month = 0;
        year = 0;
        numSimpleDates++;
    }

    /******************************************************************
     * Constructor taking month, day, and year as integers.
     *
     * @param month the month
     * @param day   the day
     * @param year  the year
     * @throws IllegalArgumentException if input
     * doesn't represent a valid date.
     *****************************************************************/
    public SimpleDate(int month, int day, int year){

            this.month = month;
            this.day = day;
            this.year = year;
            numSimpleDates++;
    }

    /******************************************************************
     * A constructor that takes the "other" object passed and sets it
     * equal to the "this" fields.
     *
     * @param other represents the SimpleDate object.
     *****************************************************************/
    public SimpleDate(SimpleDate other){

        this.day = other.day;
        this.year = other.year;
        this.month = other.month;

    }

    /******************************************************************
     * A constructor that accepts a string that represents
     * a date
     *
     * @param date A string that represents a date
     * @throws IllegalArgumentException if parameter doesn't
     * represent a valid date.
     *****************************************************************/
    public SimpleDate(String date){

        String[] parts = date.split("/");

        if (parts.length != 3){
            throw new IllegalArgumentException("Invalid Date");
        } else {
            month = Integer.parseInt(parts[0].trim());
            day = Integer.parseInt(parts[1].trim());
            year = Integer.parseInt(parts[2].trim());
        }

        if (year >= MIN_YEAR && (month > 0 && month <= NUM_MONTHS) &&
                (day > 0 && day <= getDaysInMonth(month, year))){
            this.setMonth(month);
            this.setYear(year);
            this.setDay(day);
            numSimpleDates++;
        } else {
            throw new IllegalArgumentException("Invalid Date");
        }
    }

    /******************************************************************
     * A method that casts the object "other" as a SimpleDate and
     * creates a new SimpleDate object "temp" to represent it.  Each
     * object passed to the succeeding equals method returns a boolean.
     *
     * @param other SimpleDate tested.
     * @return returns true if "this" and "other" are equal.
     ******************************************************************/
    public boolean equals(Object other){

        SimpleDate temp = (SimpleDate) other;

        return equals(this, temp);
    }

    /******************************************************************
     * A method that tests two SimpleDate objects, s1 and s2, by
     * comparing each day, month, and year.  The boolean "equals" is
     * then returned.
     *
     * @param s1 SimpleDate compared to s2.
     * @param s2 SimpleDate compared to s1.
     * @return returns "equals" boolean as true if they are equal.
     ******************************************************************/
    private static boolean equals(SimpleDate s1, SimpleDate s2){

        return ((s1.day == s2.day) && (s1.month == s2.month)
            && (s1.year == s2.year));
    }

    /******************************************************************
     * A method that compares the "this" SimpleDate to the "other"
     * SimpleDate.  In the case that the year of "this" is larger than
     * "other", 1 is returned.  If "this" is the lesser of the two,
     * then -1 is returned.  In the instance that they are the same,
     * then 0 is returned.
     *
     * @param other SimpleDate compared to "this"
     * @return depending on the result of the condition, will return
     * -1, 0, or 1.
    ******************************************************************/
    public int compareTo(SimpleDate other){

        if (this.year > other.year) {
            return 1;
        } else if (this.year < other.year) {
            return -1;
        }
        else {
            return 0;
        }
    }

    /******************************************************************
     * A method that passes the "this" SimpleDate object through the
     * succeeding static method, taking the year as a parameter.
     * Then, it either returns true or false.
     *
     * @return returns true if the year is a leap year and false
     * otherwise.
     *****************************************************************/
    public boolean isLeapYear(){

        return ((this.year % 4 == 0 && this.year % 100 != 0)
                || (this.year % 400 == 0));
    }

    /******************************************************************
     * A method that will test if the current year is divisible by 4,
     * indivisible by 100, and indivisible by 400.  If so, it is a
     * leap year.
     *
     * @param year
     * @return returns true if a leap year and false otherwise.
     *****************************************************************/
    public static boolean isLeapYear(int year){

        return ((year % 4 == 0) && (year % 100 != 0
                || year % 400 == 0));
    }

    /******************************************************************
     * A method that returns the days passed in the year based on
     * whether or not the current year is a leap year.  Depending on
     * the year, it may refer to the LEAP_DAYS_BEFORE array or simply
     * the DAYS_BEFORE array.
     *
     * @return returns the amount of days before the current date and
     * varies if it is a leap year.
     *****************************************************************/
    public int ordinalDate(){

        if (this.isLeapYear()){
            return LEAP_DAYS_BEFORE[this.month] + this.day;
        } else {
            return DAYS_BEFORE[this.month] + this.day;
        }
    }
    /******************************************************************
     * A method that increments "this" based on the corresponding
     * month. It firsts checks if the year is a leap year and adjusts.
     * Then, it checks if the next day will be January 1st of the next
     * year. If so, all date values will be incremented.  If it is not
     * December but still the last day of the month at the index,
     * day's value will be updated to 1 and month will be incremented.
     * If it is not any ot those, day will simply be incremented.
     *****************************************************************/
    public void increment(){

        if (isLeapYear(this.year)){
            DAYS_IN_MONTH[2] = 29;
        }
        if (this.month == 12 && DAYS_IN_MONTH[this.month] == day){
            this.day = 1;
            this.month = 1;
            this.year++;
        }
        else if (this.month != 12 && DAYS_IN_MONTH[this.month] == day){
            this.day = 1;
            this.month++;
        }
        else if (this.day != DAYS_IN_MONTH[this.month]){
            this.day++;
        }
    }

    /******************************************************************
     * Similar to increment(), a method that decrements "this" based
     * on the corresponding month.
     ******************************************************************/
    public void decrement(){

        day -= 1;

        if (day == 0){
            if (month == 1){
                month = NUM_MONTHS;
                day = getDaysInMonth(getMonth(), getYear());
                year--;
            } else {
                month--;
                day = getDaysInMonth(getMonth(), getYear());
            }
        }
    }

    /******************************************************************
     * A method that overrides the object class' toString method to
     * put the "this" date in the appropriate format.  If the day is
     * before the 10th of the month, the method adds a leading zero to
     * the "this" day.
     *
     * @return returns the current date in the proper format.
     ******************************************************************/
    public String toString(){

        if (day < 10){
            return String.format("%02d %s %4d",
                    day, MONTH_NAME[month], year);
        } else {
            return String.format("%02d %s %4d",
                    day, MONTH_NAME[month], year);
        }
    }

    /******************************************************************
     * A getter method that checks if the current year is a leap year
     * and the month is February.  If both conditions are true, 29 is
     * returned as it accounts for the extra day.  Otherwise, the
     * array identifying the days in each month at the index of the
     * current month is returned.
     *
     * @param month month being accessed and checked.
     * @param year year being accessed and checked.
     * @return depending on the year, either 29 or the appropriate
     * month days are returned.
    ******************************************************************/
    public static int getDaysInMonth(int month, int year){
        if(isLeapYear(year) && month == 2){
            return 29;
        }
        return DAYS_IN_MONTH[month];
    }

    /******************************************************************
     * A getter method that returns the total number of SimpleDates
     * currently in the program.
     *
     * @return returns the integer numSimpleDates, which may increment
     * in other methods based on certain conditions.
     ******************************************************************/
    public static int getNumberOfSimpleDates(){
        return numSimpleDates;
    }

    /******************************************************************
     * A getter method that returns the month currently in the month's
     * object field.
     *
     * @return the integer representing the month.
     ******************************************************************/
    public int getMonth(){
        return month;
    }

    /******************************************************************
     * A setter method that sets the passed month integer value to the
     * "this" month value.
     *
     * @param month is set to "this" month's value.
    ******************************************************************/
    public void setMonth(int month){
        this.month = month;
    }

    /******************************************************************
     * A setter method that returns the integer day value.
     *
     * @return returns the value in the day field.
     *****************************************************************/
    public int getDay(){
        return day;
    }

    /******************************************************************
     * A setter method that sets the passed day integer value to the
     * "this" day value.
     *
     * @param day is set to "this" day's value.
     *****************************************************************/
    public void setDay(int day){
        this.day = day;
    }

    /******************************************************************
     * A setter method that returns the integer year value.
     *
     * @return returns the value in the year field.
     *****************************************************************/
    public int getYear(){
        return year;
    }

    /******************************************************************
     * A setter method that sets the passed year integer value to the
     * "this" day value.
     *
     * @param year is set to "this" year's value.
     *****************************************************************/
    public void setYear(int year){
        this.year = year;
    }

    /******************************************************************
     * A method that is passed integer parameter n and instantiates a
     * new SimpleDate object "temp".  The month, day, and year are
     * set to the "this" object, and n is run through a set of
     * conditions.  If n is a positive integer, then "temp" is run
     * through the increment method and n is incremented by one day.
     * If negative, it goes through the decrement method and is
     * decremented by one day.  The final value of "temp" is returned.
     *
     * @param n amount of days from now
     * @return returns the resulting value of "temp"
     * @throws IllegalArgumentException is thrown if an invalid date
     * is given.
     *****************************************************************/
    public SimpleDate daysFromNow(int n) throws IllegalArgumentException {

        SimpleDate temp = new SimpleDate();

        temp.setDay(this.getDay());
        temp.setMonth(this.getMonth());
        temp.setYear(this.getYear());

        if (n > 0)
            for ( ; n > 0; n++){
                temp.increment();
            }
        else if (n < 0)
            for ( ; n < 0; n--){
                temp.decrement();
            }
        return temp;
    }

    /******************************************************************
     * A method that creates a new SimpleDate "temp" and sets it equal
     * to the "other" SimpleDate.  It then establishes a while
     * loop with the condition that the "other" SimpleDate and the
     * "this" SimpleDate are not the same.  While this condition is
     * true, if "this" is larger than "other", "temp" and counter are
     * incremented.  If "other" is the larger of the two, "other" and
     * counter are decremented.  In any case, the absolute value is
     * taken of the result of counter, so as not to include non-real
     * integers.
     *
     * @param other SimpleDate other becomes temp to be compared with
     *              "this"
     * @return returns the value of counter after the loop is parsed.
     *****************************************************************/
    public int daysSince(SimpleDate other){

        SimpleDate temp = new SimpleDate();
        temp.setDay(other.getDay());
        temp.setMonth(other.getMonth());
        temp.setYear(other.getYear());

        while(compareTo(temp) != 0){

            if(compareTo(temp) == 1){
                temp.increment();
                counter++;
            }
            if(compareTo(temp) == -1){
                temp.decrement();
                counter--;
            }
        }
        //days since returned
        return Math.abs(counter);
    }

    /******************************************************************
     *  A method that saves file fileName SimpleDate and prints field
     *  values as a text file.
     *
     * @param fileName represents the name of the text file that will
     *                 be saved.
     *****************************************************************/
    public void save(String fileName){
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter
                    (fileName)));
        }
        catch (IOException e){
            e.printStackTrace();
        }

        out.println(month);
        out.println(day);
        out.println(year);
        out.close();
    }

    /******************************************************************
     * A method that establishes a scanner fileReader and loads a text
     * file fileName to be loaded from the directory.  The day, month,
     * and year are read and printed.  If the file is not found,
     * an error message is displayed.
     *
     * @param fileName represents the name of the text file that will
     *                  be loaded.
     *****************************************************************/
    public void load(String fileName){

        Scanner fileReader = null;
        try {
            fileReader = new Scanner(new File(fileName));

            month = fileReader.nextInt();
            day = fileReader.nextInt();
            year = fileReader.nextInt();

            System.out.println(month + " " + day + " " + year);
        }
        catch(FileNotFoundException error){
            System.out.println("ERROR: File not found");
        }
    }
}