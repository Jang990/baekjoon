import java.util.Arrays;

class Solution {
    private static int[][] answer;
    private static int idx;

    public static int[][] solution(int[][] nodeinfo) {
        answer = new int[2][nodeinfo.length];
        Node[] node = new Node[nodeinfo.length];
        for (int i = 0; i < nodeinfo.length; i++) {
            node[i] = new Node(i+1, nodeinfo[i][0], nodeinfo[i][1]);
        }

        Arrays.sort(node, (n1 ,n2) -> {
            if (n1.y == n2.y)
                return n1.x - n2.x;
            else
                return n2.y - n1.y;
        });

        Node root = node[0];
        for (int i = 1; i < node.length; i++) {
            insertNode(root, node[i]);
        }

        idx = 0;
        search1(root);
        idx = 0;
        search2(root);
        return answer;
    }

    private static void search2(Node target) {
        if (target == null) {
            return;
        }

        search2(target.left);
        search2(target.right);
        answer[1][idx++] = target.value;
    }

    private static void search1(Node target) {
        if (target == null) {
            return;
        }

        answer[0][idx++] = target.value;
        search1(target.left);
        search1(target.right);
    }

    private static void insertNode(Node parent, Node target) {
        if (parent.x > target.x) {
            if (parent.left == null)
                parent.left = target;
            else
                insertNode(parent.left, target);
        }
        else {
            if (parent.right == null)
                parent.right = target;
            else
                insertNode(parent.right, target);
        }
    }

    static class Node {
        int value;
        int x;
        int y;
        Node left;
        Node right;

        public Node(int value, int x, int y) {
            this.value = value;
            this.x = x;
            this.y = y;
        }
    }
}