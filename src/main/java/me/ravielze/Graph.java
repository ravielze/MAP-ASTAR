package me.ravielze;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class Graph {

    // Adjacency Matrix for Cost, matrix ketetanggan untuk harga
    private Table<String, String, Double> costTable = HashBasedTable.create();

    // Adjacency List, atau list ketetanggaan
    private HashMap<String, ArrayList<String>> adjList = new HashMap<String, ArrayList<String>>();

    public Graph() {

    }

    /**
     * Untuk menambah edge pada graf
     * 
     * @param start node awal
     * @param end   node akhir, bisa ditukar-tukar
     * @param cost  distance
     */
    public void addEdge(String start, String end, Double cost) {

        // Kalau node start belum ada di adjacency List, construct new List
        if (!adjList.containsKey(start)) {
            adjList.put(start, new ArrayList<String>());
        }

        // Kalau node end belum ada di adjacency List, construct new List
        if (!adjList.containsKey(end)) {
            adjList.put(end, new ArrayList<String>());
        }

        // Tambahkan end pada adjacency list milik node start
        adjList.get(start).add(end);
        // lakukan sebaliknya pada end dan start
        adjList.get(end).add(start);

        // Masukkan node start dan end pada cost table
        // Ini adjacency matrix
        costTable.put(start, end, cost);
        costTable.put(end, start, cost);
    }

    /**
     * Untuk mengambil cost dari adjacency matrix
     * 
     * @param start node awal
     * @param end   node akhir, bisa ditukar dengan start
     * @return infinity positive apabila tidak bertetangga,
     */
    private double getCost(String start, String end) {
        // Kalau tidak ada di tabel
        if (costTable.get(start, end) == null || costTable.get(end, start) == null) {
            return Double.POSITIVE_INFINITY;
        }

        return costTable.get(start, end);
    }

    /**
     * Mendapatkan semua node pada satu graf
     * 
     * @return collection of nama node
     */
    public Collection<String> getAllNode() {
        return adjList.keySet();
    }

    public void aStarSearch(String start) {
        PriorityQueue<Node> pQueue = new PriorityQueue<Node>();
        HashMap<String, Boolean> closeTable = new HashMap<String, Boolean>();
        HashMap<String, Boolean> openTable = new HashMap<String, Boolean>();
        Collection<String> allNode = getAllNode();
        allNode.forEach((node) -> {
            closeTable.put(node, false);
            openTable.put(node, false);
        });
        pQueue.add(new Node(start, 0, 0));
        openTable.put(start, true);

        while (!pQueue.isEmpty()) {
            Node current = pQueue.poll();
            // current = remove lowest rank item from OPEN
            ArrayList<String> neighbours = adjList.get(current.getName());
            // add current to CLOSED
            closeTable.put(current.getName(), true);
            // for neighbors of current:
            for (String neighbour : neighbours) {
                // cost = g(current) + movementcost(current, neighbor)
                double cost = current.getCostG() + getCost(current.getName(), neighbour);
                // if neighbor in OPEN and cost less than g(neighbor):
                if (openTable.get(neighbour)) {
                    // remove neighbor from OPEN, because new path is better
                }
                // if neighbor in CLOSED and cost less than g(neighbor):
                else if (closeTable.get(neighbour)) {

                }
                else if ()
            }
        }
    }
}