package com.junicorn.jdsa.tree;

/**
 * Created by Lemonjing on 2016/3/17 0017.
 * Github: Lemonjing
 * email: xmusaber@163.com
 */
public class BTreeTest {
    public static void main(String[] args) {
        int[] a = {5, 8, 10, 4};
        BTree t = new BTree();

        for (int i = 0; i < a.length; i++) {
            t.buildBTree(t.root, a[i]);
        }

        t.preOrder(t.root);
        t.inOrder(t.root);
        t.postOrder(t.root);
        t.levelOrder(t.root);
    }
}
