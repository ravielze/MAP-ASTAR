package me.ravielze;

import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AStar {

    private Graph graph;
    private PriorityQueue<Node> pQueue = new PriorityQueue<Node>();
    private HashMap<String, Double> costSoFar = new HashMap<String, Double>();
    private HashMap<String, String> cameFrom = new HashMap<String, String>();

    public AStar(Graph g) {
        this.graph = g;
    }

    public String run(String start, String end) throws IllegalArgumentException {

        //
        if (!checkNode(start, end)) {
            throw new IllegalArgumentException("Node not found.");
        }
        Node startNode = graph.getNodeByString(start);
        Node endNode = graph.getNodeByString(end);
        pQueue.add(startNode);
        costSoFar.put(start, 0.0);
        cameFrom.put(start, null);

        while (!pQueue.isEmpty()) {
            Node current = pQueue.poll();

            if (current.getName() == end)
                break;

            HashSet<Node> neighbours = this.graph.getAdjacent(current);
            for (Node neighbour : neighbours) {
                Double costG = costSoFar.get(current.getName()) + graph.getCost(current, neighbour);
                if (!costSoFar.containsKey(neighbour.getName()) || costG < costSoFar.get(neighbour.getName())) {
                    costSoFar.put(neighbour.getName(), costG);
                    Double costH = neighbour.heuristic(endNode);
                    Node newNode = new Node(neighbour, costH, costG);
                    pQueue.add(newNode);
                    cameFrom.put(neighbour.getName(), current.getName());

                }
            }
        }

        if (!cameFrom.containsKey(end)) {
            throw new IllegalArgumentException("Tidak ditemukan jalan.");
        }
        String current = end;
        String path = "";
        while (current != null) {
            if (path.isEmpty()) {
                path = "" + current;
            } else {
                path = current + " -> " + path;
            }
            current = cameFrom.get(current);
        }
        return String.format("%s\t[%.2f km]", path, (costSoFar.get(end) / 1000D));
    }

    /**
     * Pengecekan apabila sebuah node terdapat pada graf
     * 
     * @param start node awal
     * @param end   node akhir
     * @return boolean true/false
     */
    public boolean checkNode(String start, String end) {
        Node startNode = graph.getNodeByString(start);
        Node endNode = graph.getNodeByString(end);
        if (startNode == null || endNode == null) {
            return false;
        }
        return true;
    }

}
