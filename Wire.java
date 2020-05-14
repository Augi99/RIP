package networksimulation;

/**
 *
 * @author Augustas Valaitis Informatika 1 grupe
 */
public class Wire {
    private Appliance device1;
    private Appliance device2;
    
    public Wire(Appliance dev1, Appliance dev2)
    {
        device1 = dev1;
        device2 = dev2;
        dev1.addLink(this);
        dev2.addLink(this);
    }
    
    public Appliance getOtherAppliance(Appliance requester)
    {
        if(requester.equals(device1))
        {
            return device2;
        }else if(requester.equals(device2))
        {
            return device1;
        }else return null;
    }
    
    public void transferPacket(Packet pack, Appliance sender)
    {
        if(sender.equals(device1))
        {
            if(sender.amIRouter())
            {
                ((Router)device2).packetArrived(pack);
            }else if(sender.amIComputer())
            {
                device2.packetArrived(pack);
            }
            
        }
        
        if(sender.equals(device2))
        {
            if(sender.amIRouter())
            {
                ((Router)device1).packetArrived(pack);
            }else if(sender.amIComputer())
            {
                device1.packetArrived(pack);
            }
        }
    }
}
