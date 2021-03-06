package networksimulation;

/**
 *
 * @author Augustas Valaitis Informatika 1 grupe
 */
public class Address {
    
    private int octet1;
    private int octet2;
    private int octet3;
    private int octet4;
    
    public Address(int data1, int data2, int data3, int data4)
    {
        if(data1 > 255 || data2 > 255 || data3 > 255 || data4 > 255)
        {
            System.out.println("Address is not valid");
            octet1 = 0;
            octet2 = 0;
            octet3 = 0;
            octet4 = 0;
        }
        
        if(data1 < 0 || data2 < 0 || data3 < 0 || data4 < 0)
        {
            System.out.println("Address is not valid");
            octet1 = 0;
            octet2 = 0;
            octet3 = 0;
            octet4 = 0;
        }
        octet1 = data1;
        octet2 = data2;
        octet3 = data3;
        octet4 = data4;
    }
    
    public String getAddress()
    {
        return Integer.toString(octet1) + '.' + Integer.toString(octet2) + '.' + Integer.toString(octet3) + '.' + Integer.toString(octet4);
    }
    
    public String getAddressInBinary()
    {
        return Integer.toString(octet1, 2) + Integer.toString(octet2, 2) + Integer.toString(octet3, 2) + Integer.toString(octet4, 2);
    }
}
