package networksimulation;

/**
 *
 * @author Augustas Valaitis Informatika  grupė
 */
public class Packet 
{
        private Address destination;
	private String text;
        private int hops;
	
	public Packet(String text, Address addr)
        {
            destination = addr;
       	    this.text=text;
            hops = 25;
	}
	
	public int getSize() 
        {
		return 1024;
	}
        
        public void decreaseHops()
        {
            hops--;
        }
        
        public boolean notDying()
        {
            if(hops > 1)
            {
                return true;
            }else return false;
        }
        
        public int getHops()
        {
            return hops;
        }
        
        public void setText(String data)
        {
            text = data;
        }
        
        public String getText()
        {
            return text;
        }
        
        public void setDestination(Address addr)
        {
            destination = addr;
        }
        
        public Address getDestination()
        {
            return destination;
        }
}
