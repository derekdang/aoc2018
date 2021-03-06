import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//TODO: Improve on runtime n^2,  ATP
public class aoc3dot2 {

	public static void main(String[] args) {
		String filename = args[0];
		List<String[]> list = new ArrayList();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			for (String line; (line = br.readLine()) != null;) {
				line = line.replace("@", "");
				line = line.replaceFirst(" ", "");
				list.add(line.split(" "));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int[][] fabric = new int[1000][1000];
		for (String[] arr : list) {
			int colOffset = 0, rowOffset = 0, numOfRows=0, numOfCols = 0;
			for (int i = 1; i<arr.length; i++) {
				if (i == 1) {
					String[] offsets = arr[i].split(",");
					colOffset = Integer.parseInt(offsets[0]);
					rowOffset = Integer.parseInt(offsets[1].replace(":", ""));
				} else if (i == 2) {
					String[] incrementAmt = arr[i].split("x");
					numOfRows = Integer.parseInt(incrementAmt[1]);
					numOfCols = Integer.parseInt(incrementAmt[0]);
				}
			}
			incrementRect(fabric, rowOffset, colOffset, numOfRows, numOfCols);
		}
		
		for (String[] arr : list) {
			int colOffset = 0, rowOffset = 0, numOfRows=0, numOfCols = 0;
			for (int i = 1; i<arr.length; i++) {
				if (i == 1) {
					String[] offsets = arr[i].split(",");
					colOffset = Integer.parseInt(offsets[0]);
					rowOffset = Integer.parseInt(offsets[1].replace(":", ""));
				} else if (i == 2) {
					String[] incrementAmt = arr[i].split("x");
					numOfRows = Integer.parseInt(incrementAmt[1]);
					numOfCols = Integer.parseInt(incrementAmt[0]);
				}
			}
			if (!doesOverlap(fabric, rowOffset, colOffset, numOfRows, numOfCols)) {
				System.out.printf("claim no: %s%n", arr[0]);
			}
		}
	}
	
	private static void incrementRect(int[][] arr, int rowOffset, int colOffset, int numOfRows, int numOfCols) {
//		System.out.printf("incrementing the rect with: rowOffset: %s, colOffset: %s, numOfRows: %s, numOfCols: %s%n", rowOffset, colOffset, numOfRows, numOfCols);
		for (int i = rowOffset, m = 0; m < numOfRows && i < arr.length; i++, m++) {
			for (int j = colOffset, n=0; n < numOfCols && j < arr[i].length; j++, n++) {
				arr[i][j]++;
			}
		}
	}
	
	private static boolean doesOverlap(int[][] arr, int rowOffset, int colOffset, int numOfRows, int numOfCols) {
		boolean overlap = false;
		for (int i = rowOffset, m = 0; m < numOfRows && i < arr.length; i++, m++) {
			for (int j = colOffset, n=0; n < numOfCols && j < arr[i].length; j++, n++) {
				if (arr[i][j] > 1) {
					overlap = true;
					break;
				}
			}
		}
		return overlap;
	}
}
