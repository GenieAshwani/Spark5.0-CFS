public class BillCalcultor {

    public static int calculateTotal(Service[] services,boolean[] selected)
    {
        int total=0;
        for(int i=0;i< services.length;i++)
        {
            if (selected[i])
            {
                total+=services[i].getPrice();
            }
        }
        return total;
    }
}
