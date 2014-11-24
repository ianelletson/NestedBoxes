package edu.gustavus.ielletso;

import java.util.*;

/**
 * Created by ielletso on 11/22/14.
 */
public class DAG {
    private List<Node> nodes;
    private Map<Node, Integer> distance;

    public DAG() {
        this.nodes = new ArrayList<Node>();
        this.distance = new HashMap<Node, Integer>();
    }

    public void addNode(int x, int y, int z) {
        Node newNode = new Node(x, y, z);
        for (Node node : this.nodes) {
            node.addEdge(newNode);
            newNode.addEdge(node);
        }
        this.nodes.add(newNode);
        this.distance.put(newNode, -1);
    }

    public int longestPath() {
        for (Node node : this.nodes)
            if (this.distance.get(node) == -1)
                dfs(node);

        int length = 0;
        for (Integer i : this.distance.values()) {
            if (i > length)
                length = i;
        }
        return length + 1; // this '+1' accounts for not counting a node in its own list of adjacent nodes
    }

    private void dfs(Node v) {
        this.distance.put(v, 0);

        for (Node w : v.getAdjacentToNodes()) {
            if (this.distance.get(w) == -1) this.dfs(w);
            int max = Math.max(this.distance.get(v), (1 + this.distance.get(w)));
            this.distance.put(v, max);
        }
    }

    @Override public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node node : nodes) {
            sb.append(node);
            for (Node in_node : node.getAdjacentToNodes()) sb.append(in_node);
            sb.append("\n");
        }
        return sb.toString();
    }

    private class Node {
        private int x, y, z;
        private List<Node> adjacentToNodes;

        private List<Node> getAdjacentToNodes() {
            return adjacentToNodes;
        }

        private Node(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
            adjacentToNodes = new LinkedList<Node>();
        }

        private void addEdge(Node w) {
            if (this.fitsInto(w))
                adjacentToNodes.add(w);
        }

        private boolean fitsInto(Node w) {
            if ((this.x < w.x) && (this.y < w.y) && (this.z < w.z)) return true;
            if ((this.x < w.y) && (this.y < w.z) && (this.z < w.x)) return true;
            return (this.x < w.z) && (this.y < w.x) && (this.z < w.y);
        }

        @Override public String toString() {
            return String.format("(%d %d %d) ", this.x, this.y, this.z);
        }
    }
}
