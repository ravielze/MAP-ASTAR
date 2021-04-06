package me.ravielze;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

// Harus dibantu dengan Package Manager seperti Maven untuk mendapatkan library dibawah ini
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class Graph {

    // Adjacency Matrix for Cost, matrix ketetanggan untuk harga
    private Table<Node, Node, Double> costTable = HashBasedTable.create();

    // Adjacency List, atau list ketetanggaan
    private HashMap<Node, HashSet<Node>> adjList = new HashMap<Node, HashSet<Node>>();

    public Graph() {

    }

    /**
     * Untuk menambah node pada graf
     * 
     * @param node Nama node
     * @param lat  Koordinat Latitude
     * @param lon  Koordinat Longitude
     * @return Node the result node added
     */
    public Node addNode(String node, double lat, double lon) {
        Node curr = getNodeByString(node);
        if (curr == null) {
            Node newNode = new Node(node, 0, 0, lat, lon);
            adjList.put(newNode, new HashSet<Node>());
            return newNode;
        }
        return curr;
    }

    /**
     * Untuk menambah node pada graf
     * 
     * @param node Nama node
     * @return Node the result node added
     */
    public Node addNode(Node node) {
        if (!adjList.containsKey(node)) {
            adjList.put(node, new HashSet<Node>());
        }
        return node;
    }

    /**
     * Untuk menambah edge pada graf
     * 
     * @param startNode node awal
     * @param endNode   node akhir, bisa ditukar-tukar
     */
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
        double distance = startNode.heuristic(endNode);
        costTable.put(startNode, endNode, distance);
        costTable.put(endNode, startNode, distance);
    }

    /**
     * Untuk mengambil cost dari adjacency matrix
     * 
     * @param start node awal
     * @param end   node akhir, bisa ditukar dengan start
     * @return infinity positive apabila tidak bertetangga,
     */
    public double getCost(Node start, Node end) {
        // Kalau tidak ada di tabel
        if (costTable.get(start, end) == null || costTable.get(end, start) == null) {
            return Double.POSITIVE_INFINITY;
        }

        return costTable.get(start, end);
    }

    /**
     * Menampilkan graf ke layar
     */
    public void showGraph() {
        for (Map.Entry<Node, HashSet<Node>> entry : adjList.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getKey().getLL());

            int maxLength = 0;
            for (Node neighbour : entry.getValue()) {
                if (neighbour.getName().length() > maxLength) {
                    maxLength = neighbour.getName().length();
                }
            }

            maxLength += 2;
            String format = "\t-> %-" + maxLength + "s %-19s %-10s\n";
            for (Node neighbour : entry.getValue()) {
                String dis = String.format("[%.2f km]", getCost(entry.getKey(), neighbour) / 1000D);
                System.out.printf(format, neighbour.getName(), neighbour.getLL(), dis);
            }
            System.out.println();
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

    /**
     * Mendapatkan instance node dengan name
     * 
     * @param name string yang akan dicari
     * @return Node node yang namanya sesuai
     */
    public Node getNodeByString(String name) {
        for (Node node : adjList.keySet()) {
            if (node.getName().equals(name)) {
                return node;
            }
        }
        return null;
    }

    /**
     * Mendapatkan tetangga dari suatu node
     * 
     * @param Node Node yang akan dicari tetangganya
     * @return HashSet<Node> Tetangga dari node
     */
    public HashSet<Node> getAdjacent(Node n) {
        return this.adjList.get(n);
    }

}