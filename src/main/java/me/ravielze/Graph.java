package me.ravielze;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Map;


import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class Graph {

    // Adjacency Matrix for Cost, matrix ketetanggan untuk harga
    private Table<Node, Node, Double> costTable = HashBasedTable.create();

    // Adjacency List, atau list ketetanggaan
    private HashMap<Node, ArrayList<Node>> adjList = new HashMap<Node, ArrayList<Node>>();
    public static final int Radius = 6371000;
    public static double haversine (double lat1, double lon1, double lat2, double lon2) {
        double phi1 = Math.toRadians(lat1);
        double phi2 = Math.toRadians(lat2);

        double deltaPhi = Math.toRadians(lat2 - lat1);
        double deltaLambda = Math.toRadians(lon2 - lon1);
        // a = math.sin(delta_phi / 2.0) ** 2 + math.cos(phi_1) * math.cos(phi_2) * math.sin(delta_lambda / 2.0) ** 2
        double a = Math.pow(Math.sin(deltaPhi / 2.0), 2) + Math.cos(phi1) * Math.cos(phi2) * Math.pow(Math.sin(deltaLambda / 2.0),2);
        
        // c = 2 * math.atan2(math.sqrt(a), math.sqrt(1 - a))
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return Radius * c;
    }

    public Graph() {

    }

    /**
     * Untuk menambah edge pada graf
     * 
     * @param start node awal
     * @param end   node akhir, bisa ditukar-tukar
     * @param cost  distance
     */
    public Node addNode (String node, double lat, double lon) {
        Node curr = getNodeByString(node);
        if (curr == null) {
            Node newNode = new Node(node, 0, 0, lat, lon);
            adjList.put(newNode, new ArrayList<Node>());
            return newNode;
        }
        return curr;
    }

    public Node addNode (Node node) {
        if (!adjList.containsKey(node)) {
            adjList.put(node, new ArrayList<Node>());
        }
        return node;
    }
    public void addEdge(Node startNode, Node endNode) {

        // Kalau node start belum ada di adjacency List, construct new List
        startNode = addNode(startNode);
        endNode = addNode(endNode);

        // Kalau node end belum ada di adjacency List, construct new List

        // Tambahkan end pada adjacency list milik node start
        adjList.get(startNode).add(endNode);
        // lakukan sebaliknya pada end dan start
        adjList.get(endNode).add(startNode);

        // Masukkan node start dan end pada cost table
        // Ini adjacency matrix
        double distance = haversine(startNode.getLatitude(), startNode.getLongitude(), endNode.getLatitude(), endNode.getLongitude());
        costTable.put(startNode,endNode, distance);
        costTable.put(endNode,startNode, distance);
    }

    /**
     * Untuk mengambil cost dari adjacency matrix
     * 
     * @param start node awal
     * @param end   node akhir, bisa ditukar dengan start
     * @return infinity positive apabila tidak bertetangga,
     */
    private double getCost(Node start, Node end) {
        // Kalau tidak ada di tabel
        if (costTable.get(start, end) == null || costTable.get(end, start) == null) {
            return Double.POSITIVE_INFINITY;
        }

        return costTable.get(start, end);
    }

    public void showGraph () {
        for (Map.Entry<Node, ArrayList<Node>> entry : adjList.entrySet()) {
            System.out.println("Node : ");
            System.out.println(entry.getKey());
            System.out.println("Neighbour : ");

            for (Node neighbour : entry.getValue()) {
                System.out.println(neighbour);
                System.out.printf("Cost : %f\n", getCost(entry.getKey(), neighbour));
            }
        }
    }

    /**
     * Mendapatkan semua node pada satu graf
     * 
     * @return collection of nama node
     */
    public Collection<Node> getAllNode() {
        return adjList.keySet();
    }

    private Node getNodeByString (String name) {
        for (Node node : adjList.keySet()) {
            if (node.getName().equals(name)) {
                return node;
            }
        }
        return null;
    }

    public void aStarSearch(String start, String end) {
        // A* Algorithm
        PriorityQueue<Node> pQueue = new PriorityQueue<Node>();
        Node startNode = getNodeByString(start);
        Node endNode = getNodeByString(end);
        pQueue.add(startNode);

        HashMap<String, Double> costSoFar = new HashMap<String, Double>();
        costSoFar.put(start, 0.0);
        HashMap<String, String> cameFrom = new HashMap<String, String>();
        cameFrom.put(start, null);

        while (!pQueue.isEmpty()) {
            Node current = pQueue.poll();

            if (current.getName() == end) break;

            ArrayList<Node> neighbours = adjList.get(current);
            for (Node neighbour : neighbours) {
                Double costG = costSoFar.get(current.getName()) + getCost(current, neighbour);
                if (!costSoFar.containsKey(neighbour.getName()) || costG < costSoFar.get(neighbour.getName())) {
                    costSoFar.put(neighbour.getName(), costG);
                    Double costH = neighbour.heuristic(endNode);
                    Node newNode = new Node(neighbour, costH, costG);
                    pQueue.add(newNode);
                    cameFrom.put(neighbour.getName(), current.getName());

                }
            }
        }
        // End of A* Algorithm

        // Untuk print ke layar 
        String current = end;
        ArrayList<String> path = new ArrayList<String>();
        while (current != null) {
            path.add(current);
            current = cameFrom.get(current);
        }
        System.out.println("Path : ");

        for (int i = path.size() - 1; i >= 0; i--) {
            if (i != path.size() - 1) {
                System.out.print("->");
            }
            System.out.print(path.get(i));
        }
        System.out.println();
        System.out.printf("Estimated cost : %.2f\n", costSoFar.get(end));

    }


}