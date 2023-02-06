import java.net.URL;
import java.util.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.util.stream.Collectors;
import java.io.IOException;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonParser.Feature;

public class TestPlanner {

    public static void maxMinLoop(int number, int min, int max) {
        Scanner scan = new Scanner(System.in);
        while (number < min || number > max) {
            System.out.println("Wrong Number Selection. \nEnter new Integer:");
            number = scan.nextInt();
        }
    }

    public static void javaToJson(File file, ArrayList<Tasks> tasksArrayList) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(file, tasksArrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void jsonToJava(File file, ArrayList<Tasks> tasksArrayList) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        ArrayList<Tasks> methodArrayList=new ArrayList<>();
        try {
            methodArrayList = mapper.readValue(file, new TypeReference<ArrayList<Tasks>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(Tasks task: methodArrayList)
            tasksArrayList.add(task);
    }

    public static void printArrayList(ArrayList<Tasks> tasksArrayList) {
        for(Tasks task: tasksArrayList) {
            System.out.println(task);
        }
    }

    public static void main(String[] args) throws JsonProcessingException, JsonParseException, JsonMappingException, IOException {

        PlannerClock plannerClock = new PlannerClock();
        plannerClock.start();
        File file = new File("Model.json");
        ArrayList<Tasks> tasksArrayList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        Scanner textScanner = new Scanner(System.in);
        outerscope:
        do {
            System.out.println("Tasks:");
            jsonToJava(file, tasksArrayList);
            printArrayList(tasksArrayList);
            System.out.println("0. Quit");
            System.out.println("1. Edit Percentage Complete");
            System.out.println("2. Mark Task as Complete");
            System.out.println("3. Add Task to list");
            int userChoice = scanner.nextByte();

            switch (userChoice) {
                case 0:
                    break outerscope;

                case 1:
                    for (int i = 0; i < tasksArrayList.size(); i++)
                        System.out.println(i + 1 + ". " + tasksArrayList.get(i));
                    System.out.println("Which Task should be edited?");
                    Byte selectTask = scanner.nextByte();
                    System.out.println("What percentage of this task has been completed?");
                    short percentageCompleted = scanner.nextShort();
                    tasksArrayList.get(selectTask - 1).setCompletionRate("" + percentageCompleted);
                    if (percentageCompleted == 100)
                        tasksArrayList.remove(selectTask);
                    javaToJson(file, tasksArrayList);
                    break;

                case 2:
                    for (int i = 0; i < tasksArrayList.size(); i++)
                        System.out.println(i + 1 + ". " + tasksArrayList.get(i));
                    System.out.println("Which Task has been completed?");
                    selectTask = scanner.nextByte();
                    tasksArrayList.remove(selectTask - 1);
                    javaToJson(file, tasksArrayList);
                    System.out.println("Task has been removed from list.");
                    break;

                case 3:
                    jsonToJava(file,tasksArrayList);
                    System.out.println("What is the name of the task?");
                    String taskName = textScanner.nextLine();
                    Tasks tasks = new Tasks();
                    tasks.setName(taskName);
                    tasksArrayList.add(tasks);
                    System.out.println("What month is it due?(1-12)");
                    System.out.println("1. January");
                    System.out.println("2. February");
                    System.out.println("3. March");
                    System.out.println("4. April");
                    System.out.println("5. May");
                    System.out.println("6. June");
                    System.out.println("7. July");
                    System.out.println("8. August");
                    System.out.println("9. September");
                    System.out.println("10. October");
                    System.out.println("11. November");
                    System.out.println("12. December");
                    byte selectMonth = scanner.nextByte();
                    maxMinLoop(selectMonth, 1, 12);
                    tasks.setMonthDue("" + selectMonth);
                    switch (selectMonth) {
                        case 1:
                            tasks.setNameMonth("January");
                            break;
                        case 2:
                            tasks.setNameMonth("February");
                            break;
                        case 3:
                            tasks.setNameMonth("March");
                            break;
                        case 4:
                            tasks.setNameMonth("April");
                            break;
                        case 5:
                            tasks.setNameMonth("May");
                            break;
                        case 6:
                            tasks.setNameMonth("June");
                            break;
                        case 7:
                            tasks.setNameMonth("July");
                            break;
                        case 8:
                            tasks.setNameMonth("August");
                            break;
                        case 9:
                            tasks.setNameMonth("September");
                            break;
                        case 10:
                            tasks.setNameMonth("October");
                            break;
                        case 11:
                            tasks.setNameMonth("November");
                            break;
                        case 12:
                            tasks.setNameMonth("December");
                            break;
                    }

                    System.out.println("What date is it due?");
                    Calendar datesAvailable=new GregorianCalendar(Calendar.YEAR, selectMonth,1);
                    int selectDateDue = scanner.nextInt();
                    maxMinLoop(selectDateDue,1, datesAvailable.getActualMaximum(Calendar.DAY_OF_MONTH));
                    tasks.setDateDue("" + selectDateDue);

                    System.out.println("What hour is it due?");
                    int selectHourDue = scanner.nextInt();
                    maxMinLoop(selectHourDue, 1, 12);
                    tasks.setHour("" + selectHourDue);

                    System.out.println("What Minute is it due?");
                    int selectMinute = scanner.nextInt();
                    maxMinLoop(selectMinute, 0, 60);
                    tasks.setMinutes("" + selectMinute);

                    System.out.println("What time of day is it due?(1 OR 2)");
                    System.out.println("1. A.M.");
                    System.out.println("2. P.M.");
                    int selectTimeOfDay = scanner.nextInt();
                    maxMinLoop(selectTimeOfDay, 1,2);
                    String nameTimeOfDay;

                    if (selectTimeOfDay == 1)
                        nameTimeOfDay = "A.M.";

                    else
                        nameTimeOfDay = "P.M.";

                    tasks.setTimeOfDay(nameTimeOfDay);
                    Comparator<Tasks> tasksComparator = Comparator
                            .comparing(Tasks::getMonthDue)
                            .thenComparing(Tasks::getDateDue)
                            .thenComparing(Tasks::getHour)
                            .thenComparing(Tasks::getMinutes)
                            .thenComparing(Tasks::getCompletionRate);
                    List<Tasks> sortedList = tasksArrayList.stream()
                            .sorted(tasksComparator)
                            .collect(Collectors.toList());

                    for (int i = 0; i < tasksArrayList.size(); i++)
                        tasksArrayList.set(i, sortedList.get(i));
                    javaToJson(file, tasksArrayList);
                    printArrayList(tasksArrayList);
                    break;
            }

        } while (true);

    }

}
