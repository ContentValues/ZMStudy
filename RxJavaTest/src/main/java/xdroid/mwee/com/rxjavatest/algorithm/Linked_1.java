package xdroid.mwee.com.rxjavatest.algorithm;

import java.util.Stack;

/**
 * Created by zhangmin on 2018/12/25.
 */

public class Linked_1 {


    public static void main(String[] args) {

        //链表1：1->2->3->4-5

        LinkNote note1 = new LinkNote(1);
        LinkNote note2 = new LinkNote(2);
       /* LinkNote note3 = new LinkNote(3);
        LinkNote note4 = new LinkNote(4);
        LinkNote note5 = new LinkNote(5);*/
        //LinkNote note6 = new LinkNote(6);

        note1.next = note2;
        /*note2.next = note3;
        note3.next = note4;
        note4.next = note5;*/
        //note5.next = note6;


        //HashSet<String> hashSet = new HashSet<>();


        //note5.next = note6;


        LinkNote note2_1 = new LinkNote(1);
        LinkNote note2_2 = new LinkNote(2);
        LinkNote note2_3 = new LinkNote(3);
       /* LinkedNote note2_4 = new LinkedNote(4);
        LinkedNote note2_5 = new LinkedNote(5);*/

        note2_1.next = note2_2;
        note2_2.next = note2_3;
        /*note2_3.next = note2_4;
        note2_4.next = note2_5;*/

        //System.out.println("求单链表中结点的个数-->" + getLength(note1));


        //System.out.println("求单链表中间结点-->" + findMidNode(note1).val);


        //System.out.println("查找单链表中的倒数第k个结点-->" + findLastNode(note1, 2).val);


        //System.out.println("合并两个有序的单链表-->" + mergeLinkList(note1, note2_1));

        System.out.println("反转的单链表-->" + reverseList(note1));

        //reversePrinter(note1);
//
//        sum();


        // System.out.println(recurSive(50));


    }

    /**
     * 求单链表中结点的个数
     */

    public static int getLength(LinkNote head) {

        //链表1：1->2->3->4-5

        int length = 0;

        while (head != null) {

            length++;
            head = head.next;

        }
        return length;

//        int length = 0;
//        while (head != null) {
//            length++;
//            head = head.next;
//        }
//        return length;

//        if (head == null) {
//            return 0;
//        }
//        LinkNote currentNote = head;
//        int length = 0;
//        while (currentNote != null) {
//            length++;
//            currentNote = head.next;
//            head = currentNote;
//        }
//        return length;
    }

    /**
     * 查找单链表中的倒数第k个结点
     * 时间复杂度为O（n）
     * <p>
     * 这里需要声明两个指针：即两个结点型的变量first和second，首先让first和second都指向第一个结点，然后让second结点往后挪k-1个位置，此时first和second就间隔了k-1个位置，然后整体向后移动这两个节点，直到second节点走到最后一个结点的时候，此时first节点所指向的位置就是倒数第k个节点的位置。时间复杂度为O（n）。
     * 考虑k=0和k大于链表长度的情况
     *
     * @param head 链表头结点
     * @param k    倒数第k
     * @return 倒数第k个节点
     */
    public static LinkNote findLastNode(LinkNote head, int k) {



        if (head == null || k <= 0) {
            throw new RuntimeException("输入参数格式不对...");
        }

        LinkNote first = head;
        LinkNote second = head;

        for (int i = 0; i < k - 1; i++) {
            second = second.next;
            if (second == null) {
                throw new RuntimeException("k越界");
            }
        }

        while (second.next != null) {
            first = first.next;
            second = second.next;
        }

        System.out.println("first--->" + first.val);
        System.out.println("second-->" + second.val);
        return first;


        //链表1：1->2->3->4-5
//        LinkNote first = head;
//        LinkNote second = head;
//        for (int i = 0; i < k - 1; i++) {
//            second = second.next;
//            if (second == null) {
//                throw new RuntimeException("k越界");
//            }
//        }
//        while (second.next != null) {
//            first = first.next;
//            second = second.next;
//        }
//        return first;


       /* LinkNote first = head;
        LinkNote second = head;

        for (int i = 0; i < k - 1; i++) {
            second = second.next;
            if (second == null) {
                throw new RuntimeException("k越界");
            }
        }

        while (second.next != null) {
            first = first.next;
            second = second.next;
        }
        return first;*/


       /* if (head == null || k <= 0) { // 输入异常
            throw new RuntimeException("输入参数格式不对...");
        }
        LinkedNote first = head; // 两个指针
        LinkedNote second = head;
        for (int i = 0; i < k - 1; i++) {
            second = second.next;
            if (second == null) { // 说明k的值大于链表的长度
                throw new RuntimeException("k越界");
            }
        }
        // 两个指针同时移动，second到达尾节点时，first是倒数第k个节点
        while (second.next != null) {
            first = first.next;
            second = second.next;
        }
        return first;*/
    }


