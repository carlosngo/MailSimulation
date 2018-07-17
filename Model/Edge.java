package Model;

public class Edge {

    private Location end;
    private double distance;
    private boolean visited;

    public Edge(Location end, double distance){
    	this.end = end;
    	this.distance = distance;
    }

    public Location getEnd(){
    	return end;
    }

    public double getDistance(){
    	return distance;
    }
    
    public boolean isVisited() {
        return visited;
    }
}

