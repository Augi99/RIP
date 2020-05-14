package networksimulation;

/**
 *
 * @author Augustas Valaitis Informatika 1 grupe
 */
public class RoutingTable {

    Entry[] content;
    private int num;
    private final static int MAX = 1286;
    
    public RoutingTable()
    {
        num = 0;
        content = new Entry[MAX];
    }
    
    public void addEntry(RemoteNetworkEntry entry)
    {
        addEntry((Entry)entry);
    }
    
    public void addEntry(DirectlyConnectedEntry entry)
    {
        addEntry((Entry)entry);
    }
    
    public void addEntry(SubnettedEntry entry)
    {
        addEntry((Entry)entry);
    }
    
    public void addEntry(Entry entry)
    {
        content[num] = entry;
        num++;
    }
    
    public Entry getEntry(int n)
    {
        return content[n];
    }
    
    public void printTable()
    {
        for(int i = 0; i < num; i++)
        {
            content[i].writeEntry();
        }
    }
    
    public void addSubnet(Address adr, int mask, Entry[] ent)
    {
        int nets = ent.length, masknum = 0;
        int[] masks = new int[64]; 
        masks[masknum] = ent[0].getSubnetMask();
        masknum++;
        for(int i = 1; i < ent.length; i++)
        {
            for(int j = 0; j < masknum; j++)
            {
                if(ent[i].getSubnetMask() == masks[j])
                {
                    masks[masknum] = ent[i].getSubnetMask();
                    masknum++;
                }
            }
            
        }
        SubnettedEntry entr = new SubnettedEntry(' ', adr, mask, " ", nets, masknum);
        
        
        
        for(int i = 0; i < nets; i++)
        {
            ent[i].setSubnetted(true);
            entr.addChild(ent[i]);
        }
        
        addEntry(entr);
        
        for(int i = 0; i < nets; i++)
        {
            ent[i].setSubnetted(true);
            addEntry(ent[i]);
        }
    }
    
    
    public Entry getLongestMatch(Address IP)
    {
        String IPbits1 = IP.getAddressInBinary();
        String IPbits2;
        int matching;
        int bestMatching = 0;
        Entry bestEntry = null;
        
        for(int i = 0; i < num; i++)
        {
            matching = 0;
            IPbits2 = content[i].getDestinationNetwork().getAddressInBinary();
            try
            {
                for(int j = 0; j < 32 && !content[i].amIChild() ; j++)
                {
                    if(IPbits2.charAt(j) == IPbits1.charAt(j))
                    {
                        matching++;
                    }else break;
                }
            }catch(Exception ex)
            {
                
            }
            
            if(matching > bestMatching)
            {
                bestMatching = matching;
                bestEntry = content[i];
            }
        }
        
        return bestEntry;
    }
    
    
    public boolean isPerfectMatch(Address destination, Address direction)
    {
        if(destination.getAddressInBinary() == destination.getAddressInBinary())
        {
            return true;
        }else return false;
    }
    
    public Entry getLongestMatch(Address IP, Entry except)
    {
        printTable();
        IP.getAddress();
        String IPbits1 = IP.getAddressInBinary();
        String IPbits2;
        int matching;
        int bestMatching = 0;
        Entry bestEntry = null;
        
        for(int i = 0; i < num; i++)
        {
            matching = 0;
            IPbits2 = content[i].getDestinationNetwork().getAddressInBinary();
            for(int j = 0; j < 32 && !content[i].amIChild() && !except.equals(content[i]); j++)
            {
                if(IPbits2.charAt(j) == IPbits1.charAt(j))
                {
                    matching++;
                }else break;
            }
            if(matching > bestMatching)
            {
                bestMatching = matching;
                bestEntry = content[i];
            }
        }
        
        return bestEntry;
    }
    
}
