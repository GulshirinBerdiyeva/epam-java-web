package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.User;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class PurchaseCommand implements Command{

    private UserService userService;

    public PurchaseCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        User user = (User) request.getSession(false).getAttribute("user");
        String role = String.valueOf(user.getRole());
//
//        if (ro)
//
//        Optional<User> optionalUser = userService.login(login, password);
//
//        if (optionalUser.isPresent()){
//
//
            return null;
    }
}
