package itu.datamining.Week6.backPropagation;

import itu.datamining.Week6.backPropagation.entities.Neuron;
import itu.datamining.Week6.backPropagation.entities.NeuronReference;
import itu.datamining.Week6.data.Iris;
import itu.datamining.Week6.data.IrisClass;

import java.util.ArrayList;

/**
 * Created by jan on 3/16/2016.
 */
public class NeuralNetwork {
    public final float INITIAL_WEIGHT = 0.2f;
    public ArrayList<Neuron> neurons;

    public NeuralNetwork(){
        init();
    }

    public IrisClass assignClass(Iris tuple){
        return null;
    }

    public NeuralNetwork trainNetwork(ArrayList<Iris> trainingData, float learningRate){

        for(Iris iris : trainingData){

            // Only first 4 neurons are input
            for(Neuron neuron : neurons.subList(0, 4)){

            }
        }

        return this;
    }

    public float calculateValue(Neuron neuron){
        return 1;
    }

    // Returns a neuron network with the structure (layer1-layer2-layer3): (4-2-1).
    private void init(){
        // Input layer
        Neuron inputSepalLengthNeuron = new Neuron(); // index 0
        Neuron inputSepalWidthNeuron = new Neuron();  // index 1
        Neuron inputPetalLengthNeuron = new Neuron(); // index 2
        Neuron inputPetalWidthNeuron = new Neuron();  // index 3

        // Hidden layer
        Neuron hiddenSepalNeuron = new Neuron();      // index 4
        Neuron hiddenPetalNeuron = new Neuron();      // index 5

        // Output layer
        Neuron outputNeuron = new Neuron();           // index 6

        // input: Neuron 0
        inputSepalLengthNeuron.neuronReferences.add(new NeuronReference(INITIAL_WEIGHT, 4));

        // input: Neuron 1
        inputSepalWidthNeuron.neuronReferences.add(new NeuronReference(INITIAL_WEIGHT, 4));

        // input: Neuron 2
        inputPetalLengthNeuron.neuronReferences.add(new NeuronReference(INITIAL_WEIGHT, 5));

        // input: Neuron 3
        inputPetalWidthNeuron.neuronReferences.add(new NeuronReference(INITIAL_WEIGHT, 5));

        // hidden: Neuron 4
        hiddenSepalNeuron.neuronReferences.add(new NeuronReference(INITIAL_WEIGHT, 6));

        // hidden: Neuron 5
        hiddenPetalNeuron.neuronReferences.add(new NeuronReference(INITIAL_WEIGHT, 6));

        // hidden: Neuron 6

        neurons.add(inputSepalLengthNeuron);
        neurons.add(inputSepalWidthNeuron);
        neurons.add(inputPetalLengthNeuron);
        neurons.add(inputPetalWidthNeuron);
        neurons.add(hiddenSepalNeuron);
        neurons.add(hiddenPetalNeuron);
        neurons.add(outputNeuron);
    }
}
