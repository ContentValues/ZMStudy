package xdroid.mwee.com.rxjavatest.algorithm.sf;

/**
 * Created by zhangmin on 2019/3/26.
 */

import java.util.Stack;

/**
 * 单链表
 * <p>
 * 双链表
 */
public class SF_Link {

    public static void main(String[] args) {

        SingleLinkedList singleList = new SingleLinkedList();
//        singleList.addHead("A");
//        singleList.addHead("B");
//        singleList.addHead("C");
//        singleList.addHead("D");
        singleList.addFooter("A");
        singleList.addFooter("B");
        singleList.addFooter("C");

        singleList.display();

        singleList.deleteFooter();
        singleList.deleteFooter();
        singleList.deleteFooter();

        singleList.display();

//        System.out.println("倒数第二个节点-->" + singleList.findNode(2).data);
//
//
//        System.out.println("查找中间节点-->" + singleList.findMidNode().data);



//        System.out.println("单链表的反转--->");
//
//
//        singleList.reverserNode();
//        singleList.display();
//
//
//        singleList.addFooter("A");
//        singleList.addFooter("B");
//        singleList.addFooter("C");
//        singleList.addFooter("D");
//
//        singleList.display();
//
//        while (!singleList.isEmpty()) {
//
//            System.out.println(singleList.deleteFooter());
//        }

//        singleList.delete("C");
//        singleList.display();
//        System.out.println(singleList.find("B").data);


//        DoubleLinkList doubleLink = new DoubleLinkList();
////        doubleLink.addHeader("A");
////        doubleLink.addHeader("B");
////        doubleLink.addHeader("C");
////        doubleLink.addHeader("D");
//
//
//        doubleLink.addFooter("A1");
//        doubleLink.addFooter("B1");
//        doubleLink.addFooter("C1");
//        doubleLink.addFooter("D1");
//
//        doubleLink.display();
//
//
////        doubleLink.deleteHeader();
////        doubleLink.deleteHeader();
//
//        doubleLink.deleteFooter();
//        doubleLink.deleteFooter();
//
//        doubleLink.display();


//        QueenDoubleLinkedList queenLinkedList = new QueenDoubleLinkedList(new DoubleLinkList());
//        queenLinkedList.insert("A");
//        queenLinkedList.insert("B");
//        queenLinkedList.insert("C");
//        queenLinkedList.insert("D");
//
//        queenLinkedList.display();
//
//        queenLinkedList.delete();
//        queenLinkedList.delete();
//
//        queenLinkedList.display();


//        StackDoubleLinkedList stackLinkedList = new StackDoubleLinkedList(new DoubleLinkList());
//        stackLinkedList.insert("A");
//        stackLinkedList.insert("B");
//        stackLinkedList.insert("C");
//        stackLinkedList.insert("D");
//
//        stackLinkedList.display();
//
//        stackLinkedList.delete();
//        stackLinkedList.delete();
//
//        stackLinkedList.display();


//        TwoWayLinkedList twoWayLinkedList = new TwoWayLinkedList();
////        twoWayLinkedList.addHeader("A");
////        twoWayLinkedList.addHeader("B");
////        twoWayLinkedList.addHeader("C");
////        twoWayLinkedList.addHeader("D");
//
//
//        twoWayLinkedList.addFooter("A");
//        twoWayLinkedList.addFooter("B");
//        twoWayLinkedList.addFooter("C");
//        twoWayLinkedList.addFooter("D");
//
//        twoWayLinkedList.display();
//
////        twoWayLinkedList.deleteHeader();
////        twoWayLinkedList.deleteHeader();
//        twoWayLinkedList.delete("C");
//
////        twoWayLinkedList.deleteFooter();
////        twoWayLinkedList.deleteFooter();
//
//        twoWayLinkedList.display();

    }


    static class Node {
        Object data;
        Node next;

        public Node(Object data) {
            this.data = data;
        }
    }

    /*-----------------------------------------------------todo 单向链表-------------------------------------------*/

    static class SingleLinkedList {

        private int size;//链表节点的个数
        private Node head;//头节点

        public SingleLinkedList() {
            size = 0;
            head = null;
        }


        /**
         * 查找单链表中的倒数第k个节点
         *
         * @param k
         * @return
         */
        public Node findNode(int k) {

            Node first = head;
            Node second = head;

            for (int i = 0; i < k - 1; i++) {
                second = second.next;
            }
            while (first.next != null && second.next != null) {
                first = first.next;
                second = second.next;
            }
            return first;

        }


