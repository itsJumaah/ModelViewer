//Jumaah, Bilal, 12232659, Assignment 3, 159.235
//Jackson, Jonny, 14089365, Assignment 3, 159.235
package nz.ac.massey.graphics.data;

import java.util.ArrayList;
import java.util.Arrays;

public class Sort {
	//This class written by Arno Leist from lecture slides
	public ArrayList<Vertices> verList = new ArrayList<Vertices>();
	
    // Program entry point
    public Sort(ArrayList<Vertices> VertArray)
    {
       
        final int totalSize = VertArray.size();
        
        float[] tempZ = new float[totalSize];
        
        final ZMeasure[] zmeas = new ZMeasure[totalSize];
        for (int i = 0; i < totalSize; ++i) {
            float z = VertArray.get(i).getZ();
            // Create one object of ZMeasure
            zmeas[i] = new ZMeasure(i, z);

        }

        // Sort all objects of the this array in ascending order of their
        // z-values
        Arrays.sort(zmeas);
        int tempi = 0;
        // print the sorted array
        for (int i = 0; i < totalSize; ++i) {
        	tempi = zmeas[i].num;
        	tempZ[tempi] = zmeas[i].z;
     
        	float x = VertArray.get(i).getX();
        	float y = VertArray.get(i).getY();
        	float z = tempZ[i];
        	verList.add(new Vertices (x, y, z));
        	
        }
    }

}

// Class to represent objects that can be put in a sortable array. We implement
// the Comparable interface to give objects the sortable property
class ZMeasure implements Comparable<ZMeasure> {

    // Some additional properties of objects of this class
    public int num;

    // Arrays of objects of this class will be sorted according to
    // this property
    public float z;

    public ZMeasure(int n, float z)
    {
        this.num = n;
        this.z = z;
    }

    // Implementing the interface requires that we provide this compare function
    @Override
    public int compareTo(final ZMeasure rhs)
    {
        if (z == rhs.z) return 0;
        else if (z < rhs.z) return -1;
        else return 1;
    }
}


