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

    
    
    
    
    
}