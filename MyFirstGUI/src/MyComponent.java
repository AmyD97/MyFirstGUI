import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Random;

import javax.swing.JComponent;

public class MyComponent extends JComponent { 

	static int counter = 1-1;
	
	static MutableCar theCars[];
	
	public MyComponent(int numCars){
		theCars = new MutableCar[numCars];
		for(int i = 0; i<numCars; i++){
			//int laneWidth = 50;
			int laneY = i * laneWidth;
			theCars[i] = new MutableCar(0, laneY, Color.MAGENTA, 0, 1);//x, y, color, speed, direction
		}
	}
	
	//static MutableCar theCar = new MutableCar(0,0,Color.BLACK, 10, 1);
	private static Random genRand = new Random();

	private static boolean someCarWon = false;
	
	public static final int laneWidth = 40;
	
	public boolean getSomeCarWon(){return someCarWon; }
	
	public boolean carCrashed(MutableCar c) {
		if (c.getCarDirection() > 0) {
			if (c.getxPos()+60 >= this.getWidth()) {
				return true;
			}
		}
		else if (c.getCarDirection() < 0) {
			if (c.getxPos() <= 0) {
				return true;
			}			
		}
		
		return false;
		
	}
	
	public void paintComponent(Graphics g) {		
		int imax = 0;
		for(int i=0; i<theCars.length; i++){
			theCars[i].draw(g);
			theCars[i].move(genRand.nextInt(10), 0);//deltaX, deltaY
			theCars[i].setColor(Color.RED);
			if(theCars[imax].getxPos()<theCars[i].getxPos()){
				imax = i;
				
			}
			if (this.carCrashed(theCars[i])) {//gano
				theCars[i].setCarDirection(theCars[i].getCarDirection()*-1);
				someCarWon = true;
			}
			
		}
		theCars[imax].setColor(Color.GREEN);
		
//		//MutableCar car2 = new MutableCar(0,40, Color.BLUE);
//		theCar.setPosition(0, 40);
//		theCar.draw(g);
		
		System.out.println("Painted " + counter++ + " times");
	}

}
