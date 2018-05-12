import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.lang.*;

import java.net.*;
import java.io.*;


public class AltitudeDisplay extends JPanel {
    private static final long serialVersionUID = 42l;

    /* TODO create GUI elements to display altitude and related
            information
    */
  
   JTextField currentAltitude;
  
    public AltitudeDisplay() {
      //GUI elements
       super( new FlowLayout(FlowLayout. LEFT, 5,0));
       setBorder(BorderFactory.createTitledBorder("Altitude Display"));
       currentAltitude = new JTextField(10);
       add(this.currentAltitude);
    }

    public void setAltitude(float altitude) {
        //puts the altitude to what the current altitude is
        currentAltitude.setText(String.valueOf(altitude));
          if(altitude>250){
        currentAltitude.setBackground(Color.GREEN);
    }
      else if(altitude>25){
        currentAltitude.setBackground(Color.YELLOW);
    }
      else{
        currentAltitude.setBackground(Color.RED);
    }
    }
}