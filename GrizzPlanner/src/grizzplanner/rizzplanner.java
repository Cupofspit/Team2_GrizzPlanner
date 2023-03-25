package grizzplanner;

public class rizzplanner {
    
}
public ParkingLot(String name, int capacity, int availability) {
    this.name = name;
    this.capacity = capacity;
    this.availability = availability;
}

public String getName() {
    return name;
}

public int getCapacity() {
    return capacity;
}

public int getAvailability() {
    return availability;
}

public void setAvailability(int availability) {
    this.availability = availability;
}

//

public Traffic() {
    // Create parking lots and add them to the list
    ParkingLot lot1 = new ParkingLot("Lot 1", 100, 80);
    ParkingLot lot2 = new ParkingLot("Lot 2", 150, 120);
    ParkingLot lot3 = new ParkingLot("Lot 3", 200, 180);
    ParkingLot lot4 = new ParkingLot("Lot 4", 250, 200);

    parkingLots = new ArrayList<>();
    parkingLots.add(lot1);
    parkingLots.add(lot2);
    parkingLots.add(lot3);
    parkingLots.add(lot4);
}

public List<ParkingLot> getParkingLots() {
    return parkingLots;
}

//

@Override
public void start(Stage primaryStage) {
    Traffic traffic = new Traffic();

    // Create a VBox to hold parking lot information
    VBox parkingLotBox = new VBox();
    for (ParkingLot lot : traffic.getParkingLots()) {
        Label nameLabel = new Label(lot.getName());
        Label capacityLabel = new Label("Capacity: " + lot.getCapacity());
        Label availabilityLabel = new Label("Availability: " + lot.getAvailability());

        parkingLotBox.getChildren().addAll(nameLabel, capacityLabel, availabilityLabel);
    }

    Scene scene = new Scene(parkingLotBox, 400, 300);

    primaryStage.setTitle("GrizzPlanner");
    primaryStage.setScene(scene);
    primaryStage.show();
}

public static void main(String[] args) {
    launch(args);
}

//chatgpt input:
//In this code, the ParkingLot class represents a single parking lot, with a name, capacity, and availability. The Traffic class contains a list of ParkingLot objects, and initializes them in its constructor. The GrizzPlanner application creates a Traffic object, and displays the parking lot information in a VBox. This VBox is created using a for loop to iterate over the parking lots in the Traffic object, and creates Labels for each piece of information (name, capacity, availability).
//I am creating a student calendar wesbite using java and javaFX that helps students stay organized and also informs the students of how busy the campuses parking lots are to help them guage how early they need to get there to be on time for class.
//Right now I need the following turned into Java code:
//Pakring Lot Class and Traffic Class
//Student looks at app- it will show parking lots and how busy they are, need object created for each parking lot (main class with all parking lots), somehow input how busy a lot is from main class, get parking lot information in a while loop
//Use or change the following code to implement my request stated above:
