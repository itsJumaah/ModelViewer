//Jumaah, Bilal, 12232659, Assignment 3, 159.235
//Jackson, Jonny, 14089365, Assignment 3, 159.235
package nz.ac.massey.graphics;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;


public class ModelViewer { 

  public static final int INIT_WIDTH = 1024;
  public static final int INIT_HEIGHT = 768;
  
  public float scale;
  public static ModelViewer m_Mod_V;

  private JFrame m_frame;

  private Canvas m_canvas;

  private JSlider m_sliderRotateX;
  private JSlider m_sliderRotateY;
  private JSlider m_sliderRotateZ;

  private JButton m_btnScaleUp;
  private JButton m_btnScaleDown;
  private JButton m_btnIncrX;
  private JButton m_btnDecrX;
  private JButton m_btnIncrY;
  private JButton m_btnDecrY;
  private JButton m_btnIncrZ;
  private JButton m_btnDecrZ;

  private JCheckBox m_chkRenderWireframe;
  private JCheckBox m_chkRenderSolid;
  private JCheckBox m_chkCullBackFaces;

  private JMenuItem m_menuOpenModelFile;

  private Model m_currentModel;

  //////////////////////////////////////////////////////////////////////////////
  private ChangeListener m_sliderChangeListener = new ChangeListener() {

    @Override
    public void stateChanged(ChangeEvent e) {
      final JSlider source = (JSlider) e.getSource();
      if (m_currentModel != null) {
        if (source == m_sliderRotateX) {
          // TODO
        } else if (source == m_sliderRotateY) {
          // TODO
        } else if (source == m_sliderRotateZ) {
          // TODO
        }
      }
    }
  };
  //////////////////////////////////////////////////////////////////////////////
  private ActionListener m_btnActionListener = new ActionListener() {
	  
	    @Override
	    public void actionPerformed(ActionEvent e) {
	      final Object source = e.getSource();
	      if (m_currentModel != null) {
	        // scale changes
	        if (source == m_btnScaleUp) {
	        //scale increment and updating the screen
	        	scale= scale*1.1f;
	        	m_canvas.repaint();
	        } else if (source == m_btnScaleDown) {
	        //scale decrement and updating the screen
	        	scale = scale*0.9f;
	        	m_canvas.repaint();
	        }
	        // translation changes
	        //////////////////////////////////////////////////////////////////////////////
	        else if (source == m_btnIncrX) {
	          if(m_currentModel != null) {
	                //get each X value increment it then set the incremented value back to X
	                for (int i = 0; i < m_currentModel.verList.size(); ++i) {
	                        float newX = m_currentModel.verList.get(i).getX() + 0.3f;
	                        m_currentModel.verList.get(i).setX(newX);
	                }
	                m_canvas.repaint();
	                //updates the screen
	          }
	          //////////////////////////////////////////////////////////////////////////////
	        } else if (source == m_btnDecrX) {
	                if(m_currentModel != null) {
	                //get each X value decrement it then set the decremented value back to X
	                for (int i = 0; i < m_currentModel.verList.size(); ++i) {
	                        float newX = m_currentModel.verList.get(i).getX() - 0.3f;
	                        m_currentModel.verList.get(i).setX(newX);
	                }
	                m_canvas.repaint();
	                //update the screen
	              }
	                //////////////////////////////////////////////////////////////////////////////
	        } else if (source == m_btnIncrY) {
	        	//get each Y value increment it then set the incremented value back to X
	                if(m_currentModel != null) {
	              
	                for (int i = 0; i < m_currentModel.verList.size(); ++i) {
	                        float newY = m_currentModel.verList.get(i).getY() + 0.3f;
	                        m_currentModel.verList.get(i).setY(newY);
	                }
	                m_canvas.repaint();
	                //updates teh screen
	              }
	                //////////////////////////////////////////////////////////////////////////////
	        } else if (source == m_btnDecrY) {
	        	//get each Y value decrement it then set the decremented value back to y
	                if(m_currentModel != null) {
	                
	                for (int i = 0; i < m_currentModel.verList.size(); ++i) {
                        float newY = m_currentModel.verList.get(i).getY() - 0.3f;
                        m_currentModel.verList.get(i).setY(newY);
	                }
	                m_canvas.repaint();
	               //update the screen
	              }
	                //////////////////////////////////////////////////////////////////////////////
	        } else if (source == m_btnIncrZ) {
	        	//get each z value increment it then set the incremented value back to z
	                if(m_currentModel != null) {
	                
	                for (int i = 0; i < m_currentModel.verList.size(); ++i) {
                        float newZ = m_currentModel.verList.get(i).getZ() + 0.3f;
                        m_currentModel.verList.get(i).setZ(newZ);
	                   
	                }
	                m_canvas.repaint();
	                //update teh screen
	              }
	                //////////////////////////////////////////////////////////////////////////////
	        } else if (source == m_btnDecrZ) {
	        	//get each z value decrement it then set the decremented value back to z
	                if(m_currentModel != null) {
	                
	                for (int i = 0; i < m_currentModel.verList.size(); ++i) {
                        float newZ = m_currentModel.verList.get(i).getZ() - 0.3f;
                        m_currentModel.verList.get(i).setZ(newZ);
	                      
	                }
	                m_canvas.repaint();
	                //update teh screen
	              }
	        }
	      }
	    }
	  };
	  //////////////////////////////////////////////////////////////////////////////
  private ActionListener m_chkActionListener = new ActionListener() {

    @Override
    public void actionPerformed(ActionEvent e) {
      final Object source = e.getSource();
      if (source == m_chkRenderWireframe) {
        
    	  m_canvas.changeWire(); //changes the wire boolean
    	  m_canvas.repaint(); //update the screen
      } else if (source == m_chkRenderSolid) {
        //changes solid boolean
    	  m_canvas.changeSolid();
    	  m_canvas.repaint(); //update teh screen
      } else if (source == m_chkCullBackFaces) {
        // changes the cull faces boolean
    	  m_canvas.changeCull();
    	  m_canvas.repaint(); //updates the screen
      }
    }
  };
  //////////////////////////////////////////////////////////////////////////////
  private ActionListener m_menuActionListener = new ActionListener() {

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == m_menuOpenModelFile) {
        final Model model = loadModelFile();
        if (model != null) {
          m_currentModel = model;
          m_canvas.setModel(model);       
          m_canvas.repaint(); //    updates teh screen after the model is loaded to make it appear
        }
      }
    }
  };

  //////////////////////////////////////////////////////////////////////////////
  private ModelViewer() {
	  
  }

  /**
   * Factory method for {@link ModelViewer}.
   */
  public static ModelViewer create() {
    final ModelViewer viewer = new ModelViewer();

    SwingUtilities.invokeLater(new Runnable() {

      @Override
      public void run() {
        viewer.createGui();
      }
    });

    return viewer;
  }
  //////////////////////////////////////////////////////////////////////////////
  /**
   * Creates the GUI. Must be called from the EDT.
   */
  private void createGui() {
    m_frame = new JFrame("Model Viewer");
    m_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    // setup the content pane
    final JPanel contentPane = new JPanel();
    contentPane.setLayout(new BorderLayout());
    m_frame.setContentPane(contentPane);

    final Border border = BorderFactory.createBevelBorder(BevelBorder.LOWERED);

    // setup the canvas and control panel
    m_canvas = new Canvas();
    m_canvas.setBorder(border);
    contentPane.add(m_canvas, BorderLayout.CENTER);
    final JComponent controlPanel = createControlPanel();
    controlPanel.setBorder(border);
    contentPane.add(controlPanel, BorderLayout.LINE_START);

    // add the menu
    final JMenuBar menuBar = new JMenuBar();
    m_frame.setJMenuBar(menuBar);
    final JMenu fileMenu = new JMenu("File");
    menuBar.add(fileMenu);
    m_menuOpenModelFile = new JMenuItem("Open");
    m_menuOpenModelFile.addActionListener(m_menuActionListener);
    fileMenu.add(m_menuOpenModelFile);
    //////////////////////////////////////////////////////////////////////////////
    // register a key event dispatcher to get a turn in handling all
    // key events, independent of which component currently has the focus
    KeyboardFocusManager.getCurrentKeyboardFocusManager()
        .addKeyEventDispatcher(new KeyEventDispatcher() {

          @Override
          public boolean dispatchKeyEvent(KeyEvent e) {
            switch (e.getKeyCode()) {
              case KeyEvent.VK_ESCAPE:
                System.exit(0);
                return true; // consume the event
              default:
                return false; // do not consume the event
            }
          }
        });

    m_frame.setSize(new Dimension(INIT_WIDTH, INIT_HEIGHT));
    m_frame.setVisible(true); 
  }
  //////////////////////////////////////////////////////////////////////////////
  /**
   * Creates and returns the control panel. Must be called from the EDT.
   */
  private JComponent createControlPanel() {
    final JPanel toolbar = new JPanel(new GridBagLayout());

    final GridBagConstraints gbcDefault = new GridBagConstraints();
    gbcDefault.gridx = 0;
    gbcDefault.gridy = GridBagConstraints.RELATIVE;
    gbcDefault.gridwidth = 2;
    gbcDefault.fill = GridBagConstraints.HORIZONTAL;
    gbcDefault.insets = new Insets(5, 10, 5, 10);
    gbcDefault.anchor = GridBagConstraints.FIRST_LINE_START;
    gbcDefault.weightx = 0.5;

    final GridBagConstraints gbcLabels =
        (GridBagConstraints) gbcDefault.clone();
    gbcLabels.insets = new Insets(5, 10, 0, 10);

    final GridBagConstraints gbcTwoCol =
        (GridBagConstraints) gbcDefault.clone();
    gbcTwoCol.gridwidth = 1;
    gbcTwoCol.gridx = 0;
    gbcTwoCol.insets.right = 5;

    GridBagConstraints gbc = null;

    // setup the rotation sliders
    m_sliderRotateX = new JSlider(JSlider.HORIZONTAL, 0, 360, 0);
    m_sliderRotateY = new JSlider(JSlider.HORIZONTAL, 0, 360, 0);
    m_sliderRotateZ = new JSlider(JSlider.HORIZONTAL, 0, 360, 0);
    m_sliderRotateX.setPaintLabels(true);
    m_sliderRotateY.setPaintLabels(true);
    m_sliderRotateZ.setPaintLabels(true);
    m_sliderRotateX.setPaintTicks(true);
    m_sliderRotateY.setPaintTicks(true);
    m_sliderRotateZ.setPaintTicks(true);
    m_sliderRotateX.setMajorTickSpacing(90);
    m_sliderRotateY.setMajorTickSpacing(90);
    m_sliderRotateZ.setMajorTickSpacing(90);
    m_sliderRotateX.addChangeListener(m_sliderChangeListener);
    m_sliderRotateY.addChangeListener(m_sliderChangeListener);
    m_sliderRotateZ.addChangeListener(m_sliderChangeListener);
    gbc = gbcDefault;
    toolbar.add(new JLabel("Rotation X:"), gbcLabels);
    toolbar.add(m_sliderRotateX, gbc);
    toolbar.add(new JLabel("Rotation Y:"), gbcLabels);
    toolbar.add(m_sliderRotateY, gbc);
    toolbar.add(new JLabel("Rotation Z:"), gbcLabels);
    toolbar.add(m_sliderRotateZ, gbc);

    m_btnScaleDown = new JButton("- size");
    m_btnScaleUp = new JButton("+ size");
    m_btnScaleDown.addActionListener(m_btnActionListener);
    m_btnScaleUp.addActionListener(m_btnActionListener);
    gbc = (GridBagConstraints) gbcTwoCol.clone();
    toolbar.add(m_btnScaleDown, gbc);
    gbc.gridx = 1;
    gbc.insets.left = gbc.insets.right;
    gbc.insets.right = gbcDefault.insets.right;
    toolbar.add(m_btnScaleUp, gbc);

    m_btnIncrX = new JButton("+ x");
    m_btnDecrX = new JButton("- x");
    m_btnIncrY = new JButton("+ y");
    m_btnDecrY = new JButton("- y");
    m_btnIncrZ = new JButton("+ z");
    m_btnDecrZ = new JButton("- z");
    m_btnIncrX.addActionListener(m_btnActionListener);
    m_btnDecrX.addActionListener(m_btnActionListener);
    m_btnIncrY.addActionListener(m_btnActionListener);
    m_btnDecrY.addActionListener(m_btnActionListener);
    m_btnIncrZ.addActionListener(m_btnActionListener);
    m_btnDecrZ.addActionListener(m_btnActionListener);

    gbc = (GridBagConstraints) gbcTwoCol.clone();
    toolbar.add(m_btnDecrX, gbc);
    gbc.gridx = 1;
    gbc.insets.left = gbc.insets.right;
    gbc.insets.right = gbcDefault.insets.right;
    toolbar.add(m_btnIncrX, gbc);

    gbc = (GridBagConstraints) gbcTwoCol.clone();
    toolbar.add(m_btnDecrY, gbc);
    gbc.gridx = 1;
    gbc.insets.left = gbc.insets.right;
    gbc.insets.right = gbcDefault.insets.right;
    toolbar.add(m_btnIncrY, gbc);

    gbc = (GridBagConstraints) gbcTwoCol.clone();
    toolbar.add(m_btnDecrZ, gbc);
    gbc.gridx = 1;
    gbc.insets.left = gbc.insets.right;
    gbc.insets.right = gbcDefault.insets.right;
    toolbar.add(m_btnIncrZ, gbc);

    // add check boxes
    gbc = gbcDefault;

    m_chkRenderWireframe = new JCheckBox("Render Wireframe");
    m_chkRenderWireframe.setSelected(true);
    m_chkRenderWireframe.addActionListener(m_chkActionListener);
    toolbar.add(m_chkRenderWireframe, gbc);

    m_chkRenderSolid = new JCheckBox("Render Solid");
    m_chkRenderSolid.setSelected(false);    //I set this to false because I didn't like it rendering solid to start off with.
    m_chkRenderSolid.addActionListener(m_chkActionListener);
    toolbar.add(m_chkRenderSolid, gbc);

    m_chkCullBackFaces = new JCheckBox("Cull Back Faces");
    m_chkCullBackFaces.setSelected(false); //change to false bcz i wanted it to not be true on the run
    m_chkCullBackFaces.addActionListener(m_chkActionListener);
    gbc = (GridBagConstraints) gbcDefault.clone();
    gbc.weighty = 1.;
    gbc.gridheight = GridBagConstraints.REMAINDER;
    toolbar.add(m_chkCullBackFaces, gbc);

    return toolbar;
  }
  //////////////////////////////////////////////////////////////////////////////
  /**
   * Displays a chooser dialog and loads the selected model.
   *
   * @return The model, or null if the user cancels the action or something
   * goes wrong.
   */
  private Model loadModelFile() {
    // show a file chooser for model files
    JFileChooser chooser = new JFileChooser("./");
    chooser.setFileFilter(new FileNameExtensionFilter(".dat model files", "dat"));
    int retVal = chooser.showOpenDialog(m_frame);
    if (retVal == JFileChooser.APPROVE_OPTION) {
      File file = chooser.getSelectedFile();

      // try to load the model from the selected file
      final Model model = Model.loadModel(file);

      if (model != null) {
    	  scale = 60;
      }

      return model;
    }

    return null;
  }
  //////////////////////////////////////////////////////////////////////////////
  public static void main(String[] args) {
	  System.out.println("--------------------------------------");
	  System.out.println("159.235 Assignment 3, Semester 2 2015 ");
	  System.out.println("Submitted by: Jumaah, Bilal,  12232659");
	  System.out.println("			and Jackson, Jonny, 14089365");
	  System.out.println("--------------------------------------");
	  
	  m_Mod_V = ModelViewer.create();
  }
}
