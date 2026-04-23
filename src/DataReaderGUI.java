import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.GridLayout;

public class DataReaderGUI extends JFrame {
    public DataReaderGUI() {
        
        setTitle("Raccoonz DDR");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout( new BorderLayout() );
        setVisible( true );

        //raccoonz label
        JLabel  racclab = new JLabel("© Raccoonz Software  ", SwingConstants.RIGHT);
        racclab.setFont( new java.awt.Font("Serif", java.awt.Font.ITALIC , 9 ) );
        add(racclab, BorderLayout.SOUTH);

        //top label
        JLabel topLabel = new JLabel("Discord Data Reader", SwingConstants.CENTER);
        topLabel.setFont( new java.awt.Font("Serif", java.awt.Font.BOLD , 18 ) );
        add(topLabel, BorderLayout.NORTH);


        //main data panel + button
        JPanel dataPanel = new JPanel( new GridLayout(9, 1));
        JPanel searchPanel = new JPanel( new BorderLayout() );

        JLabel totalMessagesLabel = new JLabel("   Total messages: ");
        JLabel totalWordsLabel = new JLabel("   Total words: ");
        JTextField searchWordField = new JTextField("");
        JButton searchWordButton = new JButton("search");
        JLabel searchWordLabel = new JLabel("   Word search: ");
        JLabel firstTimestampLabel = new JLabel("   Latest timestamp: ");
        JLabel latestTimestampLabel = new JLabel("   First timestamp: ");
        JLabel totalDaysLabel = new JLabel("   Total days: ");
        JLabel timeElapsedLabel = new JLabel("   Time elapsed: " + " ms");
        
        searchPanel.add(searchWordField, BorderLayout.CENTER);
        searchPanel.add(searchWordButton, BorderLayout.EAST);
        
        dataPanel.add(totalMessagesLabel);
        dataPanel.add(totalWordsLabel);
        dataPanel.add(searchWordLabel);
        dataPanel.add(searchPanel);
        dataPanel.add(firstTimestampLabel);
        dataPanel.add(latestTimestampLabel);
        dataPanel.add(totalDaysLabel);
        dataPanel.add(timeElapsedLabel);

        
        JButton raccbutton = new JButton("select file");
        raccbutton.setPreferredSize( new java.awt.Dimension(100, 50) );
        dataPanel.add(raccbutton, BorderLayout.SOUTH);
        add(dataPanel, BorderLayout.CENTER);
        setSize(300, 400);
        
        FileSelecter fileSelecter = new FileSelecter();
        raccbutton.addActionListener( e -> {
            fileSelecter.selectFile();
        });
        
        JsonReader jsonReader = new JsonReader();
        GeneralData generalJSONdata = jsonReader.getGeneralData(fileSelecter.getChoosenFile());
        
        totalMessagesLabel.setText("   Total messages: " + generalJSONdata.getTotalMessages());
        totalWordsLabel.setText("   Total words: " + generalJSONdata.gettotalWords());
        firstTimestampLabel.setText("   Latest timestamp: " + generalJSONdata.getFirstTimestamp());
        latestTimestampLabel.setText("   First timestamp: " + generalJSONdata.getLatestTimestamp());
        totalDaysLabel.setText("   Total days: " + generalJSONdata.getTotalDays());
        timeElapsedLabel.setText("   Time elapsed: " + generalJSONdata.gettimeElapsed() + " ms");
        searchWordButton.addActionListener( e2 -> {
            String wordToSearch = searchWordField.getText();
            int wordCount = jsonReader.getAmountInJson(fileSelecter.getChoosenFile(), wordToSearch);
            searchWordLabel.setText("   Word search: '" + wordToSearch + "' was found " + wordCount + " times");
        });
    }
}
