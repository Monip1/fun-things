package MusicProject;

public class Response{
 
    //named in levels of irritation of the computer
    private String key;
    private String output;
    private int breakingPoint;
  
    public Response(String s, String s2, int n) {
        key = s;
        output = s2;
        breakingPoint = n;
    }
 
    public String getKey(){
        return key;
    }
    public String getResponse() {
        return output;
    }
    public int getBreakingPoint(){
        return breakingPoint;
    }
}
