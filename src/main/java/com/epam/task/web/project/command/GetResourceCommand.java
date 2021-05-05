package com.epam.task.web.project.command;

import com.epam.task.web.project.data.DataException;
import com.epam.task.web.project.data.MusicFileReader;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.validator.InputParameterValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetResourceCommand implements Command{

    private static final String FILE_NAME = "appResources.properties";
    private static final String IMAGE_FILE_NAME = "imageFileName";
    private static final String AUDIO_FILE_NAME = "audioFileName";
    private static final String CURRENT_PAGE = "currentPage";

    private InputParameterValidator validator;

    public GetResourceCommand(InputParameterValidator validator) {
        this.validator = validator;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String imageFileName = request.getParameter(IMAGE_FILE_NAME);
        String audioFileName = request.getParameter(AUDIO_FILE_NAME);

        boolean isValidImageFileName = validator.isValidString(imageFileName);
        boolean isValidAudioFileName = validator.isValidString(audioFileName);
        if (!isValidImageFileName && !isValidAudioFileName) {
            throw new NullPointerException("Parameter is NULL...");
        }

        try {
            MusicFileReader reader = new MusicFileReader();
            reader.read(FILE_NAME, imageFileName, audioFileName, response);

            String page = (String) request.getSession().getAttribute(CURRENT_PAGE);
            return CommandResult.forward(page);

        } catch (DataException e) {
            throw new ServiceException(e);
        }

    }

}
