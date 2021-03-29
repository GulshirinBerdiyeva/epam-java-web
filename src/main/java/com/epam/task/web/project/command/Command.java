package com.epam.task.web.project.command;

import com.epam.task.web.project.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}
