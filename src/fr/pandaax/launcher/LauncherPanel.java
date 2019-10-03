package fr.pandaax.launcher;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fr.litarvan.openauth.AuthenticationException;
import fr.theshark34.openlauncherlib.LaunchException;
import fr.theshark34.openlauncherlib.util.Saver;
import fr.theshark34.openlauncherlib.util.ramselector.RamSelector;
import fr.theshark34.swinger.Swinger;
import fr.theshark34.swinger.event.SwingerEvent;
import fr.theshark34.swinger.event.SwingerEventListener;
import fr.theshark34.swinger.textured.STexturedButton;
import fr.theshark34.swinger.textured.STexturedProgressBar;

@SuppressWarnings("serial")
public class LauncherPanel extends JPanel implements SwingerEventListener {

    private static Saver saver = new Saver(new File(LauncherMain.UZ_DIR, "\\config\\" + "launcher-settings.txt"));
    public final File SAVER_DIR = LauncherMain.UZ_INFOS.getGameDir();
	private Image background = Swinger.getResource("background.png");
	private JTextField usernameField = new JTextField(saver.get("username"));
	private JPasswordField passwordField = new JPasswordField(saver.get("password"));
	private STexturedButton closeButton = new STexturedButton(Swinger.getResource("close.png"));
	private STexturedButton reduceButton = new STexturedButton(Swinger.getResource("red.png"));
	private STexturedButton optionsButton = new STexturedButton(Swinger.getResource("opt.png"));
	private RamSelector ramSelector = new RamSelector(new File(LauncherMain.UZ_DIR, "/config/" + "ram.txt"));
	private STexturedButton playButton = new STexturedButton(Swinger.getResource("jouer.png"));
	private STexturedProgressBar progressBar = new STexturedProgressBar(Swinger.getResource("bar.png"), Swinger.getResource("bar_loaded.png"));
	private JLabel infoLabel = new JLabel("", 0);
	//FONTS
    Font font1 = new Font("SansSerif", Font.PLAIN, 20);
    Font font2 = new Font("SansSerif", Font.PLAIN, 14);
    Font font3 = new Font("SansSerif", Font.PLAIN, 16);
	
	public LauncherPanel() {
		setLayout(null);
		setBackground(Swinger.TRANSPARENT);
		
        usernameField.setOpaque(false);
        usernameField.setBorder(null);
        usernameField.setBounds(150, 340, 350, 100);
        usernameField.setForeground(Color.WHITE);
        usernameField.setFont(font1);
        add(usernameField);
        
        passwordField.setOpaque(false);
        passwordField.setBorder(null);
        passwordField.setFont(font2);
        passwordField.setBounds(150, 360, 350, 289);
        passwordField.setForeground(Color.WHITE);
        add(passwordField);
        
        closeButton.setBounds(621, 18, 50, 32);
        closeButton.addEventListener(this);
        add(closeButton);
        
        reduceButton.setBounds(567, 18, 50, 32);
        reduceButton.addEventListener(this);
        add(reduceButton);

        optionsButton.setBounds(592, 50, 50, 32);
        optionsButton.addEventListener(this);
        add(optionsButton);
        

        playButton.setBounds(215,569);
        playButton.addEventListener(this);
        add(playButton);
        
        infoLabel.setForeground(Color.WHITE);
        infoLabel.setBounds(10, 675, 700, 30);
        add(infoLabel);
        
        progressBar.setBounds(0,675, 700, 30);
        add(progressBar);
	}
	
	
	
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
	
	
    public void onEvent(SwingerEvent e) {
        if (e.getSource() == closeButton) {
            WriteAccount();
            ramSelector.save();

            System.exit(0);
       } else if (e.getSource() == optionsButton) {
            ramSelector.display();  
       } else if (e.getSource() == playButton) {
          launch();
        } else if (e.getSource() == reduceButton) {
            LauncherFrame.getInstance().setState(1);
        }
    }
    
