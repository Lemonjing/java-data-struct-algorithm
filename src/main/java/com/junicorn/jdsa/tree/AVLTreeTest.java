package com.junicorn.jdsa.tree;

/**
 * Created by 哓哓 on 2016/3/16 0016.
 */
public class AVLTreeTest {
    public static void main(String[] args) {
        Integer a = 10;
        AVLTree t = new AVLTree();
        t.insert(a);
        System.out.println(t);
        System.out.println("==========");
        t.insert(8);
        System.out.println(t);
        System.out.println("==========");
        t.insert(5);
        System.out.println(t);
    }
}
