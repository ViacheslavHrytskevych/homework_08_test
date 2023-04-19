import java.util.List;

public class FileData {

    //  Класс представляет конкретный файл и состоит из: имя файла, размера в байтах, путь к файлу.

    private String fileName;
    private int sizeInBytes;
    private String filePath;

    public FileData(String fileName, int sizeInBytes, String filePath) {
        this.fileName = fileName;
        this.sizeInBytes = sizeInBytes;
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public int getSizeInBytes() {
        return sizeInBytes;
    }

    public String getFilePath() {
        return filePath;
    }
}
