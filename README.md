# MailSimulation
DASALGO MP

This program will simulate a Mail Delivery System.

MILESTONE 1

STATUS: FINISHED <3

MILESTONE 2

STATUS: IN PROGRESS

TODO

[1] Implement Djikstra's Algorithm in Map method getShortestPath()

[2] Add an array of distances to all other locations in the Location class, and a setter for the array.

[3] During the implementation of Dijkstra's algorithm, pass the distance array as a parameter for the setter in [2].

[4] Instead of a PostOffice variable currentStation, make it a Map variable currentRegion. Add currentLocation and another list of mails of the current region to mailman

[5] Modify mailman's sortMail() algorithm; sort mails manually by searching the region mails the shortest path, then add it to the sorted list, then set currentLocation to the destination of the delivered mail. Rinse and repeat. 

[6] Implement GUI for the roads

[7] Ask Ma'am what will happen if there is no way to get to the next destination.
