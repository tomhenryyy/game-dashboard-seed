import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.lang.*;

import java.net.*;
import java.io.*;


public class FuelDisplay extends JPanel {
    private static final long serialVersionUID = 42l;

    /* TODO create GUI elements to display fuel state
    and related information
     */
    JTextField currentFuel;
    public FuelDisplay() {
        //GUI elements
        super( new FlowLayout(FlowLayout. LEFT, 5,0));
        setBorder(BorderFactory.createTitledBorder("Fuel Display"));
        currentFuel = new JTextField(10);
        add(this.currentFuel);
    }

    void setFuel(float fuel) {
        //puts the fuel to what the current fuel is
        currentFuel.setText(String.valueOf(fuel));
        if(fuel>40){
        currentFuel.setBackground(Color.GREEN);
    }
      else if(fuel>25){
        currentFuel.setBackground(Color.YELLOW);
    }
      else{
        currentFuel.setBackground(Color.RED);
    }
    }
}