        /**
         * 查找中间节点
         *
         * @return
         */
        public Node findMidNode() {

            Node first = head;
            Node second = head;

            while (second.next != null && second.next.next != null) {

                first = first.next;
                second = second.next.next;
            }


            return first;
        }


        /**
         * 单链表的反转
         */
        public void reverserNode() {

            Stack<Node> stack = new Stack<>();

            while (head != null) {
                stack.push(head);
                head = head.next;
            }

            Node tempHeader = new Node(-1);
            Node tempHeaderFlag = tempHeader;
            while (!stack.isEmpty()) {
                Node temp = stack.pop();
                temp.next = null;
                tempHeaderFlag.next = temp;
                tempHeaderFlag = temp;
            }
            head = tempHeader.next;

        }

        /**
         * 添加头
         *
         * @param obj
         * @return
         */
        public Object addHead(Object obj) {

            Node newHead = new Node(obj);
            newHead.next = head;
            head = newHead;
            size++;
            return newHead.data;

        }

        /**
         * 删除头
         *
         * @return
         */
        public Object deleteHead() {
            if (isEmpty()) {
                return null;
            }
            Node temp = head;
            head = head.next;
            size--;
            return temp.data;

        }


        /**
         * 单链表尾部添加数据
         *
         * @param obj
         * @return
         */
        public Object addFooter(Object obj) {

            Node newFooter = new Node(obj);
            if (isEmpty()) {
                head = newFooter;
            } else {
                Node currentNode = head;
                //找到最后一个节点
                while (currentNode.next != null) {
                    currentNode = currentNode.next;
                }
                currentNode.next = newFooter;
            }
            size++;
            return newFooter;
        }

        /**
         * 单链表尾部删除数据
         *
         * @return
         */
        public Object deleteFooter() {

            if (isEmpty()) {
                return null;
            }
            Node currentNode = head;
            /**
             * 记录最后节点的前一个节点preNode  把preNode后面节点变为null
             */
            Node preNode = head;
            while (currentNode.next != null) {
                preNode = currentNode;
                currentNode = currentNode.next;
            }
            preNode.next = null;
            size--;
            return currentNode.data;
        }


        /**
         * 发现对象所在的位置
         *
         * @param obj
         * @return
         */
        public Node find(Object obj) {

            if (isEmpty()) {
                return null;
            }
            Node currentNode = head;

            while (currentNode.data != obj) {
                if (currentNode.next == null) {
                    return null;
                }
                currentNode = currentNode.next;
            }
            return currentNode;

        }

        public void deleteNode(Object value) {

            Node currentNode = head;
            Node preNode = head;
            while (currentNode.data != value){
                if(currentNode.next == null){
                    return;
                }else {
                    preNode =currentNode;
                    currentNode =currentNode.next;
                }
            }
            preNode.next = currentNode.next;

        }

        public boolean delete(Object value) {

            if (isEmpty()) {
                return false;
            }
            Node currentNode = head;
            Node preNode = head;
            while (currentNode.data != value) {

                if (currentNode.next == null) {
                    return false;
                } else {
                    preNode = currentNode;
                    currentNode = currentNode.next;
                }
            }
//            if (currentNode.data != value) {
//                return false;
//            }
            if (currentNode == head) {
                head = head.next;
                size--;
            } else {
                preNode.next = currentNode.next;
                size--;
            }
            return true;
        }


        public boolean isEmpty() {

            return size == 0;
        }


        public void display() {

            Node currentNode = head;

            while (currentNode != null) {
                System.out.println(currentNode.data);
                currentNode = currentNode.next;

            }
        }
    }

    /*-----------------------------------------------------todo 双端链表-------------------------------------------*/

    /**
     * 简单的实现双链表
     * 头部添加
     * 头部移除
     * 尾部添加
     * 尾部移除
     */
    static class DoubleLinkList {

        Node header;

        Node footer;

        int size;


        public void addHeader(Object data) {

            Node newNode = new Node(data);
            if (isEmpty()) {
                header = newNode;
                footer = newNode;
            } else {
                newNode.next = header;
                header = newNode;
            }
            size++;

        }

        public void addFooter(Object data) {

            Node newNode = new Node(data);
            if (isEmpty()) {
                header = newNode;
                footer = newNode;
            } else {
                footer.next = newNode;
                footer = newNode;
            }
            size++;
        }

