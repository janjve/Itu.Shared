/*
 * SOMTrainer.java
 *
 * Created on December 13, 2002, 2:37 PM
 */

package itu.datamining.week7.util;

import itu.datamining.week7.CoreClasses.SOMLattice;
import itu.datamining.week7.CoreClasses.SOMNode;
import itu.datamining.week7.CoreClasses.SOMVector;

import javax.sound.midi.ShortMessage;
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
		LATTICE_RADIUS = Math.max(lattice.getHeight(), lattice.getWidth())/2;

		for(int iteration = 0;iteration < NUM_ITERATIONS && running;iteration++)
		{
			double learningRate = START_LEARNING_RATE * Math.exp(-iteration/NUM_ITERATIONS);

			for (int i=0; i<inputs.size(); i++) {
				SOMVector currentInput = (SOMVector)inputs.elementAt(i);
				SOMNode bmu = lattice.getBMU(currentInput);
				// We have the BMU for this input now, so adjust everything in
				// it's neighborhood

				bmu.adjustWeights(currentInput, learningRate, 1);

				for(int x = 0; x< lattice.getHeight(); x++){
					for(int y = 0; y < lattice.getWidth(); y++){

						SOMNode currentNode = lattice.getNode(x, y);
						if(currentNode.equals(bmu)) continue; // Skip BMU

						double gaussianNeighbor = Math.exp(-(double)Math.pow(bmu.distanceTo(currentNode), 2)
								/(double)(2*Math.pow(LATTICE_RADIUS, 2)));
						currentNode.adjustWeights(currentInput, learningRate, gaussianNeighbor);
					}
				}
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
}
