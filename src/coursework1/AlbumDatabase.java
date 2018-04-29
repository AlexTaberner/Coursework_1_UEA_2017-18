package coursework1;

public class AlbumDatabase {
    //main file class showing off using the classes
    public static void main(String[] args){
        AlbumCollection albumDb = null;
        try{
            albumDb = new AlbumCollection();
            albumDb.displayCollection();
            albumDb.totalPlaytimeByArtist("Pink Floyd");
            albumDb.albumLargestNoOfTracks();
            albumDb.longestTrack();
        }
        catch(Exception e){
            System.err.println("Caught Exception: " + e.getMessage());
        }
    }
}