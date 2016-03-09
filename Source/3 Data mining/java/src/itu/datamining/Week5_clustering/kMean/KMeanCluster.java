package itu.datamining.Week5_clustering.kMean;
import java.util.ArrayList;

import itu.datamining.Week5_clustering.data.Iris;
import itu.datamining.Week5_clustering.data.IrisClass;

//ToDo: Compute cluster mean based on cluster members.
public class KMeanCluster {

	public ArrayList<Iris> ClusterMembers;
	public Iris ClusterMean;

	public KMeanCluster()
	{
		this.ClusterMembers = new ArrayList<Iris>();
	}

	@Override
	public String toString() {
		String toPrintString = "-----------------------------------CLUSTER START------------------------------------------" + System.getProperty("line.separator");

		for(Iris i : this.ClusterMembers)
		{
			toPrintString += i.toString() + System.getProperty("line.separator");
		}
		toPrintString += "-----------------------------------CLUSTER END-------------------------------------------" + System.getProperty("line.separator");

		return toPrintString;
	}

	public Iris calculateClusterMean() {
		float sepalLength_sum, sepalWidth_sum,petalLength_sum, petalWidth_sum;
		sepalLength_sum = sepalWidth_sum = petalLength_sum = petalWidth_sum = 0;

		for (Iris iris: ClusterMembers) {
			sepalLength_sum += iris.Sepal_Length;
			sepalWidth_sum += iris.Sepal_Width;
			petalLength_sum += iris.Petal_Length;
			petalWidth_sum += iris.Petal_Width;
		}
		int size = ClusterMembers.size();

		Iris clusterMean = new Iris(sepalLength_sum/size,
				sepalWidth_sum/size,
				petalLength_sum/size,
				petalWidth_sum/size,
				"ClusterMean");

		return clusterMean;
	}

	public void setClusterMean(Iris clusterMean) {
		ClusterMean = clusterMean;
	}

	@Override
	/**
	 * Used to determine whether two ItemSet objects are equal
	 */
	public boolean equals( Object o ) {
		if (!(o instanceof KMeanCluster)) {
			return false;
		}
		KMeanCluster other = (KMeanCluster) o;
		if (other.ClusterMembers.size() != this.ClusterMembers.size()) {
			return false;
		}
		int size = ClusterMembers.size();
		for (int i = 0; i < size; i++) {
			if (!ClusterMembers.contains(other.ClusterMembers.get(i))) {
				return false;
			}
		}
		return true;
	}

}
