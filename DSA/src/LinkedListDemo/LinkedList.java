package LinkedListDemo;

public class LinkedList {

    private class Node{
        int data;
        Node next;

        Node(int data)
        {
            this.data=data;
            this.next=null;
        }
    }
    private Node head;


    private Node addAtBeginning(Node head,int data)
    {
        Node node=new Node(data);
        node.next=head;
        return node;
    }

    public void addFirst(int data)
    {
        head=addAtBeginning(head,data);
    }

    public void display()
    {
        Node temp=head;
        while (temp!=null)
        {
            System.out.print(temp.data+" -> ");
            temp=temp.next;
        }
        System.out.println("null");
    }


    private Node addAtLast(Node head,int data)
    {
        Node node=new Node(data);
        if(head==null)
        {
            return node;
        }
        Node temp=head;
        while (temp.next!=null)
        {
            temp=temp.next;
        }
        temp.next=node;
        return head;
    }
    public void addLast(int data)
    {
        head=addAtLast(head,data);
    }

    private Node addAtIndex(Node head, int idx,int data)
    {
        Node tmp=head;
        int c=0;
        while(c<idx)
        {
            tmp=tmp.next;
            c++;
        }
        System.out.println(tmp.data);

        Node node=new Node(data);
        node.next=tmp.next;
        tmp.next=node;
        return head;
    }

    public void atIdx(int idx,int data)
    {
        head=addAtIndex(head,idx,data);
    }


}
