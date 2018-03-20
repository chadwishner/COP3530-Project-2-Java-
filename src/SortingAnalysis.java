import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
public class SortingAnalysis {
	public static void main(String[] args) throws FileNotFoundException{
//		fileCreator(5000);
//		fileCreator(10000);
//		fileCreator(20000);
//		fileCreator(50000);
//		return;
		
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

		System.out.print("5000 Ascending:\n\tBubble: " + bubbleSortTimer(fiveAscending) + "\n\tQuick: " + quickSortTimer(fiveAscending));
		System.out.print("\n10000 Ascending:\n\tBubble: " + bubbleSortTimer(tenAscending) + "\n\tQuick: " + quickSortTimer(tenAscending));
		System.out.print("\n20000 Ascending:\n\tBubble: " + bubbleSortTimer(twentyAscending) + "\n\tQuick: " + quickSortTimer(twentyAscending));
		System.out.print("\n50000 Ascending:\n\tBubble: " + bubbleSortTimer(fiftyAscending) + "\n\tQuick: " + quickSortTimer(fiftyAscending));

		System.out.print("\n\n5000 Descending:\n\tBubble: " + bubbleSortTimer(fiveDescending) + "\n\tQuick: " + quickSortTimer(fiveDescending));
		System.out.print("\n10000 Descending:\n\tBubble: " + bubbleSortTimer(tenDescending) + "\n\tQuick: " + quickSortTimer(tenDescending));
		System.out.print("\n20000 Descending:\n\tBubble: " + bubbleSortTimer(twentyDescending) + "\n\tQuick: " + quickSortTimer(twentyDescending));
		System.out.print("\n\n50000 Descending:\n\tBubble: " + bubbleSortTimer(fiftyDescending) + "\n\tQuick: " + quickSortTimer(fiftyDescending));

		System.out.print("\n\n5000 Random:\n\tBubble: " + bubbleSortTimer(fiveRandom) + "\n\tQuick: " + quickSortTimer(fiveRandom));
		System.out.print("\n10000 Random:\n\tBubble: " + bubbleSortTimer(tenRandom) + "\n\tQuick: " + quickSortTimer(tenRandom));
		System.out.print("\n20000 Random:\n\tBubble: " + bubbleSortTimer(twentyRandom) + "\n\tQuick: " + quickSortTimer(twentyRandom));
		System.out.print("\n\n50000 Random:\n\tBubble: " + bubbleSortTimer(fiftyRandom) + "\n\tQuick: " + quickSortTimer(fiftyRandom));
			
	}	
	public static int[] fileOpener(int size, String order){
		int[] array = new int[size];
		String fileName = size + " " + order + ".txt";
		String line;	
		int counter = 0;
		
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while((line = bufferedReader.readLine()) != null){
				array[counter] = Integer.parseInt(line);
			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Unable to open file: " + fileName);
		} catch (IOException e) {
			System.out.println("Error while reading file: " + fileName);
		}
		
		return array;
	}
	
//	File file = new File(fileName);
//	Scanner sc = new Scanner(file);
//
//	int counter = 0;
//	while(sc.hasNextLine()){
//		array[counter] = Integer.parseInt(sc.nextLine());
//	}
	
	public static long bubbleSortTimer(int[] array){
		int [] arrayCopy = array;
		long startTime = System.nanoTime();
		
		for (int x = 0; x < arrayCopy.length - 1; x++){
			for (int y = 0; y < arrayCopy.length - x - 1; y++){
				if (arrayCopy[y] > arrayCopy[y + 1]){
					int temp = arrayCopy[y];
					arrayCopy[y] = arrayCopy[y + 1];
					arrayCopy[y + 1] = temp;
				}
			}
		}
		
		long endTime = System.nanoTime();
		
		return endTime - startTime;
	}
	public static long quickSortTimer(int[] array){
		int [] arrayCopy = array;
		long startTime = System.nanoTime();
		
		int low = 0;
		int high = arrayCopy.length - 1;
		quickSort(arrayCopy, low, high);
		
		long endTime = System.nanoTime();
		
		return endTime - startTime;
	}
	
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
	
	/** Method for creating all files of size specified (ex. random, ascending, descending)
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

