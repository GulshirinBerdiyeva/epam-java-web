package com.epam.task.third.observe;

import com.epam.task.third.entity.Oval;
import com.epam.task.third.entity.Point;

import java.util.ArrayList;
import java.util.List;

public class OvalObservable extends Oval implements Observable {
    private List<Observer> observers = new ArrayList<Observer>();

    public OvalObservable(int id, Point firstPoint, Point secondPoint) {
        super(id, firstPoint, secondPoint);
    }

    @Override
    public void setFirstPoint(Point firstPoint) {
        super.setFirstPoint(firstPoint);
        notifyObservers();
    }

    @Override
    public void setSecondPoint(Point secondPoint) {
        super.setSecondPoint(secondPoint);
        notifyObservers();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
        notifyObservers();
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers){
            observer.update(this);
        }
    }

}