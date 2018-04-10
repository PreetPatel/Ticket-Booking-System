package tbs.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TheatreCollection {
    private List<Theatre> _TheatreCollection;

    public TheatreCollection() {
        _TheatreCollection = new ArrayList<>();
    }

    public List<Theatre> getTheatreCollection() {
        return _TheatreCollection;
    }

    public boolean add(Theatre theatre) {
       return _TheatreCollection.add(theatre);
    }

    public String addTheatreFromFile(File file) {
        try{
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("\n");
            while(scanner.hasNext()) {
                String[] tempInfo = scanner.next().split("\t");
                this.add(new Theatre(tempInfo[1], Integer.parseInt(tempInfo[2]),Integer.parseInt(tempInfo[3])));

            }
            scanner.close();
        } catch(ArrayIndexOutOfBoundsException | NumberFormatException e) {
            return "ERROR Incorrect Format";
        }
        catch (FileNotFoundException e){
            return "ERROR File Not Found";
        }
        return "";
    }

    public Theatre getTheatreFromServer(String theatreID) {
        for (Theatre e: _TheatreCollection) {
            if (e.getID().equals(theatreID)) {
                return e;
            }
        }
        return null;
    }
}
