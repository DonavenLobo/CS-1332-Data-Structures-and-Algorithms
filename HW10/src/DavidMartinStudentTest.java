import org.junit.Before;
import org.junit.Test;


import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DavidMartinStudentTest {

    private Graph<Character> disconnectedGraph;
    private Graph<Character> undirectedGraph;
    private Graph<Character> graph;
    public static final int TIMEOUT = 200;

    @Before
    public void init() {
        disconnectedGraph = createDisconnectedGraph();
        undirectedGraph = createUndirectedGraph();
        graph = createGraph();
    }


    /**
     * creates a disconnected graph
     *
     * @return disconnected graph
     */
    private Graph<Character> createDisconnectedGraph() {
        Set<Vertex<Character>> vertices = new HashSet<>();
        for (int i = 65; i <= 70; i++) {
            vertices.add(new Vertex<Character>((char) i));
        }
        Set<Edge<Character>> edges = new LinkedHashSet<>();
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 3));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('A'), 3));
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('C'), 1));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('A'), 1));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('B'), 7));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('C'), 7));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('D'), 2));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('B'), 2));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('F'), 2));
        edges.add(new Edge<>(new Vertex<>('F'), new Vertex<>('E'), 2));

        return new Graph<>(vertices, edges);
    }

    /**
     * Creates an undirected graph.
     * The graph is depicted in the pdf.
     *
     * @return the completed graph
     */
    private Graph<Character> createUndirectedGraph() {
        Set<Vertex<Character>> vertices = new HashSet<>();
        for (int i = 65; i <= 70; i++) {
            vertices.add(new Vertex<Character>((char) i));
        }

        Set<Edge<Character>> edges = new LinkedHashSet<>();
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 7));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('A'), 7));
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('C'), 5));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('A'), 5));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('D'), 2));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('C'), 2));
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('D'), 4));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('A'), 4));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('E'), 1));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('D'), 1));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('E'), 3));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('B'), 3));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('F'), 8));
        edges.add(new Edge<>(new Vertex<>('F'), new Vertex<>('B'), 8));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('F'), 6));
        edges.add(new Edge<>(new Vertex<>('F'), new Vertex<>('E'), 6));

        return new Graph<Character>(vertices, edges);
    }

    private Graph<Character> createGraph() {
        Set<Vertex<Character>> vertices = new HashSet<>();
        for (int i = 65; i <= 72; i++) {
            vertices.add(new Vertex<Character>((char) i));
        }
        Set<Edge<Character>> edges = new LinkedHashSet<>();
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('E'), 1));
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 2));
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('G'), 7));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('A'), 2));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('G'), 4));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('F'), 2));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('C'), 5));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('H'), 8));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('B'), 5));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('H'), 6));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('D'), 4));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('C'), 4));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('E'), 9));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('A'), 1));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('D'), 9));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('F'), 3));
        edges.add(new Edge<>(new Vertex<>('F'), new Vertex<>('E'), 3));
        edges.add(new Edge<>(new Vertex<>('F'), new Vertex<>('B'), 2));
        edges.add(new Edge<>(new Vertex<>('G'), new Vertex<>('A'), 7));
        edges.add(new Edge<>(new Vertex<>('G'), new Vertex<>('B'), 4));
        edges.add(new Edge<>(new Vertex<>('H'), new Vertex<>('B'), 8));
        edges.add(new Edge<>(new Vertex<>('H'), new Vertex<>('C'), 6));
        return new Graph<>(vertices, edges);
    }

    @Test(timeout = TIMEOUT)
    public void test_BFS() {
        List<Vertex<Character>> bfsActual = GraphAlgorithms.bfs(
                new Vertex<Character>('A'), graph);
        List<Vertex<Character>> bfsExpected = new LinkedList<>();
        bfsExpected.add(new Vertex<Character>('A'));
        bfsExpected.add(new Vertex<Character>('E'));
        bfsExpected.add(new Vertex<Character>('B'));
        bfsExpected.add(new Vertex<Character>('G'));
        bfsExpected.add(new Vertex<Character>('D'));
        bfsExpected.add(new Vertex<Character>('F'));
        bfsExpected.add(new Vertex<Character>('C'));
        bfsExpected.add(new Vertex<Character>('H'));
        assertEquals(bfsExpected, bfsActual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_BFS_Start_Null() {
        GraphAlgorithms.bfs(null, undirectedGraph);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_BFS_Graph_Null() {
        GraphAlgorithms.bfs(new Vertex<>('Z'), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_BFS_Start_Not_In_Graph() {
        GraphAlgorithms.bfs(new Vertex<>('Z'), undirectedGraph);
    }

    @Test(timeout = TIMEOUT)
    public void test_DFS() {
        List<Vertex<Character>> dfsActual = GraphAlgorithms.dfs(
                new Vertex<Character>('A'), graph);
        List<Vertex<Character>> bfsExpected = new LinkedList<>();
        bfsExpected.add(new Vertex<Character>('A'));
        bfsExpected.add(new Vertex<Character>('E'));
        bfsExpected.add(new Vertex<Character>('D'));
        bfsExpected.add(new Vertex<Character>('C'));
        bfsExpected.add(new Vertex<Character>('B'));
        bfsExpected.add(new Vertex<Character>('G'));
        bfsExpected.add(new Vertex<Character>('F'));
        bfsExpected.add(new Vertex<Character>('H'));
        assertEquals(bfsExpected, dfsActual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_DFS_Start_Null() {
        GraphAlgorithms.dfs(null, undirectedGraph);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_DFS_Graph_Null() {
        GraphAlgorithms.dfs(new Vertex<>('Z'), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_DFS_Start_Not_In_Graph() {
        GraphAlgorithms.dfs(new Vertex<>('Z'), undirectedGraph);
    }

    @Test(timeout = TIMEOUT)
    public void test_Dijkstras() {
        Map<Vertex<Character>, Integer> dijkActual = GraphAlgorithms.dijkstras(
                new Vertex<Character>('A'), graph);
        Map<Vertex<Character>, Integer> dijkExpected = new HashMap<>();
        dijkExpected.put(new Vertex<>('A'), 0);
        dijkExpected.put(new Vertex<>('B'), 2);
        dijkExpected.put(new Vertex<>('C'), 7);
        dijkExpected.put(new Vertex<>('D'), 10);
        dijkExpected.put(new Vertex<>('E'), 1);
        dijkExpected.put(new Vertex<>('F'), 4);
        dijkExpected.put(new Vertex<>('G'), 6);
        dijkExpected.put(new Vertex<>('H'), 10);

        assertEquals(dijkExpected, dijkActual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Dijkstras_Start_Null() {
        GraphAlgorithms.dijkstras(null, undirectedGraph);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Dijkstras_Graph_Null() {
        GraphAlgorithms.dijkstras(new Vertex<>('Z'), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Dijkstras_Start_Not_In_Graph() {
        GraphAlgorithms.dijkstras(new Vertex<>('Z'), undirectedGraph);
    }

    @Test(timeout = TIMEOUT)
    public void test_Prims() {
        Set<Edge<Character>> mstActual = GraphAlgorithms.prims(
                new Vertex<>('A'), graph);
        Set<Edge<Character>> edges = new HashSet<>();
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('E'), 1));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('A'), 1));
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 2));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('A'), 2));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('F'), 2));
        edges.add(new Edge<>(new Vertex<>('F'), new Vertex<>('B'), 2));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('G'), 4));
        edges.add(new Edge<>(new Vertex<>('G'), new Vertex<>('B'), 4));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('C'), 5));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('B'), 5));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('D'), 4));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('C'), 4));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('H'), 6));
        edges.add(new Edge<>(new Vertex<>('H'), new Vertex<>('C'), 6));

        assertEquals(edges, mstActual);
    }


    @Test(expected = IllegalArgumentException.class)
    public void test_Prims_Start_Null() {
        GraphAlgorithms.prims(null, undirectedGraph);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Prims_Graph_Null() {
        GraphAlgorithms.prims(new Vertex<>('Z'), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Prims_Start_Not_In_Graph() {
        GraphAlgorithms.prims(new Vertex<>('Z'), undirectedGraph);
    }

    @Test(timeout = TIMEOUT)
    public void test_Prims_Disconnected() {
        assertEquals("The graph is disconnected, there is no possible MST. \n",
                null,
                GraphAlgorithms.prims(
                        new Vertex<>('B'),
                        disconnectedGraph));
    }
}
