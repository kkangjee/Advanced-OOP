import java.util.Date;

public class FileInfo {
    String name;
    String type;
    int size;
    Date modifiedData;


    public FileInfo(String name, String type, int size, Date modifiedData) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.modifiedData = modifiedData;
    }

    public String toString() {
        return name;
    }

    String getName() {
        return this.name;
    }

    String getType() {
        return this.type;
    }

    int getSize() {
        return this.size;
    }

    Date getModifiedData() {
        return this.modifiedData;
    }


}
