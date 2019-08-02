package xdroid.mwee.com.rxjavatest.algorithm.sf;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by zhangmin on 2019/3/26.
 */

public class SF_Tree {


    public static void main(String[] args) {


        BinaryTree binaryTree = new BinaryTree();

        binaryTree.insert(10);
        binaryTree.insert(8);
        binaryTree.insert(15);
        binaryTree.insert(4);
        binaryTree.insert(9);
        binaryTree.insert(11);
        binaryTree.insert(20);
        binaryTree.insert(5);


//        System.out.println("查找最小的节点--->" + binaryTree.findMin().data);
//        System.out.println("查找最大的节点--->" + binaryTree.findMax().data);
//
//
        System.out.println("--------------------前序遍历--------------------");
        binaryTree.preOrder(binaryTree.root);
        System.out.println("--------------------后序遍历--------------------");
        binaryTree.afterOrder(binaryTree.root);
        System.out.println("--------------------中序遍历--------------------");
        binaryTree.infixOrder(binaryTree.root);

        System.out.println("--------------------层次遍历--------------------");
        binaryTree.层次遍历(binaryTree.root);


        //binaryTree.delete(4);


        //二叉树的最大深度
        System.out.println("二叉树的最大深度-------" + binaryTree.maxHeight(binaryTree.root));

        System.out.println("二叉树的最小深度-------" + binaryTree.lowerHeight(binaryTree.root));


        System.out.println("二叉树的节点个数-------" + binaryTree.size(binaryTree.root));

    }


    static class BinaryTree {


        /**
         * 根节点
         */
        Node root;


        /**
         * 查找节点
         *
         * @param data
         * @return
         */
        public Node find(int data) {

            Node currentNode = root;
            while (currentNode != null) {
                if (currentNode.data < data) {
                    currentNode = currentNode.rightChild;
                } else if (currentNode.data > data) {
                    currentNode = currentNode.leftChild;
                } else {
                    return currentNode;
                }
            }
            return null;
        }

        public boolean insert(int data) {

            Node newNode = new Node(data);
            if (root == null) {
                root = newNode;
                return true;
            }
            Node currentNode = root;
            Node parentNode = null;
            while (currentNode != null) {

                parentNode = currentNode;
                if (currentNode.data < data) {
                    if (currentNode.rightChild == null) {

                        //System.out.println("插入位置的父节点" + parentNode.data + "----插入的节点--->" + newNode.data);
                        parentNode.rightChild = newNode;
                        return true;
                    }
                    currentNode = currentNode.rightChild;

                } else if (currentNode.data > data) {

                    if (currentNode.leftChild == null) {
                        //System.out.println("插入位置的父节点" + parentNode.data + "----插入的节点--->" + newNode.data);
                        parentNode.leftChild = newNode;
                        return true;
                    }
                    currentNode = currentNode.leftChild;
                } else {
                    //System.out.println("当前已存在相同的节点无法插入");
                    return false;
                }
            }
            return false;
        }


        /**
         * 中序遍历:左子树——》根节点——》右子树
         *
         * @param current
         */
        public void infixOrder(Node current) {
            if (current != null) {
                infixOrder(current.leftChild);
                System.out.println(current.data);
                infixOrder(current.rightChild);
            }
        }

        /**
         * 前序遍历:根节点——》左子树——》右子树
         *
         * @param current
         */
        public void preOrder(Node current) {

            if (current == null) {
                return;
            }
            System.out.println(current.data);
            preOrder(current.leftChild);
            preOrder(current.rightChild);

        }

        /**
         * 后序遍历:左子树——》右子树——》根节点
         *
         * @param current
         */
        public void afterOrder(Node current) {

            if (current != null) {

                afterOrder(current.leftChild);
                afterOrder(current.rightChild);
                System.out.println(current.data);
            }

        }


        public void 层次遍历(Node current) {

            LinkedBlockingQueue<Node> linkedBlockingQueue = new LinkedBlockingQueue<>();
            try {
                linkedBlockingQueue.offer(current);
                while (!linkedBlockingQueue.isEmpty()) {
                    Node node = linkedBlockingQueue.poll();
                    System.out.println(node.data);
                    if (node.leftChild != null) {
                        linkedBlockingQueue.offer(node.leftChild);
                    }
                    if (node.rightChild != null) {
                        linkedBlockingQueue.offer(node.rightChild);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * 查找最大节点
         *
         * @return
         */
        public Node findMax() {

            Node currentNode = root;

            while (currentNode != null) {
                currentNode = currentNode.rightChild;
                if (currentNode.rightChild == null) {
                    return currentNode;
                }
            }
            return null;
        }

        /**
         * 查找最小节点
         *
         * @return
         */
        public Node findMin() {

            Node currentNode = root;
            while (currentNode != null) {
                currentNode = currentNode.leftChild;
                if (currentNode.leftChild == null) {
                    return currentNode;
                }
            }
            return null;
        }

        public boolean delete(int key) {

            Node currentNode = root;
            Node parentNode = root;

            boolean isLeftNode = false;


            //找到key对应的节点

            while (currentNode != null && currentNode.data != key) {

                parentNode = currentNode;
                if (currentNode.data > key) {
                    isLeftNode = true;
                    currentNode = currentNode.leftChild;
                } else {
                    isLeftNode = false;
                    currentNode = currentNode.rightChild;
                }
            }

            if (currentNode == null) {
                return false;
            }

            if (currentNode == root) {
                root = null;
                return true;
            }
            /**
             * 1 删除没有子节点的节点
             */
            if (currentNode.leftChild == null && currentNode.rightChild == null) {
                if (isLeftNode) {
                    parentNode.leftChild = null;
                } else {
                    parentNode.rightChild = null;
                }
                return true;
            }


            /**
             * 2 删除有一个子节点的节点
             */

            if (currentNode.leftChild == null && currentNode.rightChild != null) {

                if (isLeftNode) {
                    parentNode.leftChild = currentNode.rightChild;

                } else {
                    parentNode.rightChild = currentNode.rightChild;
                }
                return true;
            }

            if (currentNode.leftChild != null && currentNode.rightChild == null) {

                if (isLeftNode) {
                    parentNode.leftChild = currentNode.leftChild;

                } else {
                    parentNode.rightChild = currentNode.leftChild;
                }
                return true;

            }


            /**
             * 3 删除有两个子节点的节点
             */


            return false;
        }


        /**
         * 二叉树节点的个数
         *
         * @return
         */
        public int size(Node node) {
            if (node == null) {
                return 0;
            }
            int a = size(node.leftChild) + 1;
            int b = size(node.rightChild);
            return a + b;
        }


        /**
         * 最小深度
         *
         * @return
         */
        public int lowerHeight(Node node) {

            if (node == null) {
                return Integer.MAX_VALUE;
            }

            if (node.leftChild == null && node.rightChild == null) {
                return 1;
            }
            return Math.min(lowerHeight(node.leftChild), lowerHeight(node.rightChild)) + 1;
        }

        /**
         * 最大深度
         *
         * @returnl
         */
        public int maxHeight(Node node) {
            if (node == null) {
                System.out.println("找到了空节点");
                return 0;
            }
            return Math.max(maxHeight(node.leftChild), maxHeight(node.rightChild)) + 1;
        }


    }


    static class Node {
        private int data;    //节点数据
        private Node leftChild; //左子节点的引用
        private Node rightChild; //右子节点的引用

        public Node(int data) {
            this.data = data;
            this.leftChild = null;
            this.rightChild = null;

        }
    }


}
