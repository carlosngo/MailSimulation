package Model;
import java.util.*;
import java.io.*;

public class Mailman {
    String name;
    private ArrayList<Map> maps;
    private PostOffice currentStation;
    private ArrayList<Mail> bag;
    private ArrayList<Mail> sorted;
    
    /**
     * NOTE: read csv files upon instantiation.
     */
    public Mailman(String name) {
        this.name = name;
    }
    
    public void addMap(Map m) {
        maps.add(m);  
    }
    
    /**
     * reads the CSV file. add a map for every region.
     */
    public void readMaps(File csv){
	Scanner input = new Scanner(csv);
    	while(input.hasNext()){
    		String region;
    		Location location1, location2;
		int index;
    		String[] temp = input.nextLine().split(",");
    		region = temp[0];
    		location1 = new Location(temp[1], region);
	    	location2 = new Location(temp[2], region);
    		double distance = Double.parseDouble(temp[3]);
    		for(int i = 0; i < maps.size() && !found; i++){
    			if(region.equals(maps.get(i).getRegion()){
	    			found = true;
    				index = i;
			}
	    	}
	    	if(found){
		    	Map map = maps.get(index);
	    		int index1 = map.getLocations().indexOf(location1);
			if (index1 < 0)
				map.getLocations().add(location1);
			else
				location1 = map.getLocations().get(index1);
	    		int index2 = map.getLocations().indexOf(location2);
			if (index2 < 0)
				map.getLocations().add(location2);
			else
				location2 = map.getLocations().get(index2);
			location1.addConnection(new Edge(location2, distance));
			location2.addConnection(new Edge(location1, distance));
	    	}
	    	else{
	    		Map m = new Map(region);
	    		location1.addConnection(location2, distance);
	    		location2.addConnection(location1, distance);
	    		m.addLocation(location1);
	    		m.addLocation(location2);
	    		addMap(m);
		}
	}
	input.close();
    }
    
    /**
     * fetches the new batch of mails in the current region's
     * post office. add each mail to bag.
     */
    public void fetchMail() {
        for(Mail m : currentStation.pushMail())
            bag.add(m);
    }
    
    /**
     * puts all mail with destinations within
     * the current region in the ArrayList variable 
     * sorted, and then sorts it (any sort u prefer).
     */
    public void sortMail() {
        for(Mail m : bag)
            if(m.getOrigin().equals(currentStation.getRegion())
               sorted.add(m);
        for(int i = 1; i < sorted.size(); i++) {
            Mail m = sorted.remove(i);
            int j = i;
            while(j >= 0) {
                if(m.compareTo(sorted.get(j - 1)) > 0) {
                    if(j - 1 == 0)
                        sorted.add(j, m);
                    else
                        j--;
                }
                else if(m.compareTo(sorted.get(j - 1)) < 0)
                    sorted.add(j - 1, sort);
                else
                    sorted.add(j, m);
                }
            }
        }
    }
    
    /**
     * removes all mail from the current region.
     * to be called after displaying the required output.
     */
    public void deliverMail() {
        for (Mail m : sorted)
            bag.remove(m);
        sorted = new ArrayList<>();
    }
    
    public void displaySortedMails() {
        for(int i = 0; i < sorted.size(); i++)
            System.out.println(sorted.get(i).toString());
    }
    
    public void displayRoute() {
        
    }
}
