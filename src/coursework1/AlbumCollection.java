package coursework1;
import java.util.*;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AlbumCollection {
    //class variables:
    private List<Album> albums = new ArrayList<>();
    
    //constructors:
    //reads in a file from a location specified, splits each line into a List of strings
    //detects the line of the next album and then puts the previous album into an album object
    //storing it in a list of objects
    AlbumCollection(String fileLoc) throws IOException{ 
        BufferedReader in = new BufferedReader(new FileReader(fileLoc));
        String str;
        List<String> albumList = new ArrayList();
        albumList.add(in.readLine());
        str = in.readLine();
        
        do{
            //trying to make sure that we dont ever mistakenly add an album title 
            //as a song, as long as an artist or album doesn't start with a number
            //and contains " - " surronded with spaces
            //potential bug as someone may enter a text file in the wrong format
            if(Character.isDigit(str.charAt(0)) && str.contains(" - ")){
                albumList.add(str);
            }
            else{
                albums.add(new Album(albumList));
                albumList.clear();
                albumList.add(str);
            }
        }while((str = in.readLine()) != null);
        
        albums.add(new Album(albumList));
        albums.sort(null);
        
    }
    AlbumCollection() throws IOException{ 
        BufferedReader in = new BufferedReader(new FileReader(getFile()));
        String str;
        List<String> albumList = new ArrayList();
        albumList.add(in.readLine());
        str = in.readLine();
        
        do{
            if(Character.isDigit(str.charAt(0)) && str.contains(" - ")){
                albumList.add(str);
            }
            else{
                albums.add(new Album(albumList));
                albumList.clear();
                albumList.add(str);
            }
        }while((str = in.readLine()) != null);
        
        albums.add(new Album(albumList));
        albums.sort(null);
        
    }
    //Methods:
    //get a file to open using JFileChooser
    public File getFile() {
        File filePath = null;
        JFileChooser fileBrowser = new JFileChooser();
        FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        fileBrowser.setFileFilter(txtFilter);
        fileBrowser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        fileBrowser.setDialogTitle("Please select an album text file to import.");
        int result = fileBrowser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            filePath = fileBrowser.getSelectedFile();
        }
        return filePath;
    }
    //adds up all durations in tracks objects from a filterd list of albums by artist
    public void totalPlaytimeByArtist(String artist){
        List<Album> albumsByArtist = sortByArtist(artist);
        Duration artistLength = new Duration();
        for(Album albumsByArtistIndex : albumsByArtist) {
            artistLength.add(albumsByArtistIndex.totalAlbumLengthInt());
        }
        System.out.println("The total length of all '" + artist + "' albums in the collection is: " + artistLength.toString());
    }
    public void displayByArtist(String artist){
        List<Album> albumsByArtist = sortByArtist(artist);
        displayCollection(albumsByArtist);
    }
    private List<Album> sortByArtist(String artist){
        List<Album> albumsByArtist = new ArrayList<>();
        for(Album albumIndex : albums) {
            if (albumIndex.albumArtistToString().equalsIgnoreCase(artist)) {
                albumsByArtist.add(albumIndex);
            }
        }
        return albumsByArtist;
    }
    
    //finds the album with the largest list of tracks in the database
    public void albumLargestNoOfTracks(){
        int largestIndex = 0;
        int largestAmount = 0;
        for(int i = 0; i<albums.size(); i++){
            if(albums.get(i).totalAmountOfTracks() > largestAmount){
                largestIndex = i;
                largestAmount = albums.get(i).totalAmountOfTracks();
            }
        }
        System.out.print("The largest album is:- " + albums.get(largestIndex).getAlbumArtistNameFormat() + " >> ");
        System.out.println("Total Tracks:" + largestAmount);
    }
    
    //iterates through all albums and compares duration objects, stores largest one until the end of the database
    //and then prints to console
    public void longestTrack() {
        Album longest = albums.get(0);
        for(Album albumIndex : albums){
            if(albumIndex.longestTrack().trackDuration.totalSecs() > longest.longestTrack().trackDuration.totalSecs()){
                longest = albumIndex;
            }
        }
        System.out.print("The longest Track is in: " + longest.getAlbumArtistNameFormat() + " >> ");
        System.out.println(longest.longestTrack().trackDuration.toString() + " - " + longest.longestTrack().nameToString());
    }
    
    
   //displays the album collection into console
    public void displayCollection() {
        for(Album albumIndex : albums){
            albumIndex.printTotalAlbum();
        }
    }
    public void displayCollection(List<Album> input) {
        for(Album albumIndex : input){
            albumIndex.printTotalAlbum();
        }
    }
    
    //tests
    public static void main(String[] args){
        AlbumCollection test1 = null;
        try{
            test1 = new AlbumCollection(System.getProperty("user.dir") + "\\src\\coursework1\\albums.txt");
            test1.displayCollection();
            test1.totalPlaytimeByArtist("pINk FLoyd");
            test1.albumLargestNoOfTracks();
            test1.longestTrack();
        }
        catch(Exception e){
            System.err.println("Caught Exception: " + e.getMessage());
        }
        
    }
    
}
