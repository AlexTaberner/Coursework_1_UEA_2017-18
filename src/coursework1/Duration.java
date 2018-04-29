package coursework1;

public class Duration implements Comparable<Duration> {
    //class variables:
    //time is stored in secconds and converted through methods at runtime for display
    private int totalSecs;
    
    //constructors
    public Duration() {
        totalSecs = 0;
    }
    public Duration(int hour, int min, int sec){
        totalSecs = hour*60*60;
        totalSecs += min*60;
        totalSecs += sec;
    }
    public Duration(String input){

        String[] times = input.split(":");
        totalSecs = Integer.parseInt(times[0])*60*60;
        totalSecs += Integer.parseInt(times[1])*60;
        totalSecs += Integer.parseInt(times[2]);
    }
    
    //methods:
    //add duration via duration object or integer of secconds - setters
    public void add(Duration input){
        totalSecs += input.totalSecs();
    }
    public void add(int secs){
        totalSecs += secs;
    }
    
    //return formmated string of time
    @Override
    public String toString(){
        String output = String.format("%02d:%02d:%02d", hours(), mins(), secs());
        return output;
    }
    
    //getters
    public int hours() {
        return totalSecs/60/60;
    }
    public int mins() {
        return (totalSecs/60)%60;
    }  
    public int secs() {
        return totalSecs%60;
    }
    public int totalSecs(){
        return totalSecs;
    }
    
    //natural order of object
    @Override
    public int compareTo(Duration other){
        int output = 0;
        
        if(totalSecs > other.totalSecs()){
            output = 1;
        }
        else if(totalSecs < other.totalSecs()){
            output = -1;
        }
        
        return output;
    }
    
    
    //tests
    public static void main(String[] args) {
        Duration test1 = new Duration();
        System.out.println(test1.toString());
        System.out.println(test1.totalSecs());
        //should print out 0
        
        Duration test2 = new Duration(1, 30, 27);
        System.out.println(test2.toString());
        System.out.println(test2.totalSecs());
        //should print out 1:30:27 and 5427
        
        test1.add(test2);
        System.out.println(test1.toString());
        System.out.println(test1.totalSecs());
        //should print out 1:30:27 and 5427
        
        
        Duration test3 = new Duration("2:10:15");
        System.out.println(test3.toString());
        System.out.println(test3.totalSecs());
        //should print out 2:10:15 and 7815
        
        test1.add(test3);
        System.out.println(test1.toString());
        System.out.println(test1.totalSecs());
        //should print out 3:40:42 and 13242
    }
}
