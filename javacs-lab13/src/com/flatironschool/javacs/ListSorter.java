/**
 * 
 */
package com.flatironschool.javacs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Provides sorting algorithms.
 *
 */
public class ListSorter<T> {

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void insertionSort(List<T> list, Comparator<T> comparator) {
	
		for (int i=1; i < list.size(); i++) {
			T elt_i = list.get(i);
			int j = i;
			while (j > 0) {
				T elt_j = list.get(j-1);
				if (comparator.compare(elt_i, elt_j) >= 0) {
					break;
				}
				list.set(j, elt_j);
				j--;
			}
			list.set(j, elt_i);
		}
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void mergeSortInPlace(List<T> list, Comparator<T> comparator) {
		List<T> sorted = mergeSort(list, comparator);
		list.clear();
		list.addAll(sorted);
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * Returns a list that might be new.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public List<T> mergeSort(List<T> list, Comparator<T> comparator) {
        // FILL THIS IN!
        if (list.size() <= 1) {
        	return list;
        } else {
			int size = list.size();
			LinkedList l1 = new LinkedList(list.subList(0, size / 2));
			LinkedList l2 = new LinkedList(list.subList(size / 2, size));
 			List<T> left = mergeSort(l1, comparator);
 			List<T> right = mergeSort(l2, comparator);
 			List<T> ret = new LinkedList<T>();
 			int firstIndex = 0;
 			int secondIndex = 0;
 			for (int i = 0; i < size; i++) {
 				if (firstIndex < left.size() && secondIndex < right.size()) {
 					if (comparator.compare(left.get(firstIndex), right.get(secondIndex)) < 0) {
 						ret.add(left.get(firstIndex));
 						firstIndex++;
					} else {
 						ret.add(right.get(secondIndex));
 						secondIndex++;
 					}
 				} else if (firstIndex < left.size()) {
 					ret.add(left.get(firstIndex));
 					firstIndex++;
 				} else if (secondIndex < right.size()) {
 					ret.add(right.get(secondIndex));
 					secondIndex++;
 				}
 			}
 			return ret;
 		}
 	}	

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void heapSort(List<T> list, Comparator<T> comparator) {
        // FILL THIS IN!
        PriorityQueue<T> heap  = new PriorityQueue<T>(comparator);

		for (T element : list) {
			heap.offer(element);
		}

		list.clear();

		while (!heap.isEmpty()) {
			list.add(heap.poll());
		}
	}

	
	/**
	 * Returns the largest `k` elements in `list` in ascending order.
	 * 
	 * @param k
	 * @param list
	 * @param comparator
	 * @return 
	 * @return
	 */
	public List<T> topK(int k, List<T> list, Comparator<T> comparator) {
        // FILL THIS IN!
        PriorityQueue<T> heap = new PriorityQueue<T>(list.size(), comparator);
 		for (T element: list) {
 			// add the first k elements
 			if (heap.size() < k) {
 				heap.offer(element);
 			} else if (comparator.compare(element, heap.peek()) > 0) {
 				heap.poll();
 				heap.offer(element);
 			}
 		}
 		
		list.clear();

		while (!heap.isEmpty()) {
			list.add(heap.poll());
		}

		return list;
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		
		Comparator<Integer> comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer n, Integer m) {
				return n.compareTo(m);
			}
		};
		
		ListSorter<Integer> sorter = new ListSorter<Integer>();
		sorter.insertionSort(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.mergeSortInPlace(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.heapSort(list, comparator);
		System.out.println(list);
	
		list = new ArrayList<Integer>(Arrays.asList(6, 3, 5, 8, 1, 4, 2, 7));
		List<Integer> queue = sorter.topK(4, list, comparator);
		System.out.println(queue);
	}
}
