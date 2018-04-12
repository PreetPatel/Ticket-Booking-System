package tbs.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class TheatreCollection {
    private HashSet<Theatre> _TheatreCollection;

    public TheatreCollection() {
        _TheatreCollection = new HashSet<>();
    }

    public Set<Theatre> getTheatreCollection() {
        return _TheatreCollection;
    }

    public boolean add(Theatre theatre) {
       return _TheatreCollection.add(theatre);
    }

    public String addTheatreFromPath(String filePath) {
        try{
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            //Separates each scan by a new line
            scanner.useDelimiter("\n");
            String processString = "";
            while(scanner.hasNext()) {
                String infoLine = scanner.next();
                //Regex pattern checks if the format matches the required format for the csv file
                if (infoLine.matches("THEATRE\tT[0-9]*\t[0-9]*\t[0-9]*")) {
                    //Splits each line by tab spaces
                    String[] tempInfo = infoLine.split("\t");
                    //Creates a new theatre object and adds it to the theatre collection. If it fails, an error
                    // message gets added
                    if (!this.add(new Theatre(tempInfo[1], Integer.parseInt(tempInfo[2]), Integer.parseInt(tempInfo[3])))) {
                        processString = "ERROR Theatre with the ID " + tempInfo[1] + " already exists and was not added";
                    }
                    //Returns error if a line does not match the regex pattern
                } else {
                    processString = "ERROR Incorrect Format for one or more lines in Theatres file";
                }

            }
            scanner.close();
            return processString;
        } catch(ArrayIndexOutOfBoundsException | NumberFormatException e) {
            return "ERROR Incorrect Format";
        }
        catch (FileNotFoundException e){
            return "ERROR File Not Found";
        }
        catch (NullPointerException e) {
            return "ERROR File is invalid";
        }

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
