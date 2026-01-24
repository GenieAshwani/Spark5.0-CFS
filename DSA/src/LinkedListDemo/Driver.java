package LinkedListDemo;

public class Driver {
    static void main(String[] args) {
        LinkedList linkedList=new LinkedList();

        LinkedList list=new LinkedList();
      /*  list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);
        list.addFirst(40);*/

        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.addLast(40);

        list.display();

        list.atIdx(2,50);

        list.display();
    }
}
