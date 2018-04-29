package coursework1;

public class Track implements Comparable<Track>{ 
    //class variables:    
    //duration is public because its another class that is used elsewhere but its encapsulated so its not a problem
    //to let it be dot referenced from the track classs
    public Duration trackDuration;
    private String trackName;
    
    //constructors:
    public Track(String nameInput, int hours, int mins, int secs) {
        trackName = nameInput;
        trackDuration = new Duration(hours, mins, secs);
    }
    public Track(String nameInput, String timeInput) {
        trackName = nameInput;
        trackDuration = new Duration(timeInput);
    }
    public Track(String input) {
        String[] splitInput = input.split(" - ");
        trackDuration = new Duration(splitInput[0]);
        trackName = splitInput[1];
    }
    
    //methods:
    //getter
    public String nameToString() {
        return trackName;
    }
    
    //natural order
    //this compares track name and if equal the track duration and returns the interfaces expected defaults
    @Override
    public int compareTo(Track other){  
        int output = compareToName(other);
        if(output == 0){
            return compareToInt(other);
        }
        return output;
    }
    public int compareToName(Track other){
        return trackName.compareToIgnoreCase(other.nameToString());
    }
    public int compareToInt(Track other){
        return trackDuration.compareTo(other.trackDuration);
    }
    
    //tests
    public static void main(String[] args){
        
        //test constructor and methods
        Track test1 = new Track("Knights Of Cydonia", 0, 6, 6);
        System.out.println(test1.nameToString());
        System.out.println(test1.trackDuration.toString());
        
        //test other constructor
        Track test2 = new Track("Supermassive Black Hole", "00:03:32");
        System.out.println(test2.nameToString());
        System.out.println(test2.trackDuration.toString());
        
        //test compareTo, result should return -1
        System.out.println(test1.compareTo(test2));
        
        //test compareTo with same named track, should return 1
        Track test3 = new Track("00:04:32 - Supermassive Black Hole");
        System.out.println(test3.nameToString());
        System.out.println(test3.trackDuration.toString());
        System.out.println(test3.compareTo(test2));
    }   
}
