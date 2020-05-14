package networksimulation;

import static java.time.temporal.ChronoUnit.SECONDS;

import java.time.LocalTime;

/**
 *
 * @author Augustas Valaitis Informatika 2 kursas
 */
public class RemoteNetworkEntry extends Entry{
    
    private int administrativeDistance;
    private int metric;
    private Address nextHop;
    private LocalTime routeTimestamp;
    
    public RemoteNetworkEntry(char route, Address destination, int mask, int distance, int metr, Address hop, LocalTime stamp, String outgoing)
    {
        super(route, destination, mask, outgoing);
        administrativeDistance = distance;
        metric = metr;  
        nextHop = hop;
        routeTimestamp = stamp;
    }
    
    public void setAdminstrativeDistance(int distance)
    {
        administrativeDistance = distance;
    }
    public void setMetric(int metr)
    {
        metric = metr;
    }
    public void setNextHop(Address hop)
    {
        nextHop = hop;
    }
    public void setRouteTimestamp(LocalTime routeTimestamp)
    {
        routeTimestamp = routeTimestamp;
    }
    
    public int getAdministrativeDistance()
    {
        return administrativeDistance;
    }
    public int getMetric()
    {
        return metric;
    }
    public Address getNextHop()
    {
        return nextHop;
    }
    public LocalTime getRouteTimestamp()
    {
        LocalTime now = LocalTime.now();
        long seconds = now.until(routeTimestamp, SECONDS);
        int hours, minutes;
        minutes = (int)seconds / 60;
        seconds = (int)seconds % 60;
        hours = minutes / 60;
        minutes = minutes % 60;
        
        return LocalTime.of(hours, minutes, (int)seconds);
    }
    
    public LocalTime getStamp()
    {
        return routeTimestamp;
    }
    
    public void writeEntry()
    {
        if(!getSubnetted())
        {
            System.out.println(getRouteSource() + "       " + getDestinationNetwork().getAddress() + "/" + getSubnetMask() + "  [" + getAdministrativeDistance() + "/" + getMetric() + "]  via  " + getNextHop().getAddress() + ",  " + getStamp() + "  " + getOutgoingInterface());

        }else System.out.println(getRouteSource() + "         " + getDestinationNetwork().getAddress() + "/" + getSubnetMask() + "  [" + getAdministrativeDistance() + "/" + getMetric() + "]  via  " + getNextHop().getAddress() + ",  " + getStamp() + "  " + getOutgoingInterface());
    }
    
    public boolean amIRemoteNetwork()
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
