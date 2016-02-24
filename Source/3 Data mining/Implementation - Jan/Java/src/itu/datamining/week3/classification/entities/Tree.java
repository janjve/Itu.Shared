package itu.datamining.week3.classification.entities;

import itu.datamining.week3.Mushroom;
import itu.datamining.week3.classification.InformationGain;
import itu.datamining.week3.data.Class_Label;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jan on 2/17/2016.
 */
//public class DecisionTree {
//    private Node root;

    /*
    public Tree(Class_Label rootData) {
        Leaf leaf = new Leaf();
        leaf.m_classificationValue = rootData;
        root = new Leaf();
    }

    public Tree(T rootData, Object rootAttribute) {
        root = new Branch<T>(rootAttribute);
        root.data = rootData;
        root.children = new ArrayList<Node<T>>();
    }

    public void addNode(){

    }
*/

//    public Class_Label getClassification(Mushroom mushroom){
//        if(root instanceof Leaf){
//            return ((Leaf)root).m_classificationValue;
//        } else {
//            // Traverse
//        }
//    }

//    public void generateDecisionTree(ArrayList<Mushroom> mushrooms, ArrayList<Object> attributeList, InformationGain AttributeSelectionMeasure) {
//        root = CreateID3Classifier(mushrooms, attributeList, AttributeSelectionMeasure);
//    }

//    private Node CreateID3Classifier(ArrayList<Mushroom> mushrooms, ArrayList<Object> attributeList, InformationGain AttributeSelectionMeasure){
//        if(containsOnlyOneClassLabel(mushrooms)){
//            Leaf leaf = new Leaf();
//            leaf.m_classificationValue = mushrooms.get(0).m_Class;
//            root = leaf;
//        }
//        return null;
//    }
//
//    private boolean containsOnlyOneClassLabel(ArrayList<Mushroom> mushrooms){
//        Class_Label lastClassLabel = mushrooms.get(0).m_Class;
//
//        for (Mushroom mushroom: mushrooms) {
//            if(mushroom.m_Class != lastClassLabel){
//                return false;
//            }
//        }
//
//        return true;
//    }
//}
