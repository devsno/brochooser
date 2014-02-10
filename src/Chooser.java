import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileOutputStream;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Chooser extends JFrame{

	JLabel textField;
	JButton getBroButton;
	JPanel panel;
	String listPath = "brochooserlist.txt";

	public Chooser(String title)  {
		textField = new JLabel("BroChooser 9000");
		getBroButton = new JButton("Get Random Bro");
		getBroButton.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				genChosenOne();
			}
		});
		textField.setFont(new Font("Times", 0, 200));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel = (JPanel) getContentPane();
		panel.setLayout(new BorderLayout());
		panel.setSize(900,700);
		setTitle(title);
		panel.add(textField);
		panel.add(getBroButton, BorderLayout.AFTER_LAST_LINE);
		pack();
		setVisible(true);
	}

	private void genChosenOne ()  {
		ArrayList<String> list = getList();
		Random rand = new Random();
		showList(list);

		showName(list.get(rand.nextInt(list.size())));
	} 

	private void showName (String name) {
		panel.removeAll();
		JLabel nameLabel;
		nameLabel = new JLabel(name);
		nameLabel.setFont(new Font("Times", 0, 200));
		panel.add(nameLabel);
		panel.add(getBroButton, BorderLayout.AFTER_LAST_LINE);
		pack();
		setVisible(true);
	}
	
	private void showList(ArrayList<String> list){
		for(String name : list) {
			showName(name);
		}
	}

	private void deleteList() {
		deleteList(listPath);
	}

	private void deleteList(String path) {
		new File(path).delete();
	}

	private ArrayList<String> getList(){
		FileReader dataFile;
		ArrayList<String> list = new ArrayList<String>();

		try {

			try {
				//Option one: custom list
				dataFile = new FileReader(listPath);
			} 
			catch (FileNotFoundException e){
				//Fill the list to choose from with the list of brothers
				InputStream in = this.getClass().getResourceAsStream("brothers.txt");
				FileOutputStream out = new FileOutputStream(listPath);
				byte[] buffer = new byte[1024];
				int len = in.read(buffer);
				while (len != -1) {
					out.write(buffer, 0, len);
					len = in.read(buffer);
				}
				out.close();

				//Option two: full list of brothers
				dataFile = new FileReader(listPath);
			}

			BufferedReader bufferedReader = new BufferedReader(dataFile);
			String currentLine = bufferedReader.readLine();

			list.add(currentLine);
			while(currentLine != null) {
				list.add(currentLine);
				currentLine = bufferedReader.readLine();
			}

			bufferedReader.close();
		} 
		catch (IOException e) {
			System.err.println("A error occured reading file: " + e);
			e.printStackTrace();
			return null;
		}
		return list;

	}

}




