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
    
    public Wire getLink()
    {
        return connection;
    }
    
    public void sendMessage(String text, Address destination)
    {
        Packet pack = new Packet(text, destination);
        connection.getOtherAppliance(this).packetArrived(pack);
        //System.out.println("Sent");
    }
    
    public void packetArrived(Packet pack)
    {
        if(pack.getDestination().getAddress().equals(getIP().getAddress()))
        {
            System.out.println("Computer " + getIP().getAddress() + " got data:");
            System.out.println(pack.getText());
        }
    }
    
    public void deleteComputer()
    {
        for(int i = 0; i < counter; i++)
        {
            getConnection(i).getOtherAppliance(this).removeConnection(this);
        }
    }
}
