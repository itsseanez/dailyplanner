public class Tasks  {
    private String  name, timeOfDay, nameMonth;
    private String completionRate="0", hour,minutes, monthDue, dateDue;

    Tasks() {}
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getHour() {
        return hour;
    }

    public String getMinutes() {
        return minutes;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public void setDateDue(String dateDue) {
        this.dateDue = dateDue;
    }


    public void setMonthDue(String monthDue) {
        this.monthDue = monthDue;
    }

    public String getDateDue() {
        return dateDue;
    }

    public String getMonthDue() {
        return monthDue;
    }

    public String getCompletionRate() {
        return completionRate;
    }

    public void setCompletionRate(String completionRate) {
        this.completionRate = completionRate;
    }

    public String getNameMonth() {
        return nameMonth;
    }

    public void setNameMonth(String nameMonth) {
        this.nameMonth = nameMonth;
    }

    @Override
    public String toString() {
        return  name+" is due "+ nameMonth+ ", "+dateDue+ " at "+ hour+":"+minutes+" "+timeOfDay+" and is "+getCompletionRate()+"% complete.";
    }
}
