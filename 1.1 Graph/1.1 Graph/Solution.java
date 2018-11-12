import java.util.Scanner;

class GraphMatrix {
    
    private String[] tokens;
   
    private int[][] matrix;
    
    private int v;
    
    private int e;
    
    GraphMatrix() {
        e = 0;
    }
    
    GraphMatrix(final Scanner scan) {
        this.v = Integer.parseInt(scan.nextLine());
        matrix = new int[v][v];
        int edge = Integer.parseInt(scan.nextLine());
        tokens = scan.nextLine().split(",");
        for (int i = 0; i < edge; i++) {
            String[] inputs = scan.nextLine().split(" ");
            addEdge(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
        }
    }
    
    public void addEdge(final int v1, final int w1) {
        if (v1 != w1) {
            if (!hasEdge(v1, w1)) {
                matrix[v1][w1] = 1;
                matrix[w1][v1] = 1;
                e++;
            }
        }
    }
    
    public boolean hasEdge(final int v1, final int w1) {
        if (matrix[v1][w1] == 1) {
            return true;
        }
        return false;
    }
    
    public void print() {
        String str = "";
        str += v + " vertices, " + e + " edges" + "\n";
        if (e > 0) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    str += matrix[i][j] + " ";
                }
                str += "\n";
            }
            System.out.println(str);
        } else {
            str += "No edges";
            System.out.println(str);
        }
    }
}

class GraphList {
    
    private int v;
    
    private int e;
    
    private Bag<Integer>[] adj;
    
    private String[] tokens;
    
    GraphList() {
    }
    
    GraphList(final Scanner scan) {
        this.v = Integer.parseInt(scan.nextLine());
        adj = (Bag<Integer>[]) new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<Integer>();
        }
        int edges = Integer.parseInt(scan.nextLine());
        tokens = scan.nextLine().split(",");
        for (int i = 0; i < edges; i++) {
            String[] inputs = scan.nextLine().split(" ");
            addEdge(Integer.parseInt(inputs[0]),
                    Integer.parseInt(inputs[1]));
        }
    }
    
    public int v() {
        return v;
    }
    
    public int e() {
        return e;
    }
    
    public void addEdge(final int v1, final int w1) {
        if(v1 == w1) {
            return;
        }
        if (!hasEdge(v1, w1)) {
            e++;
        }
        adj[v1].add(w1);
        adj[w1].add(v1);

    }
    
    public Iterable<Integer> adj(final int v1) {
        return adj[v1];
    }
   
    public boolean hasEdge(final int v1, final int w1) {
        for(int each: adj(v1)) {
            if(each == w1) {
                return true;
            }
        }
        return false;
    }
   
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(v + " vertices, " + e + " edges" + "\n");
        if (e > 0) {
            for (int i = 0; i < v; i++) {
                s.append(tokens[i] + ": ");
                for (int j : adj[i]) {
                    s.append(tokens[j] + " ");
                }
                s.append("\n");
            }
            return s.toString();
        } else {
            s.append("No edges");
            return s.toString();
        }
    }
}

public final class Solution {
   
    private Solution() {
    }
   
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String type = scan.nextLine();
        switch (type) {
        case "List":
            GraphList lisObj = new GraphList(scan);
            System.out.println(lisObj);
            break;
        case "Matrix":
            GraphMatrix lisMat = new GraphMatrix(scan);
            lisMat.print();
            break;
        default :
            break;
        }
    }
}
