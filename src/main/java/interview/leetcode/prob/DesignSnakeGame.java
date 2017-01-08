package interview.leetcode.prob;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not familiar with the game.

The snake is initially positioned at the top left corner (0,0) with length = 1 unit.

You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's score both increase by 1.

Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.

When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.

Example:
Given width = 3, height = 2, and food = [[1,2],[0,1]].

Snake snake = new Snake(width, height, food);

Initially the snake appears at position (0,0) and the food at (1,2).

|S| | |
| | |F|

snake.move("R"); -> Returns 0

| |S| |
| | |F|

snake.move("D"); -> Returns 0

| | | |
| |S|F|

snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )

| |F| |
| |S|S|

snake.move("U"); -> Returns 1

| |F|S|
| | |S|

snake.move("L"); -> Returns 2 (Snake eats the second food)

| |S|S|
| | |S|

snake.move("U"); -> Returns -1 (Game over because snake collides with border)

 * @author jojo
 *
 */
public class DesignSnakeGame {
    private Set<Integer> bodySpace = new HashSet<Integer>();
    private Deque<Integer> body = new LinkedList<Integer>();
    private final int[][] food;
    private int width = 0, height = 0, score = 0, foodIdx = 0;
    
    /** Initialize your data structure here.
        @param width - screen width
        @param height - screen height 
        @param food - A list of food positions
        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public DesignSnakeGame (int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.food = food;
        bodySpace.add(0);
        body.offerFirst(0);
    }   
    
    /** Moves the snake.
        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
        @return The game's score after the move. Return -1 if game over. 
        Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        if(score == -1){
            return -1;
        }
        
        int row = body.peekFirst() % width;
        int col = body.peekFirst() / width;
        
        if(direction.equals("U")){
            col--;
        }
        else if(direction.equals("D")){
            col ++;
        }
        else if(direction.equals("L")){
            row--;
        }
        else if(direction.equals("R")){
            row++;
        }
        
        int head = col * width + row;
        bodySpace.remove(body.peekLast());
        body.offerFirst(head);
        
        // checking if out of boundary or head lying within body
        if(row < 0 || row >= width || col < 0 || col >= height || !bodySpace.add(head)){
            score = -1;
            return -1;
        }
        
        // assuming if the new position is food
        if(foodIdx < food.length && col == food[foodIdx][0] && row == food[foodIdx][1]){
            score++;
            bodySpace.add(body.peekLast());
            foodIdx++;
        }
        else{
            body.pollLast();
        }
        
        return score;
    }
}
