package itu.datamining.week7_backpropagation.backPropagation;

import itu.datamining.week7_backpropagation.data.Iris;

import java.util.ArrayList;

/**
 * Created by jan on 3/16/2016.
 */
public class IrisNormalizer {
    //Normalizes the Iris data
    public static void NormalizeIrisData(ArrayList<Iris> data)
    {
        float petalLength_max = Float.MIN_VALUE;
        float petalLength_min = Float.MAX_VALUE;

        float petalWidth_max = Float.MIN_VALUE;
        float petalWidth_min = Float.MAX_VALUE;

        float sepalLength_max = Float.MIN_VALUE;
        float sepalLength_min = Float.MAX_VALUE;

        float sepalWidth_max = Float.MIN_VALUE;
        float sepalWidth_min = Float.MAX_VALUE;

        for(Iris iris :data) {
            petalLength_max = Math.max(petalLength_max, iris.Petal_Length);
            petalLength_min = Math.min(petalLength_min, iris.Petal_Length);

            petalWidth_max = Math.max(petalWidth_max, iris.Petal_Width);
            petalWidth_min = Math.min(petalWidth_min, iris.Petal_Width);

            sepalLength_max = Math.max(sepalLength_max, iris.Sepal_Length);
            sepalLength_min = Math.min(sepalLength_min, iris.Sepal_Length);

            sepalWidth_max = Math.max(sepalWidth_max, iris.Sepal_Width);
            sepalWidth_min = Math.min(sepalWidth_min, iris.Sepal_Width);
        }

        for(Iris iris :data) {
            iris.Petal_Length = (iris.Petal_Length - petalLength_min) / (petalLength_max - petalLength_min);
            iris.Petal_Width = (iris.Petal_Width - petalWidth_min) / (petalWidth_max - petalWidth_min);
            iris.Sepal_Length = (iris.Sepal_Length - sepalLength_min) / (sepalLength_max - sepalLength_min);
            iris.Sepal_Width = (iris.Sepal_Width - sepalWidth_min) / (sepalWidth_max - sepalWidth_min);
        }
    }
}
