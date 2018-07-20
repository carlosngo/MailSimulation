package Model;

import java.util.*;
import java.io.*;

public class Mailman {

    String name;
    private PostOffice currentStation;
    private ArrayList<Map> maps;
    private ArrayList<Mail> bag;
    private ArrayList<Mail> sorted;

    /**
     * NOTE: read csv files upon instantiation.
     */
    public Mailman(String name) {
        this.name = name;
        maps = new ArrayList<>();
        bag = new ArrayList<>();
        sorted = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public PostOffice getCurrentStation() {
        return currentStation;
    }

    public Map getCurrentMap() {
        for (Map m : maps) 
            if (m.getRegion().equals(currentStation.getRegion()))
                return m;
        return null;
    }
    
    public ArrayList<Map> getMaps() {
        return maps;
    }

    public ArrayList<Mail> getBag() {
        return bag;
    }

    public ArrayList<Mail> getSorted() {
        return sorted;
    }
    
    public int getNoOfLocations() {
        int count = 0;
        for (Map m : maps) 
            count += m.getLocations().size();
        return count;
    }
    public void setCurrentStation(PostOffice p) {
        currentStation = p;
    }

    public void addMap(Map m) {
        maps.add(m);
    }

    /**
     * reads the CSV file. add a map for every region.
     */
    public void readMaps(File csv) throws IOException {
        try {
            String input;
            BufferedReader br = new BufferedReader(new FileReader(csv));
            br.readLine();
            while ((input = br.readLine()) != null) {
                String region;
                Location location1, location2;
                int index = -1;
                boolean found = false;
                String[] temp = input.split(",");
                region = temp[0];
                location1 = new Location(temp[1], region);
                location2 = new Location(temp[2], region);

                double distance = Double.parseDouble(temp[3]);
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
                    map.getLocations().add(location1);
                } else {
                    location1 = map.getLocations().get(index1);
                }
                int index2 = map.getLocations().indexOf(location2);
                if (index2 < 0) {
                    map.getLocations().add(location2);
                } else {
                    location2 = map.getLocations().get(index2);
                }
                location1.addConnection(new Edge(location2, distance));
                location2.addConnection(new Edge(location1, distance));
                if (!found)
                    addMap(map);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    /**
     * fetches the new batch of mails in the current region's post office. add
     * each mail to bag.
     */
    public void fetchMail() {
        for (Mail m : currentStation.pushMail()) {
            bag.add(m);
        }
    }

    /**
     * puts all mail with destinations within the current region in the
     * ArrayList variable sorted, and then sorts it (any sort u prefer).
     */
    public void sortMail() {
        for (Mail m : bag) {
            if (m.getOrigin().equals(currentStation.getRegion())) {
                sorted.add(m);
            }
        }
        Collections.sort(sorted);
    }

    /**
     * removes all mail from the current region. to be called after displaying
     * the required output.
     */
    public void deliverMail() {
        for (Mail m : sorted) {
            bag.remove(m);
        }
        sorted = new ArrayList<>();
    }

    public String displaySortedMails() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sorted.size(); i++) {
            sb.append(sorted.get(i).toString() + "\n");
        }
        return sb.toString();
    }

    public void displayRoute() {

    }
}
