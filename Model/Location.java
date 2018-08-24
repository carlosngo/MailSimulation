package Model;

import java.util.*;
import java.io.*;

public class Location {
    int index;
    String name;
    String region;
    ArrayList<Edge> connections;
    Route[] shortestPaths;
    
    public Location(String name, String region) {
        this.name = name;
        this.region = region;
        connections = new ArrayList<>();
        shortestPaths = new Route[0];
    }

    public String getRegion() {
        return region;
    }

    public String getName() {
        return name;
    }
    
    public int getIndex() {
        return index;
    }

    public ArrayList<Edge> getConnections() {
        return connections;
    }
    
    public Route getShortestPath(Location dest) {
        return shortestPaths[dest.index];
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
    
    public void setShortestPaths(Route[] shortestPaths) {
        this.shortestPaths = shortestPaths;
    }
    
    
    public void addConnection(Edge e) {
        connections.add(e);
    }

    public void removeConnection(Edge e) {
        connections.remove(e);
    }
    
    public boolean equals(Object obj) {
        Location l = (Location) obj;
        return name.equals(l.name) && region.equals(l.region);
    }
}
