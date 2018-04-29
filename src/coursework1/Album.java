package coursework1;
import java.util.*;

public class Album implements Comparable<Album>{
    
    //class variables:
    private List<Track> trackList = new ArrayList<>();
    private String albumName;
    private String albumArtist;
    
    //constructors:
    public Album(String albumInput, String artistInput){
        albumName = albumInput;
        albumArtist = artistInput;
    }
    public Album(String[] input){
        String[] names = input[0].split(" : ");
        albumArtist = names[0];
        albumName = names[1];
        for(int i = 1; i < input.length; i++){
            addTrack(input[i]);
        }
    }
    public Album(List<String> input){
        String[] names = input.get(0).split(" : ");
        albumArtist = names[0];
        albumName = names[1];
        for(int i = 1; i < input.size(); i++){
            addTrack(input.get(i));
        }
    }
    
    //methods:
    //getters
    public String albumArtistToString(){
        return albumArtist;
    }
    public String albumNameToString(){
        return albumName;
    }
    public int totalAmountOfTracks(){
        return trackList.size();
    }
    //initializes a duration object to add up all the tracks lengths 
    private Duration totalAlbumLength(){
        Duration output = new Duration();
        for(Track trackIndex : trackList){
            output.add(trackIndex.trackDuration);
        }
        return output;
    }
    public String totalAlbumLengthStr(){
        return  totalAlbumLength().toString();
    }
    public int totalAlbumLengthInt(){
        return totalAlbumLength().totalSecs();
    }
    public Track longestTrack(){
        Track longest = trackList.get(0);
        for(int i = 1; i < trackList.size(); i++){
            if(trackList.get(i).trackDuration.totalSecs() > longest.trackDuration.totalSecs()){
                longest = trackList.get(i);
            }
        }
        return longest;
    }
    public void printTotalAlbum(){
        System.out.println(getAlbumArtistNameFormat());
        for(Track trackIndex : trackList){
            System.out.println(trackIndex.trackDuration.toString() + " - " + trackIndex.nameToString());
        }
    }
    public String getAlbumArtistNameFormat() {
        String output = albumArtistToString() + " : " + albumNameToString();
        return output;
    }
    
    //setters
    public void addTrack(String trackName, String trackDuration){
        trackList.add(new Track(trackName, trackDuration));
    }
    public void addTrack(String track){
        trackList.add(new Track(track));
    }
    public void removeTrack(int index){
        trackList.remove(index);
    }
    
    //natural order
    //this compares album's artist name and if equal the album's name and returns the interfaces expected defaults
    @Override
    public int compareTo(Album other){
        int output = compareToArtist(other);
        if(output == 0){
            return compareToName(other);
        }
        else {
            return output;
        }
    }
    public int compareToArtist(Album other){
        return albumArtist.compareToIgnoreCase(other.albumArtistToString());
    }
    public int compareToName(Album other){
        return albumName.compareToIgnoreCase(other.albumNameToString());
    }
    
    //tests
    public static void main(String[] args){
        
        String[] inputArray = {"The Jimi Hendrix Experience : Are you Experienced?",
                            "0:03:22 - Foxy Lady",
                            "0:03:46 - Manic Depression",
                            "0:03:53 - Red House",
                            "0:02:35 - Can You See Me",
                            "0:03:17 - Love or Confusion",
                            "0:03:58 - I Don't Live Today",
                            "0:03:14 - May This Be Love",
                            "0:02:47 - Fire",
                            "0:06:50 - Third Stone from the Sun",
                            "0:02:53 - Remember",
                            "0:04:17 - Are You Experienced?",
                            "0:03:30 - Hey Joe (Billy Roberts)",
                            "0:03:36 - Stone Free",
                            "0:02:51 - Purple Haze",
                            "0:03:15 - 51st Anniversary",
                            "0:03:20 - The Wind Cries Mary",
                            "0:03:32 - Highway Chile",
        };
        
        List<String> input = new ArrayList<String>(Arrays.asList(inputArray));
        
        Album test1 = new Album(input);
        
        test1.printTotalAlbum();
    }
}
