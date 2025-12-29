package edu.touro.mcon264.sorting;

import java.util.Arrays;
import java.util.Comparator;

public class MergeSort implements Sorter {

    @Override
    public <T> T[] sort(T[] a, Comparator<? super T> comp) {
        if (a.length <= 1) return a;

        T[] sorted = sortRecursive(a, comp);
        System.arraycopy(sorted, 0, a, 0, a.length);
        return a;
    }

    public <T> T[] sortRecursive(T[] a, Comparator<? super T> comp) {
        if(a.length<=1){
            return a;
        }
        int mid = a.length/2;
        T[] left = sortRecursive(Arrays.copyOfRange(a,0,mid),comp);
        T[] right = sortRecursive(Arrays.copyOfRange(a,mid,a.length), comp);

        return merge(left,right,comp);
    }

    private<T> T[] merge(T[] left, T[] right, Comparator<? super T> comp){
        T[] result = Arrays.copyOf(left, left.length+right.length);
        int i,j,k;
        i=j=k=0;
        while(i<left.length && j<right.length){
            if(comp.compare(left[i],right[j])<=0){
                result[k] = left[i];
                i++;
            }
            else{
                result[k] = right[j];
                j++;
            }
            k++;
        }

        while(i<left.length){
            result[k] = left[i];
            k++;
            i++;
        }
        while(j<right.length){
            result[k] = right[j];
            k++;
            j++;
        }
        return result;
    }
}
