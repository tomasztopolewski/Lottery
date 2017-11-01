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
     *         losowania lub została wylosowana liczba z przedziału mniejszego od
     *         żądanego od użytkownika (dzieje się tak, gdy liczba początkowa jest
     *         różna od zera, a metoda obiektu 'Random.nextInt(int bound)' losuje
     *         doomyślenie liczby od 0
     *            W takim wypadku funkcja stouje rekurencje.
     *      2) wylosowana liczba nie jest równa ostatniej wylosowanej liczbie
     *         i jednocześnie mieści się w przedziale
     *      ...
     *
     * Funkcja 'returnRandomNumber()' wersja 1.3
     *
     * @return randomly number
     */
/* // Nie używana wersja funkcji.
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
*/

    /**
     * Funkcja 'lotteryNumber()' wersja 2.0 - (poprzednio 'returnRandomNumber()')
     * Przedmiot działania funkcji pozostaje niezmienny. Funkcja korzysta
     * z metody 'java.util.Random.nextInt(int bound)'.
     *
     * Założenie poprawności wyniku funkcji:
     *      funkcja 'lotteryNumber()' zwróci poprawną wartość wtedy i tylko
     *      wtedy, gdy nieprzciążona metoda obiektu 'Lottery.checkRange()' zwróci
     *      'true'. Oznacza to, że przedział z jakiego będzie losowana liczba jest
     *      poprawnie zapisany.
     *
     * Instrukcja warunkowa dzieli funkcję na trzy możliwe uwarunkowania (sprawdzane
     * po koleji, od punktu pierwszego do trzeciego, gdzie trzeci punkt zawiera
     * poprawnie rozpatrzone uwarunkowania):
     *      1) wylosowana liczba jest mniejsza od początku przedziału, jaki użytkownik
     *         wprowadził: funkcja wywołuje rekurencyjne samą siebie.
     *      2) liczba wylosowana będzie zawierała się w zdeklarowanym przedziale,
     *         funkcja sprawdzi, czy wylosowana liczba jest równa ostatniej
     *         wylosowanej liczbie: jeśli tak, funkcja wywołuje rekurencyjne
     *         samą siebie, a jeśli nie to przechodzi do ostatniej części instrukcji.
     *      3) ostatnia część instrukcji wykonuje się dla poprawnie wylosowanej liczby,
     *         takiej, która należy do przedziału i jest różna od ostatniej
     *         wylosowanej liczby. W takim przypadku: zapisuję wartość wygenerownej
     *         liczby do zmiennej 'lastNumber' i zwraca wylosowaną liczbę.
     *
     * @return randomly number from user's range
     */

    public static int lotteryNumber() {
        int generatedNumber = generator.nextInt(endNumber + 1);

        if (!(generatedNumber >= Lottery.firstNumber)) return lotteryNumber();
        else if (generatedNumber == Lottery.lastNumber) return lotteryNumber();
        else {
            lastNumber = generatedNumber;
            return generatedNumber;
        }
    }


    public static boolean checkRange() {
        return firstNumber < endNumber && firstNumber >= 0 ? true : false;
    }
    public static boolean checkRange(int firstNumber, int endNumber) {
        return firstNumber < endNumber && firstNumber >= 0 ? true : false;
    }
}

// Tomasz Topolewski 2017
