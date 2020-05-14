package networksimulation;

import java.util.Vector;

/**
 *
 * @author Augustas Valaitis Informatika 1 grupė
 */
public class Appliance extends Thread{
    private Address ip;
    private Wire[] connections;
    protected int counter;
    protected static final int MAX = 256;
    
    
    public Appliance(Address addr)
    {
        ip = addr;
        connections = new Wire[MAX];
        counter = 0;
    }
    
    public Appliance(Address addr, Wire conn)
    {
        this(addr);
        
        connections[counter] = conn;
        counter++;
        
    }
    
    public Appliance(Address addr, Wire conns[], int num)
    {
        this(addr);
        
        counter = num;
        
        for(int i = 0; i < counter; i++)
        {
            connections[i] = conns[i];
        }
    }
    
    public Address getIP()
    {
        return ip;
    }
    
    public Wire getConnection(int num)
    {
        return connections[num];
    }
    
    public void packetArrived(Packet pack)
    {
        System.out.println(pack.getText());
    }
    
    public void addLink(Wire conn)
    {
        connections[counter] = conn;
        counter++;
        if(amIRouter())
        {
            addConnection(conn.getOtherAppliance(this));
            sendTable();
        }
        
    }
    
    public void sendTable()
    {
        
    }
    
    public boolean amIRouter()
    {
        return false;
    }
    
    public boolean amIComputer()
    {
        return false;
    }
    
    public void addConnection(Appliance dev)
    {
        
    }
    
    public void removeConnection(Appliance removed)
    {
        for(int i = 0; i < counter; i++)
        {
            if(amIComputer())
            {
                ((Computer)this).addLink(null);
            }else if(removed.equals(connections[i]))
            {
                for(int j = i; j < counter; j++)
                {
                    connections[j] = connections[j+1];
                    
                    break;
                }
                counter--;
            }
        }
        
        if(amIRouter())
        {
            sendTable();
        }
    }
}
