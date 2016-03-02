package itu.datamining.week5.kMedoid;
import itu.datamining.week5.data.Iris;

import java.util.ArrayList;

public class KMedoidCluster {

public ArrayList<Iris> ClusterMembers;
public Iris Medoid;
	
public KMedoidCluster(Iris medoid)
{
	this.ClusterMembers = new ArrayList<Iris>();
	this.Medoid = medoid;
}
	
	@Override
	public String toString() {
		String toPrintString = "-----------------------------------CLUSTER START------------------------------------------" + System.getProperty("line.separator");
		toPrintString += "Medoid: "+this.Medoid.toString() + System.getProperty("line.separator");
		for(Iris i : this.ClusterMembers)
		{
			toPrintString += i.toString() + System.getProperty("line.separator");
		}
		toPrintString += "-----------------------------------CLUSTER END-------------------------------------------" + System.getProperty("line.separator");
		
		return toPrintString;
	}
	
}
