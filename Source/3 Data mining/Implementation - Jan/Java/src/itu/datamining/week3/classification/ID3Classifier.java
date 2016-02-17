package itu.datamining.week3.classification;

import itu.datamining.week3.Mushroom;
import itu.datamining.week3.classification.entities.Leaf;
import itu.datamining.week3.classification.entities.Tree;
import itu.datamining.week3.data.Class_Label;

import java.util.ArrayList;

/**
 * Created by jan on 2/17/2016.
 */
public class ID3Classifier {
    public static Tree generateDecisionTree(ArrayList<Mushroom> mushrooms, ArrayList<Object> attributeList, InformationGain AttributeSelectionMeasure) {
        if(mushrooms == null || mushrooms.isEmpty()){
            return null;
            //throw new Exception("Can't make decision tree on empty mushroom set.");
        }
        Tree decisionTree = new Tree();
        if(containsOnlyOneClassLabel(mushrooms)){
            Leaf leaf = new Leaf();
            leaf.m_classificationValue = mushrooms.get(0)
            decisionTree.addNode(new Leaf());
        }

        return decisionTree;
    }

    private static boolean containsOnlyOneClassLabel(ArrayList<Mushroom> mushrooms){
        Class_Label lastClassLabel = mushrooms.get(0).m_Class;

        for (Mushroom mushroom: mushrooms) {
            if(mushroom.m_Class != lastClassLabel){
                return false;
            }
        }

        return true;
    }
}
