package itu.datamining.week5.kMean;
import java.util.ArrayList;

import itu.datamining.week5.data.Iris;

//ToDo: Compute cluster mean based on cluster members.
public class KMeanCluster {

	public ArrayList<Iris> ClusterMembers;
	public Iris clusterMean;

	public KMeanCluster()
	{
		this.ClusterMembers = new ArrayList<Iris>();
		clusterMean = null;
	}

	public Iris calcClusterMean(){
		float sepalLength_sum, sepalWidth_sum, petalLength_sum, petalWidth_sum;
		sepalLength_sum = sepalWidth_sum = petalLength_sum = petalWidth_sum = 0;

		for(Iris iris : ClusterMembers){
			sepalLength_sum += iris.Sepal_Length;
			sepalWidth_sum += iris.Sepal_Width;
			petalLength_sum += iris.Petal_Length;
			petalWidth_sum += iris.Petal_Width;
		}

		int clusterMemberCount = ClusterMembers.size();

		Iris clusterMean = new Iris(sepalLength_sum / clusterMemberCount,
				sepalWidth_sum / clusterMemberCount,
				petalLength_sum / clusterMemberCount,
				petalWidth_sum / clusterMemberCount,
				"ClusterMean");
		this.clusterMean = clusterMean;
		return clusterMean;
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

}
