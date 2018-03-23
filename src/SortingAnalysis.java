import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
public class SortingAnalysis {
	public static void main(String[] args) throws FileNotFoundException{
		
		/* Create the files, only need to use this once
		 * fileCreator(5000);
		 * fileCreator(10000);
		 * fileCreator(20000);
		 * fileCreator(50000);
		 * return;
		*/
		
		//create all arrays, 12 total
		int[] fiveAscending = fileOpener(5000, "ascending");
		int[] fiveDescending = fileOpener(5000, "descending");
		int[] fiveRandom = fileOpener(5000, "random");
		
		int[] tenAscending = fileOpener(10000, "ascending");
		int[] tenDescending = fileOpener(10000, "descending");
		int[] tenRandom = fileOpener(10000, "random");
		
		int[] twentyAscending = fileOpener(20000, "ascending");
		int[] twentyDescending = fileOpener(20000, "descending");
		int[] twentyRandom = fileOpener(20000, "random");
		
		int[] fiftyAscending = fileOpener(50000, "ascending");
		int[] fiftyDescending = fileOpener(50000, "descending");
		int[] fiftyRandom = fileOpener(50000, "random");
		
		//create all average times, 24 total
		long fiveBubbleAscAvg = avgTime(fiveAscending, 1);
		long fiveBubbleDesAvg = avgTime(fiveDescending, 1);
		long fiveBubbleRanAvg = avgTime(fiveRandom, 1);
		
		long tenBubbleAscAvg = avgTime(tenAscending, 1);
		long tenBubbleDesAvg = avgTime(tenDescending, 1);
		long tenBubbleRanAvg = avgTime(tenRandom, 1);
		
		long twentyBubbleAscAvg = avgTime(twentyAscending, 1);
		long twentyBubbleDesAvg = avgTime(twentyDescending, 1);
		long twentyBubbleRanAvg = avgTime(twentyRandom, 1);
		
		long fiftyBubbleAscAvg = avgTime(fiftyAscending, 1);
		long fiftyBubbleDesAvg = avgTime(fiftyDescending, 1);
		long fiftyBubbleRanAvg = avgTime(fiftyRandom, 1);
		
		long fiveQuickAscAvg = avgTime(fiveAscending, 2);
		long fiveQuickDesAvg = avgTime(fiveDescending, 2);
		long fiveQuickRanAvg = avgTime(fiveRandom, 2);
		
		long tenQuickAscAvg = avgTime(tenAscending, 2);
		long tenQuickDesAvg = avgTime(tenDescending, 2);
		long tenQuickRanAvg = avgTime(tenRandom, 2);
		
		long twentyQuickAscAvg = avgTime(twentyAscending, 2);
		long twentyQuickDesAvg = avgTime(twentyDescending, 2);
		long twentyQuickRanAvg = avgTime(twentyRandom, 2);
		
		long fiftyQuickAscAvg = avgTime(fiftyAscending, 2);
		long fiftyQuickDesAvg = avgTime(fiftyDescending, 2);
		long fiftyQuickRanAvg = avgTime(fiftyRandom, 2);

		//print out times
		System.out.print("5000 Ascending Average:\n\tBubble: " + fiveBubbleAscAvg + "\n\tQuick: " + fiveQuickAscAvg);
		System.out.print("\n10000 Ascending Average:\n\tBubble: " + tenBubbleAscAvg + "\n\tQuick: " + tenQuickAscAvg);
		System.out.print("\n20000 Ascending Average:\n\tBubble: " + twentyBubbleAscAvg + "\n\tQuick: " + twentyQuickAscAvg);
		System.out.print("\n50000 Ascending Average:\n\tBubble: " + fiftyBubbleAscAvg + "\n\tQuick: " + fiftyQuickAscAvg);

		System.out.print("\n\n5000 Descending Average:\n\tBubble: " + fiveBubbleDesAvg + "\n\tQuick: " + fiveQuickDesAvg);
		System.out.print("\n10000 Descending Average:\n\tBubble: " + tenBubbleDesAvg + "\n\tQuick: " + tenQuickDesAvg);
		System.out.print("\n20000 Descending Average:\n\tBubble: " + twentyBubbleDesAvg + "\n\tQuick: " + twentyQuickDesAvg);
		System.out.print("\n\n50000 Descending Average:\n\tBubble: " + fiftyBubbleDesAvg + "\n\tQuick: " + fiftyQuickDesAvg);

		System.out.print("\n\n5000 Random Average:\n\tBubble: " + fiveBubbleRanAvg + "\n\tQuick: " + fiveQuickRanAvg);
		System.out.print("\n10000 Random Average:\n\tBubble: " + tenBubbleRanAvg + "\n\tQuick: " + tenQuickRanAvg);
		System.out.print("\n20000 Random Average:\n\tBubble: " + twentyBubbleRanAvg + "\n\tQuick: " + twentyQuickRanAvg);
		System.out.print("\n50000 Random Average:\n\tBubble: " + fiftyBubbleRanAvg + "\n\tQuick: " + fiftyQuickRanAvg);
			
	}	
	/** Opens file and stores it as an int[]
	 * Best, Worst, Average: O(n) where n is the amount of lines in the file being read
	 * @param size
	 * @param order
	 * @return int[]
	 */
	public static int[] fileOpener(int size, String order){
		//create new array
		int[] array = new int[size];
		
		//create file name
		String fileName = size + " " + order + ".txt";
		
		//create these for incrementing file
		String line;	
		int counter = 0;
		
		//try/catch for exceptions
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			//read line by line finding every int, adding it to array
			while((line = bufferedReader.readLine()) != null){
				array[counter] = Integer.parseInt(line);
				counter++;
			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Unable to open file: " + fileName);
		} catch (IOException e) {
			System.out.println("Error while reading file: " + fileName);
		}
		
		return array;
	}
	
