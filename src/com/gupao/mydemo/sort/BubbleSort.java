package com.gupao.mydemo.sort;

/**
 * @ClassName BubbleSort
 * @Author LIUHANPENG
 * @Date 2019/11/21 0021 16:43
 * 冒泡排序
 **/
public class BubbleSort {

    public static void main(String[] args) {
        Integer[] list = new Integer[]{77,99,44,55,22,88,11,00,66,33};
        long start = System.currentTimeMillis();
        Integer[] sortedList = bubbleSort(list);
        System.out.println("耗时："+(System.currentTimeMillis()-start));
        for(Integer data:sortedList){
            System.out.print(data+" ");
        }
    }

    public static Integer[] bubbleSort(Integer[] list){
        for(int i=0;i<list.length;i++){
            for(int j=i+1;j<list.length;j++){
                if(list[i]<list[j]){
                    int temp = list[i];
                    list[i] = list[j];
                    list[j]=temp;
                }
            }
        }
        return list;
    }

}
