package com.gupao.mydemo.sort;

/**
 * @ClassName SelectionSort
 * @Author LIUHANPENG
 * @Date 2019/11/21 0021 17:09
 * 选择排序--选个最小的，进行判断后才替换位置
 **/
public class SelectionSort {

    public static void main(String[] args) {
        Integer[] list = new Integer[]{77,99,44,55,22,88,11,00,66,33};
        long start = System.currentTimeMillis();
        Integer[] sortedList = selectionSort(list);
        System.out.println("耗时："+(System.currentTimeMillis()-start));
        for(Integer data:sortedList){
            System.out.print(data+" ");
        }
    }

    public static Integer[] selectionSort(Integer[] list){
        int min;
        for(int i=0;i<list.length-1;i++){
            min = list[i];
            for(int j=i+1;j<list.length;j++){
                if(list[j]<min){
                    min = list[j];
                    int temp = list[i];
                    list[i]=list[j];
                    list[j]=temp;
                }
            }
        }
        return list;
    }
}
