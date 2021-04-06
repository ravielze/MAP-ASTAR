package me.ravielze;

import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AStar {

    private Graph graph;

    // Priority Queue untuk keperluan algoritma
    private PriorityQueue<Node> pQueue = new PriorityQueue<Node>();

    // Map untuk menyimpan jarak node sejauh ini
    private HashMap<String, Double> costSoFar = new HashMap<String, Double>();

    // Map untuk menyimpan asal dari suatu node
    private HashMap<String, String> cameFrom = new HashMap<String, String>();

    public AStar(Graph g) {
        this.graph = g;
    }

    public String run(String start, String end) throws IllegalArgumentException {

        // Mengecek apakah node ada pada graf atau tidak.
        if (!checkNode(start, end)) {
            throw new IllegalArgumentException(
                    "Node tidak ditemukan. Pastikan nama sudah sesuai, karena case sensitive.");
        }
        // Dapatkan startnode dan endnode
        Node startNode = graph.getNodeByString(start);
        Node endNode = graph.getNodeByString(end);

        // Masukkan startnode kedalam priority queue
        pQueue.add(startNode);
        // Pasang harga start sebesar 0
        costSoFar.put(start, 0.0);

        // Pasang asal start sebagai null
        // null berarti start adalah posisi awal
        cameFrom.put(start, null);

        // Selama priority queue belum kosong
        while (!pQueue.isEmpty()) {
            // Dequeue
            Node current = pQueue.poll();

            // Apabila sudah ketemu, keluar dari queue
            if (current.getName() == end)
                break;

            HashSet<Node> neighbours = this.graph.getAdjacent(current);
            // Mengecek semua tetangga pada node yang di dequeue tadi
            for (Node neighbour : neighbours) {
                Double costG = costSoFar.get(current.getName()) + graph.getCost(current, neighbour);

                // Cek apakah tetangga belum ada pada jalan yang diambil atau harga G kurang
                // dari harga yang pernah disimpan
                if (!costSoFar.containsKey(neighbour.getName()) || costG < costSoFar.get(neighbour.getName())) {
                    // Simpan dengan harga terbaru
                    costSoFar.put(neighbour.getName(), costG);
                    // Heuristic disini setara dengan haversine untuk mencari jarak latitude,
                    // longitude ke bentuk meter/kilometer
                    Double costH = neighbour.heuristic(endNode);

                    // Buat node baru yang sama dengan tetangga tadi tapi memiliki cost H dan G yang
                    // berbeda
                    Node newNode = new Node(neighbour, costH, costG);

                    // Tambahkan ke priority queue
                    pQueue.add(newNode);

                    // Simpan asal muasal tetangga tadi sebagai current Node tadi
                    cameFrom.put(neighbour.getName(), current.getName());

                }
            }
        }

        // Apabila cameFrom tidak ditemukan end berarti tidak ada jalan menuju end Node
        if (!cameFrom.containsKey(end)) {
            throw new IllegalArgumentException("Tidak ditemukan jalan.");
        }

        // Penyusunan hasil agar menjadi A -> B -> C dst.
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
        // Kembalikan format seperti ini A -> B -> C [10.02 km]
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
