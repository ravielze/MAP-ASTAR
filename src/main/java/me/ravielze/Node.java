package me.ravielze;

public class Node implements Comparable<Node> {

    private String name;

    private double costH, costG;

    @Override
    public int hashCode() {
        int result = name.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Node))
            return false;

        Node otherNode = (Node) o;
        return this.getName().equals(otherNode.getName());
    }

    public String getName() {
        return this.name;
    }

    public double getCostH() {
        return this.costH;
    }

    public double getCostG() {
        return this.costG;
    }

    public Node(String name, double costH, double costG) {
        this.name = name;
        this.costH = costH;
        this.costG = costG;
    }

    public double getCost() {
        return costH + costG;
    }

    @Override
    public int compareTo(Node o) {
        return Double.valueOf(this.getCost()).compareTo(Double.valueOf(o.getCost()));
    }

}
