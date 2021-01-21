package com.epam.task.first.logic;

import com.epam.task.first.entities.Array;

public class ArrayLogic {

    public int findMax(Array array){
        int max = array.getElements().get(0);

        for (int newMax : array.getElements()){
            if (max < newMax){
                max = newMax;
            }
        }
        return max;
    }

    public int findMin(Array array){
        int min = array.getElements().get(0);

        for (int newMin : array.getElements()){
            if (min > newMin){
                min = newMin;
            }
        }
        return min;
    }

    public void replacementEvenElements(Array array){
        for (int i = 0; i < array.getElements().size(); i++){
            if (array.getElements().get(i) % 2 == 0){
                array.getElements().set(i, 0);
            }
        }
    }

    public double findMean(Array array){
        double sum = 0;
        for (int element : array.getElements()){
            sum += element;
        }
        return sum / array.getElements().size();
    }

    public int findSum(Array array){
        int sum = 0;
        for (int element : array.getElements()){
            sum += element;
        }
        return sum;
    }

}
