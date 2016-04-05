package itu.datamining.Week6.backPropagation.entities;

import java.util.ArrayList;

/**
 * Created by jan on 3/16/2016.
 */
public class Neuron {
    public ArrayList<NeuronReference> neuronReferences; // <index, weight>

    public Neuron(){
        neuronReferences = new ArrayList<>();
    }

    public Neuron(ArrayList<NeuronReference> neuronReferences) {
        this.neuronReferences = neuronReferences;
    }
}

