package me.ravielze;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class Graph {
    Table<String, String, Double> costTable = HashBasedTable.create();

    HashMap<String, ArrayList<String>> adjList = new HashMap<String, ArrayList<String>>();

    public Graph() {

    }

    public void addEdge(String start, String end, Double cost) {
        if (!adjList.containsKey(start)) {
            adjList.put(start, new ArrayList<String>());
        }
        if (!adjList.containsKey(end)) {
            adjList.put(end, new ArrayList<String>());
        }
        adjList.get(start).add(end);
        adjList.get(end).add(start);
        costTable.put(start, end, cost);
        costTable.put(end, start, cost);
    }

    public double getCost(String start, String end){
        if (costTable.)
    }

    public void aStarSearch() {
        PriorityQueue<String> pQueue = new PriorityQueue<String>();

    }
}