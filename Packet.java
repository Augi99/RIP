package networksimulation;

/**
 *
 * @author Augustas Valaitis Informatika  grupė
 */
public class Packet 
{
        private Address destination;
	private String text;
	
	public Packet(String text, Address addr)
        {
            destination = addr;
       	    this.text=text;
	}
	
	public int getSize() 
        {
		return 1024;
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
