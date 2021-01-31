package com.epam.task.third.logic;

import com.epam.task.third.entity.AbstractFigure;

public interface IFigureParameters {
    Double calculatePerimeter(AbstractFigure figure);
    Double calculateArea(AbstractFigure figure);
}
