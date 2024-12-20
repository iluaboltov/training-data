import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Клас BasicDataOperationUsingList надає методи для виконання основних операцiй з даними типу double.
 * 
 * <p>Цей клас зчитує данi з файлу "list/double.data", сортує їх та виконує пошук значення в масивi та списку.</p>
 * 
 * <p>Основнi методи:</p>
 * <ul>
 *   <li>{@link #main(String[])} - Точка входу в програму.</li>
 *   <li>{@link #doDataOperation()} - Виконує основнi операцiї з даними.</li>
 *   <li>{@link #sortArray()} - Сортує масив double.</li>
 *   <li>{@link #searchArray()} - Виконує пошук значення в масивi double.</li>
 *   <li>{@link #findMinAndMaxInArray()} - Знаходить мiнiмальне та максимальне значення в масивi double.</li>
 *   <li>{@link #sortList()} - Сортує список double.</li>
 *   <li>{@link #searchList()} - Виконує пошук значення в списку double.</li>
 *   <li>{@link #findMinAndMaxInList()} - Знаходить мiнiмальне та максимальне значення в списку double.</li>
 * </ul>
 * 
 * <p>Конструктор:</p>
 * <ul>
 *   <li>{@link #BasicDataOperationUsingList(String[])} - iнiцiалiзує об'єкт з значенням для пошуку.</li>
 * </ul>
 * 
 * <p>Константи:</p>
 * <ul>
 *   <li>{@link #PATH_TO_DATA_FILE} - Шлях до файлу з даними.</li>
 * </ul>
 * 
 * <p>Змiннi екземпляра:</p>
 * <ul>
 *   <li>{@link #doubleValueToSearch} - Значення double для пошуку.</li>
 *   <li>{@link #doubleArray} - Масив double.</li>
 *   <li>{@link #doubleList} - Список double.</li>
 * </ul>
 * 
 * <p>Приклад використання:</p>
 * <pre>
 * {@code
 * java BasicDataOperationUsingList "-3.210E+156"
 * }
 * </pre>
 */
public class BasicDataOperationUsingList {
    static final String PATH_TO_DATA_FILE = "./list/double.data";

    double doubleValueToSearch;
    double[] doubleArray;
    List<Double> doubleList;

    public static void main(String[] args) {  
        BasicDataOperationUsingList basicDataOperationUsingList = new BasicDataOperationUsingList(args);
        basicDataOperationUsingList.doDataOperation();
    }

    /**
     * Конструктор, який iнiцiалiзує об'єкт з значенням для пошуку.
     * 
     * @param args Аргументи командного рядка, де перший аргумент - значення для пошуку.
     */
    BasicDataOperationUsingList(String[] args) {
        if (args.length == 0) {
            throw new RuntimeException("Вiдсутнє значення для пошуку");
        }

        String searchValue = args[0];
        doubleValueToSearch = Double.parseDouble(searchValue);

        doubleArray = Utils.readArrayFromFile(PATH_TO_DATA_FILE);
        doubleList = new LinkedList<>();
        for (double value : doubleArray) {
            doubleList.add(value);
        }
    }

    /**
     * Виконує основнi операцiї з даними.
     * 
     * Метод зчитує масив та список об'єктiв double з файлу, сортує їх та виконує пошук значення.
     */
    void doDataOperation() {
        // операцiї з масивом
        searchArray();
        findMinAndMaxInArray();

        sortArray();
        
        searchArray();
        findMinAndMaxInArray();

        // операцiї з LinkedList
        searchList();
        findMinAndMaxInList();

        sortList();

        searchList();
        findMinAndMaxInList();

        // записати вiдсортований масив в окремий файл
        Utils.writeArrayToFile(doubleArray, PATH_TO_DATA_FILE + ".sorted");
    }

    /**
     * Сортує масив об'єктiв double та виводить початковий i вiдсортований масиви.
     * Вимiрює та виводить час, витрачений на сортування масиву в наносекундах.
     */
    void sortArray() {
        long startTime = System.nanoTime();

        Arrays.sort(doubleArray);

        Utils.printOperationDuration(startTime, "сортування масиву double");
    }

    /**
     * Метод для пошуку значення в масивi double.
     */
    void searchArray() {
        long startTime = System.nanoTime();

        int index = Arrays.binarySearch(this.doubleArray, doubleValueToSearch);

        Utils.printOperationDuration(startTime, "пошук в масивi double");

        if (index >= 0) {
            System.out.println("Значення '" + doubleValueToSearch + "' знайдено в масивi за iндексом: " + index);
        } else {
            System.out.println("Значення '" + doubleValueToSearch + "' в масивi не знайдено.");
        }
    }

    /**
     * Знаходить мiнiмальне та максимальне значення в масивi double.
     */
    void findMinAndMaxInArray() {
        if (doubleArray == null || doubleArray.length == 0) {
            System.out.println("Масив порожнiй або не iнiцiалiзований.");
            return;
        }

        long startTime = System.nanoTime();

        double min = doubleArray[0];
        double max = doubleArray[0];

        for (double doubleValue : doubleArray) {
            if (doubleValue < min) {
                min = doubleValue;
            }
            if (doubleValue > max) {
                max = doubleValue;
            }
        }

        Utils.printOperationDuration(startTime, "пошук мiнiмальної i максимальної дати i часу в масивi");

        System.out.println("Мiнiмальне значення в масивi: " + min);
        System.out.println("Максимальне значення в масивi: " + max);
    }

    /**
     * Шукає задане значення double в LinkedList.
     */
    void searchList() {
        long startTime = System.nanoTime();

        int index = Collections.binarySearch(this.doubleList, doubleValueToSearch);

        Utils.printOperationDuration(startTime, "пошук в LinkedList double");

        if (index >= 0) {
            System.out.println("Значення '" + doubleValueToSearch + "' знайдено в LinkedList за iндексом: " + index);
        } else {
            System.out.println("Значення '" + doubleValueToSearch + "' в LinkedList не знайдено.");
        }
    }

    /**
     * Знаходить мiнiмальне та максимальне значення в LinkedList double.
     */
    void findMinAndMaxInList() {
        if (doubleList == null || doubleList.isEmpty()) {
            System.out.println("LinkedList порожнiй або не iнiцiалiзований.");
            return;
        }

        long startTime = System.nanoTime();

        double min = Collections.min(doubleList);
        double max = Collections.max(doubleList);

        Utils.printOperationDuration(startTime, "пошук мiнiмальної i максимальної дати i часу в LinkedList");

        System.out.println("Мiнiмальне значення в LinkedList: " + min);
        System.out.println("Максимальне значення в LinkedList: " + max);
    }

    /**
     * Сортує LinkedList об'єктiв double та виводить початковий i вiдсортований список.
     * Вимiрює та виводить час, витрачений на сортування списку в наносекундах.
     */
    void sortList() {
        long startTime = System.nanoTime();

        Collections.sort(doubleList);

        Utils.printOperationDuration(startTime, "сортування LinkedList double");
    }
}

/**
 * Клас Utils мiститить допомiжнi методи для роботи з даними типу double.
 */
class Utils {
    /**
     * Виводить час виконання операцiї в наносекундах.
     * 
     * @param startTime Час початку операцiї в наносекундах.
     * @param operationName Назва операцiї.
     */
    static void printOperationDuration(long startTime, String operationName) {
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("\n>>>>>>>>> Час виконання операцiї '" + operationName + "': " + duration + " наносекунд");
    }

    /**
     * Зчитує масив об'єктiв double з файлу.
     * 
     * @param pathToFile Шлях до файлу з даними.
     * @return Масив об'єктiв double.
     */
    static double[] readArrayFromFile(String pathToFile) {
        double[] tempArray = new double[1000];
        int index = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                tempArray[index++] = Double.parseDouble(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        double[] finalArray = new double[index];
        System.arraycopy(tempArray, 0, finalArray, 0, index);

        return finalArray;
    }

    /**
     * Записує масив об'єктiв double у файл.
     * 
     * @param doubleArray Масив об'єктiв double.
     * @param pathToFile Шлях до файлу для запису.
     */
    static void writeArrayToFile(double[] doubleArray, String pathToFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathToFile))) {
            for (double value : doubleArray) {
                writer.write(String.valueOf(value));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
