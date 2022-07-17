package com.epam.task.first.logic;

import com.epam.task.first.entity.Array;

import java.util.List;

public class ArrayLogic {

    public int findMax(Array array){
        List<Integer> elements = array.getElements();
        int max = elements.get(0);

        for (int element : elements){
            if (max < element){
                max = element;
            }
        }
        return max;
    }

    public int findMin(Array array){
        List<Integer> elements = array.getElements();
        int min = elements.get(0);

        for (int element : elements){
            if (min > element){
                min = element;
            }
        }
        return min;
    }

    public void replacementEvenElements(Array array){
        List<Integer> elements = array.getElements();

        for (int i = 0; i < elements.size(); i++){
            if (elements.get(i) % 2 == 0){
                elements.set(i, 0);
            }
        }
    }

    public double findMean(Array array){
        List<Integer> elements = array.getElements();
        double sum = 0;

        for (int element : elements){
            sum += element;
        }
        return sum / elements.size();
    }

    public int findSum(Array array){
        List<Integer> elements = array.getElements();
        int sum = 0;

        for (int element : elements){
            sum += element;
        }
        return sum;
    }

    public int findAmountOfPositiveElements(Array array){
        List<Integer> elements = array.getElements();
        int amount = 0;

        for (int element : elements){
            if (element > 0) {
                amount++;
            }
        }
        return amount;
    }

    public int findAmountOfNegativeElements(Array array){
        List<Integer> elements = array.getElements();
        int amount = 0;

        for (int element : elements){
            if (element < 0) {
                amount++;
            }
        }
        return amount;
    }

}
