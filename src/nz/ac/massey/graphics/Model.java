//Jumaah, Bilal, 12232659, Assignment 3, 159.235
//Jackson, Jonny, 14089365, Assignment 3, 159.235
package nz.ac.massey.graphics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import nz.ac.massey.graphics.data.Lines;
import nz.ac.massey.graphics.data.Vertices;

/**
 * This class can load model data from files and manage it.
 */
class Model {

  private int m_numVertices;
  private int m_numTriangles;

  // the largest absolute coordinate value of the untransformed model data
  private float m_maxSize;

  private Model() {
  }
  //////////////////////////////////////////////////////////////////////////////
  /**
   * Creates a {@link Model} instance for the data in the specified file.
   *
   * @param file The file to load.
   * @return The {@link Model}, or null if an error occurred.
   */
  public static Model loadModel(final File file) {
    final Model model = new Model();

    // read the data from the file
    if (!model.loadModelFromFile(file)) {
      return null;
    }

    return model;
  }
  
  //////////////////////////////////////////////////////////////////////////////
  ArrayList<Vertices> verList = new ArrayList<Vertices>(); //creating array lists to hold the objects 
  ArrayList<Lines> triList = new ArrayList<Lines>();       //of the data structure
  
  /**
   * Reads model data from the specified file.
   *
   * @param file The file to load.
   * @return True on success, false otherwise.
   */

  protected boolean loadModelFromFile(final File file) {
    m_maxSize = 0.f;

    try (final Scanner scanner = new Scanner(file)) {
      // the first line specifies the vertex count
      m_numVertices = scanner.nextInt();

      // read all vertex coordinates
      float x, y, z;

      for (int i = 0; i < m_numVertices; ++i) {
        // advance the position to the beginning of the next line
        scanner.nextLine();

        // read the vertex coordinates
        x = scanner.nextFloat();
        y = scanner.nextFloat();
        z = scanner.nextFloat();

        // TODO store the vertex data
        
        verList.add(new Vertices (x, y, z)); //adding a new object of vertices (1 row) to the array list each time the loop go thru
        
        // determine the max value in any dimension in the model file
        m_maxSize = Math.max(m_maxSize, Math.abs(x));
        m_maxSize = Math.max(m_maxSize, Math.abs(y));
        m_maxSize = Math.max(m_maxSize, Math.abs(z));
      }
      //////////////////////////////////////////////////////////////////////////////
      // the next line specifies the number of triangles
      scanner.nextLine();
      m_numTriangles = scanner.nextInt();

      // read all polygon data (assume triangles); these are indices into
      // the vertex array
      int v1, v2, v3;
      
      for (int i = 0; i < m_numTriangles; ++i) {
        scanner.nextLine();

        // the model files start with index 1, we start with 0
        v1 = scanner.nextInt()- 1;
        v2 = scanner.nextInt()- 1;
        v3 = scanner.nextInt()- 1;

        // store the triangle data in a suitable data structure as objects
        triList.add(new Lines (v1, v2, v3));
        
      }
      
      //////////////////////////////////////////////////////////////////////////////
    } catch (FileNotFoundException e) {
      System.err.println("No such file " + file.toString() + ": " + e.getMessage());
      return false;
    } catch (NoSuchElementException e) {
      System.err.println("Invalid file format: " + e.getMessage());
      return false;
    } catch (Exception e) {
      System.err.println("Something went wrong while reading the" + " model file: " + e.getMessage());
      e.printStackTrace();
      return false;
    }
    //////////////////////////////////////////////////////////////////////////////
    System.out.println("Number of vertices in model: " + m_numVertices);
    System.out.println("Number of triangles in model: " + m_numTriangles);
   
    
    return true;
  }

  /**
   * Returns the largest absolute coordinate value of the original,
   * untransformed model data.
   */

  public float getMaxSize() {
    return m_maxSize;
  }
}