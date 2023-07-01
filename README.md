# Sorting-Visualizer
Java Sorting Visualizer using *Swing Components | Swing Worker | Graphics 2D

## Sorting Algorithms :
* Merge Sort

![merge sort](https://github.com/ManishK4514/Sorting-Visualizer/assets/108109935/4e22fb9c-8f69-4240-8d52-39c3970e15ad)

Merge sort is a popular sorting algorithm that follows the divide-and-conquer approach. It works by recursively dividing the input array into smaller subarrays, sorting them individually, and then merging them back together to produce the final sorted array.

Here's a step-by-step explanation of how merge sort works, using an example:

Let's say we have an unsorted array: [7, 2, 9, 4, 1, 5, 3, 8, 6]

Step 1: Divide
The first step is to divide the array into smaller subarrays until we have single elements. We repeatedly divide the array in half until each subarray contains only one element.

[7, 2, 9, 4, 1, 5, 3, 8, 6]

↓

[7, 2, 9, 4], [1, 5, 3, 8, 6]

↓

[7, 2], [9, 4], [1, 5], [3, 8, 6]

↓

[7], [2], [9], [4], [1], [5], [3], [8], [6]

Step 2: Merge
Next, we start merging the subarrays back together while sorting them in the process. We compare the elements from the left and right subarrays and place them in the correct order in a new merged array.

[7], [2], [9], [4], [1], [5], [3], [8], [6]

↓

[2, 7], [4, 9], [1, 5], [3, 6, 8]

↓

[2, 4, 7, 9], [1, 3, 5, 6, 8]

↓

[1, 2, 3, 4, 5, 6, 7, 8, 9]

Step 3: Final Result
Finally, we have a single sorted array:

[1, 2, 3, 4, 5, 6, 7, 8, 9]

This is the sorted version of the original array using the merge sort algorithm.

Merge sort has a time complexity of O(n log n), which means it's quite efficient even for large input sizes. It's a stable sorting algorithm, meaning it preserves the relative order of equal elements in the sorted output.
_____________________________________________________________________________________________________________________________________________________________________________________________________________________________

* Insertion Sort
 
  ![insertion sort](https://github.com/ManishK4514/Sorting-Visualizer/assets/108109935/8bfed4b0-7b7f-41c4-9ff3-c95554812258)

Insertion sort is a simple and intuitive sorting algorithm that works by repeatedly inserting elements from an unsorted portion of the array into their correct position within a sorted portion of the array. It is an in-place sorting algorithm, meaning it operates directly on the input array without requiring extra space.

Here's a step-by-step explanation of how insertion sort works, using an example:

Let's say we have an unsorted array: [7, 2, 9, 4, 1, 5, 3, 8, 6]

Step 1: Initialization
The first element of the array is considered as the sorted portion. So, we start with the first element at index 0.

[7, 2, 9, 4, 1, 5, 3, 8, 6]

↑ (sorted portion)

Step 2: Insertion
We then iterate through the remaining unsorted portion of the array, starting from the second element (index 1). For each element, we compare it with the elements in the sorted portion from right to left until we find its correct position.

Comparing 2 with 7, we find that 2 should be inserted before 7 in the sorted portion.

[2, 7, 9, 4, 1, 5, 3, 8, 6]

↑ (sorted portion)

Comparing 9 with 7, we find that 9 is already in the correct position.

[2, 7, 9, 4, 1, 5, 3, 8, 6]

↑ (sorted portion)

Comparing 4 with 9, we find that 4 should be inserted before 9 in the sorted portion.

[2, 7, 4, 9, 1, 5, 3, 8, 6]

↑ (sorted portion)

Similarly, we continue this process for the remaining elements until the entire array is sorted.

Step 3: Final Result
After completing the insertion process for all elements, we obtain a sorted array.

[1, 2, 3, 4, 5, 6, 7, 8, 9]

This is the sorted version of the original array using the insertion sort algorithm.

Insertion sort has a time complexity of O(n^2) in the worst case, making it less efficient than more advanced sorting algorithms such as merge sort or quicksort. However, insertion sort performs well on small arrays or nearly sorted arrays, and it has the advantage of being an in-place sorting algorithm with low memory overhead.

_____________________________________________________________________________________________________________________________________________________________________________________________________________________________


* Selection Sort

  ![Selection sort](https://github.com/ManishK4514/Sorting-Visualizer/assets/108109935/dfce618e-fb00-4b12-825f-a62370764c24)


Selection sort is a simple comparison-based sorting algorithm that divides the input array into two portions: the sorted portion and the unsorted portion. It works by repeatedly selecting the smallest (or largest) element from the unsorted portion and swapping it with the element at the beginning of the unsorted portion. This process continues until the entire array is sorted.

Here's a step-by-step explanation of how selection sort works, using an example:

Let's say we have an unsorted array: [7, 2, 9, 4, 1, 5, 3, 8, 6]

Step 1: Initialization
Initially, the sorted portion is empty, and the unsorted portion consists of the entire array.

[7, 2, 9, 4, 1, 5, 3, 8, 6]

↑ (sorted portion)

↑ (unsorted portion)

Step 2: Selection
In each iteration, we find the smallest element from the unsorted portion and swap it with the element at the beginning of the unsorted portion.

First, we find the smallest element (1) from the unsorted portion and swap it with the first element (7).

[1, 2, 9, 4, 7, 5, 3, 8, 6]

↑ (sorted portion)

↑ (unsorted portion)

Next, we find the smallest element (2) from the remaining unsorted portion and swap it with the second element (2 is already in the correct position, so no swap is performed).

[1, 2, 9, 4, 7, 5, 3, 8, 6]

↑ (sorted portion)

↑ (unsorted portion)

We repeat this process for the remaining elements until the entire array is sorted.

Step 3: Final Result
After completing the selection process for all elements, we obtain a sorted array.

[1, 2, 3, 4, 5, 6, 7, 8, 9]

This is the sorted version of the original array using the selection sort algorithm.

Selection sort has a time complexity of O(n^2) in all cases, which means it is not the most efficient sorting algorithm for large input sizes. However, it has the advantage of being easy to understand and implement. It also performs a minimal number of swaps, which can be beneficial in certain scenarios where swapping elements is expensive or costly.

_____________________________________________________________________________________________________________________________________________________________________________________________________________________________

* Bubble Sort

  ![bubble sort](https://github.com/ManishK4514/Sorting-Visualizer/assets/108109935/cb21f26b-d92d-42e1-95e1-33dd08cac596)

Bubble sort is a simple and elementary sorting algorithm that repeatedly compares adjacent elements in an array and swaps them if they are in the wrong order. It repeatedly passes through the array, gradually "bubbling" the largest (or smallest) elements to their correct positions. The algorithm gets its name from the way larger (or smaller) elements "bubble" to the end of the array during each pass.

Here's a step-by-step explanation of how bubble sort works, using an example:

Let's say we have an unsorted array: [7, 2, 9, 4, 1, 5, 3, 8, 6]

Step 1: Passes
In each pass, we compare adjacent elements in the array and swap them if they are in the wrong order. We repeat this process for the remaining unsorted portion of the array.

First Pass:
We start with the first two elements (7 and 2). Since 7 is greater than 2, we swap them.

[2, 7, 9, 4, 1, 5, 3, 8, 6]

We continue this process for the remaining adjacent elements:

[2, 7, 9, 4, 1, 5, 3, 8, 6]
[2, 7, 4, 9, 1, 5, 3, 8, 6]
[2, 7, 4, 1, 9, 5, 3, 8, 6]
[2, 7, 4, 1, 5, 9, 3, 8, 6]
[2, 7, 4, 1, 5, 3, 9, 8, 6]
[2, 7, 4, 1, 5, 3, 8, 9, 6]
[2, 7, 4, 1, 5, 3, 8, 6, 9]

After the first pass, the largest element (9) is in its correct position at the end of the array.

Second Pass:
We repeat the same process for the remaining unsorted portion of the array:

[2, 7, 4, 1, 5, 3, 8, 6, 9]
[2, 4, 7, 1, 5, 3, 8, 6, 9]
[2, 4, 1, 7, 5, 3, 8, 6, 9]
[2, 4, 1, 5, 7, 3, 8, 6, 9]
[2, 4, 1, 5, 3, 7, 8, 6, 9]
[2, 4, 1, 5, 3, 7, 6, 8, 9]

After the second pass, the second largest element (8) is in its correct position.

We continue this process until the entire array is sorted.

Step 2: Final Result
After completing all the passes, we obtain a sorted array.

[1, 2, 3, 4, 5, 6, 7, 8, 9]

This is the sorted version of the original array using the bubble sort algorithm.

Bubble sort has a time complexity of O(n^2) in the worst and average cases, making it inefficient for large input sizes. However, it has the advantage of being easy to understand and implement. Bubble

 sort is mainly used for educational purposes or when dealing with small datasets where simplicity is preferred over efficiency.  

_____________________________________________________________________________________________________________________________________________________________________________________________________________________________

* Quick Sort

  ![Quick Sort](https://github.com/ManishK4514/Sorting-Visualizer/assets/108109935/62f982d2-1259-4754-a637-767a32fbcf8f)

Quick sort is a widely used efficient sorting algorithm that follows the divide-and-conquer approach. It works by selecting a "pivot" element from the array and partitioning the other elements into two subarrays, according to whether they are less than or greater than the pivot. The subarrays are then recursively sorted, and finally combined to produce the sorted output.

Here's a step-by-step explanation of how quick sort works, using an example:

Let's say we have an unsorted array: [7, 2, 9, 4, 1, 5, 3, 8, 6]

Step 1: Choosing a Pivot
The first step is to choose a pivot element from the array. There are various strategies for selecting a pivot, but for simplicity, we'll choose the rightmost element in this example. So, our pivot is 6.

[7, 2, 9, 4, 1, 5, 3, 8, 6]
                                ↑ (Pivot)

Step 2: Partitioning
Next, we partition the array into two subarrays based on the pivot. All elements less than the pivot are placed to the left, and all elements greater than the pivot are placed to the right. The pivot itself is in its final sorted position.

[2, 4, 1, 5, 3], [6], [7, 9, 8]
                  ↑ (Pivot in sorted position)

Step 3: Recursion
We recursively apply the same steps to the subarrays on the left and right of the pivot until the entire array is sorted.

Recursion on the left subarray ([2, 4, 1, 5, 3]):
- Choose a pivot (3) and partition the subarray:
  [2, 1], [3], [4, 5]

Recursion on the right subarray ([7, 9, 8]):
- Choose a pivot (8) and partition the subarray:
  [7], [8], [9]

After completing the recursive steps, we have the following partitions:
[1], [2], [3], [4], [5], [6], [7], [8], [9]

Step 4: Final Result
Finally, we combine the sorted subarrays to obtain the final sorted array:

[1, 2, 3, 4, 5, 6, 7, 8, 9]

This is the sorted version of the original array using the quick sort algorithm.

Quick sort has an average-case time complexity of O(n log n) and is often considered one of the fastest sorting algorithms. However, in the worst case (when the pivot is always the smallest or largest element), the time complexity can be O(n^2). Various optimizations and pivot selection strategies can be employed to mitigate this issue and improve the algorithm's performance.  

_____________________________________________________________________________________________________________________________________________________________________________________________________________________________


## Main Libraries Used :
* Swing Worker
* Swing Components
* Graphics 2D

## Skills Learned :
* Multi-Threading using Swing Worker
* Customising Swing Components
* Graphics 2D
* Apply Sorting Algorithms

## Program Structure :
  
### screen
* constains 4 *CustomJPanels*
* *SHUFFLE* is a control button through which we can Shuffle the bars.
* *SORT* is a control button through which we can sort the bars with the help of `current_algorithm`.
* *SPEED SLIDER* is a slider control through which we can control the speed of the `current_sorting_algorithm`.
* *SIZE SLIDER* is a slider control through which we can control the total no. of bars.
* *SortingPanel* is the *CustomJPanel* that handles the sorting and painting on the Component.
  
### sorting algorithm
* contains all the sorting algorithms used
* we have the independent java file for each of the *SortAlgorithm*.
* *SortingAlgorithm* is an `enum` that controls the request from other panels. It also handles methods that are being called in the `current_algorithm`.
  
### customJComponent
* JComponents that have various constructors to meet the needs of the program.

There are three main indices here: `current_index`, `traversing_index`, and `selected_index`. Their task is to visually guide the user on the current state of the sorting process. Their uses varies from each Sorting Algorithms.
The information displayed such as Time Complexities and Space Complexity are being set up in the *SortingAlgorithm* enum.
Lastly, the only dynamic value on the HUD is the `array_access` which is being updated during the sorting process.

Here's my email: manish80842@gmail.com, if you have found a bug or if you have any queries regarding this project feel free to contact me.
_____________________________________________________________________________________________________________________________________________________________________________________________________________________________

