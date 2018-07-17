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
    		String[] temp = input.next().split(",");
    		region = temp[0];
    		location1 = new Location(temp[1], region);
	    	location2 = new Location(temp[2], region);
    		double distance = Double.parseDouble(temp[3]);
    		for(int i=0;i<maps.size() && !found;i++){
    			if(region.equals(maps.get(i).getRegion()){
	    			found = true;
    				int index = i;
			}
	    	}
	    	if(found){
		    	if(!maps.get(index).getLocations().contains(location1))
    				maps.get(index).addLocation(location1);
	    		int index1 = maps.get(index).getLocations().indexOf(location1);
		    	if(!maps.get(index).getLocations().contains(location2))
	    			maps.get(index).addLocation(location2);
	    		int index2 = maps.get(index).getLocations().indexOf(location2);
	    		maps.get(index).getLocations().get(location1).addConnection(new Edge(location2, distance));
	    		maps.get(index).getLocations().get(location2).addConnection(new Edge(location1, distance));
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
        for(Mail m : sorted)
		System.out.println(m.getDestination().getName());
    }
}