    /**
     * 查找单链表中的中间结点
     * first和second，只不过这里是，两个指针同时向前走，second指针每次走两步，first指针每次走一步，直到second指针走到最后一个结点时，此时first指针所指的结点就是中间结点。
     *
     * @param head
     * @return
     */
    public static LinkNote findMidNode(LinkNote head) {


        //0个元素 只有一个元素 只有二个元素
        //链表1：1->2->3->4-5
        LinkNote first = head;
        LinkNote second = head;

        while (second.next != null && second.next.next != null) {
            first = first.next;
            second = second.next.next;
        }

        return first;


//        LinkNote first = head;
//        LinkNote second = head;
//
//        while (second.next != null && second.next.next != null) {
//            first = first.next;
//            second = second.next.next;
//        }
//        return first;
    }


    /**
     * 4. 合并两个有序的单链表，合并之后的链表依然有序【出现频率高】
     * 解题思路：挨着比较链表1和链表2。这个类似于归并排序。尤其要注意两个链表都为空、和其中一个为空的情况。
     * 只需要O(1)的空间。时间复杂度为O (max(len1,len2))
     *
     * @param head1 链表1头结点
     * @param head2 链表2头结点
     * @return 合并后的链表头结点
     */
    public static LinkNote mergeLinkList(LinkNote head1, LinkNote head2) {

        //链表1：1->2
        //链表2：1->2->3
        //合并： 1->1->2->2->3

        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }

        LinkNote head = new LinkNote(-1);
        LinkNote temp = head;

        while (head1 != null && head2 != null) {
            if (head1.val > head2.val) {
                temp.next = head2;
                head2 = head2.next;
            } else {
                temp.next = head1;
                head1 = head1.next;
            }
            temp = temp.next;
        }

        if (head1 != null) {

            temp.next = head1;
        }

        if (head2 != null) {

            temp.next = head2;
        }

