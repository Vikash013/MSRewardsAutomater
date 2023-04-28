import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RewardsAutomater {

    Font titleFont = new Font("Segoe", Font.BOLD + Font.ITALIC, 25);
    Font textFont = new Font("Segoe", Font.BOLD, 17);

    public static void main(String[] args) throws InterruptedException, URISyntaxException, IOException {

        new RewardsAutomater();

        /*AutomateSearches(); you can uncomment this
        and comment line 21 if you want the automater to run
        without the GUI opening
         */


    }

    public RewardsAutomater () throws InterruptedException {

        //the creation of the GUI

        ImageIcon logo = new ImageIcon(this.getClass().getResource("logo-icon.png"));

        ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("rewards.gif")); //storing the image inside an ImageIcon which then gets stored in a JLabel
        JLabel label = new JLabel(imageIcon);
        label.setSize(1040,585); //the size of the image

        JLabel titleLabel = new JLabel("Microsoft Rewards");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(50,40,240,50);
        label.add(titleLabel);

        JTextArea descriptionLabel = new JTextArea();
        descriptionLabel.setFont(textFont);
        descriptionLabel.setBackground(new Color(0xFF6C6D7C, true));
        descriptionLabel.setForeground(Color.WHITE);
        descriptionLabel.setBounds(50,90,240,400);
        descriptionLabel.setText("Microsoft Rewards is a way \nto earn points via completing \ndaily tasks and as a result, \nbuy rewards such as gift \ncards or use the points to\ndonate to various charities. " +
                "\n\nThis application is designed \nas a way to autocomplete \nthe search task that is \ncompletable every day " +
                "\n(which requires one to do \n30 unique searches). \n\nBy pressing the Start button, \nthis application will \nautomatically do that for you.");
        descriptionLabel.setEditable(false);
        label.add(descriptionLabel);

        JPanel panel = new JPanel();
        panel.setBounds(40,40,260,460);
        panel.setBackground(new Color(0xA56C6D7C, true));
        label.add(panel);

        JButton startButton = new JButton("Start");
        startButton.setFont(titleFont);
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(new Color(0xFF6C6D7C, true));
        startButton.setBounds(770,100,200,100);
        startButton.setFocusable(false);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    AutomateSearches();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                } catch (URISyntaxException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        label.add(startButton);

        JButton stopButton = new JButton("Stop");
        stopButton.setFont(titleFont);
        stopButton.setForeground(Color.WHITE);
        stopButton.setBackground(new Color(0xFF6C6D7C, true));
        stopButton.setBounds(770,250,200,100);
        stopButton.setFocusable(false);

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        label.add(stopButton);

        JFrame frame = new JFrame("Microsoft Rewards Automater");
        frame.setSize(1055,620);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setIconImage(logo.getImage());

        frame.add(label);

        frame.setVisible(true);

    }

    public static void AutomateSearches() throws InterruptedException, URISyntaxException, IOException {

        Scanner scanner = new Scanner(new File("dictionary.txt"));

        List <String> words = new ArrayList<>();

        while (scanner.hasNext()){
            words.add(scanner.nextLine());
        }
        //this will read all lines in a dictionary file (i.e. every word) and store it in a list


        Random random = new Random();

        Desktop desktop = Desktop.getDesktop();
        int delay = 150; //100ms = 0.15 seconds

        for (int i = 0; i < 30; i++){
            desktop.browse(new URI("https://www.bing.com/search?q=" + (words.get(random.nextInt(words.size()))) + "&qs=n&form=QBRE&sp=-1&ghc=1&pq=apple&sc=10-5&sk=&cvid=6CDF882F9D9A48EC871E406C7387A416&ghsh=0&ghacc=0&ghpl="));
            Thread.sleep(delay);
        }

    }

}

