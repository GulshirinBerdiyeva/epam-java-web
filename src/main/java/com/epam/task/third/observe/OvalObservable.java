package com.epam.task.third.observe;

import com.epam.task.third.entity.Oval;
import com.epam.task.third.entity.Point;

import java.util.ArrayList;
import java.util.List;

public class OvalObservable extends Oval implements IObservable {
    private List<IObserver> observers = new ArrayList<IObserver>();

    public OvalObservable(Integer ID, Point point1, Point point2) {
        super(ID, point1, point2);
    }

    @Override
    public void setPoint1(Point point1) {
        super.setPoint1(point1);
        notifyObservers();
    }

    @Override
    public void setPoint2(Point point2) {
        super.setPoint2(point2);
        notifyObservers();
    }

    public void addObserver(IObserver observer) {
        observers.add(observer);
        notifyObservers();
    }

    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (IObserver observer : observers){
            observer.update(this);
        }
    }

}
