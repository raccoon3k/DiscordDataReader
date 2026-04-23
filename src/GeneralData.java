import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class GeneralData {
    //first & last timestamp, total messages, time elapsed
    private int totalMessages;
    private int totalWords;
    private double timeElapsed;
    private String firstTimestamp;
    private String latestTimestamp;


    public GeneralData() {
        totalMessages = 0;
        totalWords = 0;
        timeElapsed = 0;
        firstTimestamp = "00:00";
        latestTimestamp = "00:00";
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
