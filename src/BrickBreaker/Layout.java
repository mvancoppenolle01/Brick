package BrickBreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

 class Layout {
        
    public int level [][];
	public int brickW;
	public int brickH;
        
    public Layout(int row, int col){
        level = new int [row][col];
        int i;
        int j;
        for(i = 0; i < level.length; i++){
            for(j = 0; j < level[0].length; j++){
                level[i][j] = 2;
            }
        }
        brickH = 150/row;
        brickW = 540/col;
    }

    public void createBrick(Graphics2D brick){
        int i;
        int j;
        for(i = 0; i < level.length; i++){
            for(j = 0; j < level[0].length; j++){
                if(level[i][j] > 0){
                brick.setColor(Color.blue);
                brick.fillRect(j * brickW + 80, i*brickH + 30, brickW, brickH);
                brick.setStroke(new BasicStroke(4));
				brick.setColor(Color.BLACK);
				brick.drawRect(j*brickW + 80, i*brickH + 30, brickW, brickH);
            }
        }
    }
    }
    public void setBrickValue(int value, int row, int col) {
		level[row][col] = value;
    }
    
}

