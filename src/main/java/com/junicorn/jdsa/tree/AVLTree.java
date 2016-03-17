package com.junicorn.jdsa.tree;

import java.util.List;

/**
 * Created by 哓哓 on 2016/3/16 0016.
 */
public class AVLTree<T  extends Comparable<T>> {

    private T data;
    private AVLTree<T> parent;
    private AVLTree<T> leftChild;
    private AVLTree<T> rightChild;
    private int height;

    public AVLTree() {
        this(null);
    }

    public AVLTree(T t) {
        data = t;
        parent = null;
        leftChild = null;
        rightChild = null;
        height = (t == null ? -1 : 0);
    }

    /**
     * 取得根结点
     * @return
     */
    private AVLTree<T> getRoot() {
        if (data == null) {
            return null;
        }
        AVLTree<T> root = this;
        while (root.parent != null) {
            root = root.parent;
        }
        return root;
    }

    // =======getters=============
    public AVLTree<T> getLeftChild() {
        return leftChild;
    }

    public AVLTree<T> getRightChild() {
        return rightChild;
    }

    public AVLTree<T> getParent() {
        return parent;
    }

    public int getHeight() {
        return updateHeight();
    }

    public T getData() {
        return data;
    }

    //==========================

    private boolean isEmpty() {
        return data == null;
    }

    public int getLeftHeight() {
        return leftChild == null ? -1 : leftChild.height;
    }

    public int getRightHeight() {
        return rightChild == null ? -1 : rightChild.height;
    }

    private int updateHeight() {
        if (data == null) {
            height = -1;
            return  height;
        }
        int right = getRightHeight();
        int left = getLeftHeight();

        height = left > right ? (1+left) : (1+right);

        return height;
    }

    private AVLTree<T> rootInsert(T t) {
        int cp = t.compareTo(this.data);

        // 比较结果为0，更新元素
        if (cp == 0) {
            data = t;
        }

        // 比当前结点小
        if (cp < 0) {
            if (leftChild == null) {
                leftChild = new AVLTree<T>(t);
                leftChild.parent = this;
            } else {
                leftChild.rootInsert(t);
            }
        } else {
            if (rightChild == null) {
                rightChild = new AVLTree<T>();
                rightChild.parent = this;
            } else {
                rightChild.rootInsert(t);
            }
        }

        /**
         * 处理插入引起的不平衡
         */
        return process();
    }

    private AVLTree<T> process() {

        int lh = getLeftHeight();
        int rh = getRightHeight();

        AVLTree<T> root = this;

        if (lh - rh == 2) {
            if (leftChild.getLeftHeight() >= leftChild.getRightHeight()) {
                // LL型 右旋
                root = rotateRight(this);
            } else {
                // LR型 左旋+右旋
                rotateLeft(leftChild);
                root = rotateRight(this);
            }
        } else if (rh - lh == 2) {
            if (rightChild.getLeftHeight() >= rightChild.getRightHeight()) {
                // RR型 左旋
                rotateLeft(this);
            } else {
               // RL型 右旋+左旋
                rotateRight(rightChild);
                root = rotateLeft(this);
            }
        }
        // 更新当前根结点的高度
        root.updateHeight();

        return root;
    }

    public AVLTree<T> insert(T t) {
        if (t == null)
            return this;
        // 特殊情形：当前是空结点
        if (data == null) {
            data = t;
            updateHeight();
            return this;
        }
        AVLTree<T> root = getRoot(); // 根结点

        return root.rootInsert(t);
    }

    /**
     * LL型 - 右旋
     * @param tree
     * @return
     */
    private AVLTree<T> rotateRight(AVLTree<T> tree) {
        if (tree == null || tree.leftChild == null) {
            return tree;
        }
        AVLTree<T> root = tree.leftChild;
        tree.leftChild = root.rightChild;
        if (tree.leftChild != null) {
            tree.leftChild.parent = tree;
        }
        root.rightChild = tree;
        root.parent = tree.parent;
        tree.parent = root;
        if (root.parent != null) {
            if (root.parent.leftChild == tree) {
                root.parent.leftChild = root;
            } else {
                root.parent.rightChild = root;
            }
        }
        tree.updateHeight();
        root.updateHeight();

        return root;
    }

    /**
     * RR型 - 左旋
     * @param tree
     */
    private AVLTree<T> rotateLeft(AVLTree<T> tree) {
        if (tree == null || tree.rightChild == null) {
            return tree;
        }
        AVLTree<T> root = tree.rightChild;
        tree.rightChild = root.leftChild;
        if (tree.rightChild != null) {
            tree.rightChild.parent = tree;
        }
        root.leftChild = tree;
        root.parent = tree.parent;
        tree.parent = root;
        if (root.parent != null) {
            if (root.parent.leftChild == tree) {
                root.parent.leftChild = root;
            } else {
                root.parent.rightChild = root;
            }
        }
        tree.updateHeight();
        root.updateHeight();

        return root;
    }

    public static  void inOrderTraverse(AVLTree  t) {

        if (t != null) {
            inOrderTraverse(t.leftChild);
            System.out.println(t.data + " ");
            inOrderTraverse(t.rightChild);
        }
    }
}
