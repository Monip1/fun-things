import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;


public class Music{


    /**
Intro: Long Journey;Treasure EP.1: All To Zero;Ateez;false;72.0;false;5;3
Pirate King;Treasure EP.1: All To Zero;Ateez;true;85.0;true;3;1
Treasure;Treasure EP.1: All To Zero;Ateez;true;73.0;false;4;1
Twilight;Treasure EP.1: All To Zero;Ateez;true;65.0;false;4;2
Stay;Treasure EP.1: All To Zero;Ateez;false;32.0;false;3;2
My Way;Treasure EP.1: All To Zero;Ateez;true;72.0;true;2;1
HALA HALA (Hearts Awakened, Live Alive);TREASURE EP.2 : Zero To One;Ateez;false;72.0;false;5;3
Say My Name;TREASURE EP.2 : Zero To One;Ateez;true;85.0;true;3;1
Desire;TREASURE EP.2 : Zero To One;Ateez;true;73.0;false;4;1
Light;TREASURE EP.2 : Zero To One;Ateez;true;65.0;false;4;2
Promise;TREASURE EP.2 : Zero To One;Ateez;false;32.0;false;3;2


My Way

     */
    /**
     * Takes file and parses into list of songs with data as string
     * turns each song string into a node of data
     * makes a list of the nodes
     * edit nodes as user pleases
     * turn them back into a string and write to the file again
     */
    public static void main(String[] args) throws IOException{

        Path filePath = Path.of(args[0]);
	    String songFile = Files.readString(filePath);
        String toWrite = "";

        // make a linked list of song nodes
        String[] songsArray = songFile.split("\n");
        MyLinkedList<Song> songs = new MyLinkedList<>();
        for(String song: songsArray){
            songs.add(new Song(song));
        }

        Menu.start(songs);
        // System.out.println("Before edits");
        // for(int i = 0; i < songs.size(); i++){
        //     // System.out.println(songs.get(i));
        //     ((Song) iter.next()).printSongData();
        // }


        // Turn linked list back into a string to be written to the file 
        for(int i = 0; i < songs.size(); i++){
            toWrite += songs.get(i) + "\n";
        }
        Files.writeString(filePath, toWrite, StandardOpenOption.WRITE);

        //printout again
        // songFile = Files.readString(filePath);
        // songsArray = songFile.split("\n");
        // songs.clear();
        // for(String song: songsArray){
        //     songs.add(new Song(song));
        // }
        // System.out.println("After");
        // for(int i = 0; i < songs.size(); i++){
        //     // System.out.println(songs.get(i).toString());

    }
}    