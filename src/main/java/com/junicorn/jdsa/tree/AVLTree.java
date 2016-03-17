package com.junicorn.jdsa.tree;

/**
 * Created by 哓哓 on 2016/3/16 0016.
 */
public class AVLTree<T  extends Comparable<T>> {

    private T data;
    private AVLTree parent;
    private AVLTree leftChild;
    private AVLTree rightChild;
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
    private AVLTree getRoot() {
        if (data == null) {
            return null;
        }
        AVLTree root = this;
        while (root.parent != null) {
            root = root.parent;
        }
        return root;
    }

    // =======getters=============
    public AVLTree getLeftChild() {
        return leftChild;
    }

    public AVLTree getRightChild() {
        return rightChild;
    }

    public AVLTree getParent() {
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

    private void rootInsert(T t) {
        int cp = t.compareTo(this.data);

        // 比较结果为0，更新元素
        if (cp == 0) {
            data = t;
        }

        // 比当前结点小
        if (cp < 0) {
            if (leftChild == null) {
                leftChild = new AVLTree(t);
                leftChild.parent = this;
            } else {
                leftChild.rootInsert(t);
            }
        } else {
            if (rightChild == null) {
                rightChild = new AVLTree();
                rightChild.parent = this;
            } else {
                rightChild.rootInsert(t);
            }
        }

        /**
         * 处理插入引起的不平衡
         */
        process();
    }

    private void process() {

        int lh = getLeftHeight();
        int rh = getRightHeight();

        AVLTree root = this;

        if (lh - rh == 2) {
            if (leftChild.getLeftHeight() >= leftChild.getRightHeight()) {
                // LL型 右旋
                rotateRight(this);
            } else {
                rotateRight(leftChild);
                root = roatetLeft(this);
            }
        } else if (rh - lh == 2) {
            if (rightChild.getLeftHeight() >= rightChild.getRightHeight()) {
                root = rotateRight(this);
            } else {
                rotateLeft(rightChild);
                root = rotateRight(this);
            }
        }
        // 更新当前根结点的高度
        root.updateHeight();
    }

    public void insert(T t) {
        if (t == null)
            return;
        // 特殊情形：当前是空结点
        if (data == null) {
            data = t;
            updateHeight();
            return;
        }
        AVLTree root = getRoot(); // 根结点

        root.rootInsert(t);
    }

    /**
     * LL型 - 右旋
     * @param tree
     * @return
     */
    private void rotateRight(AVLTree tree) {
        if (tree == null || tree.leftChild == null) {
            return;
        }
        AVLTree root = tree.leftChild;
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

//        return root;
    }

    private void rotateLeft(AVLTree tree) {
        if (tree == null || tree.rightChild == null) {
            return;
        }
        AVLTree root = tree.rightChild;
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
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("当前根结点信息：");

        if (this.getParent() != null) {
            sb.append("parent -> " + this.getParent().getData() + ", ");
        } else {
            sb.append("parent -> null, ");
        }
        if (this.getLeftChild() != null) {
            sb.append("leftChild -> " + this.getLeftChild().getData() + ", ");
        } else {
            sb.append("leftChild -> null, ");
        }
        if (this.getRightChild() != null) {
            sb.append("rightChild -> " + this.getRightChild().getData() + ", ");
        } else {
            sb.append("rightChild -> null, ");
        }
        return sb.append("值为:" + this.getData() + " 高度为:" + this.getHeight()).toString();
    }
}
