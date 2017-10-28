package main;

import java.util.Random;

public abstract class Lottery {

    public static int firstNumber = 0, endNumber = 0;
    public static int minNumber = 0, maxNumber = 10000000;
    public static int lastNumber = -1;

    private static Random generator = new Random();

    //// kolekcja przechowująca wylosowane liczby
    //public static List<Integer> numbersFromLottery;


    public static int getFirstNumber() {
        return firstNumber;
    }
    public static void setFirstNumber(int firstNumber) {
        Lottery.firstNumber = firstNumber >= 0 ? firstNumber : 0;
    }

    public static int getEndNumber() {
        return endNumber;
    }
    public static void setEndNumber(int endNumber) {
        Lottery.endNumber = endNumber > Lottery.firstNumber ? endNumber : 0;
    }

    public int getMinNumber() {
        return minNumber;
    }
    public void setMinNumber(int minNumber) {
        Lottery.minNumber = minNumber;
    }

    public int getMaxNumber() {
        return maxNumber;
    }
    public void setMaxNumber(int maxNumber) {
        Lottery.maxNumber = maxNumber;
    }

    /*public static void createCollectionOfNumber() {
        numbersFromLottery = new List<Integer>() {...};
        // wywołana na początku programu w 'main.Main' daje gwaranację,
           że argumentem wpisany do kolekcji<Integer> będzie wartość: -1.
        numbersFromLottery.add(lastNumber);
    }*/

    public static int getLastNumber() {
        return lastNumber;
    }
    public static void setLastNumber(int lastNumber) {
        Lottery.lastNumber = lastNumber >= firstNumber && lastNumber <= endNumber ? lastNumber : -1;
    }


    /**
     * Funkcja losuje liczbę metodą obiektu 'java.util.Random.nextInt(int bound)',
     * gdzie 'bound' oznacza wartość brzegową dla podanego losowania. Oznacza to,
     * że metoda 'nextInt()' losuje liczbę tylko i wyłacznie do liczby zwartej
     * w 'bound'. Wartość 'bound' nie pojawi się w wynikach losowania. Za początek
     * przedziału, z jakiego losowane są liczby, bezwarunkowo obierane jest 0.
     *
     * W pierwszej kolejności losowana jest liczba z przedziału wskazanego przez
     * użytkownika. Polecenie dodania '1' do wartości krańcowej liczby służy
     * włączeniu liczby z krańca przedziału, to puli liczb możliwych do wyloswania.
     *
     * Instrukacja sterująca dzieli program na trzy możliwe sytuacje:
     *      1) wylosowana liczba jest równa liczbie wylosowanej podczas ostatniego
     *         losowanialub została wylosowana liczba z przedziału mniejszego od
     *         żądanego od użytkownika (dzieje się tak, gdy liczba początkowa jest
     *         różna od zera, a metoda obiektu 'Random.nextInt(int bound)' losuje
     *         doomyślenie liczby od 0
     *            W takim wypadku funkcja stouje rekurencje.
     *      2) wylosowana liczba
     *
     *
     * @return randomly number
     */
    public static int returnRandomNumber() {
        int generatedNumber = generator.nextInt(endNumber + 1);
        //generatedNumber != numbersFromLottery.add(generatedNumber).get(numbersFromLottery.size())));
        if (!(generatedNumber >= firstNumber && generatedNumber != lastNumber)) returnRandomNumber();
        else {
            //numbersFromLottery.add(generatedNumber);
            lastNumber = generatedNumber;
            return generatedNumber;
        }
        return -1;
    }

    public static boolean checkRange() {
        return firstNumber < endNumber && firstNumber >= 0 ? true : false;
    }
    public static boolean checkRange(int firstNumber, int endNumber) {
        return firstNumber < endNumber && firstNumber >= 0 ? true : false;
    }
}
