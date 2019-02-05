package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {

        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        List<UserMealWithExceed> ls = getFilteredWithExceededWithStreams(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
    }

    public static List<UserMealWithExceed>  getFilteredWithExceededWithStreams(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        return mealList.stream()
                .filter(um -> (TimeUtil.isBetween(um.getDateTime().toLocalTime(), startTime, endTime)))
                .map(um -> new UserMealWithExceed(um.getDateTime(), um.getDescription(), um.getCalories(),
                        (mealList.stream().collect(Collectors.groupingBy(um1 -> um1.getDateTime().toLocalDate(), Collectors.summingInt(um1 -> um1.getCalories()))))
                                .get(um.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> temp = new HashMap<>();
        for(UserMeal um: mealList){
            LocalDate ld = um.getDateTime().toLocalDate();
            temp.put(ld, temp.getOrDefault(ld, 0) + um.getCalories());
        }

        List<UserMealWithExceed> mealExceedList = new LinkedList<>();
        for(UserMeal um: mealList){
            LocalTime lt = um.getDateTime().toLocalTime();
            LocalDate ld = um.getDateTime().toLocalDate();

            if(TimeUtil.isBetween(lt, startTime, endTime)){
                mealExceedList.add(new UserMealWithExceed(um.getDateTime(), um.getDescription(), um.getCalories(), ((temp.get(ld)>caloriesPerDay)?true:false)));
            }
        }

        return mealExceedList;
    }
}
