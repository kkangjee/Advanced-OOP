import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.ArrayList;

public class LoadHudDisplays implements LoadDisplayInterface {
    private String txtName;

    public LoadHudDisplays(String txtName) {
        this.txtName = txtName;
    }

    @Override
    public ArrayList<String> load() {
        ArrayList<String> arr = new ArrayList<>();
        String line;
        try {
            LineNumberReader readme = new LineNumberReader(
                    new FileReader(txtName));

            while ((line = readme.readLine()) != null) {
                //System.out.println("line " + readme.getLineNumber() + " = " + line);
                arr.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(arr);
        return arr;
    }
}


