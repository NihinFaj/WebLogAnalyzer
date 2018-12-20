/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader();
    }
    
    /**
     * Constructor to take in name of the file log to be
     * analyzed
     * @param fileName The name of the file to be analyzed
     */
    public LogAnalyzer(String fileName)
    {
        reader = new LogfileReader(fileName);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    // public void printHourlyCounts()
    // {
        // System.out.println("Hr: Count");
        // for(int hour = 0; hour < hourCounts.length; hour++) {
            // System.out.println(hour + ": " + hourCounts[hour]);
        // }
    // }
    
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        int hour = 0;
        while(hour < hourCounts.length) {
            System.out.println(hour + ": " + hourCounts[hour]);
            hour++;
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
    /**
     * Return the number of accesses recorded in the log 
     */
    public int numberOfAccesses()
    {
        int total = 0;
        for(int i = 0; i < hourCounts.length; i++) {
            total = total + hourCounts[i];
        }
        return total;
    }
    
    /**
     * Function to return the busiest hour
     * within the 24hours timeframe
     * @return busiestHr The busiest hour 
     */
    public int busiestHour()
    {
        int busiestHourValue = 0;
        int busiestHr = 0;
        for(int i = 0; i < hourCounts.length; i++) {
            if(hourCounts[i] > busiestHourValue) {
                busiestHourValue = hourCounts[i];
                busiestHr = i;
            }
        }
        return busiestHr;
    }
    
}