        return head.next;


//        if (head1 == null) {
//            return head2;
//        }
//        if (head2 == null) {
//            return head1;
//        }
//        LinkNote head = new LinkNote(-1);
//        //LinkNote temp = head;
//        while (head1 != null && head2 != null) {
//            if (head1.val < head2.val) {
//                temp.next = head1;
//                head1 = head1.next;
//            } else {
//                temp.next = head2;
//                head2 = head2.next;
//            }
//            temp = temp.next;
//        }
//
//        if (head1 != null) {
//            temp.next = head1;
//        }
//
//        if (head2 != null) {
//            temp.next = head2;
//        }
//        return head.next;
    }


    public static LinkNote reverseList1(LinkNote head) {

        Stack<LinkNote> stack = new Stack<>();
        while (head != null){
            stack.push(head);
            head = head.next;
        }
        LinkNote temp = new LinkNote(-1);

        LinkNote tempFlag = temp;
        while (!stack.isEmpty()){
            LinkNote note =  stack.pop();
            note.next = null;
            tempFlag.next = note;
            tempFlag = tempFlag.next;
        }
        return temp.next;
    }










    /**
     * 单链表的反转
     * <p>
     * 思路：从头到尾遍历原链表，每遍历一个结点，将其摘下放在新链表的最前端
     *
     * @param head
     * @return
     */
    public static LinkNote reverseList(LinkNote head) {
//
//        Stack<LinkNote> stack = new Stack<>();
//        while (head != null) {
//
//            stack.push(head);
//            head = head.next;
//
//
//        }
//
//        LinkNote linkHead = new LinkNote(-1);
//
//        LinkNote temp = linkHead;
//
//        while (!stack.isEmpty()) {
//
//            LinkNote note = stack.pop();
//
//            note.next = null;
//
//            temp.next = note;
//
//            temp = temp.next;
//
//
//        }
//
//        return linkHead.next;



        // 1--2--3

        if (head == null || head.next == null) {
            return head;
        }
        LinkNote newHead = null; // 保存链表新表头
        LinkNote current = head; // 保存当前链表的遍历节点
        while (current != null) {
            // 保存当前节点的下一个节点
            LinkNote next = current.next;
            current.next = newHead;
            newHead = current;
            current = next;
        }
        return newHead;

    }


    /**
     * 倒序打印单链表
     * <p>
     * 用递归实现，但有个问题：当链表很长的时候，就会导致方法调用的层级很深，有可能造成栈溢出。
     * 注意链表为空的情况。时间复杂度为O（n）
     * <p>
     * 对于这种颠倒顺序的问题，我们应该就会想到栈，后进先出。
     * 显式用栈，是基于循环实现的，代码的鲁棒性要更好一些。
     * 注意链表为空的情况。时间复杂度为O（n）
     */
    public static void reversePrinter(LinkNote head) {

        //栈
//        Stack<LinkNote> stack = new Stack<>();
//        while (head != null) {
//            stack.push(head);
//            head = head.next;
//        }
//        while (!stack.isEmpty()){
//            System.out.println(stack.pop().val);
//        }


        //递归
        if (head == null) {
            return;
        }
        reversePrinter(head.next);
        System.out.println(head.val);


//        if (head == null) {
//            System.out.print(" 空啦空啦");
//            return;
//        }
//        System.out.print(head.val + " 哈哈哈 ");
//        reversePrinter(head.next);
//        System.out.print(head.val + " ");
       /* Stack<LinkNote> stack = new Stack<>();

        while (head != null) {
            stack.push(head);
            head = head.next;
        }

        while (!stack.isEmpty()) {
            System.out.println(stack.pop().val);
        }*/


    }


    static class LinkNote {
        public int val = 0;
        public LinkNote next = null;

        public LinkNote(int val) {
            this.val = val;
        }

        // 打印链表节点元素值
        public static void printList(LinkNote head) {
            while (head != null) {
                System.out.print(head.val + "->");
                head = head.next;
            }
            System.out.println("null");
        }
    }


    /**
     * 递归算法
     * <p>
     * 递归算法，其实说白了，就是程序的自身调用
     * 在做递归算法的时候，一定要把握住出口，也就是做递归算法必须要有一个明确的递归结束条件
     * <p>
     * 1 1 2 3 5 8 13 21 34 求第40个数
     * F1 = 1 F2 = 1 Fn = F(n-1)+F(n-2) n>2
     *
     * @return
     */
    private static int recurSive(int index) {
        if (index == 1 || index == 2) {
            return 1;
        } else {
            return recurSive(index - 1) + recurSive(index - 2);
        }
    }

    /**
     * 求1+2!+3!+…+20!的和
     *
     * @return
     */
    private static void sum() {

        long sum = 1;
        long func = 1;
        for (int i = 2; i <= 20; i++) {
            func = i * func;
            sum += func;
        }

        System.out.println("滴滴滴哈哈" + sum);

        /**
         *  sum  1
         *       1+2*1
         *       1+2*1+3*2*1
         *       1+2*1+3*2*1+4*3*2*1
         */

       /* long sum=1;
        long func=1;
        for(int i=2;i<=20;i++){
            func=func*i;
            sum+=func;
        }
        System.out.println("滴滴滴--->"+sum);*/
    }
}
