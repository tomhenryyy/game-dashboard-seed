import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.border.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class LanderDash extends JFrame implements Runnable {
    public static final long serialVersionUID = 2L;
    public static void main ( String[] args ) throws UnknownHostException {
        SwingUtilities.invokeLater( new Runnable() {
                public void run() {
                    new LanderDash();
                }
            });
    }
    //Information to go on dashboard
    //float currentFuelLevel;
    //float altitude;
   // float orientation;

    //Panel to display IP and port numnber
    DatagramPanel connection = new DatagramPanel();
    FuelDisplay fuelDisplay = new FuelDisplay();
    AltitudeDisplay altitudeDisplay = new AltitudeDisplay();
    OrientationDisplay orientationDisplay = new OrientationDisplay();
    ThrottleDisplay throttleDisplay = new ThrottleDisplay();

    JPanel displayPanel = new JPanel(new FlowLayout());
    public LanderDash(){
        super("Lunar Lander Dashboard");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(
            new BoxLayout(getContentPane(),BoxLayout.Y_AXIS) );
        //adding pannels to the window 
        add( connection );
        add(displayPanel);
        displayPanel.add(fuelDisplay);
        displayPanel.add(altitudeDisplay);
        displayPanel.add(orientationDisplay);
        displayPanel.add(throttleDisplay);
        pack();
        setVisible(true);
        (new Thread(this)).start();
    }

    public void run(){
        try {
            InetAddress address = InetAddress.getLocalHost();
            //ip address is listening for incoming connectoins on the port number             
            int portnumber = 65250;
            DatagramSocket socket = new DatagramSocket(portnumber, address);
            connection.setAddress((InetSocketAddress)socket.getLocalSocketAddress());
            while(true){
                // set up socket for reception
                if(socket!=null){
                    //start with fresh datagram packet
                    byte[] buffer = new byte[1024];
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    socket.receive( packet );
                    /* extract message and pick appart into
                    lines and key:value pairs
                     */
                    String message = new String(packet.getData());
                    String[] lines = message.trim().split("\n");
                    for(String l : lines){
                        String[] pair = l.split(":");
                        switch(pair[0]){
                            case "fuel" : fuelDisplay.setFuel(Float.parseFloat(pair[1]));
                            break;
                            case "altitude": altitudeDisplay.setAltitude(Float.parseFloat(pair[1]));
                            break;
                            case "orientation" : orientationDisplay.setOrientation(Float.parseFloat(pair[1]));
                            break;
                            case "throttle" : throttleDisplay.setCurrentThrottle(Float.parseFloat(pair[1]));
                            break;
                        }
                    }
                }
            }
        }
        catch(Exception e) {
            System.err.println(e);
            System.err.println("in LanderDash.run()");
            System.exit(-1);

        }
    }
}