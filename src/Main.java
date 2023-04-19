import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        FileNavigator fileNavigator = new FileNavigator();
        Scanner console = new Scanner(System.in);

        System.out.println("Start");

        while (true) {
            System.out.println("1 - добавить новый файл" +
                    "\n2 - текущий список файлов" +
                    "\n3 - файлы по одному пути" +
                    "\n4 - файлы не больше размера" +
                    "\n5 - удалить путь и связанные с ним файлы" +
                    "\n6 - отсортировать файлы по размеру (по возрастанию)");

            int choice;

            switch (choice = console.nextInt()) {
                case 1:
                    System.out.println("Введите имя файла:");
                    String fileName = console.next();
                    System.out.println("Введите размер файла в байтах:");
                    int sizeInBytes = console.nextInt();
                    System.out.println("Введите путь к файлу:");
                    String filePath = console.next();
                    FileData fileData = new FileData(fileName, sizeInBytes, filePath);
                    fileNavigator.add(fileData);
                    break;
                case 2:
                    fileNavigator.draw();
                    break;
                case 3:
                    System.out.println("Введите путь для поиска файлов:");
                    String path = console.next();
                    List<FileData> files = fileNavigator.find(path);
                    System.out.println("Список файлов по пути " + path + ":");
                    for (FileData file : files) {
                        System.out.println(file.getFileName());
                    }
                    break;
                case 4:
                    System.out.println("Введите максимальный размер файла в байтах:");
                    int maxSize = console.nextInt();
                    List<FileData> filteredFiles = fileNavigator.filterBySize(maxSize);
                    fileNavigator.showFilteredFilesBySize(filteredFiles);
                    break;
                case 5:
                    System.out.println("Введите путь к файлу, который нужно удалить:");
                    String pathToDelete = console.next();
                    fileNavigator.remove(pathToDelete);
                    break;
                case 6:
                    List<FileData> sortedFiles = fileNavigator.sortBySize();
                    System.out.println("Файлы, отсортированные по размеру (по возрастанию):");
                    for (FileData file : sortedFiles) {
                        System.out.println("Адрес файла: " + file.getFilePath() +
                                ", Имя файла: " + file.getFileName() +
                                ", Размер файла: " + file.getSizeInBytes() + " байт");
                    }
                    break;
                default:
                    System.out.println("Неверный выбор");
                    break;
            }
        }
    }
}
