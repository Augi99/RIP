package networksimulation;

import java.util.Vector;

/**
 *
 * @author Augustas Valaitis 1 grupė Informatika
 */
public class Router extends Appliance{
    
    private RoutingTable table;
    //private int counter;
    private boolean ready;
    private Packet processingPacket;
    
    public Router(Address ip)
    {
        super(ip);
        table = new RoutingTable();
        ready = false;
        processingPacket = null;
    }
    
    public void setRoutingTable(RoutingTable rTable)
    {
        table  = rTable;
    }
    
    public RoutingTable getRoutingTable()
    {
        return table;
    }
    
    private void processPacket(Packet pack)
    {
        Entry direction = table.getLongestMatch(pack.getDestination());
        //System.out.println(direction.getDestinationNetwork().getAddress() + direction.amIUltimate());
        if(direction.amIUltimate())
        {
            forwardPacket(pack, direction);
            return;
        }
        
        if(direction.amIParent())
        {
            for(int i = 0; i < ((SubnettedEntry)direction).getCount(); i++)
            {
                if(table.isPerfectMatch(pack.getDestination(), ((SubnettedEntry)direction).getChild(i).getDestinationNetwork()))
                {
                    //TO DO
                    return;
                }
            }
        }
        //System.out.println(pack.getDestination().getAddress());
        direction = table.getLongestMatch(pack.getDestination(), direction);
        //System.out.println(direction);
        if(direction.amIUltimate())
        {
            forwardPacket(pack, direction);
            return;
        }
        
        System.out.println("Dropping the packet");
            
    }
    
    public void packetArrived(Packet pack)
    {
        //processPacket(pack);
        processingPacket = pack;
        ready = true;
    }
    
    public boolean amIRouter()
    {
        return true;
    }
    
    public boolean amIComputer()
    {
        return false;
    }
    
    public void printPacket(Packet pack)
    {
        System.out.println(pack.getText());
    }
    
    public void addConnection(Appliance dev)
    {
        DirectlyConnectedEntry ent  = new DirectlyConnectedEntry('C', dev.getIP(), 8 , "Serial0/0/0");
        table.addEntry(ent);
    }
    
    public void forwardPacket(Packet pack, Entry destination)
    {
        //
        //System.out.println(counter);
        //System.out.println(destination.getDestinationNetwork().getAddress());
        for(int i = 0; i < counter; i++)
        {
            //printPacket(pack);
            //System.out.println(getConnection(i).getOtherAppliance(this).getIP().getAddress());
            if(getConnection(i).getOtherAppliance(this).getIP().equals(destination.getDestinationNetwork()) )
            {
                //System.out.println("Passed");
                getConnection(i).getOtherAppliance(this).packetArrived(pack);
                break;
            }
        }
    }
    
    public void run()
    {
        Packet pack = null;
        while(true)
        {
            if(ready)
            {
                pack = processingPacket;
                ready = false;
                processPacket(pack);
            }
        }
    }

}

