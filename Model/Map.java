package Model;

import java.util.*;
import java.io.*;

public class Map {

    private String region;
    private ArrayList<Location> locations;

    public Map(String region) {
        this.region = region;
        locations = new ArrayList<Location>();
        locations.add(new PostOffice(region + " Post Office", region));
    }

    public String getRegion() {
        return region;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }
    
    public void addLocation(Location l) {
        locations.add(l);
    }

    public PostOffice getPostOffice() {
        return (PostOffice) getLocations().get(0);
    }
    
    public void calculateRoutes() {
        for (Location l : locations) 
            l.setShortestPaths(dijkstra(l));
    }
    
   public Route[] dijkstra(Location start) {
        PriorityQueue<Node> heap = new PriorityQueue<>();
        ArrayList<Node> list = new ArrayList<>();
        Route[] shortestPaths = new Route[locations.size()];
        int dex = start.getIndex();
        for (int i = 0; i < locations.size(); i++) 
            if (i == dex) 
                list.add(new Node(i, 0));
            else
                list.add(new Node(i, Double.MAX_VALUE));
        heap.add(list.get(dex));
        while (!heap.isEmpty()) {
            Node current = heap.poll();
            for (Edge e : locations.get(current.index).getConnections()) {
                int index = e.getEnd().getIndex();
                double dist1 = current.distance;
                double dist2 = e.getDistance();
                Node adj = list.get(index);
                if (!adj.visited) {
                    heap.add(adj);
                    if (dist1 + dist2 < adj.distance) {
                        adj.distance = dist1 + dist2;
                        adj.prev = current;
                    }
                    adj.visited = true;
                }
            }
        }
        for (int i = 0; i < shortestPaths.length; i++) {
            Route r = new Route(list.get(i).distance);
            Node temp = list.get(i);
            while (temp != null) {
                r.getRoute().addFirst(locations.get(temp.index).getName());
                temp = temp.prev;
            }
            shortestPaths[i] = r;
        }
        return shortestPaths;
    }
    
    static class Node implements Comparable<Node> {
        int index;
        double distance;
        boolean visited;
        Node prev;
        
        public Node(int index, double distance) {
            this.index = index;
            this.distance = distance;
        }
        
        public int compareTo(Node n) {
            double difference = distance - n.distance;
            if (difference < 0)
                return -1;
            else if (difference == 0)
                return 0;
            else
                return 1;
        }
        
    }
}
