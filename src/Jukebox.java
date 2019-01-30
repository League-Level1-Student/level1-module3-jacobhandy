
/*
 *    Copyright (c) The League of Amazing Programmers 2013-2018
 *    Level 1
 */

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import javazoom.jl.player.advanced.AdvancedPlayer;

/* 1. Download the JavaZoom jar from here: http://bit.ly/javazoom
 * 2. Right click your project and add it as an External JAR (Under Java Build Path > Libraries).*/

public class Jukebox implements Runnable, MouseListener {
	JLabel Song1;
	Song song;
    JLabel Song2;
	public void createUI() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		JPanel panel = new JPanel();
		frame.add(panel);
		Song1 = loadImage("robbieRotten.jpg");
		Song1.addMouseListener(this);
		panel.add(Song1);
        Song2 = loadImage("pokemon-pikachu.jpg");
        Song2.addMouseListener(this);
        panel.add(Song2);
		frame.pack();

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Jukebox());
		Jukebox jukebox = new Jukebox();
		jukebox.createUI();
	}

	public void run() {

		// 3. Find an mp3 on your computer or on the Internet.
		// 4. Create a Song
		
		// 5. Play the Song
		//song.play();
		// Song sm64 = new Song("Super Mario 64 - Dire Dire Docks.mp3");
		// Song pokemon = new Song("Pokemon - Black and White Music - Rival
		// Battle.mp3");
		// Song halo = new Song("Halo 2 - Volume 1 - 01 - Halo Theme Mjolnir Mix.mp3");
		/*
		 * 6. Create a user interface for your Jukebox so that the user can to
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * choose which song to play. You can use can use a different button for each
		 * song, or a picture of the album cover. When the button or album cover is
		 * clicked, stop the currently playing song, and play the one that was selected.
		 */

	}

	/* Use this method to add album covers to your Panel. */
	JLabel loadImage(String fileName) {
		URL imageURL = getClass().getResource(fileName);
		Icon icon = new ImageIcon(imageURL);
		return new JLabel(icon);
	}

	class Song {

		private int duration;
		private String songAddress;
		private AdvancedPlayer mp3Player;
		private InputStream songStream;

		/**
		 * Songs can be constructed from files on your computer or Internet addresses.
		 * 
		 * Examples: <code> 
		 * 		new Song("everywhere.mp3"); 	//from default package 
		 * 		new Song("/Users/joonspoon/music/Vampire Weekend - Modern Vampires of the City/03 Step.mp3"); 
		 * 		new	Song("http://freedownloads.last.fm/download/569264057/Get%2BGot.mp3"); 
		 * </code>
		 */
		public Song(String songAddress) {
			this.songAddress = songAddress;
		}

		public void play() {
			loadFile();
			if (songStream != null) {
				loadPlayer();
				startSong();
			} else
				System.err.println("Unable to load file: " + songAddress);
		}

		public void setDuration(int seconds) {
			this.duration = seconds * 100;
		}

		public void stop() {
			if (mp3Player != null)
				mp3Player.close();
		}

		private void startSong() {
			Thread t = new Thread() {
				public void run() {
					try {
						if (duration > 0)
							mp3Player.play(duration);
						else
							mp3Player.play();
					} catch (Exception e) {
					}
				}
			};
			t.start();
		}

		private void loadPlayer() {
			try {
				this.mp3Player = new AdvancedPlayer(songStream);
			} catch (Exception e) {
			}
		}

		private void loadFile() {
			if (songAddress.contains("http"))
				this.songStream = loadStreamFromInternet();
			else
				this.songStream = loadStreamFromComputer();
		}

		private InputStream loadStreamFromInternet() {
			try {
				return new URL(songAddress).openStream();
			} catch (Exception e) {
				return null;
			}
		}

		private InputStream loadStreamFromComputer() {
			try {
				return new FileInputStream(songAddress);
			} catch (FileNotFoundException e) {
				return this.getClass().getResourceAsStream(songAddress);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource().equals(Song1)) {
			song = new Song("weare1.mp3");
			song.play();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
