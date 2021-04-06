package me.ravielze;

public class Node implements Comparable<Node> {

    private String name;

    private double costH, costG;

    private double lat;
    private double lon;

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

    public Node(String name, double costH, double costG, double lat, double lon) {
        this.name = name;
        this.costH = costH;
        this.costG = costG;
        this.lat = lat;
        this.lon = lon;
    }

    public Node(Node old, double costH, double costG){
        this.name = old.getName();
        this.lat = old.getLatitude();
        this.lon = old.getLongitude();
        this.costH = costH;
        this.costG = costG;
    }

    @Override
    public String toString() {
        return String.format("Name : %s <lon: %.3f lat: %.3f>\n", name, lon, lat);
    }

    public double getLatitude() {
        return lat;
    }

    public double getLongitude() {
        return lon;
    }

    public double getCost() {
        return costH + costG;
    }

    @Override
    public int compareTo(Node o) {
        return Double.valueOf(this.getCost()).compareTo(Double.valueOf(o.getCost()));
    }

    public double heuristic(Node end) {
        // Use euclidian distance as heuristic
        return Graph.haversine(this.getLatitude(), this.getLongitude(), end.getLatitude(), end.getLongitude());
    }

}
