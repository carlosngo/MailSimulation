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
    
    // returns distances of shortest paths from loc1 to all locations. 
    public double[] getShortestPaths(Location loc1) {
        
    }
    
    // for each vertex, set the return value of getShortestPaths() to its array of distances
    public void calculateRoutes() {
        
    }
    
    public void addLocation(Location l) {
        locations.add(l);
    }

    public PostOffice getPostOffice() {
        return (PostOffice) getLocations().get(0);
    }
}
