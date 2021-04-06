package me.ravielze;

public class Node implements Comparable<Node> {

    private String name;

    private double costH, costG;

    private double lat;
    private double lon;
    public static final long EARTH_RADIUS = 6371000L;

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
        return this.name;
    }

    public String getLL(){
        return String.format("<%.3f, %.3f>", lat, lon);
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
        
        return haversine(end);
    }

    private double haversine (Node other) {
        double lat1 = this.getLatitude();
        double lat2 = other.getLatitude();
        double lon1 = this.getLongitude();
        double lon2 = other.getLongitude();

        double phi1 = Math.toRadians(lat1);
        double phi2 = Math.toRadians(lat2);

        double deltaPhi = Math.toRadians(lat2 - lat1);
        double deltaLambda = Math.toRadians(lon2 - lon1);
        
        double a = Math.pow(Math.sin(deltaPhi / 2.0), 2) + Math.cos(phi1) * Math.cos(phi2) * Math.pow(Math.sin(deltaLambda / 2.0),2);
        
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

}
