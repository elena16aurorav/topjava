package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class MealServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String a = "dddddddddd";

        List<Meal> meals = Arrays.asList(
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );

        List<MealTo> mealsTo = MealsUtil.getWithExcess(meals, 2000);

        request.setAttribute("mealsTo", mealsTo);
        request.setAttribute("a", a);

        // Создать объект RequestDispatcher
        // чтобыForward (перенаправить) запрос к jstl_core_example01.jsp
        //RequestDispatcher dispatcher = getServletContext()
        //        .getRequestDispatcher("/WEB-INF/jsps/jstl_core_example01.jsp");
//
        // Forward (перенаправить) запрос, чтобы отобразить данные на странице JSP.
        //dispatcher.forward(request, response);

        request.getRequestDispatcher("/meals.jsp").forward(request, response);
   //     response.sendRedirect("meals.jsp");
    }
}
