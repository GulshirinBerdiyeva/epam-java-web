package com.epam.task.web.project.command;

import com.epam.task.web.project.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Implements by definite Command classes
 * */
public interface Command {
    /**
     * Execute user's request then forward or redirect him
     *
     * @param request request from Controller servlet
     * @param response response from Controller servlet
     * @return         CommandResult which forward or redirect executed request
     * @throws         ServiceException if user's request is invalid
     * */
    CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}