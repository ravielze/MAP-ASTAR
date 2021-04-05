package me.ravielze;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

/**
 * Hello world!
 *
 */
public class App {


    public static void main(String[] args) {
        Graph graph = new Graph();

        Node node1 = graph.addNode("1", 10, 20);
        Node node2 = graph.addNode("2", 30, 20);
        Node node3 = graph.addNode("3", 50, 20);
        Node node4 = graph.addNode("4", 30, 40);

        graph.addEdge(node1, node2, 40.0);
        graph.addEdge(node2, node3, 50.0);
        graph.addEdge(node1, node4, 20.0);
        graph.addEdge(node4, node3, 10.0);
        
        graph.aStarSearch("1", "4");

    }
}
// OPEN = priority queue containing START
// CLOSED = empty set
// while lowest rank in OPEN is not the GOAL:
// current = remove lowest rank item from OPEN
// add current to CLOSED
// for neighbors of current:
// cost = g(current) + movementcost(current, neighbor)
// if neighbor in OPEN and cost less than g(neighbor):
// remove neighbor from OPEN, because new path is better
// if neighbor in CLOSED and cost less than g(neighbor): ⁽²⁾
// remove neighbor from CLOSED
// if neighbor not in OPEN and neighbor not in CLOSED:
// set g(neighbor) to cost
// add neighbor to OPEN
// set priority queue rank to g(neighbor) + h(neighbor)
// set neighbor's parent to current

// reconstruct reverse path from goal to start
// by following parent pointers