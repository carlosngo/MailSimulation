/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.*;
/**
 *
 * @author Carlos
 */
public class Route {
    private LinkedList<String> route;
    private double distance;
    
    public Route(double distance) {
        this.distance = distance;
        route = new LinkedList<String>();
    }
    
    public LinkedList<String> getRoute() {
        return route;
    }
    
    public double getDistance() {
        return distance;
    }
    
}
