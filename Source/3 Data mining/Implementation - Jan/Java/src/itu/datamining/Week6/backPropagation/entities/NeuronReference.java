package itu.datamining.Week6.backPropagation.entities;

public class NeuronReference {
    public int indexReference;
    public float weight;

    public NeuronReference(){

    }

    public NeuronReference(float weight, int indexReference){
        this.weight = weight;
        this.indexReference = indexReference;
    }
}
