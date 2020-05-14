package networksimulation;

import java.time.LocalTime;
import java.util.Scanner;

/**
 *
 * @author Augustas Valaitis Informatika  grupė
 */
public class NetworkSimulation {
    
    static Scanner scan = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        final int MAXI = 25;
        int choice = 0;
        
        Router[] routers = new Router[MAXI];
        int routerNum = 0;
        
        Computer[] computers = new Computer[MAXI];
        int computerNum = 0;
        
        Wire[] connections = new Wire[MAXI];
        int wireNum = 0;
        
        while(true)
        {
            try
            {

            choice = getChoice();
            if(choice == 0)
            {
                System.out.println("Not acceptable choice");
            }else if(choice == 1)
            {
                routers[routerNum] = new Router(makeAddress(routers, computers, routerNum, computerNum));
                routers[routerNum].start();
                routerNum++;
                
                System.out.println("Router" + routerNum + " was created with IP address of " + routers[routerNum-1].getIP().getAddress());
                
            }else if(choice == 2)
            {
                computers[computerNum] = new Computer(makeAddress(routers, computers, routerNum, computerNum));
                computerNum++;
                System.out.println("Computer" + computerNum + " was created with IP address of " + computers[computerNum-1].getIP().getAddress());
            }else if(choice == 3)
            {
                int list = 0;
                System.out.println("Here are all the possible devices to connect");
                System.out.println();
                while(list < routerNum)
                {
                    System.out.println((list + 1) + ". Router" + (list+1) + " with IP address of " + routers[list].getIP().getAddress());
                    list++;
                }
                
                while(list - routerNum < computerNum)
                {
                    System.out.println((list + 1) + ". Computer" + (list - routerNum + 1) + " with IP address of " + computers[list - routerNum].getIP().getAddress());
                    list++;
                }
                
                try
                {
                    Appliance app1;
                    Appliance app2;
                    System.out.println("Your first choice:");
                    choice = Integer.parseInt(scan.nextLine());
                    if(choice > routerNum)
                    {
                        choice = choice - routerNum; 
                        app1 = computers[choice-1];
                    }else app1 = routers[choice-1];
                    
                    System.out.println("Your second choice:");
                    choice = Integer.parseInt(scan.nextLine());
                    if(choice > routerNum)
                    {
                        choice = choice - routerNum; 
                        app2 = computers[choice-1];
                    }else app2 = routers[choice-1];
                    
                    connections[wireNum] = new Wire(app1, app2);
                    wireNum++;
                    
                }catch(Exception ex)
                {
                    ex.printStackTrace();
                }
                
                
                
                        
            }else if(choice == 4)
            {
                System.out.println("Here are the routers:");
                for(int i = 0; i < routerNum; i++)
                {
                    System.out.println( (i+1) + ". Router" + (i+1));
                }
                try
                {
                    choice =  Integer.parseInt(scan.nextLine());
                    routers[choice-1].getRoutingTable().printTable();
                }catch(Exception ex)
                {
                    ex.printStackTrace();
                }
                
            }else if(choice == 5)
            {
                System.out.println("Here are all the available computers");
                for(int i = 0; i < computerNum; i++)
                {
                    System.out.println( (i + 1) + ". Computer" + (i+1) + " with IP address of " + computers[i].getIP().getAddress());
                }
                
                try
                {
                    choice = Integer.parseInt(scan.nextLine());
                    String mess = new String();
                    System.out.println("Enter data to send");
                    mess = scan.nextLine();
                    Address addr;
                    System.out.println("Enter destination address");
                    addr = makeAddress(routers, computers, routerNum, computerNum);
                    computers[choice-1].sendMessage(mess, addr);
                }catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }else if(choice == 6)
            {
                System.out.println("Here are the routers:");
                for(int i = 0; i < routerNum; i++)
                {
                    System.out.println((i+1) + ". Router" + (i+1));
                }
                try
                {
                    choice = Integer.parseInt(scan.nextLine());
                    routers[choice-1].deleteRouter();
                    for(int i = 0; i < routerNum; i++)
                    {
                        routers[routerNum] = routers[routerNum+1];
                    }
                    routerNum--;
                }catch(Exception ex)
                {
                    ex.printStackTrace();
                }
    
            }else if(choice == 7)
            {
                System.out.println("Here are the computers:");
                for(int i = 0; i < computerNum; i++)
                {
                    System.out.println((i+1) + ". Computer" + (i+1));
                }
                try
                {
                    choice = Integer.parseInt(scan.nextLine());
                    computers[choice-1].deleteComputer();
                    for(int i = 0; i < routerNum; i++)
                    {
                        computers[computerNum] = computers[computerNum+1];
                    }
                    computerNum--;
                }catch(Exception ex)
                {
                    ex.printStackTrace();
                }
                
            }else if(choice == 8)
            {
                System.exit(0);
            }
            else System.out.println("Not acceptable choice");
        } catch(Exception ex)
        {
            System.out.println("Unacceptable input");
        }
        }
        
        
    }
    
    public static int getChoice()
    {
        int choice = 0;
        System.out.println("Select what you would like to do:");
        System.out.println("1.Create a router");
        System.out.println("2.Create a computer");
        System.out.println("3.Create a link");
        System.out.println("4.Show routing table of a router");
        System.out.println("5.Send packet");
        System.out.println("6.Delete router");
        System.out.println("7.Delete computer");
        System.out.println("8.Terminate program");
        
        try
        {
            choice = Integer.parseInt(scan.nextLine());
        }
        
        
        catch(Exception ex)
        {
            ex.getStackTrace();
            return 0;
        }
        //System.out.println(choice);
        return choice;
    }
    
    public static Address makeAddress(Router[] routers, Computer[] computers, int routerNum, int computerNum)
    {
        int octet1, octet2, octet3, octet4;
        System.out.println("Enter the first octet of device's IP address");
        octet1 = Integer.parseInt(scan.nextLine());
        System.out.println("Enter the second octet of device's IP address");
        octet2 = Integer.parseInt(scan.nextLine());
        System.out.println("Enter the third octet of device's IP address");
        octet3 = Integer.parseInt(scan.nextLine());
        System.out.println("Enter the fourth octet of device's IP address");
        octet4 = Integer.parseInt(scan.nextLine());
        
        if(!checkAddress(routers, computers, routerNum, computerNum, new Address(octet1, octet2, octet3, octet4)))
        {
            return new Address(0,0,0,0);
        }
        
        return new Address(octet1, octet2, octet3, octet4);
    }
    
    public static boolean checkAddress(Router[] arr1, Computer[] arr2, int count1, int count2, Address addr)
    {
        for(int i = 0; i < count1; i++)
        {
            if(arr1[i].getIP().getAddress().equals(addr.getAddress()))
            {
                System.out.println("Address is already taken");
                return false;
            }
        }
        
        
        for(int i = 0; i < count2; i++)
        {
            if(arr2[i].getIP().getAddress() == addr.getAddress())
            {
                System.out.println("Address is already taken");
                return false;
            }
        }
        
        return true;
    }
    
}


