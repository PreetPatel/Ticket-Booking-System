package tbs.client;

import java.util.List;
import tbs.server.TBSServer;
import tbs.server.TBSServerImpl;

public class CLI3 {
	public static void main(String[] args) {
		
		int counter = 0;
		String path = "theatres1.csv";
		if (args.length > 0) {
			path = args[0]; // This allows a different file to be specified as an argument, but the default is theatres2.csv
		}
		TBSServer server = new TBSServerImpl();
		String result = server.initialise(path);
		System.out.println("Result from initialisation is {" + result + "}");  // Put in { } to make empty strings easier to see.
		System.out.println("\n");
		
		////////////////////////////////////// addArtist //////////////////////////////////////////////
		
		// Add artist 1
		String artistID1 = server.addArtist("Ewan");
		System.out.println("Expected: Some ID");
		System.out.println("Result from adding artist 'Ewan' is {" + artistID1 + "}");
		if (artistID1.contains("ERROR"))  { System.out.println("FAILED");counter++;} else System.out.println("PASSED");
		System.out.println("\n");

		// Add artist 2
		String artistID2 = server.addArtist("Bakh");
		System.out.println("Expected: Some ID");
		System.out.println("Result from adding artist 'Bakh' is {" + artistID2 + "}");
		if (artistID2.contains("ERROR"))   { System.out.println("FAILED");counter++;} else System.out.println("PASSED");
		System.out.println("\n");
		
		// Add artist 3
		String artistID3 = server.addArtist("Amanda");
		System.out.println("Expected: Some ID");
		System.out.println("Result from adding artist 'Amanda' is {" + artistID3 + "}");
		if (artistID3.contains("ERROR"))   { System.out.println("FAILED");counter++;} else System.out.println("PASSED");
		System.out.println("\n");
		
		// Add duplicate artist (should fail)
		String artistID4 = server.addArtist("eWAn");
		System.out.println("Expected: Artist already added");
		System.out.println("Result from adding artist 'eWAn' is {" + artistID4 + "}");
		if (artistID4.contains("ERROR")) System.out.println("PASSED"); else  { System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		// Add empty artist (should fail)
		String artistID5 = server.addArtist("");
		System.out.println("Expected: Artist ID empty");
		System.out.println("Result from adding artist '' is {" + artistID5 + "}");
		if (artistID5.contains("ERROR")) System.out.println("PASSED"); else  { System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		// Add null artist (should fail)
		String artistID6 = server.addArtist(null);
		System.out.println("Expected: Artist ID null");
		System.out.println("Result from adding artist 'null' is {" + artistID6 + "}");
		if (artistID6.contains("ERROR")) System.out.println("PASSED"); else  { System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		// Add empty artist (should fail)
		String artistID7 = server.addArtist("  ");
		System.out.println("Expected: Artist ID empty");
		System.out.println("Result from adding artist '    ' is {" + artistID7 + "}");
		if (artistID7.contains("ERROR")) System.out.println("PASSED"); else {System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		
		
		//////////////////////////////////////////// addAct /////////////////////////////////////////////////
		
		// Title is empty, should fail
		String actID1 = server.addAct("", artistID1, 50);
		System.out.println("Expected: Title is empty");
		System.out.println("Result from adding act to artist 'Ewan' is {" + actID1 + "}");
		if (actID1.contains("ERROR")) System.out.println("PASSED"); else  { System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		// Duration is invalid, should fail
		String actID2 = server.addAct("Lecture 3b: Making Objects", artistID1, -50);
		System.out.println("Expected: Invalid duration - negative");
		System.out.println("Result from adding act to artist 'Ewan' is {" + actID2 + "}");
		if (actID2.contains("ERROR")) System.out.println("PASSED"); else { System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		// Title is empty, should fail
		String actID3 = server.addAct(" ", artistID1, 50);
		System.out.println("Expected: Title is empty");
		System.out.println("Result from adding act to artist 'Ewan' is {" + actID3 + "}");
		if (actID3.contains("ERROR")) System.out.println("PASSED"); else { System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		// Artist is null, should fail
		String actID4 = server.addAct("Lecture 3b: Making Objects", null, 50);
		System.out.println("Expected: Artist is null");
		System.out.println("Result from adding act to artist 'Ewan' is {" + actID4 + "}");
		if (actID4.contains("ERROR")) System.out.println("PASSED"); else { System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		// Artist is empty, should fail
		String actID5 = server.addAct("Lecture 3b: Making Objects", " ", 50);
		System.out.println("Expected: Artist is empty");
		System.out.println("Result from adding act to artist 'Ewan' is {" + actID5 + "}");
		if (actID5.contains("ERROR")) System.out.println("PASSED");else { System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		// Title is null, should fail
		String actID6 = server.addAct(null, artistID1, 50);
		System.out.println("Expected: Title is null");
		System.out.println("Result from adding act to artist 'Ewan' is {" + actID6 + "}");
		if (actID6.contains("ERROR")) System.out.println("PASSED"); else { System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		// No errors, act should add
		String actID7 = server.addAct("Lecture 3b: Making Objects", artistID1, 50);
		System.out.println("Expected: Some act ID");
		System.out.println("Result from adding act to artist 'Ewan' is {" + actID7 + "}");
		if (actID7.contains("ERROR")) { System.out.println("FAILED");counter++;} else System.out.println("PASSED");
		System.out.println("\n");
		
		// Duplicate act, should fail
		String actID8 = server.addAct("Lecture 3b: Making Objects", artistID1, 50);
		System.out.println("Expected: Act already added");
		System.out.println("Result from adding act to artist 'Ewan' is {" + actID8 + "}");
		if (actID8.contains("ERROR")) System.out.println("PASSED"); else { System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		// Act with same name, different artist, should add act
		String actID9 = server.addAct("Lecture 3b: Making Objects", artistID3, 60);
		System.out.println("Expected: Some act ID");
		System.out.println("Result from adding act to artist 'Amanda' is {" + actID9 + "}");
		if (actID9.contains("ERROR")) {  System.out.println("FAILED");counter++;} else System.out.println("PASSED");
		System.out.println("\n");
		
		// Should add act
		String actID10 = server.addAct("Lecture 1: Stable Matching Problem", artistID2, 120);
		System.out.println("Expected: Some act ID");
		System.out.println("Result from adding act to artist 'Bakh' is {" + actID10 + "}");
		if (actID10.contains("ERROR")) {  System.out.println("FAILED");counter++;} else System.out.println("PASSED");
		System.out.println("\n");
		
		
		/////////////////////////////////////// getArtistNames ///////////////////////////////////////////////
		
		List<String> namesOfArtists = server.getArtistNames();
		System.out.println("Expected: List of artist names");
		System.out.println(namesOfArtists);
		if (namesOfArtists.contains("ERROR")) {  System.out.println("FAILED");counter++;} else System.out.println("PASSED");
		System.out.println("\n");

		
		/////////////////////////////////////// getArtistIDs /////////////////////////////////////////////////
		
		List<String> IDsOfArtists = server.getArtistIDs();
		System.out.println("Expected: List of artist IDs");
		System.out.println(IDsOfArtists);
		if (IDsOfArtists.contains("ERROR")) { System.out.println("FAILED");counter++;} else System.out.println("PASSED");
		System.out.println("\n");
		
		
		/////////////////////////////////////// getTheatreIDs /////////////////////////////////////////////////
		
		List<String> theatresAvailable = server.getTheatreIDs();
		System.out.println("Expected: List of theatre IDs");
		System.out.println(theatresAvailable);
		if (theatresAvailable.contains("ERROR"))  { System.out.println("FAILED");counter++;} else System.out.println("PASSED");
		System.out.println("\n");
		
		
		/////////////////////////////////////// schedulePerformance ///////////////////////////////////////////

		
		// No errors, performance should schedule
		String schedule1 = server.schedulePerformance(actID7, "T1", "2084-39-93T18:27", "$50", "$25");
		System.out.println("Expected: Some performance ID");
		System.out.println("Result from scheduling performance to " + actID7 + " is {" + schedule1 + "}");
		if (schedule1.contains("ERROR")) {  System.out.println("FAILED");counter++;} else System.out.println("PASSED");
		System.out.println("\n");
		
		// Act ID is empty, fails
		String schedule2 = server.schedulePerformance("", "T1", "2084-39-93T18:27", "$50", "$25");
		System.out.println("Expected: Act ID is empty");
		System.out.println("Result from scheduling performance to '' is {" + schedule2 + "}");
		if (schedule2.contains("ERROR")) System.out.println("PASSED"); else {  System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		// Act ID is null, fails
		String schedule3 = server.schedulePerformance(null, "T1", "2084-39-93T18:27", "$50", "$25");
		System.out.println("Expected: Act ID is null");
		System.out.println("Result from scheduling performance to 'null' is {" + schedule3 + "}");
		if (schedule3.contains("ERROR")) System.out.println("PASSED"); else { System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		// Adding another performance of same act, no errors expected
		String schedule4 = server.schedulePerformance(actID7, "T2", "2084-39-93T18:27", "$60", "$35");
		System.out.println("Expected: Some performance ID");
		System.out.println("Result from scheduling performance to " + actID7 + " is {" + schedule4 + "}");
		if (schedule4.contains("ERROR")) { System.out.println("FAILED");counter++;} else System.out.println("PASSED");
		System.out.println("\n");
		
		// Theatre does not exist
		String schedule5 = server.schedulePerformance(actID7, "T4", "2084-39-93T18:27", "$50", "$25");
		System.out.println("Expected: Theatre does not exist");
		System.out.println("Result from scheduling performance to " + actID7 + " is {" + schedule5 + "}");
		if (schedule5.contains("ERROR")) System.out.println("PASSED"); else { System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		// Theatre ID is empty
		String schedule6 = server.schedulePerformance(actID9, "", "2084-39-93T18:27", "$50", "$25");
		System.out.println("Expected: Theatre ID is empty");
		System.out.println("Result from scheduling performance to " + actID9 + " is {" + schedule6 + "}");
		if (schedule6.contains("ERROR")) System.out.println("PASSED"); else { System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		// Theatre ID is null
		String schedule7 = server.schedulePerformance(actID9, null, "2084-39-93T18:27", "$50", "$25");
		System.out.println("Expected: Theatre ID is null");
		System.out.println("Result from scheduling performance to " + actID9 + " is {" + schedule7 + "}");
		if (schedule7.contains("ERROR")) System.out.println("PASSED"); else { System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		// Time in incorrect format
		String schedule8 = server.schedulePerformance(actID9, "T1", "2084-39-93T8:27", "$50", "$25");
		System.out.println("Expected: Time is not in ISO8601");
		System.out.println("Result from scheduling performance to " + actID9 + " is {" + schedule8 + "}");
		if (schedule8.contains("ERROR")) System.out.println("PASSED"); else { System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		// Time in incorrect format
		String schedule9 = server.schedulePerformance(actID9, "T1", "2084-t9-93T18:27", "$50", "$25");
		System.out.println("Expected: Time is not in ISO8601");
		System.out.println("Result from scheduling performance to " + actID9 + " is {" + schedule9 + "}");
		if (schedule9.contains("ERROR")) System.out.println("PASSED"); else { System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		// Price is in wrong format
		String schedule10 = server.schedulePerformance(actID9, "T1", "2084-39-93T18:27", "-50", "$25");
		System.out.println("Expected: Price formatted incorrectly");
		System.out.println("Result from scheduling performance to " + actID9 + " is {" + schedule10 + "}");
		if (schedule10.contains("ERROR")) System.out.println("PASSED"); else { System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		// Price is null
		String schedule11 = server.schedulePerformance(actID9, "T1", "2084-39-93T18:27", "$50", null);
		System.out.println("Expected: Price is null");
		System.out.println("Result from scheduling performance to " + actID9 + " is {" + schedule11 + "}");
		if (schedule11.contains("ERROR")) System.out.println("PASSED"); else { System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		// Price is empty
		String schedule12 = server.schedulePerformance(actID9, "T1", "2084-39-93T18:27", "$50", "");
		System.out.println("Expected: Price is empty");
		System.out.println("Result from scheduling performance to " + actID9 + " is {" + schedule12 + "}");
		if (schedule12.contains("ERROR")) System.out.println("PASSED"); else { System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		// Adds new performance, no errors
		String schedule13 = server.schedulePerformance(actID9, "T3", "2084-39-93T18:27", "$100", "$40");
		System.out.println("Expected: Some performance ID");
		System.out.println("Result from scheduling performance to " + actID9 + " is {" + schedule13 + "}");
		if (schedule13.contains("ERROR")) { System.out.println("FAILED");counter++;} else System.out.println("PASSED");
		System.out.println("\n");

		// Adds new performance, no errors
		String schedule14 = server.schedulePerformance(actID9, "T3", null, "$100", "$40");
		System.out.println("Expected: ERROR Start time is invalid");
		System.out.println("Result from scheduling performance to " + actID9 + " is {" + schedule14 + "}");
		if (schedule12.contains("ERROR")) System.out.println("PASSED"); else { System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		
		/////////////////////////////////// getActIDsForArtist //////////////////////////////////////////////
		
		// Should return list of act IDs
		List<String> IDsOfActs1 = server.getActIDsForArtist(artistID1);
		System.out.println("Expected: List of act IDs");
		System.out.println(IDsOfActs1);
		if (IDsOfActs1.get(0).contains("ERROR")) { System.out.println("FAILED");counter++;} else System.out.println("PASSED");
		System.out.println("\n");
		
		// Artist ID does not exist, fails
		List<String> IDsOfActs2 = server.getActIDsForArtist(artistID4);
		System.out.println("Expected: Artist ID does not exist");
		System.out.println(IDsOfActs2);
		if (IDsOfActs2.get(0).contains("ERROR")) System.out.println("PASSED"); else { System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		// Artist ID is null, fails
		List<String> IDsOfActs3 = server.getActIDsForArtist(null);
		System.out.println("Expected: Artist ID is null");
		System.out.println(IDsOfActs3);
		if (IDsOfActs3.get(0).contains("ERROR")) System.out.println("PASSED"); else { System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		// Artist ID is empty, fails
		List<String> IDsOfActs4 = server.getActIDsForArtist("   ");
		System.out.println("Expected: Artist ID is empty");
		System.out.println(IDsOfActs4);
		if (IDsOfActs4.get(0).contains("ERROR")) System.out.println("PASSED"); else { System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		// Should return list of act IDs
		List<String> IDsOfActs5 = server.getActIDsForArtist(artistID2);
		System.out.println("Expected: List of act IDs");
		System.out.println(IDsOfActs5);
		if (IDsOfActs5.get(0).contains("ERROR")) { System.out.println("FAILED");counter++;} else System.out.println("PASSED");
		System.out.println("\n");
		
		// Should return list of act IDs
		List<String> IDsOfActs6 = server.getActIDsForArtist(artistID3);
		System.out.println("Expected: List of act IDs");
		System.out.println(IDsOfActs6);
		if (IDsOfActs6.get(0).contains("ERROR")) { System.out.println("FAILED");counter++;} else System.out.println("PASSED");
		System.out.println("\n");
		
		
		//////////////////////////// getPeformanceIDsForAct //////////////////////////////////////
		
		// Should return list of performance IDs
		List<String> pIDs1 = server.getPeformanceIDsForAct(actID7);
		System.out.println("Expected: List of Performance IDs");
		System.out.println(pIDs1);
		if (pIDs1.get(0).contains("ERROR")) { System.out.println("FAILED");counter++;} else System.out.println("PASSED");
		System.out.println("\n");
		
		// Should return list of performance IDs
		List<String> pIDs2 = server.getPeformanceIDsForAct(actID9);
		System.out.println("Expected: List of Performance IDs");
		System.out.println(pIDs2);
		if (pIDs2.get(0).contains("ERROR")) { System.out.println("FAILED");counter++;} else System.out.println("PASSED");
		System.out.println("\n");
		
		// Should return list of performance IDs
		List<String> pIDs3 = server.getPeformanceIDsForAct(actID10);
		System.out.println("Expected: List of Performance IDs (has no performances scheduled)");
		System.out.println(pIDs3);
		if (pIDs3.isEmpty()) System.out.println("PASSED"); else { System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		// Act ID does not exist, fails
		List<String> pIDs4 = server.getPeformanceIDsForAct(actID8);
		System.out.println("Expected: Act ID does not exist");
		System.out.println(pIDs4);
		if (pIDs4.get(0).contains("ERROR")) System.out.println("PASSED"); else { System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		// Act ID is null, fails
		List<String> pIDs5 = server.getPeformanceIDsForAct(null);
		System.out.println("Expected: Act ID is null");
		System.out.println(pIDs5);
		if (pIDs5.get(0).contains("ERROR")) System.out.println("PASSED"); else { System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		// Act ID is empty
		List<String> pIDs6 = server.getPeformanceIDsForAct("");
		System.out.println("Expected: Act ID is empty");
		System.out.println(pIDs6);
		if (pIDs6.get(0).contains("ERROR")) System.out.println("PASSED"); else { System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		
		/////////////////////////////// issueTicket ////////////////////////////////////////////////////
		
		// No errors
		String tick1 = server.issueTicket(schedule1, 3, 5);
		System.out.println("Expected: Some ticket ID");
		System.out.println(tick1);
		if (tick1.contains("ERROR")) { System.out.println("FAILED");counter++;} else System.out.println("PASSED");
		System.out.println("\n");
		
		// Booking failed, seat already booked
		String tick2 = server.issueTicket(schedule1, 3, 5);
		System.out.println("Expected: Seat already booked");
		System.out.println(tick2);
		if (tick2.contains("ERROR")) System.out.println("PASSED"); else { System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		// Performance null, fails
		String tick3 = server.issueTicket(null, 3, 5);
		System.out.println("Expected: Performance ID is null");
		System.out.println(tick3);
		if (tick3.contains("ERROR")) System.out.println("PASSED"); else { System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		// Performance ID empty, fails
		String tick4 = server.issueTicket("", 3, 5);
		System.out.println("Expected: Performance ID is empty");
		System.out.println(tick4);
		if (tick4.contains("ERROR")) System.out.println("PASSED"); else { System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		// Performance ID doesn't exist, fails
		String tick5 = server.issueTicket(schedule10, 3, 5);
		System.out.println("Expected: Performance ID doesn't exist");
		System.out.println(tick5);
		if (tick5.contains("ERROR")) System.out.println("PASSED"); else { System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		// Seat doesn't exist, fails
		String tick6 = server.issueTicket(schedule1, 0, 7);
		System.out.println("Expected: Seat doesn't exist");
		System.out.println(tick6);
		if (tick6.contains("ERROR")) System.out.println("PASSED"); else { System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		// Books seat
		String tick7 = server.issueTicket(schedule1, 5, 3);
		System.out.println("Expected: Some ticket ID");
		System.out.println(tick7);
		if (tick7.contains("ERROR")) { System.out.println("FAILED");counter++;} else System.out.println("PASSED");
		System.out.println("\n");
		
		// Books seat
		String tick8 = server.issueTicket(schedule4, 1, 5);
		System.out.println("Expected: Some ticket ID");
		System.out.println(tick8);
		if (tick8.contains("ERROR")) { System.out.println("FAILED");counter++;} else System.out.println("PASSED");
		System.out.println("\n");
		
		// Books seat
		String tick9 = server.issueTicket(schedule1, 2, 2);
		System.out.println("Expected: Some ticket ID");
		System.out.println(tick9);
		if (tick9.contains("ERROR")) { System.out.println("FAILED");counter++;} else System.out.println("PASSED");
		System.out.println("\n");
		
		// Books seat
		String tick10 = server.issueTicket(schedule1, 6, 1);
		System.out.println("Expected: Some ticket ID");
		System.out.println(tick10);
		if (tick10.contains("ERROR")) { System.out.println("FAILED");counter++;} else System.out.println("PASSED");
		System.out.println("\n");
		
		// Books seat
		String tick11 = server.issueTicket(schedule4, 9, 2);
		System.out.println("Expected: Some ticket ID");
		System.out.println(tick11);
		if (tick11.contains("ERROR")) { System.out.println("FAILED");counter++;} else System.out.println("PASSED");
		System.out.println("\n");
		
		// Books seat
		String tick12 = server.issueTicket(schedule13, 3, 7);
		System.out.println("Expected: Some ticket ID");
		System.out.println(tick12);
		if (tick12.contains("ERROR")) { System.out.println("FAILED");counter++;} else System.out.println("PASSED");
		System.out.println("\n");
		
		
		/////////////////////////////// seatsAvailable /////////////////////////////////////////////////
		
		// No errors
		List<String> freeSeats1 = server.seatsAvailable(schedule1);
		System.out.println("Expected: List of available seats");
		System.out.println(freeSeats1);
		if (freeSeats1.get(0).contains("ERROR")) { System.out.println("FAILED");counter++;} else System.out.println("PASSED");
		System.out.println("\n");
		
		// No errors
		List<String> freeSeats2 = server.seatsAvailable(schedule4);
		System.out.println("Expected: List of available seats");
		System.out.println(freeSeats2);
		if (freeSeats2.get(0).contains("ERROR")) { System.out.println("FAILED");counter++;} else System.out.println("PASSED");
		System.out.println("\n");
		
		// No errors
		List<String> freeSeats3 = server.seatsAvailable(schedule13);
		System.out.println("Expected: List of available seats");
		System.out.println(freeSeats3);
		if (freeSeats3.get(0).contains("ERROR")) { System.out.println("FAILED");counter++;} else System.out.println("PASSED");
		System.out.println("\n");
		
		// Performance ID null, fails
		List<String> freeSeats4 = server.seatsAvailable(null);
		System.out.println("Expected: Performance ID is null");
		System.out.println(freeSeats4);
		if (freeSeats4.get(0).contains("ERROR")) System.out.println("PASSED"); else { System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		// Performance ID empty, fails
		List<String> freeSeats5 = server.seatsAvailable("      ");
		System.out.println("Expected: Performance ID is empty");
		System.out.println(freeSeats5);
		if (freeSeats5.get(0).contains("ERROR")) System.out.println("PASSED"); else { System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		// Performance ID doesn't exist, fails
		List<String> freeSeats6 = server.seatsAvailable(schedule3);
		System.out.println("Expected: Performance ID doesn't exist");
		System.out.println(freeSeats6);
		if (freeSeats6.get(0).contains("ERROR")) System.out.println("PASSED"); else { System.out.println("FAILED");counter++;}
		System.out.println("\n");

		
		///////////////////////////////////// getTicketIDsForPerformance ////////////////////////////////////////
		
		// No errors
		List<String> tickList1 = server.getTicketIDsForPerformance(schedule1);
		System.out.println("Expected: List of ticket IDs");
		System.out.println(tickList1);
		if (tickList1.get(0).contains("ERROR")) { System.out.println("FAILED");counter++;} else System.out.println("PASSED");
		System.out.println("\n");
		
		// No errors
		List<String> tickList2 = server.getTicketIDsForPerformance(schedule4);
		System.out.println("Expected: List of ticket IDs");
		System.out.println(tickList2);
		if (tickList2.get(0).contains("ERROR")) { System.out.println("FAILED");counter++;} else System.out.println("PASSED");
		System.out.println("\n");
		
		// No errors
		List<String> tickList3 = server.getTicketIDsForPerformance(schedule13);
		System.out.println("Expected: List of ticket IDs");
		System.out.println(tickList3);
		if (tickList3.get(0).contains("ERROR")) { System.out.println("FAILED");counter++;} else System.out.println("PASSED");
		System.out.println("\n");
		
		// Performance ID doesn't exist
		List<String> tickList4 = server.getTicketIDsForPerformance(schedule5);
		System.out.println("Expected: Performance ID doesn't exist");
		System.out.println(tickList4);
		if (tickList4.get(0).contains("ERROR")) System.out.println("PASSED"); else { System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		// Performance ID is empty, fails
		List<String> tickList5 = server.getTicketIDsForPerformance("\t");
		System.out.println("Expected: Performance ID is empty");
		System.out.println(tickList5);
		if (tickList5.get(0).contains("ERROR")) System.out.println("PASSED"); else { System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		// Performance ID is null, fails
		List<String> tickList6 = server.getTicketIDsForPerformance(null);
		System.out.println("Expected: Performance ID is null");
		System.out.println(tickList6);
		if (tickList6.get(0).contains("ERROR")) System.out.println("PASSED"); else { System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		
		/////////////////////////////// salesReport ///////////////////////////////////////
		
		// No errors
		List<String> sales1 = server.salesReport(actID7);
		System.out.println("Expected: List of sales for performances");
		System.out.println(sales1);
		if (sales1.get(0).contains("ERROR")) { System.out.println("FAILED");counter++;} else System.out.println("PASSED");
		System.out.println("\n");
		
		// No errors
		List<String> sales2 = server.salesReport(actID9);
		System.out.println("Expected: List of sales for performances");
		System.out.println(sales2);
		if (sales2.get(0).contains("ERROR")) { System.out.println("FAILED");counter++;} else System.out.println("PASSED");
		System.out.println("\n");
		
		// No errors
		List<String> sales3 = server.salesReport(actID10);
		System.out.println("Expected: List of sales for performances");
		System.out.println(sales3);
		if (sales3.isEmpty()) System.out.println("PASSED"); else if (sales3.get(0).contains("ERROR")) { System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		// Act ID doesn't exist, fails
		List<String> sales4 = server.salesReport(actID2);
		System.out.println("Expected: Act ID doesn't exist");
		System.out.println(sales4);
		if (sales4.get(0).contains("ERROR")) System.out.println("PASSED"); else { System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		// Act ID empty, fails
		List<String> sales5 = server.salesReport("");
		System.out.println("Expected: Act ID is empty");
		System.out.println(sales5);
		if (sales5.get(0).contains("ERROR")) System.out.println("PASSED"); else { System.out.println("FAILED");counter++;}
		System.out.println("\n");
		
		// Act ID null, fails
		List<String> sales6 = server.salesReport(null);
		System.out.println("Expected: Act ID is null");
		System.out.println(sales6);
		if (sales6.get(0).contains("ERROR")) System.out.println("PASSED"); else { System.out.println("FAILED");counter++;}
		System.out.println("\n");

		if (counter > 0) {
			System.out.println("SOME CASES FAILED " + counter);
		}
		else {
			System.out.println("EVERYTHING PASSED BUT CHECK FORMATTING");
		}

		List<String> dump = server.dump();
		System.out.println("Dumped: ");
		System.out.println(dump);
	}
}