package networksimulation;

import java.time.LocalTime;

/**
 *
 * @author Augustas Valaitis Informatika  grupė
 */
public class NetworkSimulation {
    public static void main(String[] args) {
        DirectlyConnectedEntry a = new DirectlyConnectedEntry('C', new Address(256,15,12,14), 25 , "Serial0/0/1");
        a.writeEntry();
        RemoteNetworkEntry b = new RemoteNetworkEntry('R', new Address(128, 7, 6, 21), 24, 120, 2, new Address(157, 12, 55, 10), LocalTime.now(), "Serial0/0/1");
        b.writeEntry();
        
        RoutingTable c = new RoutingTable();
        c.addEntry(a);
        c.addEntry(b);
        
        c.printTable();
        
        System.out.println((c.getLongestMatch(new Address(256,15,12,13))).getDestinationNetwork().getAddress());
        
        Router r1 = new Router(new Address(127,45,69,47));
        Router r2 = new Router(new Address(137,78,48,11));
        Wire w = new Wire(r1, r2);
        r1.getRoutingTable().addEntry(a);
        w.foo();
        
        r1.getRoutingTable().printTable();
        r2.getRoutingTable().printTable();
        
        w.transferPacket(new Packet("woooo", new Address(256,15,12,14)), r2);
        
        System.out.println(" ");
        System.out.println(" ");
        
        Router r3 = new Router(new Address(124,14,45,72));
        Computer c1 = new Computer(new Address(124,13,67,41));
        Computer c2 = new Computer(new Address(147,88,14,2));
        Wire w1 = new Wire(r3, c1);
        Wire w2 = new Wire(r3, c2);
        r3.start();
        
        c1.sendMessage("Message", new Address(147,88,14,2));
    }
    
}
