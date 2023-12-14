import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static Map<String, Node> map = new HashMap<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nodeCnt = Integer.valueOf(br.readLine());
        for (int i = 0; i < nodeCnt; i++) {
            String id = String.valueOf((char) ('A'+i));
            map.put(id, new Node(id));
        }

        for (int i = 0; i < nodeCnt; i++) {
            String[] arr = br.readLine().split(" ");
            Node parent = map.get(arr[0]);
            Node left = map.get(arr[1]);
            Node right = map.get(arr[2]);
            parent.setLeft(left);
            parent.setRight(right);
        }

        br.close();

        preorder(map.get("A"));
        sb.append("\n");
        inorder(map.get("A"));
        sb.append("\n");
        postorder(map.get("A"));

        System.out.println(sb);
    }

    private static void postorder(Node node) {
        if (node.left != null)
            postorder(node.left);
        if(node.right != null)
            postorder(node.right);
        sb.append(node.id);
    }

    private static void inorder(Node node) {
        if (node.left != null)
            inorder(node.left);
        sb.append(node.id);
        if(node.right != null)
            inorder(node.right);
    }

    private static void preorder(Node node) {
        sb.append(node.id);
        if (node.left != null)
            preorder(node.left);
        if(node.right != null)
            preorder(node.right);
    }

    static class Node {
        String id;
        Node left;
        Node right;

        public Node(String id) {
            this.id = id;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }

}
