package com.gupao.mydemo.sort;

/**
 * @ClassName InsertSort
 * @Author LIUHANPENG
 * @Date 2019/11/21 0021 17:22
 * 插入排序--默认某个数据左侧是有序的
 **/
public class InsertSort {

    public static void main(String[] args) {
        Integer[] list = new Integer[]{77,99,44,55,22,88,11,00,66,33};
        long start = System.currentTimeMillis();
        Integer[] sortedList = insertSort(list);
        System.out.println("耗时："+(System.currentTimeMillis()-start));
        for(Integer data:sortedList){
            System.out.print(data+" ");
        }
    }

    public static Integer[] insertSort(Integer[] list){
        int j;
        for(int i=1;i<list.length;i++){
            int temp = list[i];
            j = i;
            while (j>0 && list[j-1]>=temp){
                list[j]=list[j-1];
                j--;
            }
            list[j]=temp;
        }
        return list;
    }
}
