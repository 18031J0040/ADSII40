


	import java.util.Scanner;

class Percolation {
    
    private boolean[] array;
    
    private Graph graph;
    
    private int arraySize;
    
    private int size;
    
    private int count;
    
    private int top;
    
    private int bottom;
    
    protected Percolation() {

    }
   
    Percolation(final int n) {
        this.arraySize = n;
        this.size = n * n;
        this.top = size;
        this.bottom = size + 1;
        this.count = 0;
        graph = new Graph(size + 2);
        array = new boolean[size];
        for (int i = 0; i < arraySize; i++) {
            graph.addEdge(top, i);
            graph.addEdge(bottom, size - i - 1);
        }
    }
    
    public int toOneD(final int row, final int col) {
        return (arraySize * (row - 1)) + (col - 1);
    }
    
    private void connectOpenSites(final int row, final int col) {
        if (array[col] && !graph.hasEdge(row, col)) {
            graph.addEdge(row, col);
        }
    }
    
    public void open(final int row, final int col) {
        int index = toOneD(row, col);
        array[index] = true;
        count++;
        int toprow = index - arraySize;
        int bottomrow = index + arraySize;
        if (arraySize == 1) {
            graph.addEdge(top, index);
            graph.addEdge(bottom, index);
        }
        if (bottomrow < size) {         //bottom
            connectOpenSites(index, bottomrow);
        }
        if (toprow >= 0) {              //top
            connectOpenSites(index, toprow);
        }
        if (col == 1) {                 //left
            if (col != arraySize) {
                connectOpenSites(index, index + 1);
            }
            return;
        }
        if (col == arraySize) {         //right
            connectOpenSites(index, index - 1);
            return;
        }
        connectOpenSites(index, index + 1);
        connectOpenSites(index, index - 1);
    }
    
    public boolean isOpen(final int row, final int col) {
        return array[toOneD(row, col)];
    }
    
    public int numberOfOpenSites() {
        return count;
    }
    
    public boolean percolates() {
        CC connectedComponents = new CC(graph);
        return connectedComponents.connected(top, bottom);
    }
}

public final class Solution {
   
    protected Solution() {

    }
    
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());
        Percolation pobj = new Percolation(n);
        while (scan.hasNext()) {
            String[] tokens = scan.nextLine().split(" ");
            pobj.open(Integer.parseInt(tokens[0]),
                      Integer.parseInt(tokens[1]));
        }
        System.out.println(pobj.percolates()
                           && pobj.numberOfOpenSites() != 0);
    }
}