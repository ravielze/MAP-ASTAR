package me.ravielze;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

/**
 * Hello world!
 *
 */
public class App {

    public static Table<String, String, Double> adjTable = HashBasedTable.create();

    public static void main(String[] args) {
        System.out.println("Hello World!");
        adjTable.put("A", "C", 5D);
        System.out.println(adjTable.get("A", "B"));
        System.out.println(adjTable.get("A", "C"));
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