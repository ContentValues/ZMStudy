package xdroid.mwee.com.rxjavatest.algorithm;

/**
 * Created by zhangmin on 2018/12/11.
 * <p>
 * 1 树的最大深度
 * 2 树的最小深度
 */

public class Tree_1 {

    public static void main(String[] args) {


        TreeNote treeNoteright3 = new TreeNote(null, null, 70);
        TreeNote treeNoteright2 = new TreeNote(null, treeNoteright3, 60);
        TreeNote treeNoteleft2 = new TreeNote(null, null, 40);
        TreeNote treeNoteleft1 = new TreeNote(treeNoteleft2, treeNoteright2, 50);
        TreeNote treeNoteright1 = new TreeNote(null, null, 90);
        TreeNote rootNote = new TreeNote(treeNoteleft1, treeNoteright1, 80);

//        System.out.println(getMaxHeight(rootNote));
//        System.out.println(getLowerHeight(rootNote));
        System.out.println(numberOfLeafs(rootNote));
    }


    static class TreeNote {

        public TreeNote left;
        public TreeNote right;
        public int value = 0;

        public TreeNote(TreeNote left, TreeNote right, int value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }
    }


    static int getMaxHeight(TreeNote note) {

        if (note == null) {
            return 0;
        }

        return Math.max(getMaxHeight(note.left), getMaxHeight(note.right)) + 1;


//        if (note == null) {
//            return 0;
//        }
//        /*if (note.left == null && note.right == null) {
//            return 1;
//        }*/
//        return Math.max(getMaxHeight(note.left), getMaxHeight(note.right)) + 1;
    }


    static int getLowerHeight(TreeNote note) {

        if (note == null) {
            return Integer.MAX_VALUE;
        }

        if (note.left == null && note.right == null) {
            return 1;
        }

        return Math.min(getLowerHeight(note.left), getLowerHeight(note.right)) + 1;


//        if (note == null) {
//            return Integer.MAX_VALUE;
//        }
//        if (note.left == null && note.right == null) {
//            return 1;
//        }
//        return Math.min(getLowerHeight(note.left), getLowerHeight(note.right)) + 1;
    }

    //计算树中叶结点的个数
    private static int numberOfLeafs(TreeNote note) {
        int nodes = 0;
//        else if (note.left == null && note.right == null)
//            return 1;
        if (note == null) {
            return 0;
        } else {

            nodes = numberOfLeafs(note.left) + numberOfLeafs(note.right);
        }
        return nodes;
    }
}
