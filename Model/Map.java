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
    
    // apply dijkstra's algorithm here.
    public double getShortestPath(Location loc1, Location loc2) {
        
    }
    
    public void addLocation(Location l) {
        locations.add(l);
    }

    public PostOffice getPostOffice() {
        return (PostOffice) getLocations().get(0);
    }
}
