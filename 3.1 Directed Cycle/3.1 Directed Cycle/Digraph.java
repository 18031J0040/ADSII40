

public class Digraph {
    
    private final int vertices;
   
    private int edges;
   
    private Bag<Integer>[] adj;
    
    private int[] indegree;

   
    public Digraph(final int v) {
        if (v < 0) {
            throw new IllegalArgumentException(
                "Number of vertices in a Digraph must be nonnegative");
        }
        this.vertices = v;
        this.edges = 0;
        indegree = new int[vertices];
        adj = (Bag<Integer>[]) new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            adj[i] = new Bag<Integer>();
        }
    }

   
    public int vertices() {
        return vertices;
    }

   
    public int edges() {
        return edges;
    }
    public void addEdge(final int v, final int w) {
        adj[v].add(w);
        indegree[w]++;
        edges++;
    }

    
    public Iterable<Integer> adj(final int v) {
        return adj[v];
    }

    
    public int outdegree(final int v) {
        return adj[v].size();
    }

    
    public int indegree(final int v) {
        return indegree[v];
    }

   
    public Digraph reverse() {
        Digraph reverse = new Digraph(vertices);
        for (int j = 0; j < vertices; j++) {
            for (int w : adj(j)) {
                reverse.addEdge(w, j);
            }
        }
        return reverse;
    }
}