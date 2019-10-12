public class CompareSize implements BubbleSort {
    public void bubbleSort(Object[] objs) {
        FileInfo[] data = (FileInfo[]) objs;
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = 0; j < data.length - 1; j++) {
                if (data[j].getSize() > data[j + 1].getSize()) { // swap
                    FileInfo temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
    }
}
