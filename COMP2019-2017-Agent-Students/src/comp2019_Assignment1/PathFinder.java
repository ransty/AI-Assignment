package comp2019_Assignment1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * This class finds the best path from a start location to the goal (HQ) location given the map.
 * Each location along the path, including start and end location, must have a terrain value less than or equal to a given threshold.
 * The entry point for your code is in method findPath().
 *
 * DO NOT MODIFY THE SIGNATURE OF EXISTING METHODS AND VARIABLES.
 * Otherwise, JUnit tests will fail and you will receive no credit for your code.
 * Of course, you can add additional methods and classes in your implementation.
 *
 */
public class PathFinder {

	protected Location start;		// start location
	protected Location goal;		// goal location
	protected RectangularMap map;   // the map
	protected int terrainThreshold; // threshold for navigable terrain

	private int explored = 0;	    //  number of states visited during the search;
	// increment this whenever you remove a state from the frontier

	private Map<Location, Location> parents;
	private HashMap<Location, Integer> fVals;
	private HashMap<Location, Integer> gVals;
	private Path path;


	public PathFinder(RectangularMap map, Location start, Location goal, int terrainThreshold) {
		this.map = map;
		this.start = start;
		this.goal = goal;
		this.terrainThreshold = terrainThreshold;
		this.parents = new HashMap<Location, Location>();
		this.fVals = new HashMap<Location, Integer>();
		this.gVals = new HashMap<Location, Integer>();
		//this.cost = new HashMap<Location, Integer>();
	}

	public RectangularMap getMap() {
		return map;
	}

	public Location getStart() {
		return start;
	}

	public Location getGoal() {
		return goal;
	}

	public int getTerrainThreshold() {
		return terrainThreshold;
	}

	public int getExplored() {
		return explored;
	}

	/* DO NOT CHANGE THE CODE ABOVE */
	/* adding imports and variables is okay. */
	/* TODO: add your code below. you can add extra methods. */

	public Path findPath() {
		// Stops the Agent going above their terrain threshold
		if (map.getValueAt(start) > terrainThreshold) {
			return null;
		}
		// A* uses a heuristic function to calculate path
		// Two lists needed, frontier list and visited list

		// Each tile is given a score of G + H where
		// G is the cost from the start point to the current square
		// H is the estimated movement cost from the current square to destination point

		// Each tile has a terrain with a difficulty, this is the movement cost

		// Cost of each square F = G + H

		// 1. Get the square on the frontier list which has the lowest score.
		// 2. Remove S from the frontier list and add S to the visited list
		// 3. For each square T in S's walkable adjacent tiles:
		// A. If T is in the visited list: ignore it
		// B. If T is not in the frontier list: add it and compute its score
		// C. If T is already in the frontier list: Check if the F score is lower when we use the current
		// generated path to get there. if it is, update its score and update its parent as well
		// G Values
		gVals.put(start, 0); // because it costs nothing to start

		// Priority queue to hold all values of Locations, ordered with F values
		PriorityQueue<Location> frontier = new PriorityQueue<Location>(map.getColumns() * map.getRows(), new fCompare());
		frontier.add(start);

		// List to store visited nodes
		LinkedList<Location> visited = new LinkedList<Location>();

		while (!frontier.isEmpty()) {

			// Takes the location with lowest f value
			Location current = frontier.peek();

			if (current.equals(goal)) {
				System.out.println("Goal found. ");
				path = printPath(goal);
				return path;
			}
			
			visited.add(frontier.poll());
			explored++;

			Iterable<Location> neighboursList = map.getNeighbours(current);
			for (Location next : neighboursList) {
				// If the Agent can't visit the next location, skip it
				if (map.getValueAt(next) > terrainThreshold) {
					continue;
				}
				int gScore = gVals.get(current) + (map.getValueAt(next) + map.getValueAt(current));
				int fScore = (int) (gScore + (heuristicCalc(next)));

				if (visited.contains(next)) {
					if (gVals.get(next) == null) {
						gVals.put(next, gScore);
					}
					if (fVals.get(next) == null) {
						fVals.put(next, fScore);
					}

					if (fScore >= fVals.get(next)) {
						continue;
					}
				}

				if (!frontier.contains(next) || fScore < fVals.get(next)) {
					parents.put(next, current);
					gVals.put(next, gScore);
					fVals.put(next, fScore);
					if (!frontier.contains(next)) {
						frontier.add(next);
					}
				}
			}
		}

		return null;
	}

	private Path printPath(Location current) {

		LinkedList<Location> shortestPath = new LinkedList<Location>();
		Location node = goal;
		while (node != null) {
			shortestPath.add(node);
			node = parents.get(node);
		}
		Collections.reverse(shortestPath);

		// Add it to path variable
		path = new Path(shortestPath.pop());
		for (Location next : shortestPath) {
			int parentVal = map.getValueAt(parents.get(next));
			path.moveTo(next, map.getValueAt(next) + parentVal);
		}
		return path;
	} 

	// Calculates heuristic function (Manhattan)
	private int heuristicCalc(Location current) {
		int x = Math.abs(current.getRow() - this.goal.getRow());
		int y = Math.abs(current.getColumn() - this.goal.getColumn());
		return (x + y);
	}

	class fCompare implements Comparator<Location> {

		public int compare(Location o1, Location o2) {
			if (fVals.get(o1) < fVals.get(o2)) {
				return -1;
			} else if (fVals.get(o1) > fVals.get(o2)) {
				return 1;
			} else {
				return 1;
			}
		}
	}

}



