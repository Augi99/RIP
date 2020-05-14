package networksimulation;

/**
 *
 * @author Augustas Valaitis Informatika 1 grupe
 */
public class Computer extends Appliance
{
    Wire connection;
    public Computer(Address ip)
    {
        super(ip);
        connection = null;
    }
    
    public void addLink(Wire conn)
    {
        connection = conn;
    }
    
    public void sendMessage(String text, Address destination)
    {
        Packet pack = new Packet(text, destination);
        connection.getOtherAppliance(this).packetArrived(pack);
    }
    
    public void packetArrived(Packet pack)
    {
        if(pack.getDestination().getAddress().equals(getIP().getAddress()))
        {
            System.out.println(pack.getText());
        }
    }
    
}
