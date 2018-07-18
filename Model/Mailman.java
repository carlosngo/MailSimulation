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

    public ArrayList<Map> getMaps() {
        return maps;
    }

    public ArrayList<Mail> getBag() {
        return bag;
    }

    public ArrayList<Mail> getSorted() {
        return sorted;
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
                boolean isPostOffice1 = false;
                boolean isPostOffice2 = false;
                String[] temp = input.split(",");
                region = temp[0];
                location1 = new Location(temp[1], region);
                location2 = new Location(temp[2], region);
                String[] split1 = location1.getName().trim().split("\\s+");
                String[] split2 = location2.getName().trim().split("\\s+");
                if (split1.length > 1) {
                    if (split1[split1.length - 2].equals("Post") && split1[split1.length - 1].equals("Office")) {
                        isPostOffice1 = true;
                    }
                }

                if (split2.length > 1) {
                    if (split2[split2.length - 2].equals("Post") && split2[split2.length - 1].equals("Office")) {
                        isPostOffice2 = true;
                    }
                }

                double distance = Double.parseDouble(temp[3]);
                for (int i = 0; i < maps.size() && !found; i++) {
                    if (region.equals(maps.get(i).getRegion())) {
                        found = true;
                        index = i;
                    }
                }
                if (found) {
                    Map map = maps.get(index);
                    int index1 = map.getLocations().indexOf(location1);
                    if (index1 < 0) {
                        if (isPostOffice1) {
                            map.getLocations().add(new PostOffice(location1.getName(), location1.getRegion()));
                        } else {
                            map.getLocations().add(location1);
                        }
                    } else {
                        location1 = map.getLocations().get(index1);
                    }
                    int index2 = map.getLocations().indexOf(location2);
                    if (index2 < 0) {
                        if (isPostOffice2) {
                            map.getLocations().add(new PostOffice(location2.getName(), location2.getRegion()));
                        } else {
                            map.getLocations().add(location2);
                        }
                    } else {
                        location2 = map.getLocations().get(index2);
                    }
                    location1.addConnection(new Edge(location2, distance));
                    location2.addConnection(new Edge(location1, distance));
                } else {
                    Map m = new Map(region);
                    location1.addConnection(new Edge(location2, distance));
                    location2.addConnection(new Edge(location1, distance));
                    if (isPostOffice1) {
                        m.addLocation(new PostOffice(location1.getName(), location1.getRegion()));
                    } else {
                        m.addLocation(location1);
                    }
                    if (isPostOffice1) {
                        m.addLocation(new PostOffice(location1.getName(), location1.getRegion()));
                    } else {
                        m.addLocation(location1);
                    }
                    addMap(m);
                }
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
        for (int i = 0; i < sorted.size() - 1; i++) {
            for (int j = 0; j < sorted.size() - i - 1; j++) {
                if (sorted.get(j).compareTo(sorted.get(j + 1)) > 0) {
                    Collections.swap(sorted, j, j + 1);
                }
            }
        }
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

    public void displaySortedMails() {
        for (int i = 0; i < sorted.size(); i++) {
            System.out.println(sorted.get(i).toString());
        }
    }

    public void displayRoute() {

    }
}
