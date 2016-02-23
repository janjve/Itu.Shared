package itu.datamining.week3.classification;

import itu.datamining.week3.Mushroom;

import java.util.ArrayList;

/**
 * Created by jan on 2/17/2016.
 */
public class InformationGain {
    public Object getBestAttribute(ArrayList<Mushroom> mushrooms, ArrayList<Object> attributeList){
        if(attributeList != null && !attributeList.isEmpty()){
            return attributeList.get(0);
        }
        return null;
    }
}
