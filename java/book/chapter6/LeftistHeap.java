package chapter6;

import java.nio.BufferUnderflowException;

/**
 * Created by outrun on 5/18/16.
 */
public class LeftistHeap<AnyType extends Comparable<? super AnyType>> {

    public LeftistHeap() {
        root = null;
    }

    public void merge (LeftistHeap<AnyType> rhs) {
        if (this == rhs) {
            return;
        }

        root = merge(root, rhs.root);
        rhs.root = null;
    }

    private LeftistNode<AnyType> merge(LeftistNode<AnyType> h1, LeftistNode<AnyType> h2) {
        if (h1 == null) {
            return h2;
        }
        if (h2 == null) {
            return h1;
        }
        if (h1.element.compareTo(h2.element) < 0) {
            return merge1(h1, h2);
        } else {
            return merge1(h2, h1);
        }
    }

    private LeftistNode<AnyType> merge1 (LeftistNode<AnyType> h1, LeftistNode<AnyType> h2) {
        if (h1.left == null) {
            h1.left = h2;
        } else {
            h1.right = merge(h1.right, h2);
            if (h1.left.npl < h1.right.npl) {
                swapChildren(h1);
            }
            h1.npl = h1.right.npl + 1;
        }
        return h1;
    }

    private static <AnyType> void swapChildren(LeftistNode<AnyType> t) {
        LeftistNode<AnyType> tmp = t.left;
        t.left = t.right;
        t.right = tmp;
    }

    public void insert(AnyType x) {
        root = merge(new LeftistNode<AnyType>(x) , root);
    }

    public AnyType findMin() {
        if (isEmpty()) {
            throw new BufferUnderflowException();
        }
        return root.element;
    }

    public AnyType deleteMin() {
        if (isEmpty()) {
            throw new BufferUnderflowException();
        }

        AnyType minItem = root.element;
        root = merge(root.left, root.right);

        return minItem;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void makeEmpty() {
        root = null;
    }

    private static class LeftistNode<AnyType> {
        LeftistNode(AnyType theElement) {
            this(theElement, null, null);
        }

        LeftistNode (AnyType theElement, LeftistNode<AnyType> lt, LeftistNode<AnyType> rt) {
            element = theElement;
            left = lt;
            right = rt;
            npl = 0;
        }

        AnyType element;
        LeftistNode<AnyType> left;
        LeftistNode<AnyType> right;
        int npl;
    }

    private LeftistNode<AnyType> root;

}
