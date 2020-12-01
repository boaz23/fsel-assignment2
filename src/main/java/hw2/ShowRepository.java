package hw2;

import hw2.data.Show;
import hw2.data.Show;

import java.util.HashMap;

public class ShowRepository {

    private int nextShowId;
    private HashMap<Integer, Show> shows;

    public ShowRepository() {
        this.nextShowId = 1;
        this.shows = new HashMap<>();
    }

    public int addShow(Show show){
        int showId = nextShowId++;
        show.setId(showId);
        shows.put(showId, show);
        return showId;
    }

    public Show getShow(int showId){
        return shows.getOrDefault(showId, null);
    }

    public boolean hasShow(int showId){
        return shows.containsKey(showId);
    }

    public int size(){
        return shows.size();
    }
}
