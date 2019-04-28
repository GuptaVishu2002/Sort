import java.io.File;
import java.util.Scanner;

class Data {
  int value;
  Data(int value){
    this.value = value;
  }
  public String toString(){
    return "" + value;
  }
}

class rp11_3 {
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
  
  static void trumpSort(){ 
  	int i = 0;
  	int max = i;
  	int x = dataNum;
  	int y = dataNum;
    for (i = 1; i < x; i++){
		if(compare(max,i) <= 0){
			max = i;
		}else{		 
			for(int j = i;j < y-2; j++){
				swap(j,j+1);
			}	
			x--;
		}	
	}
	for(i = 0; i < dataNum; i++){
      System.out.println("" + data[i]);
    }	
  }
  
  static void quickSort(){
  		quickSort(0, dataNum-1);
  }
  
  static void quickSort(int l, int r){
        if (l >= r)
            return;

        int v = partition(l, r);

        quickSort(l, v - 1);

        quickSort(v + 1, r);
  }
  
  static int partition(int l, int r){
        int i = l - 1;
        int j = r;

        while (true) {
            while (compare(++i,r) < 0)
                ;
            while (i < --j && compare(r,j)<0)
                ;
            if (i >= j)
                break;
            swap(i, j);
        }
        swap(i, r);
        return i;	
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
    String file = "input3.tsv";
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
        data[dataNum] = new Data(value);
        dataNum++;
      }
      scan.close();

      if      ("b".equals(sortType)){ 
		  bubbleSort();
		  printData();
		  System.err.println("compareCount = " + compareCount);
		  System.err.println("   swapCount = " + swapCount);
      }else if ("s".equals(sortType)){ 
		  selectionSort();
		  printData();
		  System.err.println("compareCount = " + compareCount);
		  System.err.println("   swapCount = " + swapCount);
      }else if ("i".equals(sortType)){ 
		  insertionSort();
		  printData();
		  System.err.println("compareCount = " + compareCount);
		  System.err.println("   swapCount = " + swapCount);
      }else if ("q".equals(sortType)){ 
		  quickSort();
		  printData();
		  System.err.println("compareCount = " + compareCount);
		  System.err.println("   swapCount = " + swapCount);
      }else if ("trump".equals(sortType)){
      	  System.out.println("Start deporting");
		  trumpSort();
	  }	
      
    }catch(java.io.FileNotFoundException e){
      System.err.println(e.getMessage());
    }
  }

}
