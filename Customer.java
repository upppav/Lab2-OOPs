package oops_lab_2;

import java.util.*;
public class Customer
{
    static ArrayList <PizzaPasta> queue= new ArrayList();
   
    byte size,crust,flavor,toppings[]; 
    byte sauce,type; 
    public static void main(String args[])
    {

    System.out.println(" Greetings! Here is your menu options and pricing. We serve pizzas and pastas.");
    Customer c= new Customer();

    Scanner in=new Scanner(System.in);
    for(;;)
    {
        System.out.println("Is customer ready for placing order?true/false");
        boolean res=in.nextBoolean();
        if(res==false)
        break;
        else
            c.order();
    }
    c.kitchen(); 
    }
    private void order()
    {
    
        Scanner in=new Scanner(System.in);
        System.out.println("Input 1. for Pizza or 2. Pasta.");
        byte choice=in.nextByte();
        switch (choice)
        {
            case 1:
                System.out.println(" For pizza lovers available:\n 3 sizes (cost 'x' times)-- x=1. Small 2. Medium 3. Large; \n 2 crusts (no charge) -- 1. Thin or 2. Thick; \n 3 flavors -- 1. veg (20x USD) 2. non-veg (25x USD) 3. vegan (15x USD); \n toppings (1 USD each)-- 1.Cheese,2.Mushroom,3.Tomato,4.Jalapeno,5.Spinach \n");
                System.out.println(" Pizza size 1. Small 2. Medium 3. Large?");
                byte size=in.nextByte();
                System.out.println(" Crust 1. Thin or 2. Thick?");
                byte crust=in.nextByte();
                System.out.println("Flavors 1. veg or 2. non-veg or 3. vegan ?");
                byte flavor=in.nextByte();         
                System.out.println("Toppings how many?");
                byte n=in.nextByte();
                byte toppings[]=new byte[n];
                for(byte i=0;i<n;i++)
                {
                    System.out.print("Input choice for topping 1.Cheese,2.Mushroom,3.Tomato,4.Jalapeno,5.Spinach?");
                    toppings[i]=in.nextByte();
                    System.out.println();
                }
                PizzaPasta p= new PizzaPasta(choice,size, crust, flavor,toppings);
                p.pizzaPrice();
                queue.add(p);
            break;
            case 2:
                System.out.println(" For pasta lovers available:\n 2 flavors-- 1. White sauce or 2. Red Sauce (10 USD); \n 2 types (no charge) -- 1. Penne or 2. Ditalini");
                System.out.println(" Pasta sauce 1. White sauce or 2. Red Sauce?");
                byte sauce=in.nextByte();
                System.out.println(" Type 1. Penne or 2. Ditalini?");
                byte type=in.nextByte();                
                p= new PizzaPasta(choice,sauce,type);
                p.pastaPrice();
                queue.add(p);
            break;
            default:
            System.out.println("Sorry! not in menu this time. Please try something else.");
        }
    }
    private void kitchen()
    {
   
        for (int i=0; i<queue. size();i++)
        {
          
            if(queue.get(i).cost<0 || (queue.get(i).choice!=1 && queue.get(i).choice!=2))
            System.out.println("Apologies, Order:"+(i+1)+" cannot be prep.");
            else
            {
            System.out.println("Enjoy, Order:"+(i+1)+" prepared. Please make payment.");
            if (queue.get(i).choice==1)
            queue.get(i).pizzaDisplay();
            else if (queue.get(i).choice==2)
            queue.get(i).pastaDisplay();
            }
        }
    
    }
}
class PizzaPasta
{
    byte choice; 
    byte size,crust,flavor,toppings[]; 
    byte sauce,type;
    int cost=-1;
    public PizzaPasta(byte choice,byte size,byte crust,byte flavor,byte toppings[]) 
    {
        this.choice=choice;this.size=size;this.crust=crust;this.flavor=flavor;this.toppings=toppings;
    }
    public PizzaPasta(byte choice,byte sauce,byte type) 
    {
        this.choice=choice;this.sauce=sauce;this.type=type;
    }
    public void pizzaPrice()
    {

        cost=(flavor==1)?20:((flavor==2)?25:(flavor==3)?15:-1);

        cost=cost*size; 

        cost=cost+1*toppings.length;

        cost=(size>=1&&size<=3)?cost:-1;
    }
    public void pastaPrice()
    {
       
        cost=(sauce==1)?10:((sauce==2)?20:-1);
        
        cost=(type==1||type==2)?cost:-1;
    }
    public void pizzaDisplay()
    {
        System.out.print("Your Pizza choices: size:"+size+" crust:"+crust+" flavor:"+flavor+" toppings:");
        for (byte t:toppings)
            System.out.print(t+" ");
        System.out.println(" price="+cost+" USD");
    }    
    public void pastaDisplay()
    {
       
        System.out.println("Your Pasta choices: sauce="+sauce+" type="+type+" price="+cost+" USD");
    }
}