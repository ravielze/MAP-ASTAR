package me.ravielze;

public class Node implements Comparable<Node> {

    private String name;

    private double costH, costG;

    private Integer x;
    private Integer y;

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

    public Node(String name, double costH, double costG, Integer x, Integer y) {
        this.name = name;
        this.costH = costH;
        this.costG = costG;
        this.x = x;
        this.y = y;
    }
    public void show () {
        System.out.printf("Name : %s x : %d y : %d\n", name, x, y);
    }

    public Integer getX () {
        return x;
    }
    public Integer getY () {
        return y;
    }

    public double getCost() {
        return costH + costG;
    }

    @Override
    public int compareTo(Node o) {
        return Double.valueOf(this.getCost()).compareTo(Double.valueOf(o.getCost()));
    }

}
