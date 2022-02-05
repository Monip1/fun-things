import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;


public class Music{


    /**
Intro: Long Journey, Ateez, false, 72, false, 5, 3
Pirate King, Ateez, true, 85, true, 3, 1
Treasure, Ateez, true, 73, false, 4, 1
Twilight, Ateez, true, 65, false, 4, 2
Stay, Ateez, false, 32, false, 3, 2
My Way, Ateez, true, 72, true, 2, 1

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
        MyLinkedList.MyListIterator iter;
        for(String song: songsArray){
            songs.add(new Song(song));
        }
        iter = songs.new MyListIterator();
        System.out.println("Before edits");
        for(int i = 0; i < songs.size(); i++){
            // System.out.println(songs.get(i));
            ((Song) iter.next()).printSongData();
        }


        //Turn linked list back into a string to be written to the file 
        for(int i = 0; i < songs.size(); i++){
            toWrite += songs.get(i) + "\n";
        }
        Files.writeString(filePath, toWrite, StandardOpenOption.WRITE);

        //printout again
        songFile = Files.readString(filePath);
        songsArray = songFile.split("\n");
        songs.clear();
        for(String song: songsArray){
            songs.add(new Song(song));
        }
        System.out.println("After");
        for(int i = 0; i < songs.size(); i++){
            // System.out.println(songs.get(i).toString());
        }

    }
}