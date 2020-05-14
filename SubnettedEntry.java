package networksimulation;

/**
 *
 * @author Augustas Valaitis Informatika 
 */
public class SubnettedEntry extends Entry{
    
    private int subnets;
    private int masks;
    private Entry[] children;
    int count;
    
    public SubnettedEntry(char route, Address destination, int mask, String outgoing, int subnetnum, int masknum)
    {
        super(route, destination, mask, outgoing);
        subnets = subnetnum;
        masks = masknum;
        children = new Entry[masks];
        count = 0;
    }
    
    public void setSubnets(int num)
    {
        subnets = num;
    }
    
    public void setMasks(int num)
    {
        masks = num;
    }
    
    public int getSubnets()
    {
        return subnets;
    }
    
    public int getMasks()
    {
        return masks;
    }
    
    public void writeEntry()
    {
        System.out.println("    " +  getDestinationNetwork() + "/" + getSubnetMask() + " is variably subnetted, " + getSubnets() + " subnets, " + getMasks() + " masks");
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
        return true;
    }
    
    public boolean amIChild()
    {
        return false;
    }
    
    public void addChild(Entry ent)
    {
        children[count] = ent;
        count++;
    }
    
    public Entry getChild(int which)
    {
        if(which <= count)
        {
            return children[which];
        }else return null;
    }
    
    public int getCount()
    {
        return count;
    }
}
