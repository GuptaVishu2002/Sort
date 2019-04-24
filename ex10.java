import java.io.File;
import java.util.Scanner;

class Data {
  int value;
  int line;
  Data(int value, int line){
    this.value = value;
    this.line  = line;
  }
  public String toString(){
    return "" + value + " " + line;
  }
}

class ex10 {
  static int compareCount = 0;
  static int swapCount    = 0;
  static Data[] data = new Data[100000];
  static int dataNum = 0;

  static void bubbleSort(){ 
    for (int i = 0; i < dataNum - 1; i++){
      for (int j = dataNum - 1 ; j > i ; j--){
        if (compare(j-1, j) > 0){
        	swap(j-1, j);
      	}
      }
    }
  }

  static void insertionSort(){ 
  	for (int i = 0; i < dataNum ; i++){
      int j = i;
      while (j >= 1 && compare(j-1,j) > 0){
      	swap(j-1, j);
      	j--;
      }
    }  
  }
  
  static void selectionSort(){ 
    for (int i = 0; i < dataNum - 1; i++){
      int min = i;
      for (int j = i + 1; j < dataNum; j++){
        if (compare(min, j) > 0) min = j;
      }
      swap(i, min);
    }
  }

  static int compare(int i, int j){
    compareCount++;
    return data[i].value - data[j].value;
  }

  static void swap(int i, int j){
    swapCount++;
    Data tmp = data[i];
    data[i] = data[j];
    data[j] = tmp;
    return;
  }

  static void printData(){
    for(int i = 0; i < dataNum; i++){
      System.out.println("" + data[i]);
    }
  }

  public static void main(String[] argv){
    String sortType = "";
    String file = "input_aasc.tsv";
    if (argv.length >= 1) {
      sortType = argv[0];
    }
    
    if (argv.length == 2) {
      file = argv[1];
    }
    
    try {
      Scanner scan = new Scanner(new File(file));
      while(scan.hasNextLine()){
        String    str  = scan.nextLine();
        String[] input = str.split(" ");
        int value = Integer.parseInt(input[0]);
        int line  = Integer.parseInt(input[1]);
        data[dataNum] = new Data(value, line);
        dataNum++;
      }
      scan.close();

      if      ("b".equals(sortType)) bubbleSort();
      else if ("s".equals(sortType)) selectionSort();
      else if ("i".equals(sortType)) insertionSort();

      printData();
      System.err.println("compareCount = " + compareCount);
      System.err.println("   swapCount = " + swapCount);
      
    }catch(java.io.FileNotFoundException e){
      System.err.println(e.getMessage());
    }
  }

}
