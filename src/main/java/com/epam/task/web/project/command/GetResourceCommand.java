package com.epam.task.web.project.command;

import com.epam.task.web.project.data.DataException;
import com.epam.task.web.project.data.MusicFileReader;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.validator.InputParameterValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetResourceCommand implements Command{

    private static final String TYPE = "type";
    private static final String FILE_NAME = "fileName";
    private static final String ICON = "icon";
    private static final String IMAGE = "image";
    private static final String MUSIC = "music";
    private static final String DATA_FILE_NAME = "appResources.properties";
    private static final String CURRENT_PAGE = "currentPage";

    private InputParameterValidator validator;

    public GetResourceCommand(InputParameterValidator validator) {
        this.validator = validator;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String type = request.getParameter(TYPE);
        String fileName = request.getParameter(FILE_NAME);

        boolean isValidType = validator.isStringValid(type);
        boolean isValidFileName = validator.isStringValid(fileName);
        if (!isValidType && !isValidFileName) {
            throw new NullPointerException("Parameter is NULL!");
        }

        try {
            MusicFileReader reader = new MusicFileReader();
            switch (type) {
                case ICON:
                    reader.readIconFile(DATA_FILE_NAME, fileName, response);
                    break;
                case IMAGE:
                    reader.readImageFile(DATA_FILE_NAME, fileName, response);
                    break;
                case MUSIC:
                    reader.readAudioFile(DATA_FILE_NAME, fileName, response);
                    break;
                default:
                    throw new ServiceException("Unknown type of required resource! \"" + type + "\"");
            }

            String page = (String) request.getSession().getAttribute(CURRENT_PAGE);
            return CommandResult.forward(page);

        } catch (DataException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}