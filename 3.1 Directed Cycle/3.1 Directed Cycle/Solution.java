

import java.util.Scanner;

public final class Solution {
    
    private Solution() {
        //unused constructor.
    }
    
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        int vertices = Integer.parseInt(sc.nextLine());
        int edges = Integer.parseInt(sc.nextLine());
        Digraph digraph = new Digraph(vertices);
        while (sc.hasNext()) {
            String[] input = sc.nextLine().split(" ");
            digraph.addEdge(Integer.parseInt(input[0]),
                            Integer.parseInt(input[1]));
        }
        DirectedCycle directedcycle = new DirectedCycle(digraph);
        System.out.println(directedcycle);
    }
}
