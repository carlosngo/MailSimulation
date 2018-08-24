package Model;

import java.util.*;
import java.io.*;

public class Mailman {
    String name;
    private Map currentMap;
    private Location currentLocation;

    private ArrayList<Map> maps;
    private ArrayList<Mail> allMail;
    private ArrayList<Mail> regionMail;

    /**
     * NOTE: read csv files upon instantiation.
     */
    
    public Mailman(String name) {
        this.name = name;
        maps = new ArrayList<>();
        allMail = new ArrayList<>();
        regionMail = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Map getCurrentMap() {
        return currentMap;
    }
    
    public Location getCurrentLocation() {
        return currentLocation;
    }

    
    public ArrayList<Map> getMaps() {
        return maps;
    }

    public ArrayList<Mail> getAllMail() {
        return allMail;
    }

    public ArrayList<Mail> getRegionMail() {
        return regionMail;
    }
    
    public void setCurrentMap(Map m) {
        currentMap = m;
    }
    
    public void setCurrentMap(PostOffice po) {
        for (Map m : maps) {
            if (m.getPostOffice().equals(po))
                currentMap = m;
        }
    }
    
    public void setCurrentLocation(Location l) {
        currentLocation = l;
    }
    
    public int getNoOfLocations() {
        int count = 0;
        for (Map m : maps) 
            count += m.getLocations().size();
        return count;
    }

    public void addMap(Map m) {
        maps.add(m);
    }

    /**
     * reads the CSV file. add a map for every region.
     */
    public boolean readMaps(File csv) throws IOException {
        maps = new ArrayList<>();
        try {
            String input;
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(csv),"UTF-8"));
            br.readLine();
            while ((input = br.readLine()) != null) {
                String region;
                double distance;
                Location location1, location2;
                int index = -1;
                boolean found = false;
                String[] temp = input.split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");
                try {
                    region = temp[0];
                    if (!temp[1].isEmpty() && temp[1].charAt(0) == '"')
                        location1 = new Location(temp[1].substring(1, temp[1].length() - 1), region);
                    else
                        location1 = new Location(temp[1], region);
                    if (!temp[2].isEmpty() && temp[2].charAt(0) == '"')
                        location2 = new Location(temp[2].substring(1, temp[2].length() - 1), region);
                    else
                        location2 = new Location(temp[2], region);
                    distance = Double.parseDouble(temp[3]);
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    return false;
                }
                for (int i = 0; i < maps.size() && !found; i++) {
                    if (region.equals(maps.get(i).getRegion())) {
                        found = true;
                        index = i;
                    }
                }
                Map map;
                if (found) {
                    map = maps.get(index);
                } else {
                    map = new Map(region);
                }
                int index1 = map.getLocations().indexOf(location1);
                if (index1 < 0) {
                    location1.setIndex(map.getLocations().size());
                    map.getLocations().add(location1);
                } else {
                    location1 = map.getLocations().get(index1);
                }
                int index2 = map.getLocations().indexOf(location2);
                if (index2 < 0) {
                    location2.setIndex(map.getLocations().size());
                    map.getLocations().add(location2);
                } else {
                    location2 = map.getLocations().get(index2);
                }
                location1.addConnection(new Edge(location2, distance));
                location2.addConnection(new Edge(location1, distance));
                if (!found) 
                    addMap(map);
            }
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }
    
    public boolean makeOneWay(Location src, Location dest) {
        int edges = 0;
        Edge toRemove = null;
        for (Edge e : dest.getConnections()) {
            if (e.getEnd().equals(src)) {
                edges++;
                toRemove = e;
                break;
            }
        }    
        
        for (Edge e : src.getConnections()) {
            if (e.getEnd().equals(dest)) {
                edges++;
                break;
            }
        }
           
        if (edges != 2)
            return false;
        dest.removeConnection(toRemove);
        return true;
    }
    
    public void addMail(Mail m) {
        allMail.add(m);
        if (m.getOrigin().equals(currentMap.getPostOffice())) {
            if (!regionMail.contains(m)) {
                regionMail.add(m);
//                System.out.println(m.getRecipient());
            }
        }
    }
    /**
     * puts all mail with destinations within the current region in the
     * ArrayList variable sorted, and then sorts it (any sort u prefer).
     */
    public ArrayList<Route> sortNPlan() {
        ArrayList<Route> routes = new ArrayList<>();
        ArrayList<Mail> newSorted = new ArrayList<>();
        while (!regionMail.isEmpty()) {
            Mail nextMail = null;
            double minDistance = Double.MAX_VALUE;
            for (Mail m : regionMail) {
                Route r = currentLocation.getShortestPath(m.getDestination());
                double distance = r.getDistance();
                if (distance < minDistance) {
                    minDistance = distance;
                    nextMail = m;
                }
            }
            Route nextRoute = currentLocation.getShortestPath(nextMail.getDestination());
//            for (String name : nextRoute.getRoute())
//                System.out.println(name);
            routes.add(nextRoute);
            newSorted.add(nextMail);
            currentLocation = nextMail.getDestination();
            regionMail.remove(nextMail);
        }
        regionMail = newSorted;
        currentLocation = currentMap.getPostOffice();
        return routes;
    }

    /**
     * removes all mail from the current region. to be called after displaying
     * the required output.
     */   
    public void deliverMail() {
        allMail.remove(regionMail.remove(0));
    }
    

}
