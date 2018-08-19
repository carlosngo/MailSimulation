/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Driver;

import Model.*;
import View.*;
import java.io.*;
/**
 *
 * @author Carlos
 */
public class Main {
    public static void main(String[] args) throws IOException {
        StartScreen sc = new StartScreen();
//        Map map = new Map("Manila");
//        PostOffice po = map.getPostOffice();
//        Location loc1 = new Location("DLSU", "Manila");
//        loc1.setIndex(1);
//        Location loc2 = new Location("ADMU", "Manila");
//        loc2.setIndex(2);
//        Location loc3 = new Location("USTE", "Manila");
//        loc3.setIndex(3);
//        Location loc4 = new Location("Mapua", "Manila");
//        loc4.setIndex(4);
//        
//        po.addConnection(new Edge(loc1, 5));
//        loc1.addConnection(new Edge(loc2, 5));
//        loc1.addConnection(new Edge(loc4, 7.5));
//        loc2.addConnection(new Edge(loc3, 10));
//        loc2.addConnection(new Edge(loc4, 4));
//        loc3.addConnection(new Edge(loc4, 15));
//        loc1.addConnection(new Edge(loc4, 10));
//        loc4.addConnection(new Edge(loc1, 5));
//        map.addLocation(loc1);
//        map.addLocation(loc2);
//        map.addLocation(loc3);
//        map.addLocation(loc4);
//        map.calculateRoutes();
//        Mailman m = new Mailman("Carlos");
//        m.addMap(map);
//        m.setCurrentMap(map);
//        m.setCurrentLocation(po);
//        m.getBag().add(new Mail("Johanna", po, loc1, new DateTime(1,2,3,4,5)));
//        m.getBag().add(new Mail("Johanna", po, loc2, new DateTime(1,2,3,4,5)));
//        m.getBag().add(new Mail("Johanna", po, loc3, new DateTime(1,2,3,4,5)));
//        m.getBag().add(new Mail("Johanna", po, loc4, new DateTime(1,2,3,4,5)));
//        for (Route nextRoute : m.sortMail()){
//            System.out.println("Distance: " + nextRoute.getDistance() + "\nRoute: ");
//                for (String name : nextRoute.getRoute()) {
//                    System.out.println(name);
//                }
//        }
    }
}
