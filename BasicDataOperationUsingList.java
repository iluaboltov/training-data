import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Клас BasicDataOperationUsingList надає методи для виконання основних операцій з даними типу double.
 * 
 * <p>Цей клас зчитує дані з файлу "./list/double.data", сортує їх та виконує пошук значення в масиві double.</p>
 * 
 * <p>Основні методи:</p>
 * <ul>
 *   <li>{@link #main(String[])} - Точка входу в програму.</li>
 *   <li>{@link #doDataOperation()} - Виконує основні операції з даними.</li>
 *   <li>{@link #sortArray()} - Сортує масив double.</li>
 *   <li>{@link #searchArray()} - Виконує пошук значення в масиві double.</li>
 *   <li>{@link #findMinAndMaxInArray()} - Знаходить мінімальне та максимальне значення в масиві double.</li>
 *   <li>{@link #sortList()} - Сортує колекцію ArrayList double.</li>
 *   <li>{@link #searchList()} - Виконує пошук значення в колекції double.</li>
 *   <li>{@link #findMinAndMaxInList()} - Знаходить мінімальне та максимальне значення в колекції double.</li>
 * </ul>
 * 
 * <p>Конструктор:</p>
 * <ul>
 *   <li>{@link #BasicDataOperationUsingList(String[])} - ініціалізує об'єкт з значенням для пошуку.</li>
 * </ul>
 * 
 * <p>Константи:</p>
 * <ul>
 *   <li>{@link #PATH_TO_DATA_FILE} - Шлях до файлу з даними.</li>
 * </ul>
 * 
 * <p>Змінні екземпляра:</p>
 * <ul>
 *   <li>{@link #doubleValueToSearch} - Значення double для пошуку.</li>
 *   <li>{@link #doubleArray} - Масив double.</li>
 *   <li>{@link #doubleList} - Колекція ArrayList double.</li>
 * </ul>
 * 
 * <p>Приклад використання:</p>
 * <pre>
 * {@code
 * java BasicDataOperationUsingList "123.456"
 * }
 * </pre>
 */
public class BasicDataOperationUsingList {
    static final String PATH_TO_DATA_FILE = "./list/double.data";

    double doubleValueToSearch;
    double[] doubleArray;
    ArrayList<Double> doubleList;

    public static void main(String[] args) {
        BasicDataOperationUsingList basicDataOperationUsingList = new BasicDataOperationUsingList(args);
        basicDataOperationUsingList.doDataOperation();
    }

    /**
     * Конструктор, який ініціалізує об'єкт з значенням для пошуку.
     * 
     * @param args Аргументи командного рядка, де перший аргумент - значення для пошуку.
     */
    BasicDataOperationUsingList(String[] args) {
        if (args.length == 0) {
            throw new RuntimeException("Відсутнє значення для пошуку");
        }

        String searchValue = args[0];
        doubleValueToSearch = Double.parseDouble(searchValue);

        doubleArray = Utils.readArrayFromFile(PATH_TO_DATA_FILE);
        doubleList = new ArrayList<>(Arrays.asList(Arrays.stream(doubleArray).boxed().toArray(Double[]::new)));
    }

    /**
     * Виконує основні операції з даними.
     * 
     * Метод зчитує масив та колекцію ArrayList об'єктів double з файлу, сортує їх та виконує пошук значення.
     */
    void doDataOperation() {
        // операції з масивом даних
        searchArray();
        findMinAndMaxInArray();

        sortArray();
        
        searchArray();
        findMinAndMaxInArray();

        // операції з ArrayList
        searchList();
        findMinAndMaxInList();

        sortList();

        searchList();
        findMinAndMaxInList();

        // записати відсортований масив в окремий файл
        Utils.writeArrayToFile(doubleArray, PATH_TO_DATA_FILE + ".sorted");
    }

    /**
     * Сортує масив об'єктів double та виводить початковий і відсортований масиви.
     * Вимірює та виводить час, витрачений на сортування масиву в наносекундах.
     */
    void sortArray() {
        long startTime = System.nanoTime();

        Arrays.sort(doubleArray);

        Utils.printOperationDuration(startTime, "сортування масиву даних");
    }

    /**
     * Метод для пошуку значення в масиві даних.
     */
    void searchArray() {
        long startTime = System.nanoTime();

        int index = Arrays.binarySearch(this.doubleArray, doubleValueToSearch);

        Utils.printOperationDuration(startTime, "пошук в масиві даних");

        if (index >= 0) {
            System.out.println("Значення '" + doubleValueToSearch + "' знайдено в масиві за індексом: " + index);
        } else {
            System.out.println("Значення '" + doubleValueToSearch + "' в масиві не знайдено.");
        }
    }

    /**
     * Знаходить мінімальне та максимальне значення в масиві даних.
     */
    void findMinAndMaxInArray() {
        if (doubleArray == null || doubleArray.length == 0) {
            System.out.println("Масив порожній або не ініціалізований.");
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

        Utils.printOperationDuration(startTime, "пошук мінімальної і максимальної даних в масиві");

        System.out.println("Мінімальне значення в масиві: " + min);
        System.out.println("Максимальне значення в масиві: " + max);
    }

    /**
     * Шукає задане значення даних в ArrayList даних.
     */
    void searchList() {
        long startTime = System.nanoTime();

        int index = Collections.binarySearch(this.doubleList, doubleValueToSearch);

        Utils.printOperationDuration(startTime, "пошук в ArrayList даних");        

        if (index >= 0) {
            System.out.println("Значення '" + doubleValueToSearch + "' знайдено в ArrayList за індексом: " + index);
        } else {
            System.out.println("Значення '" + doubleValueToSearch + "' в ArrayList не знайдено.");
        }
    }

    /**
     * Знаходить мінімальне та максимальне значення в double даних.
     */
    void findMinAndMaxInList() {
        if (doubleList == null || doubleList.isEmpty()) {
            System.out.println("ArrayList порожній або не ініціалізований.");
            return;
        }

        long startTime = System.nanoTime();

        double min = Collections.min(doubleList);
        double max = Collections.max(doubleList);

        Utils.printOperationDuration(startTime, "пошук мінімальної і максимальної даних в ArrayList");

        System.out.println("Мінімальне значення в ArrayList: " + min);
        System.out.println("Максимальне значення в ArrayList: " + max);
    }

    /**
     * Сортує ArrayList об'єктів double та виводить початковий і відсортований списки.
     * Вимірює та виводить час, витрачений на сортування списку в наносекундах.
     */
    void sortList() {
        long startTime = System.nanoTime();

        Collections.sort(doubleList);

        Utils.printOperationDuration(startTime, "сортування ArrayList даних");
    }
}

/**
 * Клас Utils містить допоміжні методи для роботи з даними типу double.
 */
class Utils {
    /**
     * Виводить час виконання операції в наносекундах.
     * 
     * @param startTime Час початку операції в наносекундах.
     * @param operationName Назва операції.
     */
    static void printOperationDuration(long startTime, String operationName) {
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("\n>>>>>>>>> Час виконання операції '" + operationName + "': " + duration + " наносекунд");
    }

    /**
     * Зчитує масив об'єктів double з файлу.
     * 
     * @param pathToFile Шлях до файлу з даними.
     * @return Масив об'єктів double.
     */
    static double[] readArrayFromFile(String pathToFile) {
        double[] tempArray = new double[1000];
        int index = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                double doubleValue = Double.parseDouble(line);
                tempArray[index++] = doubleValue;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        double[] finalArray = new double[index];
        System.arraycopy(tempArray, 0, finalArray, 0, index);

        return finalArray;
    }

    /**
     * Записує масив об'єктів double у файл.
     * 
     * @param doubleArray Масив об'єктів double.
     * @param pathToFile Шлях до файлу для запису.
     */
    static void writeArrayToFile(double[] doubleArray, String pathToFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathToFile))) {
            for (double doubleValue : doubleArray) {
                writer.write(String.valueOf(doubleValue));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}