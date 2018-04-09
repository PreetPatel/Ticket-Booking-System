
package tbs.client;

import java.util.*;
import tbs.server.TBSServer;
import tbs.server.TBSServerImpl;

public class CLI {
	public static void main(String[] args) {
		String path = "theatres1.csv";
		if (args.length > 0) {
			path = args[0]; // This allows a different file to be specified as an argument, but the default is theatres2.csv
		}
		TBSServer server = new TBSServerImpl();
		String result = server.initialise(path);
		System.out.println("Result from initialisation is {" + result + "}");  // Put in { } to make empty strings easier to see.
		server.dump(); // Implement dump() to print something useful here to determine whether your initialise method has worked.

		System.out.println("//////////////////////// RESULTS FROM ADDING ARTIST ////////////////////////////////////////");
		
		String artistID1 = server.addArtist("Ewan");
		System.out.println("Result from adding artist 'Ewan' is {" + artistID1 + "}");
		server.dump(); // Check that the server has been updated

		String artistID2 = server.addArtist("Ewan");
		System.out.println("Result from adding artist 'Ewan' is {" + artistID2 + "}");
		server.dump(); // Check that the server has been updated

		String artistID3 = server.addArtist("Preet");
		System.out.println("Result from adding artist 'Preet' is {" + artistID3 + "}");
		server.dump(); // Check that the server has been updated

		String artistID4 = server.addArtist("Ed Sheeran");
		System.out.println("Result from adding artist 'Ed Sheeran' is {" + artistID4 + "}");
		server.dump(); // Check that the server has been updated

		String artistID5 = server.addArtist("    ");
		System.out.println("Result from adding artist '    ' is {" + artistID5 + "}");
		server.dump(); // Check that the server has been updated

		String artistID6 = server.addArtist(null);
		System.out.println("Result from adding artist with Null name is {" + artistID6 + "}");
		server.dump(); // Check that the server has been updated

		System.out.println("////////////////////////////////////////////////////////////////////////////////////////////");
		System.out.println("///////////////////////////////RESULTS FROM ADDING ACTS ////////////////////////////////////");
		
		String actID1 = server.addAct("Lecture 3b: Making Objects", artistID1, 50); // this also checks that the artist ID is used properly
		System.out.println("Result from adding act to artist 'Ewan' is {" + actID1 + "}");
		server.dump();

		String actID2 = server.addAct("Divide Tour", artistID4, 120); // this also checks that the artist ID is used properly
		System.out.println("Result from adding act 'Divide Tour' to artist 'Ed Sheeran' is {" + actID2 + "}");
		server.dump();

		String actID3 = server.addAct("World Tour", artistID5, 60); // this also checks that the artist ID is used properly
		System.out.println("Result from adding act 'World Tour' to an artist which did not get added (Produced error)' is {" + actID3 + "}");
		server.dump();

		String actID4 = server.addAct("World Tour", null, 60); // this also checks that the artist ID is used properly
		System.out.println("Result from adding act 'World Tour' to a null artist is {" + actID4 + "}");
		server.dump();

		System.out.println("////////////////////////////////////////////////////////////////////////////////////////////");

		System.out.print("Result from GetTheatreID is { "); //Results from GetTheatreID
		List<String> TheatreIDs = server.getTheatreIDs();
		for(String e: TheatreIDs) { System.out.print(e + " "); }
		System.out.println("}");

		System.out.print("Result from GetArtistIDs is { "); //Results from GetArtistID
		List<String> ArtistIDs = server.getArtistIDs();
		for(String e: ArtistIDs) { System.out.print(e + " "); }
		System.out.println("}");

		System.out.print("Result from GetArtistNames is { "); //Results from GetArtistName
		List<String> ArtistNames = server.getArtistNames();
		for(String e: ArtistNames) { System.out.print(e + " "); }
		System.out.println("}");

		System.out.print("Result from GetActIDForArtist is { "); //Results from GetArtistName
		List<String> ActNamesByArtist1 = server.getActIDsForArtist("1");
		for(String e: ActNamesByArtist1) { System.out.print(e + " "); }
		System.out.println("}");

		System.out.println("////////////////////////////////////////////////////////////////////////////////////////////");
		System.out.println("///////////////////////////////RESULTS FROM ADDING Performance /////////////////////////////");


		String schedule1 = server.schedulePerformance(actID2, "T1", "9999-99-99T99:9t", "$200", "$100"); // this also checks that the artist ID is used properly
		System.out.println("Result from adding performance on 99th of the 9Tth Month (WRONG DATE) to  ED Sheeran's Act is {" + schedule1 + "}");
		server.dump();

		String schedule2 = server.schedulePerformance(actID2, "T1", "9999-99-99T99:99", "$200", "$100"); // this also checks that the artist ID is used properly
		System.out.println("Result from adding performance on 99th of the 99th Month to  ED Sheeran's Act is {" + schedule2 + "}");
		server.dump();

		String schedule3 = server.schedulePerformance(actID2, "T1", "9999-99-99T99:99", "$200$", "$100"); // this also checks that the artist ID is used properly
		System.out.println("Result from adding performance with wrong currency format ($100$) to  ED Sheeran's Act is {" + schedule3 + "}");
		server.dump();

		String schedule4 = server.schedulePerformance(actID2, "T1", "9999-99-99T99:99", "$022000", "$100"); // this also checks that the artist ID is used properly
		System.out.println("Result from adding performance with wrong currency format ($022000) to  ED Sheeran's Act is {" + schedule4 + "}");
		server.dump();

		String schedule5 = server.schedulePerformance(actID2, "T2", "9999-99-99T99:99", "$220", "$100"); // this also checks that the artist ID is used properly
		System.out.println("Result from adding performance in Theatre 2 for  ED Sheeran's Act is {" + schedule5 + "}");
		server.dump();

		System.out.println("////////////////////////////////////////////////////////////////////////////////////////////");

		String ticket1 = server.issueTicket(schedule2,1,1);
		System.out.println("Result from purchasing premium tickets to  ED Sheeran's Performance is {" + ticket1 + "}");

		String ticket2 = server.issueTicket(schedule1,1,1);
		System.out.println("Result from purchasing tickets to  a non-Existing Performance is {" + ticket2 + "}");

		String ticket3 = server.issueTicket(schedule2,1,1);
		System.out.println("Result from purchasing non available tickets to  ED Sheeran's Performance is {" + ticket3 + "}");

		String ticket5 = server.issueTicket(schedule5,1,1);
		System.out.println("Result from purchasing tickets to  ED Sheeran's Performance in Theatre 2 is {" + ticket5 + "}");

		System.out.println("///////////////HURRY! ED SHEERAN'S PERFORMANCE IS SELLING OUT!//////////////////////////////");
		int counter = 0;
		for (int i = 1; i< 8; i++ ) {
			for (int j = 1; j<8;j++) {
				server.issueTicket(schedule2, i, j);
				counter++;
			}
		}
		System.out.println("Number of Tickets issued: " + counter);

		System.out.println("///////////////WOW! ED SHEERAN'S PERFORMANCE IS NOW SOLD OUT!///////////////////////////////");

		String ticket4 = server.issueTicket(schedule2,6,2);
		System.out.println("Result from purchasing tickets to  ED Sheeran's sold out Performance is {" + ticket4 + "}");

		System.out.println("////////////////////////////////////////////////////////////////////////////////////////////");
		System.out.print("Result from GetTicketIDsForPerformance for ED Sheeran's Performance in theatre 1 is { "); //Results from GetTheatreID
		List<String> ticketIDs = server.getTicketIDsForPerformance(schedule2);
		for(String e: ticketIDs) { System.out.print(e + " ,"); }
		System.out.println("}");

		System.out.println("////////////////////////////////////////////////////////////////////////////////////////////");
		System.out.print("Result from GetAvailableSeatsForPerformance for ED Sheeran's Performance in theatre 1 is { "); //Results from GetTheatreID
		List<String> availableSeats = server.seatsAvailable(schedule2);
		for(String e: availableSeats) { System.out.print(e + " , "); }
		System.out.println("}");

		System.out.print("Result from GetAvailableSeatsForPerformance for ED Sheeran's Performance in theatre 2 is { "); //Results from GetTheatreID
		List<String> availableSeats1 = server.seatsAvailable(schedule5);
		for(String e: availableSeats1) { System.out.print(e + " , "); }
		System.out.println("}");

		System.out.print("Result from GetAvailableSeatsForPerformance for ED Sheeran's Performance for a non existing performance is { "); //Results from GetTheatreID
		List<String> availableSeats2 = server.seatsAvailable(schedule1);
		for(String e: availableSeats2) { System.out.print(e + " , "); }
		System.out.println("}");

		System.out.println("////////////////////////////////////////////////////////////////////////////////////////////");
		System.out.print("Result from sales report for Ed Sheeran's World Tour Act is { \n"); //Results from GetTheatreID
		List<String> salesReport1 = server.salesReport(actID2);
		for(String e: salesReport1) { System.out.print(e + "\n"); }
		System.out.println("}");
/*
	}

}
*/

/*package tbs.client;

import java.util.List;

import tbs.server.TBSServer;
import tbs.server.TBSServerImpl;

public class CLI {
    public static void main(String[] args) {
        String path = "theatres1.csv";

        if (args.length > 0) {
            path = args[0]; // This allows a different file to be specified as an argument, but the default is theatres1.csv
        }

        TBSServer server = new TBSServerImpl();*/

        // -=-=-=-=-=-=-=-=-=- // initialise // -=-=-=-=-=-=-=-=-=- //
        String result1 = server.initialise(path);
        System.out.println("Result of initialization is: " + result1 + "\n");
        // Will have to test this myself, change theatres1.csv to contain invalid formats.

        // -=-=-=-=-=-=-=-=-=- // addArtist // -=-=-=-=-=-=-=-=-=- //
        String ArtistID1 = server.addArtist("Bon Jovi");
        System.out.print("Expected: an ID \nGot     : " + ArtistID1 + "\n-=-=-=-\n");
        String ArtistID2 = server.addArtist("Sheeran");
        System.out.print("Expected: an ID \nGot     : " + ArtistID2 + "\n-=-=-=-\n");
        String ArtistID3 = server.addArtist("Kanye West");
        System.out.print("Expected: an ID \nGot     : " + ArtistID3 + "\n-=-=-=-\n");

        //Adding a duplicate
        String ArtistID4 = server.addArtist("Kanye West");
        System.out.print("Expected: ERROR: Artist already exists\nGot     : " + ArtistID4 + "\n-=-=-=-\n");

        //Adding an empty name;
        String ArtistID5 = server.addArtist("");
        System.out.print("Expected: ERROR: No name entered\nGot     : " + ArtistID5 + "\n-=-=-=-\n");

        // -=-=-=-=-=-=-=-=-=- // addAct // -=-=-=-=-=-=-=-=-=- //
        String Act1ID = server.addAct("Skinny Love", ArtistID1, 4);
        System.out.print("Expected: an ID \nGot     : " + Act1ID + "\n-=-=-=-\n");
        String Act2ID = server.addAct("Holocene", ArtistID1, 6);
        System.out.print("Expected: an ID \nGot     : " + Act2ID + "\n-=-=-=-\n");
        String Act3ID = server.addAct("Power", ArtistID3, 5);
        System.out.print("Expected: an ID \nGot     : " + Act3ID + "\n-=-=-=-\n");

        //Adding an empty title
        String Act4ID = server.addAct("", ArtistID1, 5);
        System.out.print("Expected: ERROR: Empty Title\nGot     : " + Act4ID + "\n-=-=-=-\n");

        //Adding to a non-existent artist
        String Act5ID = server.addAct("All Star", "ART4" , 5);
        System.out.print("Expected: ERROR: Artist not found\nGot     : " + Act5ID + "\n-=-=-=-\n");

        //Adding to an empty artist
        String Act6ID = server.addAct("All Star", "" , 5);
        System.out.print("Expected: ERROR: Artist not found\nGot     : " + Act6ID + "\n-=-=-=-\n");

        //Adding a negative duration
        String Act7ID = server.addAct("Perfect", ArtistID2 , -1);
        System.out.print("Expected: ERROR: Negative time\nGot     : " + Act7ID + "\n-=-=-=-\n");

        // -=-=-=-=-=-=-=-=-=- // getActIDsForArtist // -=-=-=-=-=-=-=-=-=- //
        List<String> ActIDs1 = server.getActIDsForArtist(ArtistID1);
        System.out.print("Expected: [an ID, another ID] \nGot     : " + ActIDs1 + "\n-=-=-=-\n");
        List<String> ActIDs2 = server.getActIDsForArtist(ArtistID2);
        System.out.print("Expected: [] \nGot     : " + ActIDs2 + "\n-=-=-=-\n");

        //Requesting IDs for an Artist that doesn't exist
        List<String> ActIDs3 = server.getActIDsForArtist("Hurricane Katrina?");
        System.out.print("Expected: ERROR: Artist not found \nGot     : " + ActIDs3 + "\n-=-=-=-\n");

        // -=-=-=-=-=-=-=-=-=- // schedulePerformance // -=-=-=-=-=-=-=-=-=- //
        String PerfID1 = server.schedulePerformance(Act1ID, "T1", "1999-05-13T11:27", "$10", "$20");
        System.out.print("Expected: an ID \nGot     : " + PerfID1 + "\n-=-=-=-\n");
        String PerfID2 = server.schedulePerformance(Act1ID, "T1", "2000-05-13T01:27", "$15", "$25");
        System.out.print("Expected: an ID \nGot     : " + PerfID2 + "\n-=-=-=-\n");
        String PerfID3 = server.schedulePerformance(Act3ID, "T2", "2010-07-13T15:27", "$15", "$25");
        System.out.print("Expected: an ID \nGot     : " + PerfID3 + "\n-=-=-=-\n");

        // Testing with invalid actID
        String PerfID4 = server.schedulePerformance("invaled", "T2", "2010-07-13T15:27", "15", "25");
        System.out.print("Expected: ERROR: Invalid actID \nGot     : " + PerfID4 + "\n-=-=-=-\n");

        // Testing with invalid theatre ID
        String PerfID5 = server.schedulePerformance(Act3ID, "Invalid", "2010-07-13T15:27", "15", "25");
        System.out.print("Expected: ERROR: Invalid TheatreID \nGot     : " + PerfID5 + "\n-=-=-=-\n");

        // Testing with non ISO time
        String PerfID6 = server.schedulePerformance(Act3ID, "T2", "2010-07-13-54:54", "15", "25");
        System.out.print("Expected: ERROR: Time format not ISO8601 \nGot     : " + PerfID6 + "\n-=-=-=-\n");

        // Testing with invalid prices
        String PerfID7 = server.schedulePerformance(Act3ID, "T2", "2010-07-13T15:27", "-15", "25");
        System.out.print("Expected: ERROR: Prices are invalid \nGot     : " + PerfID7 + "\n-=-=-=-\n");
        String PerfID8 = server.schedulePerformance(Act3ID, "T2", "2010-07-13T15:27", "xd", "25");
        System.out.print("Expected: ERROR: Prices are invalid \nGot     : " + PerfID8 + "\n-=-=-=-\n");

        // -=-=-=-=-=-=-=-=-=- // issueTicket // -=-=-=-=-=-=-=-=-=- //
        String TicketID1 = server.issueTicket(PerfID1, 4, 5);
        System.out.print("Expected: an ID \nGot     : " + TicketID1 + "\n-=-=-=-\n");
        String TicketID2 = server.issueTicket(PerfID1, 5, 5);
        System.out.print("Expected: an ID \nGot     : " + TicketID2 + "\n-=-=-=-\n");
        String TicketID3 = server.issueTicket(PerfID2, 5, 5);
        System.out.print("Expected: an ID \nGot     : " + TicketID3 + "\n-=-=-=-\n");

        // Trying to book a ticket for the same seat
        String TicketID4 = server.issueTicket(PerfID1, 4, 5);
        System.out.print("Expected: ERROR: Seat is booked \nGot     : " + TicketID4 + "\n-=-=-=-\n");

        // Trying to book a ticket for a seat out of bounds
        String TicketID5 = server.issueTicket(PerfID1, 4, 500);
        System.out.print("Expected: ERROR: Seat does not exist \nGot     : " + TicketID5 + "\n-=-=-=-\n");

        // -=-=-=-=-=-=-=-=-=- // salesReport // -=-=-=-=-=-=-=-=-=- //
        List<String> Sales1 = server.salesReport(Act1ID);
        System.out.print("Expected: Two sales reports \nGot     : " + Sales1 + "\n-=-=-=-\n");
        List<String> Sales2 = server.salesReport(Act2ID);
        System.out.print("Expected: [] \nGot     : " + Sales2 + "\n-=-=-=-\n");
        // Testing with an invalid actID
        List<String> Sales3 = server.salesReport("invalid");
        System.out.print("Expected: ERROR: Act does not exist \nGot     : " + Sales3 + "\n-=-=-=-\n");

    }
}
