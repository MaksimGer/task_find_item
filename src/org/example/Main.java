package org.example;

import java.util.Scanner;

/*
    Задача на java core:
    Программа при старте спрашивает размер массива (проверяет что размер не меньше 10, и что ввели цифру а не текст, и что цифра это целое число)

    После чего создаёт массив нужного размера и наполняет его числами по порядку (1,2,3...n)

    Далее берет случайное число из массива и удаляет его. Т.е. массив должен уменьшиться на один элемент и стать размером n-1.

    Следующий шаг - это перемешивание массива. Нужно чтобы массив n-1 основательно был перемешан (это делать своим кодом, не прибегая к готовым библиотекам перемешивания)

    Ваша задача, за минимальное количество действий найти потерянный элемент из перемешанного массива (тот который мы случайно удалили).
* */
public class Main {
    public static void main(String[] args) {
        int N = setArraySize();
        int[] array = createArray(N);

        array = removeRandomItem(array);

        shuffleTheArray(array);

        System.out.println("----------------------");
        System.out.println("Finding an item ...");
        int deletedItem = findDeletedItem(array);
        System.out.println("Deleted item is: " + deletedItem);

//        printArray(array);
    }

    private static int setArraySize() {
        Scanner in = new Scanner(System.in);
        int arrSize = 0;

        while (arrSize < 10){
            System.out.println("Please enter an array size (N >= 10)!");

            if(!in.hasNextInt()){
                System.out.println("This is not an integer!");
                in.next();
                continue;
            }

            arrSize = in.nextInt();
        }

        return arrSize;
    }

    private static int[] createArray(int N) {
        int[] array = new int[N];

        for(int i = 0; i < N; i++){
            array[i] = i+1;
        }

        return array;
    }

    private static int[] removeRandomItem(int[] array) {
        int newLength = array.length - 1;
        int[] newArray = new int[newLength];

        int indexToDelete = getRandomNumber(0, newLength);
        System.out.println(String.format("Item \"%d\" removed", array[indexToDelete]));

        for(int i = 0; i< indexToDelete; i++){
            newArray[i] = array[i];
        }

        for (int i = indexToDelete; i < newLength; i++){
            newArray[i] = array[i+1];
        }

//        for(int i = 0, j=0; i < array.length; i++){
//            if(i != elementToDelete){
//                newArray[j] = array[i];
//                j++;
//            }
//        }
        return newArray;
    }

    private static void shuffleTheArray(int[] array) {
        for(int i = 0; i< array.length; i++){
            int randomIndex = getRandomNumber(0, array.length -1);

            int curItem = array[i];
            array[i] = array[randomIndex];
            array[randomIndex] = curItem;
        }
    }

    private static int findDeletedItem(int[] array) {
        long startSum = (long)(1 + array.length + 1) * (array.length + 1) / 2;
        long curSum = 0;

        for (int item : array) {
            curSum += item;
        }

        return (int) (startSum - curSum);
    }

    private static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private static void printArray(int[] arr) {
        System.out.print("Array: [ " );
        for(int item : arr){
            System.out.print(item + " ");
        }
        System.out.println("]");
    }
}