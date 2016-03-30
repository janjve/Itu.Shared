/*
 * SOMTrainer.java
 *
 * Created on December 13, 2002, 2:37 PM
 */

package itu.datamining.Week8_Classification_And_Propogation.util;

import itu.datamining.Week8_Classification_And_Propogation.CoreClasses.SOMLattice;
import itu.datamining.Week8_Classification_And_Propogation.CoreClasses.SOMNode;
import itu.datamining.Week8_Classification_And_Propogation.CoreClasses.SOMVector;

import java.util.Vector;

/**
 *
 * @author  alanter
 */
public class SOMTrainer implements Runnable {
	// These constants can be changed to play with the learning algorithm
	private static final double START_LEARNING_RATE = 0.07;
	private static final int	NUM_ITERATIONS = 500;
	
	// These two depend on the size of the lattice, so are set later
	private double LATTICE_RADIUS;
	private double TIME_CONSTANT;
	private LatticeRenderer renderer;
	private SOMLattice lattice;
	private Vector inputs;
	private static boolean running;
	private Thread runner;
	
	/** Creates a new instance of SOMTrainer */
	public SOMTrainer() {
		running = false;
	}
	
	
		
	// Train the given lattice based on a vector of input vectors
	public void setTraining(SOMLattice latToTrain, Vector in,
							LatticeRenderer latticeRenderer)
	{
		lattice = latToTrain;
		inputs = in;
		renderer = latticeRenderer;
	}
	
	public void start() {
		if (lattice != null) {
			runner = new Thread(this);
			runner.setPriority(Thread.MIN_PRIORITY);
			running = true;
			runner.start();
		}
	}
	
	/***
	 * The run method is used to train/constuct the SOM.
	 */
	public void run() {
		//Initialize constants (e.g. t etc. you will need for calculations
		double learningRate = 1 * Math.exp(0 / NUM_ITERATIONS);
		for(int iteration = 0;iteration < NUM_ITERATIONS && running;iteration++)
		{
			for (int i=0; i<inputs.size(); i++) {
				SOMVector currentInput = (SOMVector)inputs.elementAt(i);
				SOMNode bmu = lattice.getBMU(currentInput);
				// We have the BMU for this input now, so adjust everything in
				// it's neighborhood

				double neighborhood = gaussianNeighborhood(bmu.getVector(), currentInput);

				for (int j = 0; j < lattice.getWidth() ; j++) {
					for(int k = 0; k < lattice.getHeight() ; k++) {
						SOMNode node = lattice.getNode(j,k);
						double distance = node.getVector().euclideanDist(bmu.getVector());
						if(distance > neighborhood) {
							node.adjustWeights(,learningRate,);
						}
					}
				}
				learningRate *= Math.exp(i / NUM_ITERATIONS);
 			}
			
			
			//Last thing to do, pass the updated lattice to the GUI to visualize it
			renderer.render(lattice, iteration);
		}
		
		//Used by the GUI
		running = false;
	}

	public void stop() {
		if (runner != null) {
			running = false;
			while (runner.isAlive()) {};
		}
	}

	private double gaussianNeighborhood(SOMVector v1, SOMVector v2){
		return (-1) * Math.exp(Math.pow(v1.euclideanDist(v2),2) / 2 * Math.pow(lattice.getRadius(),2));
	}
}
