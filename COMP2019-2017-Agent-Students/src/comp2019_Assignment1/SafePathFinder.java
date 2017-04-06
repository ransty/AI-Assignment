package comp2019_Assignment1;


public class SafePathFinder extends PathFinder {

    protected RectangularMap enemyMap;  // shows the probability of being captured at each square
    protected double survivalThreshold; // the minimum probability of survival that is required

    public SafePathFinder(RectangularMap map, RectangularMap enemyMap, Location start, Location goal,
                          int terrainThreshold, double survivalThreshold) {
        super(map, start, goal, terrainThreshold);
        this.enemyMap = enemyMap;
        this.survivalThreshold = survivalThreshold;
    }

   	/* DO NOT CHANGE THE CODE ABOVE */
    /* adding imports and variables is okay. */
    
    /* TODO: Question 2
     * add your code below. you can add extra methods. 
     * */
    
//    Unfortunately, the enemy has become aware of the agent and has dispatched troops in the area. Your agent has
//    been given a map of the territory controlled by the enemy, in addition to the terrain map as in Question 1.
//    Analogous to the terrain, the map shows the strength of the enemy’s presence at each location. The dimensions of
//    this map will be the same as that of the terrain map. All the strength indicators will be integers in the range [0,100]
//    (with 0 and 100 included).

    
    
    
    
    
}