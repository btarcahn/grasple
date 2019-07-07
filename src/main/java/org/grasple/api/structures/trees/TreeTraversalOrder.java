package org.grasple.api.structures.trees;

/**
 * There are 3 main tree traversal orders:
 * <p>1. Inorder traversal: left > root > right </p>
 * <p>2. Preorder traversal: root > right > left</p>
 * <P>3. Postorder traversal: left > right >root</P>
 */
public enum TreeTraversalOrder {
    INORDER, PREORDER, POSTORDER;
}
