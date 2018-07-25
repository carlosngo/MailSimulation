package Model;

import java.util.*;
import java.io.*;

public class Location {

    String name;
    String region;
    ArrayList<Edge> connections;
    double[] distances;

    public Location(String name, String region) {
        this.name = name;
        this.region = region;
        connections = new ArrayList<>();
    }

    public String getRegion() {
        return region;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Edge> getConnections() {
        return connections;
    }
    
    public void setDistances(double[] distances) {
        this.distances = distances;   
    }
    
    public void addConnection(Edge e) {
        connections.add(e);
    }
    
    public double getShortestPath(int locationIndex) {
        return distances[locationIndex];
    }

    public boolean equals(Object obj) {
        Location l = (Location) obj;
        return name.equals(l.name) && region.equals(l.region);
    }
}
