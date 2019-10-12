class Sorter {
    BubbleSort bubbleSort = new CompareFileName();

    public void setBubbleSort(BubbleSort bubbleSort) {
        this.bubbleSort = bubbleSort;
    }

    public void bubbleSort(Object[] objs) {
        bubbleSort.bubbleSort(objs);
    }

}










//    void printInts(int[] data) {
//        for (int i : data) {
//            System.out.println(i);
//        }
//    }
//
//    void printStrings(String[] data2) {
//        for (String i : data2) {
//            System.out.println(i);
//        }
//    }
//
//    void printDates(Date[] data3) {
//        for (Date d : data3) {
//            System.out.println(d);
//        }
//    }