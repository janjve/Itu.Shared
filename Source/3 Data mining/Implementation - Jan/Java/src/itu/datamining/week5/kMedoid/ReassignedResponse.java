package itu.datamining.week5.kMedoid;

import itu.datamining.week5.kMean.KMeanCluster;

import java.util.ArrayList;

public class ReassignedResponse {
    public boolean reassigned;
    public ArrayList<KMeanCluster> newCluster;

    public ReassignedResponse(boolean reassigned, ArrayList<KMeanCluster> newCluster){
        this.reassigned = reassigned;
        this.newCluster = newCluster;
    }
}