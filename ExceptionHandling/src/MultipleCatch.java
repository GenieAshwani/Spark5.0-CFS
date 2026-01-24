public class MultipleCatch {
    static void main(String[] args) {

       int res= data();
        System.out.println(res);
    }

    public static int data() {
        int arr[] = {1, 0, 2, 3, 34, 45};
        try {
            int res=arr[0]/arr[1];
            //int res = arr[20];
            return res;
        } catch (ArithmeticException e) {
            System.out.println("ArithmeticException");
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("ArrayIndexOutOfBoundsException");
            ex.printStackTrace();
        } catch (Exception ee) {
            System.out.println("Exception");
            ee.printStackTrace();
        }

        return 0;
    }
}

