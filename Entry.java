package networksimulation;

/**
 *
 * @author Augustas Valaitis Informatika 1 grupe
 */
public class Entry {
    private char routeSource;
    private Address destinationNetwork;
    private int subnetMask;
    private String outgoingInterface;
    protected boolean subnetted;
    
    public Entry(char route, Address destination, int mask, String outgoing)
    {
        routeSource = route;
        destinationNetwork = destination;
        subnetMask = mask;
        outgoingInterface = outgoing;
        subnetted = false;
    }
    
    
    public void setRouteSource(char route)
    {
        routeSource = route;
    }
    
    public void setDestinationNetwork(Address destination)
    {
        destinationNetwork = destination;
    }
    
    public void setSubnetMask(int mask)
    {
        subnetMask = mask;
    }
    
    public void setOutgoingInterface(String outgoing)
    {
        outgoingInterface = outgoing;
    }
    
    public void setSubnetted(boolean state)
    {
        subnetted = state;
    }
    
    public char getRouteSource()
    {
        return routeSource;
    }
    
    public Address getDestinationNetwork()
    {
        return destinationNetwork;
    }
    
    public int getSubnetMask()
    {
        return subnetMask;
    }
    
    public boolean getSubnetted()
    {
        return subnetted;
    }
        
    public String getOutgoingInterface()
    {
        return outgoingInterface;
    }
    
    public boolean amIDirectlyConnected()
    {
        return false;
    }
    
    public boolean amIRemoteNetwork()
    {
        return false;
    }
    
    public void writeEntry()
    {
        
    }
    
    public boolean amIUltimate()
    {
        return false;
    }
    
    public boolean amILevel1()
    {
        return false;
    }
    
    public boolean amIParent()
    {
        return false;
    }
    
    public boolean amIChild()
    {
        return false;
    }
    
    public void lookup()
    {
        
    }
    
    
}