	/** bubble sort timer
	 * Worst, Average: O(n^2) where n is the amount of elements in the array
	 * Best: O(n) where n is the amount of elements in the array
	 * does bubble sort and times it in nanoseconds
	 * @param array
	 * @return long
	 */
	public static long bubbleSortTimer(int[] array){
		
		//create copy of OG array
		int [] arrayCopy = array.clone();
		
		//start timer
		long startTime = System.nanoTime();
		
		//do bubble sort
		for (int x = 0; x < arrayCopy.length - 1; x++){
			for (int y = 0; y < arrayCopy.length - x - 1; y++){
				if (arrayCopy[y] > arrayCopy[y + 1]){
					int temp = arrayCopy[y];
					arrayCopy[y] = arrayCopy[y + 1];
					arrayCopy[y + 1] = temp;
				}
			}
		}
		
		//end timer
		long endTime = System.nanoTime();	
		
		//return difference
		return endTime - startTime;
	}
	/** quick sort timer
	 * O(1) constant time
	 * calls quick sort function and times it in nanoseconds
	 * @param array
	 * @return long
	 */
	public static long quickSortTimer(int[] array){
		
		//create copy of OG array
		int [] arrayCopy = array.clone();
		
		//start timer
		long startTime = System.nanoTime();
		
		//call quickSort
		int low = 0;
		int high = arrayCopy.length - 1;
		quickSort(arrayCopy, low, high);
		
		//end timer
		long endTime = System.nanoTime();

		//return difference
		return endTime - startTime;
	}
	
	/** recursive quick sort
	 * Best, Average: O(nlogn) where n is the amount of elements in the array
	 * Worst: O(n^2) where n is the amount of elements in the array
	 * @param array
	 * @param low
	 * @param high
	 */
	public static void quickSort(int[] array, int low, int high){
		if (array == null || array.length == 0){
			return;
		}
		if (low >= high){
			return;
		}
		
		//pick pivot
		int middle = low + (high - low) / 2;
		int pivot = array[middle];
		
		//sort left < pivot and right > pivot
		int x = low;
		int y = high;
		
		while (x <= y){
			while (array[x] < pivot){
				x++;
			}
			while (array[y] > pivot){
				y--;
			}
			if (x <= y){
				int temp = array[x];
				array[x] = array[y];
				array[y] = temp;
				x++;
				y--;
			}
		}
		
		if (low < y){
			quickSort(array, low, y);
		}
		if (high > x){
			quickSort(array, x, high);
		}
		
	}
	/** find average of 5 runs
	 * O(1) constant time
	 * @param array
	 * @param  int sort (1 for bubble, 2 for quick)
	 * @return long
	 */
	public static long avgTime(int[] array, int sort){
		//initialize average variable
		long avgTime = 0;
		
		//if 1, add 20 bubble sort times
		if (sort == 1){
			for (int x = 0; x < 5; x++){
				avgTime += bubbleSortTimer(array);
			}
		}
		
		//if 2, add 20 quick sort times
		if (sort == 2){
			for (int x = 0; x < 5; x++){
				avgTime += quickSortTimer(array);
			}
		}
		
		//return average
		return avgTime / 5;
	}
	
	/** Method for creating all files of size specified (ex. random, ascending, descending), only run this once separately
	 * O(n) where n is the size specified
	 * @param size
	 * @throws FileNotFoundException
	 */
	public static void fileCreator(int size) throws FileNotFoundException{
		
		//create random file
		PrintWriter writer = new PrintWriter(size + " random.txt");
		
		//create arrayList of specified size and fill in order
        ArrayList<Integer> list = new ArrayList<Integer>(size);
        for(int i = 1; i <= size; i++) {
            list.add(i);
        }

        //select a random index, write it to file, remove from arrayList
        Random rand = new Random();
        while(list.size() > 0) {
            int index = rand.nextInt(list.size());
            writer.println(list.remove(index));
        }	
	    writer.close();
	    
	    //create ascending file
		PrintWriter writer2 = new PrintWriter(size + " ascending.txt");
		for (int i = 1; i <= size; i++){
			writer2.println(i);
		}
		writer2.close();
		
		//create descending file
		PrintWriter writer3 = new PrintWriter(size + " descending.txt");
		for (int i = size; i >= 1; i--){
			writer3.println(i);
		}
		writer3.close();

	}
		
}

