package site.qipeng;


public class SortTest {

    public static void main(String[] args) {
        SortTest sort = new SortTest();
        int[] arr = new int[]{4, 1, 6, 8, 2, 45, 87, 5, 13, 7, 3, 54};

//        int[] maopao = sort.maopao(arr);
//        for (int a : maopao) {
//            System.out.print(a + ",");
//        }

        int[] charu = sort.charu(arr);
        for (int a : charu) {
            System.out.print(a + ",");
        }
    }

    public int[] maopao(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j< arr.length - i - 1; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return arr;
    }


    public int[] charu(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int value = arr[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (arr[j] > value) {
                    arr[j+1] = arr[j];
                }else {
                    break;
                }
            }
            arr[j+1] = value;
        }
        return arr;
    }
}