    public RamSelector getRamSelector() {
        return ramSelector;
    }
    public void setFieldsEnabled(boolean enabled) {
        usernameField.setEnabled(enabled);
        passwordField.setEnabled(enabled);
        playButton.setEnabled(enabled);
    }
    public STexturedProgressBar getProgressBar() {
        return progressBar;
    }

    public void setInfoText(String text) {
        infoLabel.setText(text);
    }
    
    public void WriteRam() {
        File folder = new File(new File(String.valueOf(SAVER_DIR)), "\\config\\");
        File userfile = new File(new File(SAVER_DIR + "\\config\\"), "ram.txt");
        if (folder.exists()) {
            if (!userfile.exists()) {
                try {
                    FileWriter checker = new FileWriter(new File(SAVER_DIR + "\\config\\" + "ram.txt"));

                    checker.close();
                } catch (Exception localException) {
                }
            }
        } else {
            folder.mkdirs();
            try {
                userfile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                FileWriter checker = new FileWriter(new File(SAVER_DIR + "\\config\\" + "ram.txt"));

                checker.close();
            } catch (Exception localException2) {
            }
        }
    }
    
    public void WriteAccount() {
        File folder = new File(new File(String.valueOf(SAVER_DIR)), "\\config\\");
        File userfile = new File(new File(SAVER_DIR + "\\config\\"), "launcher-settings.txt");
        if (folder.exists()) {
            if (userfile.exists()) {
                try {
                    FileWriter checker = new FileWriter(new File(SAVER_DIR + "\\config\\" + "launcher-settings.txt"));
                    checker.write("username=" + this.usernameField.getText());
                    checker.write("\npassword=" + this.passwordField.getText());

                    checker.close();
                } catch (Exception localException) {
                }
            } else {
                try {
                    userfile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    FileWriter checker = new FileWriter(new File(SAVER_DIR + "\\config\\" + "launcher-settings.txt"));
                    checker.write("username=" + this.usernameField.getText());
                    checker.write("\npassword=" + this.passwordField.getPassword());
                    checker.close();
                } catch (Exception localException1) {
                }
            }
        } else {
            folder.mkdirs();
            try {
                userfile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                FileWriter checker = new FileWriter(new File(SAVER_DIR + "\\config\\" + "launcher-settings.txt"));
                checker.write("username=" + this.usernameField.getText());
                checker.write("\npassword=" + this.passwordField.getPassword());

                checker.close();
            } catch (Exception localException2) {
            }
        }
    }
    public void launch() {
        setFieldsEnabled(false);
        if ((usernameField.getText().replaceAll(" ", "").length() == 0)
                || (passwordField.getText().length() == 0)) {
            JOptionPane.showMessageDialog(this, "Erreur, veuillez entrer un nom d'utilisateur et un mot de passe valide.",
                    "Erreur", 0);
            setFieldsEnabled(true);
            return;
        }
        Thread t = new Thread() {
            public void run() {
                try {
                    LauncherMain.auth(usernameField.getText(),
                            passwordField.getText());
                } catch (AuthenticationException e) {
                    JOptionPane.showMessageDialog(LauncherPanel.this,
                            "Erreur impossible de ce connecter : " + e.getErrorModel().getErrorMessage(), "Erreur",
                            0);
                    setFieldsEnabled(true);
                    return;
                }
                try {
                	LauncherMain.update();
                } catch (Exception e) {
                	LauncherMain.interruptThread();
                    JOptionPane.showMessageDialog(LauncherPanel.this,
                            "Impossible de mettre à jour le launcher.",
                            "Erreur", 0);
                    return;
                }
                WriteAccount();
                ramSelector.save();

                try {
                	LauncherMain.launch();
                } catch (LaunchException e) {
                    JOptionPane.showMessageDialog(LauncherPanel.this,
                            "Impossible de lancer le jeu!", "Erreur", 0);
                    setFieldsEnabled(true);
                }
            }
        };
        t.start();
    }
 }

	
	


