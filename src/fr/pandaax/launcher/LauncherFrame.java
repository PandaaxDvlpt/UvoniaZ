package fr.pandaax.launcher;

import javax.swing.JFrame;

import fr.theshark34.swinger.Swinger;
import fr.theshark34.swinger.util.WindowMover;

@SuppressWarnings("serial")
public class LauncherFrame extends JFrame {
 private static LauncherFrame instance;
 private LauncherPanel launcherPanel;
 
 public LauncherFrame() {
	 // Fenetre
	 setTitle("UvoniaZ");
	 
	 setSize(700,700);
	 setUndecorated(true);
     setBackground(Swinger.TRANSPARENT);
     this.launcherPanel = new LauncherPanel();
     setDefaultCloseOperation(3);
     setLocationRelativeTo(null);
     setContentPane(this.launcherPanel = new LauncherPanel());
     setIconImage(Swinger.getResource("icon.png"));
     WindowMover mover = new WindowMover(this);
     addMouseListener(mover);
     addMouseMotionListener(mover);
     
     setVisible(true);
    
 	}
 	
 	public static void main(String[] args) {
 		Swinger.setSystemLookNFeel();
 		Swinger.setResourcePath("/fr/pandaax/launcher/rc/");
 		
 		instance = new LauncherFrame();
 	}
 public static LauncherFrame getInstance() {
     return instance;
 }

 public LauncherPanel getLauncherPanel() {
     return this.launcherPanel;
 }
 
}
