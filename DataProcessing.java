import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class DataProcessing {

    // Method for reading from file and converting to Double array
    public static Double[] readArrayFromFile(String pathToFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            return br.lines()
                     .map(dataLine -> {
                         try {
                             return Double.parseDouble(dataLine); // Convert to Double
                         } catch (NumberFormatException e) {
                             System.err.println("Invalid number format: " + dataLine); // Log error for invalid data
                             return Double.NaN; // Return NaN for invalid entries
                         }
                     })
                     .filter(d -> !d.isNaN()) // Filter out NaN values
                     .toArray(Double[]::new); // Convert to array
        } catch (IOException e) {
            throw new RuntimeException("Error reading data from file: " + pathToFile, e);
        }
    }

    // Method for sorting an array of doubles
    public static void sortAndPrintDoubles(Double[] data) {
        Arrays.sort(data);
        System.out.println("Sorted Data: ");
        for (Double d : data) {
            System.out.println(d);
        }
    }

    // Method for saving sorted data to file
    public static void saveSortedArrayToFile(Double[] sortedArray, String outputPath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            for (Double value : sortedArray) {
                writer.write(value.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error writing sorted data to file: " + outputPath, e);
        }
    }

    public static void main(String[] args) {
        // Example usage
        String pathToFile = "./list/double.data"; // Input file with doubles
        Double[] data = readArrayFromFile(pathToFile);
        
        // Sorting and printing the data
        sortAndPrintDoubles(data);
        
        // Save sorted data to a new file
        saveSortedArrayToFile(data, "./list/sorted_data.txt");
    }
}
