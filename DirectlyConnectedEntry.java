package networksimulation;

/**
 *
 * @author Augustas Valaitis Informatika 2 kursas
 */
public class DirectlyConnectedEntry extends Entry{
    
    
    public DirectlyConnectedEntry(char route, Address destination, int mask,  String outgoing)
    {
        super(route, destination, mask, outgoing);
    }
    
    public void writeEntry()
    {
        if(!getSubnetted())
        {
            if(getSubnetMask() > 0)
            {
                System.out.println(getRouteSource()+ "       " + getDestinationNetwork().getAddress() + "/" + getSubnetMask() + " is directly connected, " + getOutgoingInterface());
            }else System.out.println(getRouteSource()+ "       " + getDestinationNetwork().getAddress()  + " is directly connected, " + getOutgoingInterface());
            
        }else System.out.println(getRouteSource()+ "         " + getDestinationNetwork().getAddress() + "/" + getSubnetMask() + " is directly connected, " + getOutgoingInterface());
    }
    
    public boolean amIDirectlyConnected()
    {
        return true;
    }
    
    public boolean amIUltimate()
    {
        return true;
    }
    
    public boolean amILevel1()
    {
        if(getSubnetted())
        {
            return false;
        } else return true;
        
    }
    
    public boolean amIParent()
    {
        return false;
    }
    
    public boolean amIChild()
    {
        if(getSubnetted())
        {
            return true;
        } else return false;
    }
}
