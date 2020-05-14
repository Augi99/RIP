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
