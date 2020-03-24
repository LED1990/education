package mongo;

import model.mongo.Computer;
import model.mongo.Interfaces;

import java.util.ArrayList;
import java.util.List;

public class ComputerMock {

    public static List<Computer> mockComputers(){

        List<Computer> computers = new ArrayList<>();

        List<Interfaces> computerInterfaces = new ArrayList<>();
        computerInterfaces.add(new Interfaces("mause", "MS"));
        computerInterfaces.add(new Interfaces("keyboard", "KY"));
        computers.add(new Computer("Dell", computerInterfaces));

        List<Interfaces> computer2Interfaces = new ArrayList<>();
        computer2Interfaces.add(new Interfaces("mouse", "MS"));
        computer2Interfaces.add(new Interfaces("monitor", "MO"));
        computers.add(new Computer("Dell", computer2Interfaces));

        List<Interfaces> computer3Interfaces = new ArrayList<>();
        computer3Interfaces.add(new Interfaces("monitor", "MO"));
        computer3Interfaces.add(new Interfaces("keyboard", "KY"));
        computers.add(new Computer("Optimus", computer3Interfaces));

        List<Interfaces> computer4Interfaces = new ArrayList<>();
        computer4Interfaces.add(new Interfaces("headphones", "HP"));
        computer4Interfaces.add(new Interfaces("monitor", "MO"));
        computers.add(new Computer("Optimus", computer4Interfaces));

        List<Interfaces> computer5Interfaces = new ArrayList<>();
        computer5Interfaces.add(new Interfaces("headphones", "HP"));
        computer5Interfaces.add(new Interfaces("mouse", "MS"));
        computers.add(new Computer("HP", computer5Interfaces));

        List<Interfaces> computer6Interfaces = new ArrayList<>();
        computer6Interfaces.add(new Interfaces("keyboard", "KY"));
        computer6Interfaces.add(new Interfaces("monitor", "MO"));
        computers.add(new Computer("ASUS", computer6Interfaces));

        List<Interfaces> computer7Interfaces = new ArrayList<>();
        computer7Interfaces.add(new Interfaces("keyboard", "KY"));
        computer7Interfaces.add(new Interfaces("mouse", "MS"));
        computers.add(new Computer("ASUS", computer7Interfaces));

        List<Interfaces> computer8Interfaces = new ArrayList<>();
        computer8Interfaces.add(new Interfaces("headphones", "HP"));
        computer8Interfaces.add(new Interfaces("monitor", "MO"));
        computers.add(new Computer("Dell", computer8Interfaces));

        return computers;
    }
}
