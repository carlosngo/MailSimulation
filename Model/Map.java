package Model;

public class Map{

	private String region;
	private ArrayList<Location> locations;

	public Map(String region){
		this.region = region;
		locations = new ArrayList<Location>();
	}

	public void addLocation(Location l){
		locations.add(l);
	}

	public PostOffice getPostOffice(){

	}
}
