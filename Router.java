package networksimulation;

import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.time.LocalTime;

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
        sendTable();
    }
    
    public RoutingTable getRoutingTable()
    {
        return table;
    }
    
    private void processPacket(Packet pack)
    {
        
        Entry direction = table.getLongestMatch(pack.getDestination());
        //System.out.println(direction.getDestinationNetwork().getAddress() + direction.amIUltimate());
        
        if(pack.notDying())
        {
            pack.decreaseHops();
        }else 
        {
            System.out.println("Packet with message :" + pack.getText() + " has died");
        }
        
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
                    
                    forwardPacket(pack, direction);
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
    
    public void sendTable()
    {
        for(int i = 0; i < counter; i++)
        {
            if(getConnection(i).getOtherAppliance(this).amIRouter())
            {
                ((Router)getConnection(i).getOtherAppliance(this)).updateTable(getRoutingTable(), this);
            }
        }
    }
    
    public void updateTable(RoutingTable update, Appliance sender)
    {
        
        for(int i = 0; i < update.getCounter(); i++)
        {
            //addConnection(sender);
            for(int j = 0; j < getRoutingTable().getCounter(); j++)
            {
                if(update.getEntry(i).getDestinationNetwork().getAddress().equals(this.getIP().getAddress()))
                {
                    break;
                }
                if(update.getEntry(i).getDestinationNetwork().getAddress().equals(this.getRoutingTable().getEntry(j).getDestinationNetwork().getAddress()))
                {
                    break;
                }else
                {
                    if(update.getEntry(i).amIDirectlyConnected())
                    {
                        getRoutingTable().addEntry(new RemoteNetworkEntry('R', update.getEntry(i).getDestinationNetwork(), update.getEntry(i).getSubnetMask() ,120, 1, sender.getIP(), LocalTime.now(), update.getEntry(i).getOutgoingInterface()));
                    }else if(update.getEntry(i).amIRemoteNetwork())
                    {
                        getRoutingTable().addEntry(new RemoteNetworkEntry('R', update.getEntry(i).getDestinationNetwork(), update.getEntry(i).getSubnetMask() ,120, ((RemoteNetworkEntry)update.getEntry(i)).getMetric() + 1, sender.getIP(), LocalTime.now(), update.getEntry(i).getOutgoingInterface()));
                    }                      
                }
            }
        }
    }
    
    public void addConnection(Appliance dev)
    {
        DirectlyConnectedEntry ent  = new DirectlyConnectedEntry('C', dev.getIP(), 0 , "Serial0/0/0");
        table.addEntry(ent);
        sendTable();
    }
    

    
    public void forwardPacket(Packet pack, Entry destination)
    {
        if(destination.amIRemoteNetwork())
        {
            forwardPacket(pack, (RemoteNetworkEntry) destination);
            return;
        }
        for(int i = 0; i < counter; i++)
        {
            if(getConnection(i).getOtherAppliance(this).getIP().equals(destination.getDestinationNetwork()) )
            {
                //System.out.println("Passed");
                getConnection(i).getOtherAppliance(this).packetArrived(pack);
                break;
            }
        }
    }
    
    
    public void forwardPacket(Packet pack, RemoteNetworkEntry destination)
    {
        for(int i = 0; i < counter; i++)
        {
            if(getConnection(i).getOtherAppliance(this).getIP().equals(destination.getNextHop()) )
            {
                //System.out.println("Passed");
                getConnection(i).getOtherAppliance(this).packetArrived(pack);
                break;
            }
        }
    }
    
    public void run()
    {
        //System.out.println("Thread started");
        Packet pack = null;
        
        while(true)
        {
            try
            {
                TimeUnit.SECONDS.sleep(5);
                //System.out.println(ready);
            }catch(Exception ex)
            {
                
            }
            
            
            if(ready)
            {
                System.out.println("Routing packet");
                pack = processingPacket;
                ready = false;
                processPacket(pack);
            }
        }
    }
    
    public void deleteRouter()
    {
        for(int i = 0; i < counter; i++)
        {
            getConnection(i).getOtherAppliance(this).removeConnection(this);
        }
    }
    
    
    

}

