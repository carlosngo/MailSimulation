package Model;

import java.util.*;
import java.io.*;

public class Map {

    private String region;
    private ArrayList<Location> locations;

    public Map(String region) {
        this.region = region;
        locations = new ArrayList<Location>();
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

    }
}