        public boolean deleteHeader() {
            if (isEmpty()) {
                return false;
            }
            if (header.next == null) {//只有一个节点
                header = null;
                footer = null;
            } else {
                header = header.next;
            }

            size--;
            return true;
        }

        public boolean deleteFooter() {

            if (isEmpty()) {
                return false;
            }

            //找到footer前一个节点preNode 然后preNode.next = null
            Node currentNode = header;
            Node preNode = header;
            while (currentNode.next != null) {//最后跳出循环条件currentNode肯定是最后一个节点
                preNode = currentNode; //这个是到达最后一个节点之前的一个节点
                currentNode = currentNode.next;
            }
            preNode.next = null;
            size--;
            return true;
        }

        public boolean isEmpty() {

            return size == 0;
        }

        public int getSize() {

            return size;
        }

        public void display() {
            Node currentNode = header;
            while (currentNode != null) {
                System.out.println(currentNode.data);
                currentNode = currentNode.next;
            }
        }
    }


    /**
     * 用双端链表实现队列 ---->先进先出
     * 尾部添加
     * 头部移除
     */
    static class QueenDoubleLinkedList {

        DoubleLinkList dp;

        public QueenDoubleLinkedList(DoubleLinkList dp) {
            this.dp = dp;
        }

        /**
         * 头部插入
         *
         * @param data
         */
        public void insert(Object data) {

            dp.addFooter(data);
        }

        public void delete() {

            dp.deleteHeader();
        }

        public boolean isEmpty() {

            return dp.isEmpty();
        }

        public int getSize() {

            return dp.getSize();
        }

        public void display() {

            dp.display();
        }
    }


    /**
     * 双向队列实现栈  ---->后进先出
     * 头部添加
     * 头部移除
     */
    static class StackDoubleLinkedList {

        DoubleLinkList dp;

        public StackDoubleLinkedList(DoubleLinkList dp) {
            this.dp = dp;
        }

        /**
         * 头部插入
         *
         * @param data
         */
        public void insert(Object data) {

            dp.addHeader(data);
        }

        public void delete() {

            dp.deleteHeader();
        }

        public boolean isEmpty() {

            return dp.isEmpty();
        }

        public int getSize() {

            return dp.getSize();
        }

        public void display() {

            dp.display();
        }
    }


    /*-----------------------------------------------------todo 双向链表-------------------------------------------*/

    static class TwoWayLinkedList {

        Note header;
        Note footer;
        int size;


        static class Note {

            Object obj;
            Note pre;
            Note next;

            public Note(Object obj) {
                this.obj = obj;
                pre = null;
                next = null;

            }
        }

        public int getSize() {

            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }


        /**
         * 头部加入
         *
         * @param value
         */
        public void addHeader(Object value) {

            Note newHeader = new Note(value);

            if (isEmpty()) {
                header = newHeader;
                footer = newHeader;
            } else {
                header.pre = newHeader;
                newHeader.next = header;
                header = newHeader;
            }
            size++;
        }


        /**
         * 尾部插入数据
         *
         * @param value
         */
        public void addFooter(Object value) {

            Note newFooter = new Note(value);

            if (isEmpty()) {
                header = newFooter;
                footer = newFooter;
            } else {
                footer.next = newFooter;
                newFooter.pre = footer;
                footer = newFooter;
            }
            size++;
        }

        /**
         * 头部删除
         *
         * @return
         */
        public Note deleteHeader() {

            if (isEmpty()) {
                return null;
            }
            Note temp = header;
            header = header.next;
            header.pre = null;
            size--;
            return temp;
        }

        public Note deleteFooter() {

            if (isEmpty()) {
                return null;
            }
            Note temp = footer;
            footer = footer.pre;
            footer.next = null;
            size--;
            return temp;
        }


        /**
         * 删除指定对象的
         *
         * @param value
         * @return
         */
        public Note delete(Object value) {

            if (isEmpty()) {
                return null;
            }
            Note temp = header;
            while (temp.obj != value) {
                if (temp.next == null) {//最后一个节点了
                    return null;
                }
                temp = temp.next;
            }

            //Note preNote = temp.pre;

            temp.pre.next = temp.next;

            temp.next.pre = temp.pre;

            return temp;

        }


        public void display() {

            Note tempHeader = header;
            while (tempHeader != null) {
                System.out.println(tempHeader.obj);
                tempHeader = tempHeader.next;
            }

        }
    }

}
