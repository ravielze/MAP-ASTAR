package me.ravielze;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {

    private Graph result;

    public Reader(String filename) throws FileNotFoundException{
        File file = new File(filename);
        if (!file.exists()){
            throw new FileNotFoundException("File " + filename + " is not found.");
        }

        Scanner fileScanner = new Scanner(file);
        int N = fileScanner.nextInt();
        Graph g = new Graph();
        ArrayList<Node> adjList = new ArrayList<>();
        for (int i = 0; i < N; i++){
            String nodeName = fileScanner.next();
            double lat = fileScanner.nextDouble();
            double lon = fileScanner.nextDouble();
            Node node = g.addNode(nodeName, lat, lon);
            adjList.add(node);
        }

        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                int connected = fileScanner.nextInt();
                if (connected != 0) {
                    g.addEdge(adjList.get(i), adjList.get(j));
                }
            }
        }
        fileScanner.close();
        g.showGraph();
        this.result = g;
    }

    public Graph getResult() throws IllegalStateException{
        if (this.result == null){
            throw new IllegalStateException("Reader doesn't have readed graph");
        }
        return this.result;
    }
}
