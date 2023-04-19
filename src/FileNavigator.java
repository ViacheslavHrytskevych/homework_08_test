import java.util.*;

public class FileNavigator {

    private Map<String, List<FileData>> filesByPath = new HashMap<>();

    public void add(FileData fileData) {

        String filePath = fileData.getFilePath();
        String key = getKeyFromPath(filePath);
        if (!filesByPath.containsKey(key)) {
            filesByPath.put(key, new ArrayList<>());
        }
        List<FileData> files = filesByPath.get(key);
        if (isFileDataConsistentWithKeyPath(fileData, key)) {
            files.add(fileData);
        } else {
            System.out.println("Ошибка: значение пути для " + fileData.getFileName() + " не соответствует ключу " + key);
        }
    }

    private String getKeyFromPath(String path) {
        return path.substring(0, path.lastIndexOf("/"));
    }

    private boolean isFileDataConsistentWithKeyPath(FileData fileData, String keyPath) {
        return fileData.getFilePath().startsWith(keyPath);
    }

    public List<FileData> find(String path) {

        String key = getKeyFromPath(path);
        if (!filesByPath.containsKey(key)) {
            return new ArrayList<>();
        }
        List<FileData> files = filesByPath.get(key);
        List<FileData> result = new ArrayList<>();
        for (FileData file : files) {
            if (file.getFilePath().startsWith(path)) {
                result.add(file);
            }
        }
        return result;
    }

    public List<FileData> filterBySize(int maxSize) {

        List<FileData> filteredFiles = new ArrayList<>();

        for (List<FileData> fileList : filesByPath.values()) {
            for (FileData fileData : fileList) {
                if (fileData.getSizeInBytes() <= maxSize) {
                    filteredFiles.add(fileData);
                }
            }
        }

        return filteredFiles;
    }

    public void remove(String path) {

        if (filesByPath.containsKey(path)) {
            filesByPath.remove(path);
        } else {
            String key = getKeyFromPath(path);
            if (filesByPath.containsKey(key)) {
                List<FileData> files = filesByPath.get(key);
                files.removeIf(fileData -> fileData.getFilePath().equals(path));
                if (files.isEmpty()) {
                    filesByPath.remove(key);
                }
            }
        }
    }
    public List<FileData> sortBySize() {

        List<FileData> allFiles = new ArrayList<>();
        for (List<FileData> files : filesByPath.values()) {
            allFiles.addAll(files);
        }
        Collections.sort(allFiles, Comparator.comparingInt(FileData::getSizeInBytes));
        return allFiles;

    }

    public void draw() {
        for (Map.Entry<String, List<FileData>> entry : filesByPath.entrySet()) {
            String key = entry.getKey();
            List<FileData> files = entry.getValue();
            for (FileData file : files) {
                System.out.println("Адрес файла: " + file.getFilePath() +
                        ", Имя файла: " + file.getFileName() +
                        ", Размер файла: " + file.getSizeInBytes() + " байт");
            }
        }
    }
    public void printFiles(List<FileData> files) {
        for (FileData file : files) {
            System.out.println("Адрес файла: " + file.getFilePath() +
                    ", Имя файла: " + file.getFileName() +
                    ", Размер файла: " + file.getSizeInBytes() + " байт");
        }
    }

    public void showFilteredFilesBySize(List<FileData> filteredFiles) {
        if (filteredFiles.isEmpty()) {
            System.out.println("Файлов не найдено");
            return;
        }
        System.out.println("Список файлов:");
        for (FileData fileData : filteredFiles) {
            System.out.println(fileData.getFilePath() + "\\" + fileData.getFileName());
        }
    }
}
