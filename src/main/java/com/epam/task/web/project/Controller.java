package com.epam.task.web.project;

import com.epam.task.web.project.command.Command;
import com.epam.task.web.project.command.CommandFactory;
import com.epam.task.web.project.command.CommandResult;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        boolean isRedirect = false;

        try {
            String command = request.getParameter("command");
            Command action = CommandFactory.create(command);

            CommandResult result = action.execute(request, response);
            page = result.getPage();
            isRedirect = result.isRedirect();
        }catch (Exception e){
            request.setAttribute("errorCommand", e.getMessage());
            page = "/error.jsp";
        }

        if (!isRedirect){
            forward(request, response, page);
        }else {
            redirect(response, page);
        }

        request.getSession(true).setAttribute("currentPage", page);
    }

    private void forward(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    private void redirect(HttpServletResponse response, String page) throws IOException {
        response.sendRedirect(page);
    }

}
