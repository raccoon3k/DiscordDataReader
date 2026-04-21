import java.io.File;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class GeneralData {
    //first & last timestamp, total messages, time elapsed
    int totalMessages;
    int totalWords;
    double timeElapsed;
    String firstTimestamp;
    String latestTimestamp;
    File fileToRead;

    public GeneralData() {
        totalMessages = 0;
        totalWords = 0;
        timeElapsed = 0;
        firstTimestamp = "00:00";
        latestTimestamp = "00:00";
        fileToRead = null;
    }


    public int getTotalMessages() {
        return this.totalMessages;
    }

    public void setTotalMessages(int totalMessages) {
        this.totalMessages = totalMessages;
    }

    public int gettotalWords() {
        return this.totalWords;
    }

    public void settotalWords(int totalWords) {
        this.totalWords = totalWords;
    }

    public double gettimeElapsed() {
        return timeElapsed;
    }

    public void settimeElapsed( double timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    public String getFirstTimestamp() {
        return this.firstTimestamp;
    }

    public void setFirstTimestamp(String firstTimestamp) {
        this.firstTimestamp = firstTimestamp;
    }

    public String getLatestTimestamp() {
        return this.latestTimestamp;
    }

    public void setLatestTimestamp(String latestTimestamp) {
        this.latestTimestamp = latestTimestamp;
    }

    public File getFileToRead() {
        return this.fileToRead;
    }

    public void setFileToRead(File fileToRead) {
        this.fileToRead = fileToRead;
    }

    public String getTotalDays() {
        String first = getFirstTimestamp();
        String latest = getLatestTimestamp();
        LocalDate firstDate = LocalDate.parse(first.split(" ")[0]);
        LocalDate latestDate = LocalDate.parse(latest.split(" ")[0]);
        return String.valueOf(ChronoUnit.DAYS.between(latestDate, firstDate));
    }

    @Override
    public String toString() {
        return "{" +
            " totalMessages='" + getTotalMessages() + "'" +
            ", totalWords='" + gettotalWords() + "'" +
            ", over= " + getTotalDays()+ " days" +
            ", firstTimestamp='" + getFirstTimestamp() + "'" +
            ", latestTimestamp='" + getLatestTimestamp() + "'" +
            " timeElapsed= " + gettimeElapsed() + "ms" +
            "}";
    }

}
