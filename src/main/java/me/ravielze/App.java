package me.ravielze;
import java.io.FileNotFoundException;

/**
 * Hello world!
 *
 */
public class App {


    public static void main(String[] args) {
        // Graph graph = new Graph();

        // Node node1 = graph.addNode("1", 10, 20);
        // Node node2 = graph.addNode("2", 30, 20);
        // Node node3 = graph.addNode("3", 50, 20);
        // Node node4 = graph.addNode("4", 30, 40);

        // graph.addEdge(node1, node2, 40.0);
        // graph.addEdge(node2, node3, 50.0);
        // graph.addEdge(node1, node4, 20.0);
        // graph.addEdge(node4, node3, 10.0);
        
        // graph.aStarSearch("1", "4");
        // PERPUS -6.888164952656184, 107.61074257375813
        // TVSV -6.889401947617707, 107.61005237227467
        // KOSAN JASON -6.882529043953158, 107.6120730575974
        // SCOOPS -6.886761780365588, 107.61340448334381
        // MCD -6.884912807490634, 107.6134357176906
        // Node perpus = new Node("Perpus", 0,0,-6.888164952656184, 107.61074257375813);
        // Node tvst = new Node("TVST", 0,0,-6.889401947617707, 107.61005237227467);
        // Node kosanJason = new Node("Kosan", 0,0,-6.882529043953158, 107.6120730575974);
        // Node scoops = new Node("Scoops", 0, 0, -6.886761780365588, 107.61340448334381);
        // Node mcd = new Node("MCD", 0, 0, -6.884912807490634, 107.6134357176906);
        // System.out.println(mcd.heuristic(scoops));
        try {
            Reader reader = new Reader("../test3.txt");
            Graph g = reader.getResult();
            g.aStarSearch("A", "H");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
