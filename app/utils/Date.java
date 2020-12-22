package utils;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Date implements Serializable,Comparable<Date> {
    private int day;
    private  int month;
    private int year;

    public Date(int day, int month) {
        this.day = day;
        this.month = month;
        this.year=2020;  //giving a default year assuming this league is only for 2020
    }



    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public  int getYear() {
        return year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Date)) return false;
        Date date = (Date) o;
        return day == date.day &&
                month == date.month &&
                year == date.year;
    }

    public static boolean DateIsValid(String date){
        SimpleDateFormat df =new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);
        df.setNumberFormat(NumberFormat.getIntegerInstance());
        try {
            df.parse(date) ;

        } catch (ParseException parseException) {
            return false;
        }
        return true;

    }
    @Override
    public String toString() {
        return  day+ "/" +month+"/"+year;
    }

    @Override
    public int compareTo(Date o) {
        int result=0;
        if(this.getMonth()>o.getMonth()){
            result=1;
        }else {
            if(this.getMonth()==o.getMonth()){
                if(this.getDay()>o.getDay()){
                    result=1;
                }else {
                    result=-1;
                }
            }else {
                result=-1;
            }
        }



        return result;
    }
}
